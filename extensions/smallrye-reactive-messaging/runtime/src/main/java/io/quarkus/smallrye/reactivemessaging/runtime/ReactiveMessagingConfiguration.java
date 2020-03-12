package io.quarkus.smallrye.reactivemessaging.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class ReactiveMessagingConfiguration {

    /**
     * Enables or disables the <em>strict</em> mode.
     */
    @ConfigItem(defaultValue = "false")
    public boolean strict;

    /**
     * Whether or not Reactive Messaging metrics are published in case the smallrye-metrics extension is present
     * (default to false).
     */
    @ConfigItem(name = "metrics.enabled", defaultValue = "false")
    public boolean metricsEnabled;
}
