package io.dropwizard.metrics;

import com.appoptics.metrics.client.IPoster;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface PosterFactory extends Discoverable {
    IPoster createPoster();

}

