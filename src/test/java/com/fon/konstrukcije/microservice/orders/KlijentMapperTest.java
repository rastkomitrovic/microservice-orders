package com.fon.konstrukcije.microservice.orders;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;
import com.fon.konstrukcije.microservice.orders.mapper.KlijentMapper;

public class KlijentMapperTest {
	
	@InjectMocks
	Klijent klijent;
	@InjectMocks
	KlijentDTO klijentDTO;
	
	@Mock
	KlijentMapper klijentMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void toDTOTest() {
		
		KlijentDTO klijentDTOTest = new KlijentDTO();
		
		klijentDTOTest.setAdresa(klijent.getAdresa());
		klijentDTOTest.setBrojTelefona(klijent.getBrojTelefona());
		klijentDTOTest.setEmail(klijent.getEmail());
		klijentDTOTest.setId(klijent.getId());
		klijentDTOTest.setIme(klijent.getIme());
		klijentDTOTest.setPrezime(klijent.getPrezime());
		
		when(klijentMapper.toDTO(klijent)).thenReturn(klijentDTOTest);
	}
	
	@Test
	public void toEntityTest() {
		
		Klijent klijentTest = new Klijent();
		
		klijentTest.setAdresa(klijentDTO.getAdresa());
		klijentTest.setBrojTelefona(klijentDTO.getBrojTelefona());
		klijentTest.setEmail(klijentDTO.getEmail());
		klijentTest.setId(klijentDTO.getId());
		klijentTest.setIme(klijentDTO.getIme());
		klijentTest.setPrezime(klijentDTO.getPrezime());
		
		when(klijentMapper.toEntity(klijentDTO)).thenReturn(klijentTest);
	}

}
