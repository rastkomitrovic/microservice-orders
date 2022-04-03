package com.fon.konstrukcije.microservice.orders.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class KlijentMapperTest {

    @InjectMocks
    private KlijentMapper klijentMapper;

    @Test
    public void mapsToDTOCorrectly() {
        Klijent klijent = new Klijent(1, "Ime", "Prezime", "Email", "Telefon", "Adresa");

        KlijentDTO klijentDTO = klijentMapper.toDTO(klijent);

        assertEquals(klijent.getId(), klijentDTO.getId());
        assertEquals(klijent.getIme(), klijentDTO.getIme());
        assertEquals(klijent.getPrezime(), klijentDTO.getPrezime());
        assertEquals(klijent.getEmail(), klijentDTO.getEmail());
        assertEquals(klijent.getBrojTelefona(), klijentDTO.getBrojTelefona());
        assertEquals(klijent.getAdresa(), klijentDTO.getAdresa());
    }

    @Test
    public void mapsToEntityCorrectly() {
        KlijentDTO klijentDTO = new KlijentDTO(1, "Ime", "Prezime", "Email", "Telefon", "Adresa");

        Klijent klijent = klijentMapper.toEntity(klijentDTO);

        assertEquals(klijentDTO.getId(), klijent.getId());
        assertEquals(klijentDTO.getIme(), klijent.getIme());
        assertEquals(klijentDTO.getPrezime(), klijent.getPrezime());
        assertEquals(klijentDTO.getEmail(), klijent.getEmail());
        assertEquals(klijentDTO.getBrojTelefona(), klijent.getBrojTelefona());
        assertEquals(klijentDTO.getAdresa(), klijent.getAdresa());

    }

    @Test
    public void returnsNullDTOForNullEntity() {
        assertNull(klijentMapper.toDTO(null));
    }

    @Test
    public void returnsNullEntityForNullDTO() {
        assertNull(klijentMapper.toEntity(null));
    }


}
