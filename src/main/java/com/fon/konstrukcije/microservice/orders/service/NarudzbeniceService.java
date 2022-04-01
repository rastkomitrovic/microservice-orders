package com.fon.konstrukcije.microservice.orders.service;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.mapper.NarudzbeniceMapper;
import com.fon.konstrukcije.microservice.orders.repository.NarudzbeniceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class NarudzbeniceService {

    @Resource
    private NarudzbeniceRepository repository;

    @Resource
    private NarudzbeniceMapper mapper;


    public NarudzbenicaDTO save(NarudzbenicaDTO narudzbenicaDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(narudzbenicaDTO)));
    }

    public NarudzbenicaDTO update(NarudzbenicaDTO narudzbenicaDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(narudzbenicaDTO)));
    }

    public Optional<NarudzbenicaDTO> findById(Integer id) {
        return repository.findById(id).map(mapper::toDTO);
    }

}
