
package io.quarkus.kubernetes.deployment;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class ProbeConfig {

    /**
     * The http path to use for the probe For this to work, the container port also
     * needs to be set
     *
     * Assuming the container port has been set (as per above comment), if
     * execAction or tcpSocketAction are not set, an http probe will be used
     * automatically even if no path is set (which will result in the root path
     * being used)
     */
    @ConfigItem
    Optional<String> httpActionPath;

    /**
     * The command to use for the probe.
     */
    @ConfigItem
    Optional<String> execAction;

    /**
     * The tcp socket to use for the probe (the format is host:port).
     */
    @ConfigItem
    Optional<String> tcpSocketAction;

    /**
     * The amount of time to wait in seconds before starting to probe.
     */
    @ConfigItem(defaultValue = "0")
    Integer initialDelaySeconds;

    /**
     * The period in which the action should be called.
     */
    @ConfigItem(defaultValue = "30")
    Integer periodSeconds;

    /**
     * The amount of time to wait for each action.
     */
    @ConfigItem(defaultValue = "10")
    Integer timeoutSeconds;

    /**
     * The success threshold to use.
     */
    @ConfigItem(defaultValue = "1")
    Integer successThreshold;

    /**
     * The failure threshold to use.
     */
    @ConfigItem(defaultValue = "3")
    Integer failureThreshold;

}
