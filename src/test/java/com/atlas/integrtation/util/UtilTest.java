package com.atlas.integrtation.util;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public abstract class UtilTest {
	
	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	public String obtainAccessToken(String username, String password, MockMvc mock)
			throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grantType);
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);

		ResultActions result = mock
				.perform(
						post("/oauth/token").params(params)
								.with(httpBasic(clientId, clientSecret))
								.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse()
				.getContentAsString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();

		return jsonParser.parseMap(resultString).get("access_token").toString();

	}

}
