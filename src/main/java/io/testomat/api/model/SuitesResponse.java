package io.testomat.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuitesResponse {
    @JsonProperty("data")
    private Datas data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Datas {

        @JsonProperty("id")
        private String id;

        @JsonProperty("type")
        private String type;

        @JsonProperty("attributes")
        private Attributes attributes;

        @JsonProperty("relationships")
        private Relationships relationships;

    }
}
