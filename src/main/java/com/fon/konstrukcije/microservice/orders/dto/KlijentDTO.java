package com.fon.konstrukcije.microservice.orders.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class KlijentDTO {

    @NotNull(message = "Id klijenta ne sme biti null")
    private Integer id;

    private String ime;

    private String prezime;

    private String email;

    private String brojTelefona;

    private String adresa;

    public KlijentDTO() {

    }

    public KlijentDTO(Integer id, String ime, String prezime, String email, String brojTelefona, String adresa) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlijentDTO klijentDTO = (KlijentDTO) o;
        return Objects.equals(id, klijentDTO.id);
    }

    @Override
    public String toString() {
        return "KlijentDTO{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
