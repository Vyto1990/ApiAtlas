package com.atlas.integration.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SecurityConfigTest {

	private MockMvc mock;

	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(this.context)
				.addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void givenNoToken_getCoordinatesTest() throws Exception {
		mock.perform(get("/api/listAdress")).andExpect(
				status().isUnauthorized());
	}

	@Test
	public void givenInvalidRole_getCoordinatesTest() throws Exception {
		String accessToken = obtainAccessToken("elias.lima", "jwtpass");
		//listAdress only can accces  by user admin.admin 
		mock.perform(
				get("/api/listAdress").header("Authorization",
						"Bearer " + accessToken)).andExpect(status().isForbidden());
	}
	
	@Test
	public void givenValidRole_getCoordinatesTest() throws Exception {
		String accessToken = obtainAccessToken("admin.admin", "jwtpass");
		//listAdress only can accces by user admin.admin 
		mock.perform(
				get("/api/listAdress").header("Authorization",
						"Bearer " + accessToken)).andExpect(status().isOk());
	} 

	private String obtainAccessToken(String username, String password)
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
