package io.testomat.api.suites.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {

    private List<Object> labels;
    private List<Object> tags;
    private Object issues;
    @JsonProperty("jira-issues")
    private Object jiraIssues;
    @JsonProperty("is-branched")
    private boolean isBranched;
    @JsonProperty("is-detail")
    private boolean isDetail;
    private String title;
    private Object emoji;
    private Object code;
    private boolean sync;
    @JsonProperty("file-type")
    private String fileType;
    @JsonProperty("test-count")
    private int testCount;
    @JsonProperty("filtered-tests")
    private Object filteredTests;
    private Object file;
    private Object notes;
    @JsonProperty("created-at")
    private String createdAt;
    @JsonProperty("updated-at")
    private String updatedAt;
    @JsonProperty("assigned-to")
    private Object assignedTo;
    @JsonProperty("to-url")
    private String toUrl;
    private int position;
    @JsonProperty("is-root")
    private boolean isRoot;
    @JsonProperty("public-title")
    private String publicTitle;
    private String description;
    @JsonProperty("parent-id")
    private Object parentId;
    private Object path;
}
