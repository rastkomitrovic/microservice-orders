package com.fon.konstrukcije.microservice.orders.rest;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import com.fon.konstrukcije.microservice.orders.facade.NarudzbeniceFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v0/narudzbenice")
public class NarudzbeniceRestController {

    @Resource
    private NarudzbeniceFacade facade;

    @PostMapping
    public ResponseEntity<NarudzbenicaDTO> save(@RequestBody @Valid NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.save(narudzbenicaDTO));
    }

    @PutMapping
    public ResponseEntity<NarudzbenicaDTO> update(@RequestBody @Valid NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.update(narudzbenicaDTO));
    }
}
