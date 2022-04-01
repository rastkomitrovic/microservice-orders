package com.fon.konstrukcije.microservice.orders.rest;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import com.fon.konstrukcije.microservice.orders.facade.NarudzbeniceFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v0/narudzbenice")
public class NarudzbeniceRestController {

    @Resource
    private NarudzbeniceFacade facade;

    @PostMapping
    @Operation(summary = "Sacuvava novu narudzbenicu", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno sacuvana narudzbenica"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen"),
            @ApiResponse(responseCode = "500", description = "Greska na serveru")
    })
    public ResponseEntity<NarudzbenicaDTO> save(@RequestBody @Valid NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.save(narudzbenicaDTO));
    }

    @PutMapping
    @Operation(summary = "Izmenjuje postojecu narudzbenicu", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno izmenjena narudzbenica"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen"),
            @ApiResponse(responseCode = "500", description = "Greska na serveru")
    })
    public ResponseEntity<NarudzbenicaDTO> update(@RequestBody @Valid NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.update(narudzbenicaDTO));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Vraca narudzbenicu sa prosledjenim brojem narudzbenice", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno prosledjena"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen"),
            @ApiResponse(responseCode = "500", description = "Greska na serveru")
    })
    public ResponseEntity<Optional<NarudzbenicaDTO>> findById(@PathVariable Integer id) {
    	return ResponseEntity.ok(facade.findById(id));
    }
    
    @GetMapping("/{page}/{size}/{sort}")
    @Operation(summary = "Vraca narudzbenice po paginaciji po prosledjenom parametru", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno prosledjena"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen"),
            @ApiResponse(responseCode = "500", description = "Greska na serveru")
    })
    public ResponseEntity<Page<NarudzbenicaDTO>> findPage(@PathVariable Integer page, Integer size, String sort, @RequestBody String search) {
    	return ResponseEntity.ok(facade.findPage(page, size, sort, search));
    }
}
