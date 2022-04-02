package com.fon.konstrukcije.microservice.orders;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;
import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;
import com.fon.konstrukcije.microservice.orders.entity.StavkaNarudzbenice;
import com.fon.konstrukcije.microservice.orders.entity.embedded.StavkaNarudzbeniceEmbeddedId;
import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;
import com.fon.konstrukcije.microservice.orders.mapper.KlijentMapper;
import com.fon.konstrukcije.microservice.orders.mapper.NarudzbeniceMapper;
import com.fon.konstrukcije.microservice.orders.mapper.ProizvodMapper;

public class NarudzbeniceMapperTest {
	
	@Mock
	KlijentMapper klijentMapper;
	@Mock
	ProizvodMapper proizvodMapper;
	@Mock
	NarudzbeniceMapper narudzbeniceMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	public void toDTOTest() {
		
		NarudzbenicaDTO narudzbenicaDTOTest = new NarudzbenicaDTO();
		StavkaNarudzbeniceEmbeddedId embeddedIdTest = new StavkaNarudzbeniceEmbeddedId(25, 1);
		Set<StavkaNarudzbenice> stavkeNarudzbeniceTest = new HashSet<>();
		Proizvod proizvodTest = new Proizvod(21, "P1", "Tester", JedinicaMere.KOMAD);
		
		Narudzbenica narudzbenicaTest = new Narudzbenica(1, new Klijent(1, "Test Ime", "Test Prezime", "test@mail", "06571", "Nepoznata"),
														LocalDateTime.now(), LocalDateTime.now().plusHours(1), 165.56, stavkeNarudzbeniceTest);
	
		StavkaNarudzbenice stavkaNarudzbeniceTest1 = new StavkaNarudzbenice(embeddedIdTest, narudzbenicaTest, 5, 50.0, 250.0, proizvodTest);
		stavkeNarudzbeniceTest.add(stavkaNarudzbeniceTest1);

		narudzbenicaDTOTest.setBrojNarudzbenice(narudzbenicaTest.getBrojNarudzbenice());
		narudzbenicaDTOTest.setDatumKreiranja(narudzbenicaTest.getDatumKreiranja());
		narudzbenicaDTOTest.setDatumAzuriranja(narudzbenicaTest.getDatumAzuriranja());
		narudzbenicaDTOTest.setKlijent(klijentMapper.toDTO(narudzbenicaTest.getKlijent()));
		narudzbenicaDTOTest.setStavkeNarudzbenice(narudzbenicaTest
                			.getStavkeNarudzbenice()
                			.stream()
                			.map(this::toDTOStavka)
                			.collect(Collectors.toList()));
		narudzbenicaDTOTest.setUkupno(narudzbenicaTest.getUkupno());
		
		when(narudzbeniceMapper.toDTO(narudzbenicaTest)).thenReturn(narudzbenicaDTOTest);
	}
	
	private StavkaNarudzbeniceDTO toDTOStavka(StavkaNarudzbenice stavkaNarudzbenice) {

        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO = new StavkaNarudzbeniceDTO();

        stavkaNarudzbeniceDTO.setBrojNarudzbenice(stavkaNarudzbenice.getId().getBrojNarudzbenice());
        stavkaNarudzbeniceDTO.setCena(stavkaNarudzbenice.getCena());
        stavkaNarudzbeniceDTO.setKolicina(stavkaNarudzbenice.getKolicina());
        if (Hibernate.isInitialized(stavkaNarudzbenice.getProizvod()))
            stavkaNarudzbeniceDTO.setProizvodDTO(proizvodMapper.toDTO(stavkaNarudzbenice.getProizvod()));
        stavkaNarudzbeniceDTO.setRb(stavkaNarudzbenice.getId().getRb());
        stavkaNarudzbeniceDTO.setUkupnaCena(stavkaNarudzbenice.getUkupnaCena());

        return stavkaNarudzbeniceDTO;
    }
	
	public void toEntityTest() {
		
		Narudzbenica narudzbenicaTest = new Narudzbenica();
		StavkaNarudzbeniceEmbeddedId embeddedIdTest = new StavkaNarudzbeniceEmbeddedId(25, 1);
		List<StavkaNarudzbenice> stavkeNarudzbeniceTest = new ArrayList<>();
		Proizvod proizvodTest = new Proizvod(21, "P1", "Tester", JedinicaMere.KOMAD);
		Klijent klijentTest = new Klijent(1, "Test Ime", "Test Prezime", "test@mail", "06571", "Nepoznata");
		
		StavkaNarudzbenice stavkaNarudzbeniceTest1 = new StavkaNarudzbenice(embeddedIdTest, narudzbenicaTest, 5, 50.0, 250.0, proizvodTest);
		stavkeNarudzbeniceTest.add(stavkaNarudzbeniceTest1);

		// IZBACUJE MI GRESKU
		NarudzbenicaDTO narudzbenicaDTOTest = new NarudzbenicaDTO(1, klijentTest,
														          LocalDateTime.now(), LocalDateTime.now().plusHours(1), 
														          165.56, stavkeNarudzbeniceTest);
		
		narudzbenicaTest.setBrojNarudzbenice(narudzbenicaDTOTest.getBrojNarudzbenice());
		narudzbenicaTest.setDatumKreiranja(narudzbenicaDTOTest.getDatumKreiranja());
		narudzbenicaTest.setDatumAzuriranja(narudzbenicaDTOTest.getDatumAzuriranja());
		narudzbenicaTest.setKlijent(klijentMapper.toEntity(narudzbenicaDTOTest.getKlijent()));
		narudzbenicaTest.setStavkeNarudzbenice(narudzbenicaDTOTest
						.getStavkeNarudzbenice()
						.stream()
						.map(it -> toEntityStavka(it, narudzbenicaTest))
						.collect(Collectors.toSet()));
		narudzbenicaTest.setUkupno(narudzbenicaDTOTest.getUkupno());
		
		when(narudzbeniceMapper.toEntity(narudzbenicaDTOTest)).thenReturn(narudzbenicaTest);
							
	}
	
	private StavkaNarudzbenice toEntityStavka(StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO, Narudzbenica narudzbenica) {

        StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
        StavkaNarudzbeniceEmbeddedId embeddedId = new StavkaNarudzbeniceEmbeddedId();

        embeddedId.setBrojNarudzbenice(narudzbenica.getBrojNarudzbenice());
        embeddedId.setRb(stavkaNarudzbeniceDTO.getRb());

        stavkaNarudzbenice.setCena(stavkaNarudzbeniceDTO.getCena());
        stavkaNarudzbenice.setId(embeddedId);
        stavkaNarudzbenice.setNarudzbenica(narudzbenica);
        stavkaNarudzbenice.setKolicina(stavkaNarudzbeniceDTO.getKolicina());
        stavkaNarudzbenice.setProizvod(proizvodMapper.toEntity(stavkaNarudzbeniceDTO.getProizvodDTO()));
        stavkaNarudzbenice.setUkupnaCena(stavkaNarudzbeniceDTO.getUkupnaCena());

        return stavkaNarudzbenice;
    }
}
