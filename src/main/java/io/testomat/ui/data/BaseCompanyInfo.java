package io.testomat.ui.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseCompanyInfo {

    private String companyName;
    private List<Member> members;
    private Status status;

    @Data
    @Builder
    public static class Member {

        private String name;
        private String email;

    }

    @AllArgsConstructor
    public enum Status {
        ACTIVE("Active"),
        REMOVED("Removed");

        public final String status;

        @Override
        public String toString() {
            return status;
        }
    }
}
