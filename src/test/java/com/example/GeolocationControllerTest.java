package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GeolocationControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String url = "http://localhost:";
	private String idPhoto = "31891703085";

	@Before
	public void before() throws Exception {
	}

	@Test
	public void geolocationTest() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> entity = this.restTemplate.getForEntity(url + this.port + "/geolocation/" + idPhoto,
				List.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void geolocationPage1Test() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("page", "1");

		@SuppressWarnings("rawtypes")
		ResponseEntity<List> entity = this.restTemplate.getForEntity(url + this.port + "/geolocation/" + idPhoto+"?page={page}",
				List.class, params);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
