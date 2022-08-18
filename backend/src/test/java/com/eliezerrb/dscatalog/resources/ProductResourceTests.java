package com.eliezerrb.dscatalog.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.eliezerrb.dscatalog.dto.ProductDTO;
import com.eliezerrb.dscatalog.services.ProductService;
import com.eliezerrb.dscatalog.tests.Factory;

@WebMvcTest(ProductResource.class)
public class ProductResourceTests {
	
	// MockMvc utilizado para chamar endpoint no teste
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;
	
	private ProductDTO productDTO;
	
	// PageImpl ao invez de Page porque ele é objeto de página concreto e aceita o new
	private PageImpl<ProductDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
		
		productDTO = Factory.createProductDTO();
		page = new PageImpl<>(List.of(productDTO));
		
		// Simulando mockado service.findAllPaged com qualquer argumento(any)
		when(service.findAllPaged(any())).thenReturn(page);
	}
	
	@Test
	public void findAllShouldReturnPage() throws Exception{
		
		// Fazendo requisição get e esperando o resultado ok(200) tudo na mesma linha
		// mockMvc.perform(get("/products")).andExpect(status().isOk());
		
		// Outra forma de fazer para facilitar o entendimento Legibilidade 
		ResultActions result = mockMvc.perform(get("/products")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());

	}
}