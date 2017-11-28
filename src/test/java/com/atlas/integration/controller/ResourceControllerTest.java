package com.atlas.integration.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atlas.integration.service.GenericService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ResourceControllerTest {

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

	@Mock
	private GenericService genericService;

	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(this.context)
				.addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void getD(){
	}
}
