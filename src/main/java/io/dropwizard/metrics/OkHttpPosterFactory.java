package io.dropwizard.metrics;

import com.appoptics.metrics.client.IPoster;
import com.appoptics.metrics.client.OkHttpPoster;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("okhttp")
public class OkHttpPosterFactory implements PosterFactory {

    @Override
    public IPoster createPoster() {
        return new OkHttpPoster();
    }
}
