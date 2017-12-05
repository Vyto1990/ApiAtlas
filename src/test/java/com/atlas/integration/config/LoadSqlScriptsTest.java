package com.atlas.integration.config;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql({ "/sql-scripts/drop-schema.sql", "/sql-scripts/schema.sql",
		"/sql-scripts/data.sql" })
public class LoadSqlScriptsTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Test
	public void sqlTest() {
		
		List<String> listAdress = jdbcTemplate.queryForList("select direction from adress",
                String.class);
		assertEquals(13, listAdress.size());
		
		List<String> listUser = jdbcTemplate.queryForList("select id from app_role",
                String.class);
		assertEquals(2, listUser.size());
		
		List<String> listRole = jdbcTemplate.queryForList("select id from app_user",
                String.class);
		assertEquals(2, listRole.size());
		
		List<String> listUserRole = jdbcTemplate.queryForList("select user_id from user_role",
                String.class);
		assertEquals(3, listUserRole.size());
		
	}

}
