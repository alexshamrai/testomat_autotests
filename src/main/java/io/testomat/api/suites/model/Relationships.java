package io.testomat.api.suites.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Relationships{

    private Children children;
    private Branch branch;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Children{

        private List<Object> data;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Branch{

        private Object data;
    }
}
