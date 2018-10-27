## Overview

This project provides an easy way to send metrics from your Dropwizard project 
to AppOptics.  It automatically includes the 
[metrics-appoptics](https://github.com/appoptics/metrics-appoptics) library and 
sets up the reporter based on your application YAML config.  For more information 
on how you can use [metrics-appoptics](https://github.com/appoptics/metrics-appoptics)
at runtime, please see some [example usages](https://github.com/appoptics/metrics-appoptics#fluent-helper).

## Usage

There are two steps. First, you must add the `dropwizard-metrics-appoptics` Maven dependency to your POM file. Second,
the application config YAML needs to be updated to configure the AppOptics Reporter, which will send your Metrics
data to AppOptics.

First, add the `dropwizard-metrics-appoptics` dependency in your POM:

### For Dropwizard 1.x and greater

> Unfortunately we don't support versions prior to Dropwizard 1.x

Find the latest [version here](https://search.maven.org/search?q=g:com.appoptics.metrics%20AND%20a:dropwizard-metrics-appoptics).

    <dependency>
        <groupId>com.appoptics.metrics</groupId>
        <artifactId>dropwizard-metrics-appoptics</artifactId>
        <version>1.0.0</version>
    </dependency>


Next, add a `metrics` configuration element to your YAML config file:

    metrics:
      reporters:
        - type: appoptics
          token: "<AppOptics API Token>"
          tags:
            static:
              tier: webapp
            environment:
              host: NODE_NAME
              ...
          timeout: [optional (int), number of seconds, defaults to 5]
          prefix: [optional (string), prepended to metric names]
          name: [optional (string), name of the reporter]


That's it.  Once your application starts, your metrics should soon appear in AppOptics.

*The static tags are completely defined in the yaml file.  The environment tags' names are also
defined in the yaml file, but their values are determined by the environment variables at the
time the AppOptics reporter is created.  In this case, the environment variable with the name 
`NODE_NAME` would be queried and then assigned to the tag name `host`.*

## Alternative token config

If you wish to not have your token in the configuration, you can alternatively
set it with the `APPOPTICS_TOKEN` environment variable.

## Whitelist / Blacklist

By default, all expanded metrics (percentiles, rates) are submitted for each Timer, Histogram,
and Meter.  If you wish to whitelist only certain metrics, you can do so like this:

    metrics:
      reporters:
        - type: appoptics
          token: "<AppOptics API Token>"
          tags: ...
          timeout: [optional (int), number of seconds, defaults to 5]
          prefix: [optional (string), prepended to metric names]
          name: [optional (string), name of the reporter]
          metricWhitelist:
          	- PCT_75
          	- PCT_98
          	- PCT_99
          	- RATE_MEAN
          	- RATE_1_MINUTE
          	- RATE_5_MINUTE

 Similarly, if you wish to blacklist certain expanded metrics, you would do something
 similar to the above example, but replace `metricWhitelist` with `metricBlacklist`.

 The full set of expanded metric names that you can specify are:

 * MEDIAN
 * PCT_75
 * PCT_95
 * PCT_98
 * PCT_99
 * PCT_999
 * COUNT
 * RATE_MEAN
 * RATE_1_MINUTE
 * RATE_5_MINUTE
 * RATE_15_MINUTE

 Note that you cannot supply both `metricWhitelist` and `metricBlacklist`.

## Contributors

* Initial code: [Chris Huang](https://github.com/tianx2)
