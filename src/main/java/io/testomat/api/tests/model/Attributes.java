package io.testomat.api.tests.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes{
	private String title;
	private String state;
	private String emoji;
	private Integer recordingsCount;
	private String code;
	private String file;
	private String priority;
	private boolean sync;
	private String lastSyncId;
	private List<Object> runStatuses;
	private String assignedTo;
	private String description;
	private String suiteId;
	private String hasExamples;
	private List<Object> params;
	private String publicTitle;
	private List<Object> tags;
	private String previousDescription;
	private String importId;
	private String playUrl;
	private String jiraIssues;
	private List<Attachment> attachments;
}