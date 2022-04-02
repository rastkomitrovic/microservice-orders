package com.fon.konstrukcije.microservice.orders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.entity.embedded.StavkaNarudzbeniceEmbeddedId;
import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;
import com.fon.konstrukcije.microservice.orders.service.NarudzbeniceService;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class NarudzbenicaFacadeTest {

    @Mock
    NarudzbeniceService narudzbeniceService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTest() {

        NarudzbenicaDTO narudzbenicaDTOTest = new NarudzbenicaDTO();
        KlijentDTO klijentDTOTest = new KlijentDTO(150, "Dusko", "Dusic", "dule@mail.com", "06571124", "Poznata Adresa");
        ProizvodDTO proizvodDTOTest = new ProizvodDTO(21, "P1", "Tester", JedinicaMere.KOMAD);

        StavkaNarudzbeniceEmbeddedId embeddedIdTest = new StavkaNarudzbeniceEmbeddedId(25, 1);
        List<StavkaNarudzbeniceDTO> stavkaNarudzbeniceDTOTests = new LinkedList<>();

        // MISLIM DA ME ZEZA KOD EQUALS
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTOTest = new StavkaNarudzbeniceDTO(5, 1, 12, 3D, 36D, proizvodDTOTest);
        stavkaNarudzbeniceDTOTests.add(stavkaNarudzbeniceDTOTest);

        narudzbenicaDTOTest.setBrojNarudzbenice(100);
        narudzbenicaDTOTest.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTOTest.setDatumAzuriranja(LocalDateTime.now().plusHours(1));
        narudzbenicaDTOTest.setKlijent(klijentDTOTest);
        narudzbenicaDTOTest.setStavkeNarudzbenice(stavkaNarudzbeniceDTOTests);


        when(narudzbeniceService.save(narudzbenicaDTOTest)).thenReturn(narudzbenicaDTOTest);
    }

    @Test
    public void updateTest() {

        NarudzbenicaDTO narudzbenicaDTOTest = new NarudzbenicaDTO();
        KlijentDTO klijentDTOTest = new KlijentDTO(150, "Dusko", "Dusic", "dule@mail.com", "06571124", "Poznata Adresa");
        ProizvodDTO proizvodDTOTest = new ProizvodDTO(21, "P1", "Tester", JedinicaMere.KOMAD);

        StavkaNarudzbeniceEmbeddedId embeddedIdTest = new StavkaNarudzbeniceEmbeddedId(25, 1);
        List<StavkaNarudzbeniceDTO> stavkaNarudzbeniceDTOTests = new LinkedList<>();

        // MISLIM DA ME ZEZA KOD EQUALS
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTOTest = new StavkaNarudzbeniceDTO(5, 1, 12, 3D, 36D, proizvodDTOTest);
        stavkaNarudzbeniceDTOTests.add(stavkaNarudzbeniceDTOTest);

        narudzbenicaDTOTest.setBrojNarudzbenice(100);
        narudzbenicaDTOTest.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTOTest.setDatumAzuriranja(LocalDateTime.now().plusHours(1));
        narudzbenicaDTOTest.setKlijent(klijentDTOTest);
        narudzbenicaDTOTest.setStavkeNarudzbenice(stavkaNarudzbeniceDTOTests);

        when(narudzbeniceService.findById(anyInt())).thenReturn(Optional.of(narudzbeniceService.save(narudzbenicaDTOTest)));
    }

    @Test
    public void findById() {

        NarudzbenicaDTO narudzbenicaDTOTest = new NarudzbenicaDTO();
        KlijentDTO klijentDTOTest = new KlijentDTO(150, "Dusko", "Dusic", "dule@mail.com", "06571124", "Poznata Adresa");
        ProizvodDTO proizvodDTOTest = new ProizvodDTO(21, "P1", "Tester", JedinicaMere.KOMAD);

        StavkaNarudzbeniceEmbeddedId embeddedIdTest = new StavkaNarudzbeniceEmbeddedId(25, 1);
        List<StavkaNarudzbeniceDTO> stavkaNarudzbeniceDTOTests = new LinkedList<>();

        // MISLIM DA ME ZEZA KOD EQUALS
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTOTest = new StavkaNarudzbeniceDTO(5, 1, 12, 3D, 36D, proizvodDTOTest);
        stavkaNarudzbeniceDTOTests.add(stavkaNarudzbeniceDTOTest);

        narudzbenicaDTOTest.setBrojNarudzbenice(100);
        narudzbenicaDTOTest.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTOTest.setDatumAzuriranja(LocalDateTime.now().plusHours(1));
        narudzbenicaDTOTest.setKlijent(klijentDTOTest);
        narudzbenicaDTOTest.setStavkeNarudzbenice(stavkaNarudzbeniceDTOTests);

        when(narudzbeniceService.findById(anyInt())).thenReturn(Optional.of(narudzbenicaDTOTest));
    }

    @Test
    public void findPageTest() {

        //when(narudzbeniceRepository.findAllBySearch(null, anyString()))
    }

}
