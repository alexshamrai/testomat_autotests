package io.testomat.ui.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseTestInfo {

    private String name;
    private TestType testType;

    @AllArgsConstructor
    public enum TestType {
        MANUAL("manual"),
        AUTOMATED("automated");

        public final String label;

        @Override
        public String toString() {
            return label;
        }
    }
}
