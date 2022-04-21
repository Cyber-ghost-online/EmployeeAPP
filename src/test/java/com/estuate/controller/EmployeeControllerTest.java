package com.estuate.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.estuate.entity.Employee;
import com.estuate.repository.EmployeeRepository;
import com.estuate.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest extends Abstract{

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService empService;
	
	@MockBean
	private Employee emp;
	
	@MockBean
	private EmployeeRepository empRepository;
	
	@Test
	public void testgetAllEmployee() throws Exception {
		List<Employee> employee = new ArrayList<>();
		
		employee.add(new Employee(15,"Palani","Macharla","palani@gmail.com",20263));
		employee.add(new Employee(24,"Suraj","Kumar","suraj@gmail.com",19568));
		
		Mockito.when(empService.getAllEmployee()).thenReturn(employee);
		mockMvc.perform(get("/emp/employeelist")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testaddEmployee() throws Exception {
		Employee employee=new Employee();
		employee.setEmpId(90);
		employee.setFirstName("Vikas");
		employee.setLastName("P J");
		employee.setEmail("vikas@gmail.com");
		employee.setSalary(19300);
		mockMvc.perform(post("/emp/addemployee").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(asJsonString(employee))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		 String uri = "/emp/updateemployee/2";
	      Employee empl = new Employee();
	      empl.setFirstName("Surya");
	      String inputJson = super.mapToJson(empl);
	      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      mvcResult.getResponse().getStatus();
	      mvcResult.getResponse().getContentAsString();
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		 String uri = "/emp/deleteemployee/45";
	     MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	     mvcResult.getResponse().getStatus();
	     mvcResult.getResponse().getContentAsString();
	}
	
	@Test
	public void testgetEmployeeById() throws Exception{
		Optional<Employee> empl = Optional.of(new Employee(1,"suraj","kumar", "suraj@gmail.com", 45632.0));
	    Mockito.when(empService.getEmployeeById(1)).thenReturn(empl);
	 
	    mockMvc.perform(MockMvcRequestBuilders.get("/emp/getemployee/1")
	    		.accept(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.empId", Matchers.is(1)))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",Matchers.is("suraj")))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",Matchers.is("kumar")))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("suraj@gmail.com")))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.salary",Matchers.is(45632.0)));
//	        Mockito.verify(empService).getEmployeeById(1);
	    }
	
	@Test
	public void testIdNotExist() throws Exception {
//		when(empService.getEmployeeById(1L)).thenThrow(new ResourceNotFoundException(""));
		doReturn(Optional.empty()).when(empService).getEmployeeById(1L);

		mockMvc.perform(get("/emp/getemployee/",1L))
		.andExpect(status().isNotFound());
	}
	
public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}