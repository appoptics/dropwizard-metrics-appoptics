package io.dropwizard.metrics;

import com.appoptics.metrics.client.DefaultPoster;
import com.appoptics.metrics.client.IPoster;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("default")
public class DefaultPosterFactory implements PosterFactory {

    @Override
    public IPoster createPoster() {
        return new DefaultPoster();
    }
}
