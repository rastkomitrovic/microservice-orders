package com.fon.konstrukcije.microservice.orders;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;
import com.fon.konstrukcije.microservice.orders.mapper.ProizvodMapper;

public class ProizvodMapperTest {
	
	@InjectMocks
	Proizvod proizvod;	
	@InjectMocks
	ProizvodDTO proizvodDTO;
	
	@Mock
	ProizvodMapper proizvodMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void toDTOTest() {
		
		ProizvodDTO proizvodDTOTest = new ProizvodDTO();
		
		proizvodDTOTest.setId(proizvod.getId());
		proizvodDTOTest.setJedinica(proizvod.getJedinica());
		proizvodDTOTest.setNaziv(proizvod.getNaziv());
		proizvodDTOTest.setTipProizvoda(proizvod.getTipProizvoda());
		
		when(proizvodMapper.toDTO(proizvod)).thenReturn(proizvodDTOTest);
	}
	
	@Test
	public void toEntityTest() {
		
		Proizvod proizvodTest = new Proizvod();
		
		proizvodTest.setId(proizvodDTO.getId());
		proizvodTest.setJedinica(proizvodDTO.getJedinica());
		proizvodTest.setNaziv(proizvodDTO.getNaziv());
		proizvodTest.setTipProizvoda(proizvodDTO.getTipProizvoda());
		
		when(proizvodMapper.toEntity(proizvodDTO)).thenReturn(proizvodTest);
	}

}
