package io.testomat.api.tests.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@lombok.Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestsRequest {
    private Data data;

    @lombok.Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private String type;
        private Attributes attributes;
    }

    @lombok.Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attributes {
        private String title;
        private String description;
        private String suite_id;
        private int priority;
    }
}
