package com.fon.konstrukcije.microservice.orders.mapper;

import java.util.stream.Collectors;
import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;
import com.fon.konstrukcije.microservice.orders.entity.StavkaNarudzbenice;
import com.fon.konstrukcije.microservice.orders.entity.embedded.StavkaNarudzbeniceEmbeddedId;

@Service
public class NarudzbenicaMapper {

    @Resource
    private KlijentMapper klijentMapper;
    @Resource
    private ProizvodMapper proizvodMapper;


    public NarudzbenicaDTO toDTO(Narudzbenica narudzbenica) {
        if (narudzbenica == null)
            return null;

        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO();

        narudzbenicaDTO.setBrojNarudzbenice(narudzbenica.getBrojNarudzbenice());
        narudzbenicaDTO.setDatumAzuriranja(narudzbenica.getDatumAzuriranja());
        narudzbenicaDTO.setDatumKreiranja(narudzbenica.getDatumKreiranja());
        narudzbenicaDTO.setUkupno(narudzbenica.getUkupno());
        if (Hibernate.isInitialized(narudzbenica.getStavkeNarudzbenice()))
            narudzbenicaDTO.setStavkeNarudzbenice(narudzbenica
                    .getStavkeNarudzbenice()
                    .stream()
                    .map(this::toDTOStavka)
                    .collect(Collectors.toList()));
        narudzbenicaDTO.setKlijent(klijentMapper.toDTO(narudzbenica.getKlijent()));

        return narudzbenicaDTO;
    }

    private StavkaNarudzbeniceDTO toDTOStavka(StavkaNarudzbenice stavkaNarudzbenice) {
        if (stavkaNarudzbenice == null)
            return null;

        StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO = new StavkaNarudzbeniceDTO();

        stavkaNarudzbeniceDTO.setBrojNarudzbenice(stavkaNarudzbenice.getId().getBrojNarudzbenice());
        stavkaNarudzbeniceDTO.setCena(stavkaNarudzbenice.getCena());
        stavkaNarudzbeniceDTO.setKolicina(stavkaNarudzbenice.getKolicina());
        if (Hibernate.isInitialized(stavkaNarudzbenice.getProizvod()))
            stavkaNarudzbeniceDTO.setProizvod(proizvodMapper.toDTO(stavkaNarudzbenice.getProizvod()));
        stavkaNarudzbeniceDTO.setRb(stavkaNarudzbenice.getId().getRb());
        stavkaNarudzbeniceDTO.setUkupnaCena(stavkaNarudzbenice.getUkupnaCena());

        return stavkaNarudzbeniceDTO;
    }

    public Narudzbenica toEntity(NarudzbenicaDTO narudzbenicaDTO) {
        if (narudzbenicaDTO == null)
            return null;

        Narudzbenica narudzbenica = new Narudzbenica();

        narudzbenica.setBrojNarudzbenice(narudzbenicaDTO.getBrojNarudzbenice());
        narudzbenica.setDatumAzuriranja(narudzbenicaDTO.getDatumAzuriranja());
        narudzbenica.setDatumKreiranja(narudzbenicaDTO.getDatumKreiranja());
        narudzbenica.setUkupno(narudzbenicaDTO.getUkupno());
        narudzbenica.setKlijent(klijentMapper.toEntity(narudzbenicaDTO.getKlijent()));
        narudzbenica.setStavkeNarudzbenice(narudzbenicaDTO
                .getStavkeNarudzbenice()
                .stream()
                .map(it -> toEntityStavka(it, narudzbenica))
                .collect(Collectors.toList()));
        narudzbenica.setUkupno(narudzbenicaDTO.getUkupno());

        return narudzbenica;
    }


    private StavkaNarudzbenice toEntityStavka(StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO, Narudzbenica narudzbenica) {
        if (stavkaNarudzbeniceDTO == null)
            return null;

        StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
        StavkaNarudzbeniceEmbeddedId embeddedId = new StavkaNarudzbeniceEmbeddedId();

        embeddedId.setBrojNarudzbenice(narudzbenica.getBrojNarudzbenice());
        embeddedId.setRb(stavkaNarudzbeniceDTO.getRb());

        stavkaNarudzbenice.setCena(stavkaNarudzbeniceDTO.getCena());
        stavkaNarudzbenice.setId(embeddedId);
        stavkaNarudzbenice.setNarudzbenica(narudzbenica);
        stavkaNarudzbenice.setKolicina(stavkaNarudzbeniceDTO.getKolicina());
        stavkaNarudzbenice.setProizvod(proizvodMapper.toEntity(stavkaNarudzbeniceDTO.getProizvod()));
        stavkaNarudzbenice.setUkupnaCena(stavkaNarudzbeniceDTO.getUkupnaCena());

        return stavkaNarudzbenice;
    }

}
