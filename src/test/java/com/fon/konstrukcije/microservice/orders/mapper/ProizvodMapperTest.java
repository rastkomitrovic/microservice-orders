package com.fon.konstrukcije.microservice.orders.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProizvodMapperTest {

    @InjectMocks
    private ProizvodMapper proizvodMapper;

    @Test
    public void mapsToDTOCorrectly() {
        Proizvod proizvod = new Proizvod(1, "Proizvod", "Tip", JedinicaMere.KOMAD);

        ProizvodDTO proizvodDTO = proizvodMapper.toDTO(proizvod);

        assertEquals(proizvod.getId(), proizvodDTO.getId());
        assertEquals(proizvod.getNaziv(), proizvodDTO.getNaziv());
        assertEquals(proizvod.getTipProizvoda(), proizvodDTO.getTipProizvoda());
        assertEquals(proizvod.getJedinica(), proizvodDTO.getJedinica());
    }

    @Test
    public void mapsToEntityCorrectly() {
        ProizvodDTO proizvodDTO = new ProizvodDTO(1, "Proizvod", "Tip", JedinicaMere.KOMAD);

        Proizvod proizvod = proizvodMapper.toEntity(proizvodDTO);

        assertEquals(proizvodDTO.getId(), proizvod.getId());
        assertEquals(proizvodDTO.getNaziv(), proizvod.getNaziv());
        assertEquals(proizvodDTO.getTipProizvoda(), proizvod.getTipProizvoda());
        assertEquals(proizvodDTO.getJedinica(), proizvod.getJedinica());
    }

    @Test
    public void returnsNullDTOForNullEntity() {
        assertNull(proizvodMapper.toDTO(null));
    }

    @Test
    public void returnsNullEntityForNullDTO() {
        assertNull(proizvodMapper.toEntity(null));
    }
}
