package io.testomat.api.model;


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
public  class Attributes {

    @JsonProperty("labels")
    private List<Object> labels;

    @JsonProperty("tags")
    private List<Object> tags;

    @JsonProperty("issues")
    private Object issues;

    @JsonProperty("jira-issues")
    private Object jiraIssues;

    @JsonProperty("is-branched")
    private boolean isBranched;

    @JsonProperty("is-detail")
    private boolean isDetail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("emoji")
    private Object emoji;

    @JsonProperty("code")
    private Object code;

    @JsonProperty("sync")
    private boolean sync;

    @JsonProperty("file-type")
    private String fileType;

    @JsonProperty("test-count")
    private int testCount;

    @JsonProperty("filtered-tests")
    private Object filteredTests;

    @JsonProperty("file")
    private Object file;

    @JsonProperty("notes")
    private Object notes;

    @JsonProperty("created-at")
    private String createdAt;

    @JsonProperty("updated-at")
    private String updatedAt;

    @JsonProperty("assigned-to")
    private Object assignedTo;

    @JsonProperty("to-url")
    private String toUrl;

    @JsonProperty("position")
    private int position;

    @JsonProperty("is-root")
    private boolean isRoot;

    @JsonProperty("public-title")
    private String publicTitle;

    @JsonProperty("description")
    private String description;

    @JsonProperty("parent-id")
    private Object parentId;

    @JsonProperty("path")
    private Object path;
}
