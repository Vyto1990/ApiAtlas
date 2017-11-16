package com.atlas.integration.model.apigoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeometryGoogle {
	
	CoordinatesGoogle location;

	public CoordinatesGoogle getLocation() {
		return location;
	}

	public void setLocation(CoordinatesGoogle location) {
		this.location = location;
	}
	
	

}
