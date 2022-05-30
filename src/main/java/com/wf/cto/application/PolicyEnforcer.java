package com.wf.cto.application;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class PolicyEnforcer {
	
	private String projectProfile;
	private String enforcePolicy;
	private static String PROFILE_URL = "http://localhost:8081/getprofile";
	private static String PROJECT_ID = "projectID";
	
	public String getEnforcePolicy() {
		return enforcePolicy;
	}

	public void setEnforcePolicy(String enforcePolicy) {
		this.enforcePolicy = enforcePolicy;
	}

	public enum ProfileType {
		DBSENSITIVE, NETWORKSENSITIVE, DEFAULT
	}
	
	public String getProjectProfile() {
		return projectProfile;
	}

	public void setProjectProfile(String projectID, RestTemplate restTemplate) {
		 String url = UriComponentsBuilder.fromHttpUrl(PROFILE_URL)
				    .queryParam(PROJECT_ID, projectID)
				    .toUriString();

		String response = restTemplate.getForObject(url, String.class);
		this.projectProfile = response;
	}


}
