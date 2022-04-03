package com.fon.konstrukcije.microservice.orders.facade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.*;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import com.fon.konstrukcije.microservice.orders.service.NarudzbeniceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;


@ExtendWith(MockitoExtension.class)
public class NarudzbenicaFacadeTest {

    @Mock
    private NarudzbeniceService service;

    @InjectMocks
    private NarudzbeniceFacade facade;

    @Test
    public void savesCorrectly() throws NarudzbeniceMicroserviceException {
        KlijentDTO klijentDTO = new KlijentDTO();

        ProizvodDTO proizvodDTO1 = new ProizvodDTO(1, "Proizvod1", "Tip1", JedinicaMere.KOMAD);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO1 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO1);

        ProizvodDTO proizvodDTO2 = new ProizvodDTO(2, "Proizvod2", "Tip2", JedinicaMere.LITAR);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO2 = new StavkaNarudzbeniceDTO(3, 2, 3, 100D, 1000D, proizvodDTO2);

        ProizvodDTO proizvodDTO3 = new ProizvodDTO(3, "Proizvod2", "Tip2", JedinicaMere.LITAR);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO3 = new StavkaNarudzbeniceDTO(4, 2, 4, 200D, 1000D, proizvodDTO3);

        List<StavkaNarudzbeniceDTO> stavkeNarudzbenice = new LinkedList<>();
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO1);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO2);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO3);

        LocalDateTime datumKreiranja = LocalDateTime.now();
        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(1, klijentDTO, datumKreiranja, LocalDateTime.now(), 1000D, stavkeNarudzbenice);
        NarudzbenicaDTO narudzbenicaDTOToReturn = mock(NarudzbenicaDTO.class);

        when(service.findById(narudzbenicaDTO.getBrojNarudzbenice())).thenReturn(Optional.empty());
        when(service.save(narudzbenicaDTO)).thenReturn(narudzbenicaDTOToReturn);

        NarudzbenicaDTO returnedNarudzbenicaDTO = facade.save(narudzbenicaDTO);

        assertNotEquals(datumKreiranja, narudzbenicaDTO.getDatumKreiranja());
        assertNull(narudzbenicaDTO.getDatumAzuriranja());
        assertEquals(1300D, narudzbenicaDTO.getUkupno());

        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getBrojNarudzbenice());
        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getRb());
        assertEquals(2, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getKolicina());
        assertEquals(100D, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getCena());
        assertEquals(200D, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getUkupnaCena());
        assertEquals(proizvodDTO1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getProizvod());

        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getBrojNarudzbenice());
        assertEquals(2, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getRb());
        assertEquals(3, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getKolicina());
        assertEquals(100D, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getCena());
        assertEquals(300D, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getUkupnaCena());
        assertEquals(proizvodDTO2, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getProizvod());

        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getBrojNarudzbenice());
        assertEquals(3, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getRb());
        assertEquals(4, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getKolicina());
        assertEquals(200D, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getCena());
        assertEquals(800D, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getUkupnaCena());
        assertEquals(proizvodDTO3, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getProizvod());

        assertEquals(narudzbenicaDTOToReturn, returnedNarudzbenicaDTO);
    }

    @Test
    public void saveThrowsNarudzbeniceMicroserviceExceptionIfIdExists() {
        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(1, null, null, null, null, null);
        NarudzbenicaDTO narudzbenicaDTOToReturn = mock(NarudzbenicaDTO.class);

        when(service.findById(narudzbenicaDTO.getBrojNarudzbenice())).thenReturn(Optional.of(narudzbenicaDTOToReturn));

        assertThrowsExactly(NarudzbeniceMicroserviceException.class, () -> facade.save(narudzbenicaDTO), "Vec postoji narudzbenica sa prosledjenim brojem: 1");
    }

    @Test
    public void saveThrowsNarudzbeniceMicroserviceExceptionIfTwoStavkeWithSameProizvod() {
        KlijentDTO klijentDTO = new KlijentDTO();

        ProizvodDTO proizvodDTO1 = new ProizvodDTO(1, "Proizvod1", "Tip1", JedinicaMere.KOMAD);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO1 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO1);

        ProizvodDTO proizvodDTO2 = new ProizvodDTO(2, "Proizvod2", "Tip2", JedinicaMere.LITAR);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO2 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO2);

        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO3 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO1);

        List<StavkaNarudzbeniceDTO> stavkeNarudzbenice = new LinkedList<>();
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO1);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO2);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO3);

        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(1, klijentDTO, LocalDateTime.now(), LocalDateTime.now(), 1000D, stavkeNarudzbenice);

        when(service.findById(narudzbenicaDTO.getBrojNarudzbenice())).thenReturn(Optional.empty());

        assertThrowsExactly(NarudzbeniceMicroserviceException.class, () -> facade.save(narudzbenicaDTO), "Prosledjeni proizvod u stavici se nalazi u vise stavki narudzbenice. Proizvod Id: 1");
    }

    @Test
    public void updatesCorrectly() throws NarudzbeniceMicroserviceException {
        KlijentDTO klijentDTO = new KlijentDTO();

        ProizvodDTO proizvodDTO1 = new ProizvodDTO(1, "Proizvod1", "Tip1", JedinicaMere.KOMAD);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO1 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO1);

        ProizvodDTO proizvodDTO2 = new ProizvodDTO(2, "Proizvod2", "Tip2", JedinicaMere.LITAR);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO2 = new StavkaNarudzbeniceDTO(3, 2, 3, 100D, 1000D, proizvodDTO2);

        ProizvodDTO proizvodDTO3 = new ProizvodDTO(3, "Proizvod2", "Tip2", JedinicaMere.LITAR);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO3 = new StavkaNarudzbeniceDTO(4, 2, 4, 200D, 1000D, proizvodDTO3);

        List<StavkaNarudzbeniceDTO> stavkeNarudzbenice = new LinkedList<>();
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO1);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO2);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO3);

        LocalDateTime datumAzuriranja = LocalDateTime.now();
        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(1, klijentDTO, LocalDateTime.now(), datumAzuriranja, 1000D, stavkeNarudzbenice);
        NarudzbenicaDTO narudzbenicaDTOFromDb = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTOToReturn = mock(NarudzbenicaDTO.class);

        LocalDateTime datumKreiranjaFromDb = LocalDateTime.now();
        when(narudzbenicaDTOFromDb.getDatumKreiranja()).thenReturn(datumKreiranjaFromDb);
        when(service.findById(narudzbenicaDTO.getBrojNarudzbenice())).thenReturn(Optional.of(narudzbenicaDTOFromDb));
        when(service.update(narudzbenicaDTO)).thenReturn(narudzbenicaDTOToReturn);

        NarudzbenicaDTO returnedNarudzbenicaDTO = facade.update(narudzbenicaDTO);

        assertEquals(datumKreiranjaFromDb, narudzbenicaDTO.getDatumKreiranja());
        assertNotEquals(datumAzuriranja, narudzbenicaDTO.getDatumAzuriranja());
        assertEquals(1300D, narudzbenicaDTO.getUkupno());

        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getBrojNarudzbenice());
        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getRb());
        assertEquals(2, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getKolicina());
        assertEquals(100D, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getCena());
        assertEquals(200D, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getUkupnaCena());
        assertEquals(proizvodDTO1, narudzbenicaDTO.getStavkeNarudzbenice().get(0).getProizvod());

        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getBrojNarudzbenice());
        assertEquals(2, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getRb());
        assertEquals(3, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getKolicina());
        assertEquals(100D, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getCena());
        assertEquals(300D, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getUkupnaCena());
        assertEquals(proizvodDTO2, narudzbenicaDTO.getStavkeNarudzbenice().get(1).getProizvod());

        assertEquals(1, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getBrojNarudzbenice());
        assertEquals(3, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getRb());
        assertEquals(4, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getKolicina());
        assertEquals(200D, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getCena());
        assertEquals(800D, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getUkupnaCena());
        assertEquals(proizvodDTO3, narudzbenicaDTO.getStavkeNarudzbenice().get(2).getProizvod());

        assertEquals(narudzbenicaDTOToReturn, returnedNarudzbenicaDTO);
    }

    @Test
    public void updateThrowsNarudzbeniceMicroserviceExceptionIfIdDoesntExist() {
        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(1, null, null, null, null, null);

        when(service.findById(narudzbenicaDTO.getBrojNarudzbenice())).thenReturn(Optional.empty());

        assertThrowsExactly(NarudzbeniceMicroserviceException.class, () -> facade.update(narudzbenicaDTO), "Ne postoji narudzbenica sa prosledjenim brojem: 1");
    }

    @Test
    public void updateThrowsNarudzbeniceMicroserviceExceptionIfTwoStavkeWithSameProizvod() {
        KlijentDTO klijentDTO = new KlijentDTO();

        ProizvodDTO proizvodDTO1 = new ProizvodDTO(1, "Proizvod1", "Tip1", JedinicaMere.KOMAD);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO1 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO1);

        ProizvodDTO proizvodDTO2 = new ProizvodDTO(2, "Proizvod2", "Tip2", JedinicaMere.LITAR);
        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO2 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO2);

        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO3 = new StavkaNarudzbeniceDTO(2, 2, 2, 100D, 1000D, proizvodDTO1);

        List<StavkaNarudzbeniceDTO> stavkeNarudzbenice = new LinkedList<>();
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO1);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO2);
        stavkeNarudzbenice.add(stavkaNarudzbeniceDTO3);

        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO(1, klijentDTO, LocalDateTime.now(), LocalDateTime.now(), 1000D, stavkeNarudzbenice);

        when(service.findById(narudzbenicaDTO.getBrojNarudzbenice())).thenReturn(Optional.empty());

        assertThrowsExactly(NarudzbeniceMicroserviceException.class, () -> facade.update(narudzbenicaDTO), "Prosledjeni proizvod u stavici se nalazi u vise stavki narudzbenice. Proizvod Id: 1");
    }

    @Test
    public void findsByIdCorrectly() throws NarudzbeniceMicroserviceException {
        Integer id = 1;

        NarudzbenicaDTO narudzbenicaDTO = mock(NarudzbenicaDTO.class);

        when(service.findById(id)).thenReturn(Optional.of(narudzbenicaDTO));

        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = facade.findById(id);

        assertTrue(optionalNarudzbenicaDTO.isPresent());
        assertEquals(narudzbenicaDTO, optionalNarudzbenicaDTO.get());
    }

    @Test
    public void returnsEmptyForNonExistentId() throws NarudzbeniceMicroserviceException {
        Integer id = 1;

        when(service.findById(id)).thenReturn(Optional.empty());

        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = facade.findById(id);

        assertFalse(optionalNarudzbenicaDTO.isPresent());
    }


    @Test
    public void findsPageWithNullSearchCorrectly() throws NarudzbeniceMicroserviceException {
        Integer page = 0;
        Integer size = 2;
        String sortBy = "sort";
        String search = null;
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        NarudzbenicaDTO narudzbenicaDTO1 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO2 = mock(NarudzbenicaDTO.class);

        List<NarudzbenicaDTO> listaNarudzbenica = new LinkedList<>();
        listaNarudzbenica.add(narudzbenicaDTO1);
        listaNarudzbenica.add(narudzbenicaDTO2);

        Page<NarudzbenicaDTO> pageToReturn = new PageImpl<>(listaNarudzbenica);

        when(service.findPage(paging, search)).thenReturn(pageToReturn);

        Page<NarudzbenicaDTO> returnedPage = facade.findPage(page, size, sortBy, search);

        assertEquals(pageToReturn, returnedPage);
    }

    @Test
    public void findsPageWithEmptySearchCorrectly() throws NarudzbeniceMicroserviceException {
        Integer page = 0;
        Integer size = 2;
        String sortBy = "sort";
        String search = "";
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        NarudzbenicaDTO narudzbenicaDTO1 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO2 = mock(NarudzbenicaDTO.class);

        List<NarudzbenicaDTO> listaNarudzbenica = new LinkedList<>();
        listaNarudzbenica.add(narudzbenicaDTO1);
        listaNarudzbenica.add(narudzbenicaDTO2);

        Page<NarudzbenicaDTO> pageToReturn = new PageImpl<>(listaNarudzbenica);

        when(service.findPage(paging, search)).thenReturn(pageToReturn);

        Page<NarudzbenicaDTO> returnedPage = facade.findPage(page, size, sortBy, search);

        assertEquals(pageToReturn, returnedPage);
    }

    @Test
    public void findsPageWithNotNullOrEmptySearchCorrectly() throws NarudzbeniceMicroserviceException {
        Integer page = 0;
        Integer size = 2;
        String sortBy = "sort";
        String search = "search";
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        NarudzbenicaDTO narudzbenicaDTO1 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO2 = mock(NarudzbenicaDTO.class);

        List<NarudzbenicaDTO> listaNarudzbenica = new LinkedList<>();
        listaNarudzbenica.add(narudzbenicaDTO1);
        listaNarudzbenica.add(narudzbenicaDTO2);

        Page<NarudzbenicaDTO> pageToReturn = new PageImpl<>(listaNarudzbenica);

        when(service.findPage(paging, search)).thenReturn(pageToReturn);

        Page<NarudzbenicaDTO> returnedPage = facade.findPage(page, size, sortBy, search);

        assertEquals(pageToReturn, returnedPage);
    }
}
