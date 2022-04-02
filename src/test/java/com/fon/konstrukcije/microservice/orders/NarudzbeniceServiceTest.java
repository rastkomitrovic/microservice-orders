package com.fon.konstrukcije.microservice.orders;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fon.konstrukcije.microservice.orders.entity.Klijent;
import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;
import com.fon.konstrukcije.microservice.orders.entity.StavkaNarudzbenice;
import com.fon.konstrukcije.microservice.orders.entity.embedded.StavkaNarudzbeniceEmbeddedId;
import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;
import com.fon.konstrukcije.microservice.orders.repository.NarudzbeniceRepository;
import com.fon.konstrukcije.microservice.orders.service.NarudzbeniceService;

public class NarudzbeniceServiceTest {
	
	@InjectMocks
	NarudzbeniceService narudzbeniceService;
	
	@Mock
	NarudzbeniceRepository narudzbeniceRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveNarudzbenice() {
		
		Narudzbenica narudzbenicaTest = new Narudzbenica();
		Klijent klijentTest = new Klijent(150, "Dusko", "Dusic", "dule@mail.com", "06571124", "Poznata Adresa");
		Proizvod proizvodTest = new Proizvod(21, "P1", "Tester", JedinicaMere.KOMAD);
		StavkaNarudzbeniceEmbeddedId embeddedIdTest = new StavkaNarudzbeniceEmbeddedId(25, 1);
		Set<StavkaNarudzbenice> stavkeNarudzbeniceTest = new HashSet<>();
		
		StavkaNarudzbenice stavkaNarudzbeniceTest1 = new StavkaNarudzbenice(embeddedIdTest, narudzbenicaTest, 5, 50.0, 250.0, proizvodTest);
		stavkeNarudzbeniceTest.add(stavkaNarudzbeniceTest1);
		
		narudzbenicaTest.setBrojNarudzbenice(100);
		narudzbenicaTest.setDatumKreiranja(LocalDateTime.now());
		narudzbenicaTest.setDatumAzuriranja(LocalDateTime.now().plusHours(1));
		narudzbenicaTest.setKlijent(klijentTest);
		narudzbenicaTest.setStavkeNarudzbenice(stavkeNarudzbeniceTest);
		
		
		when(narudzbeniceRepository.save(narudzbenicaTest)).thenReturn(narudzbenicaTest);
	}

}
