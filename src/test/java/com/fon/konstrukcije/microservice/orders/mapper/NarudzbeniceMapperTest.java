package com.fon.konstrukcije.microservice.orders.mapper;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.*;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;
import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;
import com.fon.konstrukcije.microservice.orders.entity.StavkaNarudzbenice;
import com.fon.konstrukcije.microservice.orders.entity.embedded.StavkaNarudzbeniceEmbeddedId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NarudzbeniceMapperTest {

    @Mock
    private KlijentMapper klijentMapper;

    @Mock
    private ProizvodMapper proizvodMapper;

    @InjectMocks
    private NarudzbeniceMapper narudzbeniceMapper;

    @Test
    public void mapsToDTOCorrectly() {

        Narudzbenica narudzbenica = new Narudzbenica();
        narudzbenica.setBrojNarudzbenice(123);

        Klijent klijent = mock(Klijent.class);
        KlijentDTO klijentDTO = mock(KlijentDTO.class);
        when(klijentMapper.toDTO(klijent)).thenReturn(klijentDTO);
        narudzbenica.setKlijent(klijent);

        narudzbenica.setDatumKreiranja(LocalDateTime.now());
        narudzbenica.setDatumAzuriranja(LocalDateTime.now());
        narudzbenica.setUkupno(1000D);

        Proizvod proizvod1 = mock(Proizvod.class);
        ProizvodDTO proizvodDTO1 = mock(ProizvodDTO.class);
        when(proizvodMapper.toDTO(proizvod1)).thenReturn(proizvodDTO1);
        StavkaNarudzbeniceEmbeddedId stavkaNarudzbeniceEmbeddedId1 = new StavkaNarudzbeniceEmbeddedId(narudzbenica.getBrojNarudzbenice(), 1);
        StavkaNarudzbenice stavkaNarudzbenice1 = new StavkaNarudzbenice(stavkaNarudzbeniceEmbeddedId1, narudzbenica, 2, 100D, 200D, proizvod1);

        Proizvod proizvod2 = mock(Proizvod.class);
        ProizvodDTO proizvodDTO2 = mock(ProizvodDTO.class);
        when(proizvodMapper.toDTO(proizvod2)).thenReturn(proizvodDTO2);
        StavkaNarudzbeniceEmbeddedId stavkaNarudzbeniceEmbeddedId2 = new StavkaNarudzbeniceEmbeddedId(narudzbenica.getBrojNarudzbenice(), 2);
        StavkaNarudzbenice stavkaNarudzbenice2 = new StavkaNarudzbenice(stavkaNarudzbeniceEmbeddedId2, narudzbenica, 2, 400D, 800D, proizvod2);

        List<StavkaNarudzbenice> stavkeNarudzbenice = new LinkedList<>();
        stavkeNarudzbenice.add(stavkaNarudzbenice1);
        stavkeNarudzbenice.add(stavkaNarudzbenice2);

        narudzbenica.setStavkeNarudzbenice(stavkeNarudzbenice);

        NarudzbenicaDTO narudzbenicaDTO = narudzbeniceMapper.toDTO(narudzbenica);

        assertEquals(narudzbenica.getBrojNarudzbenice(), narudzbenicaDTO.getBrojNarudzbenice());
        assertEquals(klijentDTO, narudzbenicaDTO.getKlijent());
        assertEquals(narudzbenica.getDatumKreiranja(), narudzbenicaDTO.getDatumKreiranja());
        assertEquals(narudzbenica.getDatumAzuriranja(), narudzbenicaDTO.getDatumAzuriranja());
        assertEquals(narudzbenica.getUkupno(), narudzbenicaDTO.getUkupno());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(0).getId().getBrojNarudzbenice(), narudzbenicaDTO.getStavkeNarudzbenice().get(0).getBrojNarudzbenice());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(0).getId().getRb(), narudzbenicaDTO.getStavkeNarudzbenice().get(0).getRb());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(0).getKolicina(), narudzbenicaDTO.getStavkeNarudzbenice().get(0).getKolicina());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(0).getCena(), narudzbenicaDTO.getStavkeNarudzbenice().get(0).getCena());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(0).getUkupnaCena(), narudzbenicaDTO.getStavkeNarudzbenice().get(0).getUkupnaCena());
        assertEquals(proizvodDTO1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getProizvod());

        assertEquals(narudzbenica.getStavkeNarudzbenice().get(1).getId().getBrojNarudzbenice(), narudzbenicaDTO.getStavkeNarudzbenice().get(1).getBrojNarudzbenice());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(1).getId().getRb(), narudzbenicaDTO.getStavkeNarudzbenice().get(1).getRb());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(1).getKolicina(), narudzbenicaDTO.getStavkeNarudzbenice().get(1).getKolicina());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(1).getCena(), narudzbenicaDTO.getStavkeNarudzbenice().get(1).getCena());
        assertEquals(narudzbenica.getStavkeNarudzbenice().get(1).getUkupnaCena(), narudzbenicaDTO.getStavkeNarudzbenice().get(1).getUkupnaCena());
        assertEquals(proizvodDTO2, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getProizvod());
    }

    @Test
    public void toEntityTest() {

        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO();
        narudzbenicaDTO.setBrojNarudzbenice(123);

        KlijentDTO klijentDTO = mock(KlijentDTO.class);
        Klijent klijent = mock(Klijent.class);
        when(klijentMapper.toEntity(klijentDTO)).thenReturn(klijent);
        narudzbenicaDTO.setKlijent(klijentDTO);

        narudzbenicaDTO.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTO.setDatumAzuriranja(LocalDateTime.now());
        narudzbenicaDTO.setUkupno(1000D);

        ProizvodDTO proizvodDTO1 = mock(ProizvodDTO.class);
        Proizvod proizvod1 = mock(Proizvod.class);
        when(proizvodMapper.toEntity(proizvodDTO1)).thenReturn(proizvod1);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO1 = new StavkaNarudzbeniceDTO(narudzbenicaDTO.getBrojNarudzbenice(), 1, 2, 100D, 200D, proizvodDTO1);

        ProizvodDTO proizvodDTO2 = mock(ProizvodDTO.class);
        Proizvod proizvod2 = mock(Proizvod.class);
        when(proizvodMapper.toEntity(proizvodDTO2)).thenReturn(proizvod2);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO2 = new StavkaNarudzbeniceDTO(narudzbenicaDTO.getBrojNarudzbenice(), 1, 2, 400D, 800D, proizvodDTO2);

        List<StavkaNarudzbeniceDTO> stavkeNarudzbenice = new LinkedList<>();
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO1);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO2);
        narudzbenicaDTO.setStavkeNarudzbenice(stavkeNarudzbenice);

        Narudzbenica narudzbenica = narudzbeniceMapper.toEntity(narudzbenicaDTO);

        assertEquals(narudzbenicaDTO.getBrojNarudzbenice(), narudzbenica.getBrojNarudzbenice());
        assertEquals(klijent, narudzbenica.getKlijent());
        assertEquals(narudzbenicaDTO.getDatumKreiranja(), narudzbenica.getDatumKreiranja());
        assertEquals(narudzbenicaDTO.getDatumAzuriranja(), narudzbenica.getDatumAzuriranja());
        assertEquals(narudzbenicaDTO.getUkupno(), narudzbenica.getUkupno());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(0).getBrojNarudzbenice(), narudzbenica.getStavkeNarudzbenice().get(0).getId().getBrojNarudzbenice());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(0).getRb(), narudzbenica.getStavkeNarudzbenice().get(0).getId().getRb());
        assertEquals(narudzbenica, narudzbenica.getStavkeNarudzbenice().get(0).getNarudzbenica());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(0).getKolicina(), narudzbenica.getStavkeNarudzbenice().get(0).getKolicina());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(0).getCena(), narudzbenica.getStavkeNarudzbenice().get(0).getCena());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(0).getUkupnaCena(), narudzbenica.getStavkeNarudzbenice().get(0).getUkupnaCena());
        assertEquals(proizvod1, narudzbenica.getStavkeNarudzbenice().get(0).getProizvod());

        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(1).getBrojNarudzbenice(), narudzbenica.getStavkeNarudzbenice().get(1).getId().getBrojNarudzbenice());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(1).getRb(), narudzbenica.getStavkeNarudzbenice().get(1).getId().getRb());
        assertEquals(narudzbenica, narudzbenica.getStavkeNarudzbenice().get(1).getNarudzbenica());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(1).getKolicina(), narudzbenica.getStavkeNarudzbenice().get(1).getKolicina());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(1).getCena(), narudzbenica.getStavkeNarudzbenice().get(1).getCena());
        assertEquals(narudzbenicaDTO.getStavkeNarudzbenice().get(1).getUkupnaCena(), narudzbenica.getStavkeNarudzbenice().get(1).getUkupnaCena());
        assertEquals(proizvod2, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getProizvod());
    }

    @Test
    public void returnsNullDTOForNullEntity() {
        assertNull(narudzbeniceMapper.toDTO(null));
    }

    @Test
    public void returnsNullEntityForNullDTO() {
        assertNull(narudzbeniceMapper.toEntity(null));
    }
}
