package io.testomat.api.suites.model;

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

        private String id;
        private String type;
        private Attributes attributes;
        private Relationships relationships;

    }
}
