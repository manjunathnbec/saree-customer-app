package com.mnb.example.test.hospital;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllPatientsSuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/hospital/patients")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getPatientSuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/hospital/patient/2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getPatientFailed() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/hospital/patient/6")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}


	@Test
	public void addPatientSuccess() throws Exception{
		String testPatient = "{\"patientId\":4,\"name\":\"Malu\",\"phone\":\"7688678090\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/hospital/patients/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(testPatient)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

}
