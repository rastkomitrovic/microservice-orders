package com.fon.konstrukcije.microservice.orders.facade;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.dto.StavkaNarudzbeniceDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import com.fon.konstrukcije.microservice.orders.service.NarudzbeniceService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NarudzbeniceFacade {

    @Resource
    private NarudzbeniceService service;

    public NarudzbenicaDTO save(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        if (service.findById(narudzbenicaDTO.getBrojNarudzbenice()).isPresent())
            throw new NarudzbeniceMicroserviceException("Vec postoji narudzbenica sa unetim Id-om");

        narudzbenicaDTO.setDatumKreiranja(LocalDateTime.now());
        narudzbenicaDTO.setDatumAzuriranja(null);

        calculateTotalSumAndSetSingleItemTotalSum(narudzbenicaDTO);

        return service.save(narudzbenicaDTO);
    }

    public NarudzbenicaDTO update(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        Optional<NarudzbenicaDTO> optionalNarudzbenicaDTO = service.findById(narudzbenicaDTO.getBrojNarudzbenice());
        if (!optionalNarudzbenicaDTO.isPresent())
            throw new NarudzbeniceMicroserviceException("Ne postoji narudzbenica sa prosledjenim id-om");

        NarudzbenicaDTO narudzbenicaDTOFromDb = optionalNarudzbenicaDTO.get();
        narudzbenicaDTO.setDatumKreiranja(narudzbenicaDTOFromDb.getDatumKreiranja());
        narudzbenicaDTO.setDatumAzuriranja(LocalDateTime.now());
        narudzbenicaDTO.getStavkeNarudzbenice().forEach(it -> it.setUkupnaCena(it.getCena() * it.getKolicina()));

        calculateTotalSumAndSetSingleItemTotalSum(narudzbenicaDTO);

        return service.update(narudzbenicaDTO);
    }

    public void calculateTotalSumAndSetSingleItemTotalSum(NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException{
        Double ukupno = 0D;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < narudzbenicaDTO.getStavkeNarudzbenice().size(); i++) {
            StavkaNarudzbeniceDTO stavka = narudzbenicaDTO.getStavkeNarudzbenice().get(i);

            if(set.contains(stavka.getProizvodDTO().getId()))
                throw new NarudzbeniceMicroserviceException("Jedan proizvod se moze naci samo uokviru jedne stavke proizvoda");
            set.add(stavka.getProizvodDTO().getId());

            stavka.setBrojNarudzbenice(narudzbenicaDTO.getBrojNarudzbenice());
            stavka.setRb(i + 1);
            stavka.setUkupnaCena(stavka.getCena() * stavka.getKolicina());
            ukupno += stavka.getUkupnaCena();
        }
        narudzbenicaDTO.setUkupno(ukupno);
    }


    public Optional<NarudzbenicaDTO> findById(Integer id) {
        return service.findById(id);
    }


    public Page<NarudzbenicaDTO> findPage(Integer page, Integer size, String sortBy, String serach) {

        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        return service.findPage(paging, serach);
    }
}
