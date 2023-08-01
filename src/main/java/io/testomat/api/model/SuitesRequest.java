package io.testomat.api.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuitesRequest {

    @JsonProperty("data")
    private DataDetail datas;

    @Data
    @Builder
    public static class DataDetail {

        @JsonProperty("attributes")
        private Attributes attributes;

        @JsonProperty("type")
        private String type ;

    }

}
