package com.fon.konstrukcije.microservice.orders.mapper;

import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;

@Service
public class ProizvodMapper {

    public ProizvodDTO toDTO(Proizvod proizvod) {
        ProizvodDTO proizvodDTO = new ProizvodDTO();

        proizvodDTO.setId(proizvod.getId());
        proizvodDTO.setJedinica(proizvod.getJedinica());
        proizvodDTO.setNaziv(proizvod.getNaziv());
        proizvodDTO.setTipProizvoda(proizvod.getTipProizvoda());

        return proizvodDTO;
    }

    public Proizvod toEntity(ProizvodDTO dto) {
        Proizvod proizvod = new Proizvod();

        proizvod.setId(dto.getId());
        proizvod.setJedinica(dto.getJedinica());
        proizvod.setNaziv(dto.getNaziv());
        proizvod.setTipProizvoda(dto.getTipProizvoda());

        return proizvod;
    }

}
