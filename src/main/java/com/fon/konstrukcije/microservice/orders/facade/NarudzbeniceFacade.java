package com.fon.konstrukcije.microservice.orders.facade;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import com.fon.konstrukcije.microservice.orders.service.NarudzbeniceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NarudzbeniceFacade {

    @Resource
    private NarudzbeniceService service;

    public NarudzbenicaDTO save(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        if (service.findById(narudzbenicaDTO.getBrojNarudzbenice()).isPresent())
            throw new NarudzbeniceMicroserviceException("Vec postoji narudzbenica sa unetim Id-om");
        narudzbenicaDTO.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTO.setDatumAzuriranja(null);
        for (int i = 0; i < narudzbenicaDTO.getStavkeNarudzbenice().size(); i++) {
            StavkaNarudzbeniceDTO stavka = narudzbenicaDTO.getStavkeNarudzbenice().get(i);
            stavka.setRb(i + 1);
            stavka.setUkupnaCena(stavka.getCena() * stavka.getKolicina());
        }
        return service.save(narudzbenicaDTO);
    }

    public NarudzbenicaDTO update(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = service.findById(narudzbenicaDTO.getBrojNarudzbenice());
        if (!optionalNarudzbenicaDTO.isPresent())
            throw new NarudzbeniceMicroserviceException("Ne postoji narudzbenica sa prosledjenim id-om");
        narudzbenicaDTO.setDatumKreiranja(null);
        narudzbenicaDTO.setDatumAzuriranja(LocalDateTime.now());
        narudzbenicaDTO.getStavkeNarudzbenice().forEach(it -> it.setUkupnaCena(it.getCena() * it.getKolicina()));
        for (int i = 0; i < narudzbenicaDTO.getStavkeNarudzbenice().size(); i++) {
            StavkaNarudzbeniceDTO stavka = narudzbenicaDTO.getStavkeNarudzbenice().get(i);
            stavka.setRb(i + 1);
            stavka.setUkupnaCena(stavka.getCena() * stavka.getKolicina());
        }
        return service.update(narudzbenicaDTO);
    }
}
