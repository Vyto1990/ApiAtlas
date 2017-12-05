package com.atlas.integration.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;

import com.atlas.integration.model.Adress;
import com.atlas.integration.model.Status;
import com.atlas.integration.repository.AdressRepository;
import com.atlas.integration.service.GenericService;


import com.atlas.integrtation.util.UtilTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ResourceControllerTest extends UtilTest {

	private MockMvc mock;
	private String accessToken;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	@Mock
	private GenericService genericService;
	
	@Autowired
    private AdressRepository repositoryService;

	
	
	@Before
	public void setup() throws Exception {
		this.mock = MockMvcBuilders.webAppContextSetup(this.context)
				.addFilter(springSecurityFilterChain).build();
		 accessToken = obtainAccessToken("admin.admin", "jwtpass", mock);
	}

	@Test
	public void listaAdressTest() throws Exception {
		
		 List<Adress> adress = Arrays.asList(new Adress("Valls"), new
		 Adress("Picamoixons"));
		 when(genericService.findAllAdress()).thenReturn(adress);
		 mock.perform(get("/api/listAdress").header("Authorization",
		 "Bearer " + accessToken)).andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$[0].id", is(1)))
         .andExpect(jsonPath("$[0].name", is("Barcelona, Barcelona")));
	}
	
	@Test
	public void saveAdressTest() throws Exception {
		Adress adr = new Adress("Valls");
		when(genericService.addAdress(adr.getName())).thenReturn(Status.OK);
		
		mock.perform(get("/api/addAdress/{adress}", adr.getName())
			.header("Authorization",
		 "Bearer " + accessToken)).andExpect(status().isOk());
	}
	

}
