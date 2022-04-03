package com.fon.konstrukcije.microservice.orders.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import com.fon.konstrukcije.microservice.orders.dto.NarudzbenicaDTO;
import com.fon.konstrukcije.microservice.orders.exception.NarudzbenicaMicroserviceException;
import com.fon.konstrukcije.microservice.orders.facade.NarudzbenicaFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NarudzbenicaRestController.class)
public class NarudzbenicaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NarudzbenicaFacade facade;


    @Test
    public void savesCorrectly() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 0,\n" +
                "    \"klijent\": {\n" +
                "        \"id\": 1,\n" +
                "        \"ime\": \"Ivan\",\n" +
                "        \"prezime\": \"Cukic\",\n" +
                "        \"email\": \"ivancukic@gmail.com\",\n" +
                "        \"brojTelefona\": \"+381631234567\",\n" +
                "        \"adresa\": \"Brace Baruha 25\"\n" +
                "    },\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 1,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO();
        given(facade.save(any())).willReturn(narudzbenicaDTO);

        mockMvc.perform(
                post("/api/v0/narudzbenice")
                        .content(jsonNarudzbenicaDTO)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void saveThrowsNarudzbeniceMicroserviceExceptionCorrectly() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 0,\n" +
                "    \"klijent\": {\n" +
                "        \"id\": 1,\n" +
                "        \"ime\": \"Ivan\",\n" +
                "        \"prezime\": \"Cukic\",\n" +
                "        \"email\": \"ivancukic@gmail.com\",\n" +
                "        \"brojTelefona\": \"+381631234567\",\n" +
                "        \"adresa\": \"Brace Baruha 25\"\n" +
                "    },\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 1,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        given(facade.save(any())).willThrow(new NarudzbenicaMicroserviceException("Greska"));

        mockMvc.perform(
                        post("/api/v0/narudzbenice")
                                .content(jsonNarudzbenicaDTO)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Greska u zahtevu")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("Greska")));
    }

    @Test
    public void validationErrorForNullKlijentOnSave() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 4,\n" +
                "    \"klijent\": null,\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 1,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                        post("/api/v0/narudzbenice")
                                .content(jsonNarudzbenicaDTO)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Greska u parametrima API poziva")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("Klijent narudzbenice ne sme biti null")));
    }

    @Test
    public void multipleValidationErrorsOnSave() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 4,\n" +
                "    \"klijent\": {\n" +
                "        \"id\": 1,\n" +
                "        \"ime\": \"Ivan\",\n" +
                "        \"prezime\": \"Cukic\",\n" +
                "        \"email\": \"ivancukic@gmail.com\",\n" +
                "        \"brojTelefona\": \"+381631234567\",\n" +
                "        \"adresa\": \"Brace Baruha 25\"\n" +
                "    },\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": -4,\n" +
                "            \"rb\": -1,\n" +
                "            \"kolicina\": -2,\n" +
                "            \"cena\": -100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": -3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": -2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": -2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                        post("/api/v0/narudzbenice")
                                .content(jsonNarudzbenicaDTO)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Greska u parametrima API poziva")))
                .andExpect(jsonPath("$.errors", Matchers.hasSize(4)));
    }

    @Test
    public void updatesCorrectly() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 0,\n" +
                "    \"klijent\": {\n" +
                "        \"id\": 1,\n" +
                "        \"ime\": \"Ivan\",\n" +
                "        \"prezime\": \"Cukic\",\n" +
                "        \"email\": \"ivancukic@gmail.com\",\n" +
                "        \"brojTelefona\": \"+381631234567\",\n" +
                "        \"adresa\": \"Brace Baruha 25\"\n" +
                "    },\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 1,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO();
        given(facade.update(any())).willReturn(narudzbenicaDTO);

        mockMvc.perform(
                put("/api/v0/narudzbenice")
                        .content(jsonNarudzbenicaDTO)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void updateThrowsNarudzbeniceMicroserviceExceptionCorrectly() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 0,\n" +
                "    \"klijent\": {\n" +
                "        \"id\": 1,\n" +
                "        \"ime\": \"Ivan\",\n" +
                "        \"prezime\": \"Cukic\",\n" +
                "        \"email\": \"ivancukic@gmail.com\",\n" +
                "        \"brojTelefona\": \"+381631234567\",\n" +
                "        \"adresa\": \"Brace Baruha 25\"\n" +
                "    },\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 1,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        given(facade.update(any())).willThrow(new NarudzbenicaMicroserviceException("Greska"));

        mockMvc.perform(
                        put("/api/v0/narudzbenice")
                                .content(jsonNarudzbenicaDTO)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Greska u zahtevu")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("Greska")));
    }

    @Test
    public void validationErrorForNullKlijentOnUpdate() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 4,\n" +
                "    \"klijent\": null,\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 1,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                        put("/api/v0/narudzbenice")
                                .content(jsonNarudzbenicaDTO)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Greska u parametrima API poziva")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("Klijent narudzbenice ne sme biti null")));
    }

    @Test
    public void multipleValidationErrorsOnUpdate() throws Exception {
        String jsonNarudzbenicaDTO = "{\n" +
                "    \"brojNarudzbenice\": 4,\n" +
                "    \"klijent\": {\n" +
                "        \"id\": 1,\n" +
                "        \"ime\": \"Ivan\",\n" +
                "        \"prezime\": \"Cukic\",\n" +
                "        \"email\": \"ivancukic@gmail.com\",\n" +
                "        \"brojTelefona\": \"+381631234567\",\n" +
                "        \"adresa\": \"Brace Baruha 25\"\n" +
                "    },\n" +
                "    \"datumKreiranja\": \"2022-04-01T21:50:41\",\n" +
                "    \"datumAzuriranja\": \"2022-04-01T21:51:43\",\n" +
                "    \"ukupno\": 600.0,\n" +
                "    \"stavkeNarudzbenice\": [\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": -4,\n" +
                "            \"rb\": -1,\n" +
                "            \"kolicina\": -2,\n" +
                "            \"cena\": -100.0,\n" +
                "            \"ukupnaCena\": 200.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": -3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": -2,\n" +
                "            \"kolicina\": 2,\n" +
                "            \"cena\": 200.0,\n" +
                "            \"ukupnaCena\": 400.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": -2,\n" +
                "                \"naziv\": \"Keks\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"GRAM\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"brojNarudzbenice\": 4,\n" +
                "            \"rb\": 3,\n" +
                "            \"kolicina\": 1,\n" +
                "            \"cena\": 100.0,\n" +
                "            \"ukupnaCena\": 100.0,\n" +
                "            \"proizvod\": {\n" +
                "                \"id\": 3,\n" +
                "                \"naziv\": \"Mleko\",\n" +
                "                \"tipProizvoda\": \"Hrana\",\n" +
                "                \"jedinica\": \"LITAR\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                        put("/api/v0/narudzbenice")
                                .content(jsonNarudzbenicaDTO)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Greska u parametrima API poziva")))
                .andExpect(jsonPath("$.errors", Matchers.hasSize(4)));
    }

    @Test
    public void notFoundOnFindByIdWithId0() throws Exception {
        mockMvc.perform(
                get("/api/v0/narudzbenice/0")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void notFoundOnFindByIdWithIdNegative() throws Exception {
        mockMvc.perform(
                get("/api/v0/narudzbenice/-1")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void findsByIdCorrectly() throws Exception {
        NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO();

        given(facade.findById(1)).willReturn(Optional.of(narudzbenicaDTO));

        mockMvc.perform(
                        get("/api/v0/narudzbenice/1")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(Matchers.notNullValue())));
    }

    @Test
    public void findsByIdCorrectlyNullResult() throws Exception {
        given(facade.findById(1)).willReturn(Optional.empty());

        mockMvc.perform(
                        get("/api/v0/narudzbenice/1")
                ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.is("null")));
    }

    @Test
    public void notFoundOnFindPageWithPageNegative() throws Exception {
        mockMvc.perform(
                get("/api/v0/narudzbenice/-1/2/datumKreiranja")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void notFoundOnFindPageWithSize0() throws Exception {
        mockMvc.perform(
                get("/api/v0/narudzbenice/0/0/datumKreiranja")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void notFoundOnFindPageWithSizeNegative() throws Exception {
        mockMvc.perform(
                get("/api/v0/narudzbenice/0/-2/datumKreiranja")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void notFoundOnFindPageWithRandomSortText() throws Exception {
        mockMvc.perform(
                get("/api/v0/narudzbenice/0/-2/datumKreiranja")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void findsCorrectlyOnAllowedSortParams() throws Exception {
        List<NarudzbenicaDTO> list = new LinkedList<>();
        given(facade.findPage(any(), any(), any(), any())).willReturn(new PageImpl<>(list));

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/brojNarudzbenice")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/klijent")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/datumKreiranja")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/datumAzuriranja")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/ukupno")
        ).andExpect(status().isOk());
    }

    @Test
    public void findsCorrectlyOnAllowedSortParamsAndSearch() throws Exception {
        List<NarudzbenicaDTO> list = new LinkedList<>();
        given(facade.findPage(any(), any(), any(), eq("Hello"))).willReturn(new PageImpl<>(list));

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/brojNarudzbenice?search=Hello")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/klijent?search=Hello")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/datumKreiranja?search=Hello")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/datumAzuriranja?search=Hello")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/v0/narudzbenice/0/2/ukupno?search=Hello")
        ).andExpect(status().isOk());
    }

}
