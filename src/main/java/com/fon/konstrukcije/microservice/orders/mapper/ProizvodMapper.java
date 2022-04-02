package com.fon.konstrukcije.microservice.orders.mapper;

import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;

@Service
public class ProizvodMapper {

    public ProizvodDTO toDTO(Proizvod proizvod) {
        if (proizvod == null)
            return null;
        ProizvodDTO proizvodDTO = new ProizvodDTO();

        proizvodDTO.setId(proizvod.getId());
        proizvodDTO.setJedinica(proizvod.getJedinica());
        proizvodDTO.setNaziv(proizvod.getNaziv());
        proizvodDTO.setTipProizvoda(proizvod.getTipProizvoda());

        return proizvodDTO;
    }

    public Proizvod toEntity(ProizvodDTO proizvodDTO) {
        if (proizvodDTO == null)
            return null;

        Proizvod proizvod = new Proizvod();

        proizvod.setId(proizvodDTO.getId());
        proizvod.setJedinica(proizvodDTO.getJedinica());
        proizvod.setNaziv(proizvodDTO.getNaziv());
        proizvod.setTipProizvoda(proizvodDTO.getTipProizvoda());

        return proizvod;
    }

}
