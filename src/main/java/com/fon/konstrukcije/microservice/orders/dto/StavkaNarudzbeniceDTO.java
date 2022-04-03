package com.fon.konstrukcije.microservice.orders.dto;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class StavkaNarudzbeniceDTO {

    @NotNull(message = "Broj narudzbenice u stavci narudzbenici ne sme biti null")
    @Min(value = 0, message = "Broj narudzbenice mora biti minimum 0")
    private Integer brojNarudzbenice;

    @NotNull(message = "Redni broj narudzbenice u stavci narudzbenice ne sme biti null")
    private Integer rb;

    @NotNull(message = "Kolicina u stavci narudzbenice ne sme biti null")
    @Min(value = 1, message = "Kolicina mora biti minimum 1")
    private Integer kolicina;

    @NotNull(message = "Cena u stavci narudzbenice ne sme biti null")
    @Min(value = 1, message = "Cena mora biti minimum 1")
    private Double cena;

    private Double ukupnaCena;

    @NotNull(message = "Proizvod stavke narudzbenice ne sme biti null")
    @Valid
    private ProizvodDTO proizvod;

    public StavkaNarudzbeniceDTO() {

    }

    public StavkaNarudzbeniceDTO(Integer brojNarudzbenice, Integer rb, Integer kolicina, Double cena, Double ukupnaCena, ProizvodDTO proizvod) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.ukupnaCena = ukupnaCena;
        this.proizvod = proizvod;
    }

    public Integer getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(Integer brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(Double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public ProizvodDTO getProizvod() {
        return proizvod;
    }

    public void setProizvod(ProizvodDTO proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbeniceDTO that = (StavkaNarudzbeniceDTO) o;
        return Objects.equals(brojNarudzbenice, that.brojNarudzbenice) && Objects.equals(rb, that.rb);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbeniceDTO{" +
                "brojNarudzbenice=" + brojNarudzbenice +
                ", rb=" + rb +
                ", kolicina=" + kolicina +
                ", cena=" + cena +
                ", ukupnaCena=" + ukupnaCena +
                ", proizvodDTO=" + proizvod +
                '}';
    }
}
