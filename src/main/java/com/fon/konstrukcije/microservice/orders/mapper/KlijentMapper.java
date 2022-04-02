package com.fon.konstrukcije.microservice.orders.mapper;

import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;

@Service
public class KlijentMapper {

    public KlijentDTO toDTO(Klijent klijent) {
        if (klijent == null)
            return null;

        KlijentDTO klijentDTO = new KlijentDTO();

        klijentDTO.setId(klijent.getId());
        klijentDTO.setAdresa(klijent.getAdresa());
        klijentDTO.setBrojTelefona(klijent.getBrojTelefona());
        klijentDTO.setEmail(klijent.getEmail());
        klijentDTO.setIme(klijent.getIme());
        klijentDTO.setPrezime(klijent.getPrezime());

        return klijentDTO;
    }

    public Klijent toEntity(KlijentDTO klijentDTO) {
        if (klijentDTO == null)
            return null;

        Klijent klijent = new Klijent();

        klijent.setId(klijentDTO.getId());
        klijent.setAdresa(klijentDTO.getAdresa());
        klijent.setBrojTelefona(klijentDTO.getBrojTelefona());
        klijent.setEmail(klijentDTO.getEmail());
        klijent.setIme(klijentDTO.getIme());
        klijent.setPrezime(klijentDTO.getPrezime());

        return klijent;
    }

}
