package com.fon.konstrukcije.microservice.orders.mapper;

import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;

@Service
public class KlijentMapper {

    public KlijentDTO toDTO(Klijent klijent) {
        KlijentDTO klijentDTO = new KlijentDTO();

        klijentDTO.setId(klijent.getId());
        klijentDTO.setAdresa(klijent.getAdresa());
        klijentDTO.setBrojTelefona(klijent.getBrojTelefona());
        klijentDTO.setEmail(klijent.getEmail());
        klijentDTO.setIme(klijent.getIme());
        klijentDTO.setPrezime(klijent.getPrezime());

        return klijentDTO;
    }

    public Klijent toEntity(KlijentDTO dto) {
        Klijent klijent = new Klijent();

        klijent.setId(dto.getId());
        klijent.setAdresa(dto.getAdresa());
        klijent.setBrojTelefona(dto.getBrojTelefona());
        klijent.setEmail(dto.getEmail());
        klijent.setIme(dto.getIme());
        klijent.setPrezime(dto.getPrezime());

        return klijent;
    }

}
