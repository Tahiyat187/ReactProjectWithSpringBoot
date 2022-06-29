package com.example.handsOnProject2.integrationTesting;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.handsOnProject2.ErpProjectApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ErpProjectApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTestController {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void registerTest() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/authenticate").content("{\"username\":\"naser\",\"password\":\"bjit1234\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String[] body = result.getResponse().getContentAsString().split(",");
		String jwtToken = "Bearer "+body[0].substring(13, body[0].length()-1);
		this.mockMvc.perform(post("/register").content("{\"name\":\"akif\",\"age\":\"24\",\"password\":\"bjit1234\","
				+ "\"address\":\"dhaka\",\"role\":\"ROLE_STUDENT\"}").header("Authorization", jwtToken)
        		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void findByNameTest() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/authenticate").content("{\"username\":\"tahiyat\",\"password\":\"bjit1234\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String[] body = result.getResponse().getContentAsString().split(",");
		String jwtToken = "Bearer "+body[0].substring(13, body[0].length()-1);
		MvcResult searchResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/search?name=naser").header("Authorization", jwtToken)).andExpect(status().isOk()).andReturn();
		String responseBody = searchResult.getResponse().getContentAsString();
		assertTrue(responseBody.contains("naser"));
	}
}
