package com.nexcent.metrics.scheduler;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.Serializable;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        "dispatch.task.executor.name=Nexcent Metrics Updater",
        "dispatch.task.executor.type=nexcent-metrics-updater"
    },
    service = DispatchTaskExecutor.class
)
public class NexcentMetricsDispatchTaskExecutor
    extends BaseDispatchTaskExecutor {

    @Override
    public void doExecute(
            DispatchTrigger dispatchTrigger,
            DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
        throws Exception {

        UnicodeProperties settings =
            dispatchTrigger.getDispatchTaskSettingsUnicodeProperties();

        double minimumGrowthPercent = GetterUtil.getDouble(
            settings.getProperty("minimumGrowthPercent"), -10);
        double maximumGrowthPercent = GetterUtil.getDouble(
            settings.getProperty("maximumGrowthPercent"), 10);

        _validateGrowthRange(minimumGrowthPercent, maximumGrowthPercent);

        List<String> externalReferenceCodes = _getExternalReferenceCodes(
            settings.getProperty("metricExternalReferenceCodes"));

        long companyId = dispatchTrigger.getCompanyId();

        ObjectDefinition objectDefinition =
            _objectDefinitionLocalService.
                getObjectDefinitionByExternalReferenceCode(
                    _OBJECT_DEFINITION_EXTERNAL_REFERENCE_CODE, companyId);

        StringBuilder output = new StringBuilder();

        for (String externalReferenceCode : externalReferenceCodes) {
            _updateMetric(
                companyId, dispatchTrigger.getUserId(), objectDefinition,
                externalReferenceCode, minimumGrowthPercent,
                maximumGrowthPercent, output);
        }

        dispatchTaskExecutorOutput.setOutput(output.toString());
    }

    @Override
    public String getName() {
        return "Nexcent Metrics Updater";
    }

    private List<String> _getExternalReferenceCodes(String configuredValue) {
        if ((configuredValue == null) || configuredValue.isBlank()) {
            return _DEFAULT_EXTERNAL_REFERENCE_CODES;
        }

        List<String> externalReferenceCodes = Arrays.stream(
            configuredValue.split(","))
            .map(String::trim)
            .filter(value -> !value.isEmpty())
            .distinct()
            .collect(Collectors.toList());

        if (externalReferenceCodes.isEmpty()) {
            throw new IllegalArgumentException(
                "metricExternalReferenceCodes must contain at least one " +
                    "external reference code");
        }

        return externalReferenceCodes;
    }

    private long _getMetricValue(
        Map<String, Serializable> values, String externalReferenceCode) {

        Serializable metricValue = values.get("metricValue");

        if (metricValue == null) {
            throw new IllegalArgumentException(
                "Metric value is missing for " + externalReferenceCode);
        }

        try {
            return Long.parseLong(
                metricValue.toString().replace(",", "").trim());
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(
                "Metric value must be an integer for " +
                    externalReferenceCode + ": " + metricValue,
                numberFormatException);
        }
    }

    private void _updateMetric(
            long companyId, long userId, ObjectDefinition objectDefinition,
            String externalReferenceCode, double minimumGrowthPercent,
            double maximumGrowthPercent, StringBuilder output)
        throws Exception {

        ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry(
            externalReferenceCode, 0,
            objectDefinition.getObjectDefinitionId());

        if (objectEntry == null) {
            throw new IllegalArgumentException(
                "No metric entry found for external reference code " +
                    externalReferenceCode);
        }

        Map<String, Serializable> values = new HashMap<>(
            _objectEntryLocalService.getValues(objectEntry));

        long currentValue = _getMetricValue(values, externalReferenceCode);
        double growthPercent = ThreadLocalRandom.current().nextDouble(
            minimumGrowthPercent, Math.nextUp(maximumGrowthPercent));
        long updatedValue = Math.max(
            0,
            Math.round(currentValue * (1 + (growthPercent / 100))));

        values.put("metricValue", updatedValue);
        values.put("previousMetricValue", currentValue);
        values.put("snapshotDate", LocalDate.now());

        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setCompanyId(companyId);
        serviceContext.setUserId(userId);

        _objectEntryLocalService.updateObjectEntry(
            userId, objectEntry.getObjectEntryId(),
            objectEntry.getObjectEntryFolderId(), values, serviceContext);

        if (output.length() > 0) {
            output.append(System.lineSeparator());
        }

        output.append(externalReferenceCode);
        output.append(": ");
        output.append(currentValue);
        output.append(" -> ");
        output.append(updatedValue);
        output.append(" (");
        output.append(
            String.format(Locale.ROOT, "%+.2f%%", growthPercent));
        output.append(")");
    }

    private void _validateGrowthRange(
        double minimumGrowthPercent, double maximumGrowthPercent) {

        if (!Double.isFinite(minimumGrowthPercent) ||
            !Double.isFinite(maximumGrowthPercent) ||
            (minimumGrowthPercent < -100) ||
            (maximumGrowthPercent > 100) ||
            (maximumGrowthPercent < minimumGrowthPercent)) {

            throw new IllegalArgumentException(
                "Growth percentages must be finite and satisfy -100 <= " +
                    "minimumGrowthPercent <= maximumGrowthPercent <= 100");
        }
    }

    private static final List<String> _DEFAULT_EXTERNAL_REFERENCE_CODES =
        List.of(
            "NXC_METRIC_MEMBERS", "NXC_METRIC_CLUBS",
            "NXC_METRIC_EVENT_BOOKINGS", "NXC_METRIC_PAYMENTS");

    private static final String
        _OBJECT_DEFINITION_EXTERNAL_REFERENCE_CODE = "NXC_METRIC_SNAPSHOT";

    @Reference
    private ObjectDefinitionLocalService _objectDefinitionLocalService;

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;

}
