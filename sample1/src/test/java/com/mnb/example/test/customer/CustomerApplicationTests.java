package com.mnb.example.test.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllCustomersSuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/customers")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getCustomerSuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/customer/2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getCustomerFailed() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/customer/6")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}


	@Test
	public void addCustomerSuccess() throws Exception{
		String testCustomer = "{\"customerId\":4,\"name\":\"Malu\",\"phone\":\"7688678090\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/customers/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(testCustomer)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

}
