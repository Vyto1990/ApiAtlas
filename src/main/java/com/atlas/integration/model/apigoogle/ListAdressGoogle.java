package com.atlas.integration.model.apigoogle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAdressGoogle {

	private List<AdressGoogle> results;
	private String status;

	public List<AdressGoogle> getResults() {
		return results;
	}

	public void setResults(List<AdressGoogle> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
