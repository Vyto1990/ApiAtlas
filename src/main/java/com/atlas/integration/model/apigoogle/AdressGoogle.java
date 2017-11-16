package com.atlas.integration.model.apigoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdressGoogle {
	
	GeometryGoogle geometry;
	String formatted_address;

	public GeometryGoogle getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryGoogle geometry) {
		this.geometry = geometry;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	
}
