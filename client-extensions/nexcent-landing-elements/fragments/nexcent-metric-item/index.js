const metricValue = fragmentElement.querySelector('[data-nxc-metric-value]');
const numberFormatter = new Intl.NumberFormat('en-US', {
    maximumFractionDigits: 0,
});
const modalTrigger = fragmentElement.querySelector('[data-nxc-modal]');
const isEditMode =
    (typeof layoutMode !== 'undefined') && (layoutMode === 'edit');

fragmentElement.classList.toggle(
    'nxc-metric-item-fragment--edit',
    isEditMode
);

if (modalTrigger) {
    modalTrigger.toggleAttribute('data-nxc-modal-editing', isEditMode);
}

const formatMetricValue = () => {
    if (!metricValue) {
        return;
    }

    const value = metricValue.textContent.trim();
    const normalizedValue = value.replaceAll(',', '');

    if (!/^-?\d+$/.test(normalizedValue)) {
        return;
    }

    const formattedValue = numberFormatter.format(BigInt(normalizedValue));

    if (value !== formattedValue) {
        metricValue.textContent = formattedValue;
    }
};

formatMetricValue();

if (metricValue) {
    const observer = new MutationObserver(formatMetricValue);

    observer.observe(metricValue, {
        characterData: true,
        childList: true,
        subtree: true,
    });
}
