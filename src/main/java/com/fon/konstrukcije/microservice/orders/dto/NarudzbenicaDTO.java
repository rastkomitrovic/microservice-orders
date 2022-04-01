package com.fon.konstrukcije.microservice.orders.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class NarudzbenicaDTO {

    private Integer brojNarudzbenice;

    private LocalDateTime datumKreiranja;

    private LocalDateTime datumAzuriranja;

    private Double ukupno;

    private Set<StavkaNarudzbeniceDTO> stavkeNarudzbenice;


    public NarudzbenicaDTO(){

    }

    public NarudzbenicaDTO(Integer brojNarudzbenice, LocalDateTime datumKreiranja, LocalDateTime datumAzuriranja, Double ukupno, Set<StavkaNarudzbeniceDTO> stavkeNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.datumKreiranja = datumKreiranja;
        this.datumAzuriranja = datumAzuriranja;
        this.ukupno = ukupno;
        this.stavkeNarudzbenice = stavkeNarudzbenice;
    }

    public Integer getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(Integer brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public LocalDateTime getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDateTime datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public LocalDateTime getDatumAzuriranja() {
        return datumAzuriranja;
    }

    public void setDatumAzuriranja(LocalDateTime datumAzuriranja) {
        this.datumAzuriranja = datumAzuriranja;
    }

    public Double getUkupno() {
        return ukupno;
    }

    public void setUkupno(Double ukupno) {
        this.ukupno = ukupno;
    }

    public Set<StavkaNarudzbeniceDTO> getStavkeNarudzbenice() {
        return stavkeNarudzbenice;
    }

    public void setStavkeNarudzbenice(Set<StavkaNarudzbeniceDTO> stavkeNarudzbenice) {
        this.stavkeNarudzbenice = stavkeNarudzbenice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NarudzbenicaDTO that = (NarudzbenicaDTO) o;
        return Objects.equals(brojNarudzbenice, that.brojNarudzbenice);
    }

    @Override
    public String toString() {
        return "NarudzbenicaDTO{" +
                "brojNarudzbenice=" + brojNarudzbenice +
                ", datumKreiranja=" + datumKreiranja +
                ", datumAzuriranja=" + datumAzuriranja +
                ", ukupno=" + ukupno +
                ", stavkeNarudzbenice=" + stavkeNarudzbenice +
                '}';
    }
}
