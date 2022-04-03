package com.fon.konstrukcije.microservice.orders.service;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.mapper.NarudzbenicaMapper;
import com.fon.konstrukcije.microservice.orders.repository.NarudzbenicaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class NarudzbeniceService {

    @Resource
    private NarudzbenicaRepository repository;

    @Resource
    private NarudzbenicaMapper mapper;


    public NarudzbenicaDTO save(NarudzbenicaDTO narudzbenicaDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(narudzbenicaDTO)));
    }

    public NarudzbenicaDTO update(NarudzbenicaDTO narudzbenicaDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(narudzbenicaDTO)));
    }

    public Optional<NarudzbenicaDTO> findById(Integer id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public Page<NarudzbenicaDTO> findPage(Pageable paging, String search) {
        if (search == null || search.isEmpty()) {

            return repository.findAll(paging).map(mapper::toDTO);
        } else {

            return repository.findAllBySearch(paging, search).map(mapper::toDTO);
        }
    }

}
