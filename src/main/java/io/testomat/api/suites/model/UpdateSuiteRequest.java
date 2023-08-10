package io.testomat.api.suites.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSuiteRequest {

    @JsonProperty("data")
    private DataDetail data;

    @Data
    @Builder
    public static class DataDetail {

        private String id;
        private Attributes attributes;
        private String type ;
    }

}
