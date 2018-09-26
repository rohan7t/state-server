package com.vistar.media.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author Rohan Tigadi
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validScenarioArizona() throws Exception {
	String input = "longitude=-111.216141&latitude=34.666038";
	mvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(input))
		.andExpect(status().isOk()).andExpect(content().string(equalTo("Arizona\n")));
    }

    @Test
    public void validScenarioPennsylvania() throws Exception {
	String input = "longitude=-77.036133&latitude=40.513799";
	mvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(input))
		.andExpect(status().isOk()).andExpect(content().string(equalTo("Pennsylvania\n")));
    }

    @Test
    public void validScenarioTexas() throws Exception {
	String input = "longitude=-99.967462&latitude=32.839522";
	mvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(input))
		.andExpect(status().isOk()).andExpect(content().string(equalTo("Texas\n")));
    }

    @Test
    public void validScenarioOutsideUS() throws Exception {
	String input = "longitude=11.258448&latitude=44.796314";
	mvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(input))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("This location is not in the United States\n")));
    }

    @Test
    public void invalidScenarioNoLatitude() throws Exception {
	String input = "longitude=11.258448";
	mvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(input))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("The location you provided is not valid\n")));
    }

}
