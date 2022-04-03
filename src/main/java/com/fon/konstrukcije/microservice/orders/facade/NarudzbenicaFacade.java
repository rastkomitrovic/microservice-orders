package com.fon.konstrukcije.microservice.orders.facade;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbenicaMicroserviceException;
import com.fon.konstrukcije.microservice.orders.service.NarudzbenicaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class NarudzbenicaFacade {

    @Resource
    private NarudzbenicaService service;

    public NarudzbenicaDTO save(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbenicaMicroserviceException {
        if (service.findById(narudzbenicaDTO.getBrojNarudzbenice()).isPresent())
            throw new NarudzbenicaMicroserviceException("Vec postoji narudzbenica sa prosledjenim brojem: " + narudzbenicaDTO.getBrojNarudzbenice());

        narudzbenicaDTO.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTO.setDatumAzuriranja(null);

        calculateTotalSumAndSetSingleItemTotalSum(narudzbenicaDTO);

        return service.save(narudzbenicaDTO);
    }

    public NarudzbenicaDTO update(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbenicaMicroserviceException {
        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = service.findById(narudzbenicaDTO.getBrojNarudzbenice());
        if (!optionalNarudzbenicaDTO.isPresent())
            throw new NarudzbenicaMicroserviceException("Ne postoji narudzbenica sa prosledjenim brojem: " + narudzbenicaDTO.getBrojNarudzbenice());

        NarudzbenicaDTO narudzbenicaDTOFromDb = optionalNarudzbenicaDTO.get();
        narudzbenicaDTO.setDatumKreiranja(narudzbenicaDTOFromDb.getDatumKreiranja());
        narudzbenicaDTO.setDatumAzuriranja(LocalDateTime.now());

        calculateTotalSumAndSetSingleItemTotalSum(narudzbenicaDTO);

        return service.update(narudzbenicaDTO);
    }

    public void calculateTotalSumAndSetSingleItemTotalSum(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbenicaMicroserviceException {
        Double ukupno = 0D;
        List<Integer> set = new LinkedList<>();
        for (int i = 0; i < narudzbenicaDTO.getStavkeNarudzbenice().size(); i++) {
            StavkaNarudzbeniceDTO stavka = narudzbenicaDTO.getStavkeNarudzbenice().get(i);

            if (set.contains(stavka.getProizvod().getId()))
                throw new NarudzbenicaMicroserviceException("Prosledjeni proizvod u stavici se nalazi u vise stavki narudzbenice. Proizvod Id: " + stavka.getProizvod().getId());
            set.add(stavka.getProizvod().getId());

            stavka.setBrojNarudzbenice(narudzbenicaDTO.getBrojNarudzbenice());
            stavka.setRb(i + 1);
            stavka.setUkupnaCena(stavka.getCena() * stavka.getKolicina());
            ukupno += stavka.getUkupnaCena();
        }
        narudzbenicaDTO.setUkupno(ukupno);
    }


    public Optional<NarudzbenicaDTO> findById(Integer id) throws NarudzbenicaMicroserviceException {
        return service.findById(id);
    }


    public Page<NarudzbenicaDTO> findPage(Integer page, Integer size, String sortBy, String search) throws NarudzbenicaMicroserviceException {
        return service.findPage(PageRequest.of(page, size, Sort.by(sortBy)), search);
    }
}
