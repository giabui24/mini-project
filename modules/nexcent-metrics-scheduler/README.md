# Nexcent Metrics Scheduler

This module registers the `Nexcent Metrics Updater` Job Scheduler task
executor. Each trigger can select the metric entries to update and configure a
random growth range.

## Build and deploy

From the workspace root:

```powershell
.\gradlew.bat :modules:nexcent-metrics-scheduler:deploy
```

## Create a Job Scheduler trigger

1. Open **Global Menu > Control Panel > Configuration > Job Scheduler**.
2. On **Job Scheduler Triggers**, click **Add**.
3. Select **Nexcent Metrics Updater** and enter a job name.
4. Save the new task, open it, and select the **Details** tab.
5. In the large editor directly below the **Name** field, enter the task
   settings using Java properties syntax. The editor uses XML highlighting in
   this Liferay version, but the executor settings are still `key=value`
   properties:

```properties
metricExternalReferenceCodes=NXC_METRIC_MEMBERS,NXC_METRIC_PAYMENTS
minimumGrowthPercent=-10
maximumGrowthPercent=10
```

6. Save the details.
7. Open its **Job Scheduler Trigger** tab, activate the trigger, and enter a
   cron expression. For the demo, run it every five minutes:

```text
0 0/5 * * * ?
```

8. Disable **Overlap Allowed** and use **Single Node** cluster mode.
9. Save, or click **Run Now** to test immediately.

The execution log reports each updated metric, its previous and new values,
and the generated growth percentage.

## Settings

| Setting | Default | Description |
| --- | --- | --- |
| `metricExternalReferenceCodes` | All four sample metrics | Comma-separated Object Entry external reference codes |
| `minimumGrowthPercent` | `-10` | Inclusive lower growth bound |
| `maximumGrowthPercent` | `10` | Inclusive upper growth bound |

Both percentage bounds must be between `-100` and `100`, and the minimum
cannot exceed the maximum. Negative values simulate a decrease.

Supported sample entry external reference codes:

- `NXC_METRIC_MEMBERS`
- `NXC_METRIC_CLUBS`
- `NXC_METRIC_EVENT_BOOKINGS`
- `NXC_METRIC_PAYMENTS`

The executor looks up the Object Definition by `NXC_METRIC_SNAPSHOT`. A
configured entry must exist, and its `metricValue` must be a Long Integer. On
every update it copies the current value to `previousMetricValue`, writes the
new value to `metricValue`, updates `snapshotDate`, and clamps the result to
zero.
