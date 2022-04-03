package com.fon.konstrukcije.microservice.orders.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.mapper.NarudzbenicaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;
import com.fon.konstrukcije.microservice.orders.repository.NarudzbenicaRepository;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NarudzbenicaServiceTest {

    @Mock
    private NarudzbenicaRepository repository;

    @Mock
    private NarudzbenicaMapper mapper;

    @InjectMocks
    private NarudzbeniceService service;


    @Test
    public void savesCorrectly() {
        NarudzbenicaDTO narudzbenicaDTO = mock(NarudzbenicaDTO.class);

        Narudzbenica narudzbenica = mock(Narudzbenica.class);
        Narudzbenica narudzbenicaToReturn = mock(Narudzbenica.class);
        NarudzbenicaDTO narudzbenicaDTOToReturn = mock(NarudzbenicaDTO.class);

        when(mapper.toEntity(narudzbenicaDTO)).thenReturn(narudzbenica);
        when(repository.save(narudzbenica)).thenReturn(narudzbenicaToReturn);
        when(mapper.toDTO(narudzbenicaToReturn)).thenReturn(narudzbenicaDTOToReturn);

        assertEquals(narudzbenicaDTOToReturn, service.save(narudzbenicaDTO));
    }

    @Test
    public void updatesCorrectly() {
        NarudzbenicaDTO narudzbenicaDTO = mock(NarudzbenicaDTO.class);

        Narudzbenica narudzbenica = mock(Narudzbenica.class);
        Narudzbenica narudzbenicaToReturn = mock(Narudzbenica.class);
        NarudzbenicaDTO narudzbenicaDTOToReturn = mock(NarudzbenicaDTO.class);

        when(mapper.toEntity(narudzbenicaDTO)).thenReturn(narudzbenica);
        when(repository.save(narudzbenica)).thenReturn(narudzbenicaToReturn);
        when(mapper.toDTO(narudzbenicaToReturn)).thenReturn(narudzbenicaDTOToReturn);

        assertEquals(narudzbenicaDTOToReturn, service.update(narudzbenicaDTO));
    }

    @Test
    public void findsByIdCorrectly() {
        Integer id = 1;
        Narudzbenica narudzbenica = mock(Narudzbenica.class);
        NarudzbenicaDTO narudzbenicaDTO = mock(NarudzbenicaDTO.class);

        when(mapper.toDTO(narudzbenica)).thenReturn(narudzbenicaDTO);
        when(repository.findById(id)).thenReturn(Optional.of(narudzbenica));

        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = service.findById(id);
        assertTrue(optionalNarudzbenicaDTO.isPresent());
        assertEquals(narudzbenicaDTO, optionalNarudzbenicaDTO.get());
    }

    @Test
    public void returnsEmptyForNonExistentId() {
        Integer id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = service.findById(id);
        assertFalse(optionalNarudzbenicaDTO.isPresent());
    }

    @Test
    public void findPageWhenSearchNullCorrectly() {
        Pageable paging = PageRequest.of(1, 2, Sort.by("sort"));

        Narudzbenica narudzbenica1 = mock(Narudzbenica.class);
        Narudzbenica narudzbenica2 = mock(Narudzbenica.class);
        Narudzbenica narudzbenica3 = mock(Narudzbenica.class);

        NarudzbenicaDTO narudzbenicaDTO1 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO2 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO3 = mock(NarudzbenicaDTO.class);

        when(mapper.toDTO(narudzbenica1)).thenReturn(narudzbenicaDTO1);
        when(mapper.toDTO(narudzbenica2)).thenReturn(narudzbenicaDTO2);
        when(mapper.toDTO(narudzbenica3)).thenReturn(narudzbenicaDTO3);

        List<Narudzbenica> listNarudzbenice = new LinkedList<>();
        listNarudzbenice.add(narudzbenica1);
        listNarudzbenice.add(narudzbenica2);
        listNarudzbenice.add(narudzbenica3);

        when(repository.findAll(paging)).thenReturn(new PageImpl<>(listNarudzbenice));

        Page<NarudzbenicaDTO> pageNarudzbenice = service.findPage(paging, null);

        assertEquals(narudzbenicaDTO1, pageNarudzbenice.getContent().get(0));
        assertEquals(narudzbenicaDTO2, pageNarudzbenice.getContent().get(1));
        assertEquals(narudzbenicaDTO3, pageNarudzbenice.getContent().get(2));
    }

    @Test
    public void findPageWhenSearchEmptyCorrectly() {
        Pageable paging = PageRequest.of(1, 2, Sort.by("sort"));

        Narudzbenica narudzbenica1 = mock(Narudzbenica.class);
        Narudzbenica narudzbenica2 = mock(Narudzbenica.class);
        Narudzbenica narudzbenica3 = mock(Narudzbenica.class);

        NarudzbenicaDTO narudzbenicaDTO1 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO2 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO3 = mock(NarudzbenicaDTO.class);

        when(mapper.toDTO(narudzbenica1)).thenReturn(narudzbenicaDTO1);
        when(mapper.toDTO(narudzbenica2)).thenReturn(narudzbenicaDTO2);
        when(mapper.toDTO(narudzbenica3)).thenReturn(narudzbenicaDTO3);

        List<Narudzbenica> listNarudzbenice = new LinkedList<>();
        listNarudzbenice.add(narudzbenica1);
        listNarudzbenice.add(narudzbenica2);
        listNarudzbenice.add(narudzbenica3);

        when(repository.findAll(paging)).thenReturn(new PageImpl<>(listNarudzbenice));

        Page<NarudzbenicaDTO> pageNarudzbenice = service.findPage(paging, "");

        assertEquals(narudzbenicaDTO1, pageNarudzbenice.getContent().get(0));
        assertEquals(narudzbenicaDTO2, pageNarudzbenice.getContent().get(1));
        assertEquals(narudzbenicaDTO3, pageNarudzbenice.getContent().get(2));
    }

    @Test
    public void findPageWhenSearchNotNullOrEmptyCorrectly() {
        Pageable paging = PageRequest.of(1, 2, Sort.by("sort"));

        Narudzbenica narudzbenica1 = mock(Narudzbenica.class);
        Narudzbenica narudzbenica2 = mock(Narudzbenica.class);
        Narudzbenica narudzbenica3 = mock(Narudzbenica.class);

        NarudzbenicaDTO narudzbenicaDTO1 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO2 = mock(NarudzbenicaDTO.class);
        NarudzbenicaDTO narudzbenicaDTO3 = mock(NarudzbenicaDTO.class);

        when(mapper.toDTO(narudzbenica1)).thenReturn(narudzbenicaDTO1);
        when(mapper.toDTO(narudzbenica2)).thenReturn(narudzbenicaDTO2);
        when(mapper.toDTO(narudzbenica3)).thenReturn(narudzbenicaDTO3);

        List<Narudzbenica> listNarudzbenice = new LinkedList<>();
        listNarudzbenice.add(narudzbenica1);
        listNarudzbenice.add(narudzbenica2);
        listNarudzbenice.add(narudzbenica3);

        when(repository.findAllBySearch(paging, "search")).thenReturn(new PageImpl<>(listNarudzbenice));

        Page<NarudzbenicaDTO> pageNarudzbenice = service.findPage(paging, "search");

        assertEquals(narudzbenicaDTO1, pageNarudzbenice.getContent().get(0));
        assertEquals(narudzbenicaDTO2, pageNarudzbenice.getContent().get(1));
        assertEquals(narudzbenicaDTO3, pageNarudzbenice.getContent().get(2));
    }

}
