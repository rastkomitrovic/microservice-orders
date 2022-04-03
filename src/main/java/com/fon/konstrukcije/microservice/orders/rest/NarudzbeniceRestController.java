package com.fon.konstrukcije.microservice.orders.rest;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import com.fon.konstrukcije.microservice.orders.facade.NarudzbeniceFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v0/narudzbenice")
public class NarudzbeniceRestController {

    @Resource
    private NarudzbeniceFacade facade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Sacuvava novu narudzbenicu", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno sacuvana narudzbenica"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen", content = @Content),
            @ApiResponse(responseCode = "500", description = "Greska na serveru", content = @Content)
    })
    public ResponseEntity<NarudzbenicaDTO> save(@RequestBody @Valid NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.save(narudzbenicaDTO));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Izmenjuje postojecu narudzbenicu", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno izmenjena narudzbenica"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen", content = @Content),
            @ApiResponse(responseCode = "500", description = "Greska na serveru", content = @Content)
    })
    public ResponseEntity<NarudzbenicaDTO> update(@RequestBody @Valid NarudzbenicaDTO narudzbenicaDTO) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.update(narudzbenicaDTO));
    }

    @GetMapping(value = "/{brojNarudzbenice:^[1-9]+[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vraca narudzbenicu sa prosledjenim brojem narudzbenice", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno prosledjena"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen"),
            @ApiResponse(responseCode = "500", description = "Greska na serveru")
    },
            parameters = {
                    @Parameter(name = "brojNarudzbenice", required = true, description = "Broj narudzbenice za pronaci", schema = @Schema(implementation = Integer.class), example = "1")
            })
    public ResponseEntity<Optional<NarudzbenicaDTO>> findById(@PathVariable("brojNarudzbenice") Integer brojNarudzbenice) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.findById(brojNarudzbenice));
    }

    @GetMapping(value = "/{page:^0|[1-9]+[0-9]*$}/{size:^[1-9]+[0-9]*$}/{sortBy:brojNarudzbenice|klijent|datumKreiranja|datumAzuriranja|ukupno}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vraca narudzbenice po paginaciji po prosledjenom parametru", responses = {
            @ApiResponse(responseCode = "200", description = "Uspesno prosledjena"),
            @ApiResponse(responseCode = "400", description = "Los zahtev je prosledjen"),
            @ApiResponse(responseCode = "500", description = "Greska na serveru")
    },
            parameters = {
                    @Parameter(name = "page", required = true, description = "Redni broj stranice (krece se od 0 pa na dalje)", schema = @Schema(implementation = Integer.class), example = "0"),
                    @Parameter(name = "size", required = true, description = "Broj elemenata po stranici (krece se od 1 pa na vise)", schema = @Schema(implementation = Integer.class), example = "2"),
                    @Parameter(name = "sortBy", required = true, description = "Polje po kom se sortira", schema = @Schema(implementation = String.class), examples = {
                            @ExampleObject(name = "Broj narudzbenice", value = "brojNarudzbenice", description = "Sortiranje po broju narudzbenice u rastucem redosledu"),
                            @ExampleObject(name = "Klijent", value = "klijent", description = "Sortiranje po ID-u klijenta u rastucem redosledu"),
                            @ExampleObject(name = "Datum kreiranja", value = "datumKreiranja", description = "Sortiranje po datumu kreiranja narudzbine u rastucem redosledu"),
                            @ExampleObject(name = "Datum azuriranja", value = "datumAzuriranja", description = "Sortiranje po datumu azuriranja porudzbine u rastucem redsoledu"),
                            @ExampleObject(name = "Ukupno", value = "ukupno", description = "Sortiranje po ukupnoj sumi porudzbine u rastucem redosledu"),
                    }),
                    @Parameter(name = "search", required = false, description = "Parametar po kom se pretrazuje", schema = @Schema(implementation = String.class), example = "Ivan Cukic")
            })
    public ResponseEntity<Page<NarudzbenicaDTO>> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("sortBy") String sortBy, @RequestParam(required = false, defaultValue = "") String search) throws NarudzbeniceMicroserviceException {
        return ResponseEntity.ok(facade.findPage(page, size, sortBy, search));
    }
}
