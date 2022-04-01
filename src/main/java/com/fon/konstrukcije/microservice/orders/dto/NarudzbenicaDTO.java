package com.fon.konstrukcije.microservice.orders.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class NarudzbenicaDTO {

    @NotNull(message = "Broj narudzbenice ne sme biti null")
    private Integer brojNarudzbenice;

    @NotNull(message = "Klijent narudzbenice ne sme biti null")
    private KlijentDTO klijent;

    private LocalDateTime datumKreiranja;

    private LocalDateTime datumAzuriranja;

    private Double ukupno;

    @NotNull(message = "Stavke narudzbenice ne smeju biti null")
    private List<StavkaNarudzbeniceDTO> stavkeNarudzbenice;


    public NarudzbenicaDTO(){

    }

    public NarudzbenicaDTO(Integer brojNarudzbenice, KlijentDTO klijent, LocalDateTime datumKreiranja, LocalDateTime datumAzuriranja, Double ukupno, List<StavkaNarudzbeniceDTO> stavkeNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.klijent = klijent;
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

    public KlijentDTO getKlijent() {
        return klijent;
    }

    public void setKlijent(KlijentDTO klijent) {
        this.klijent = klijent;
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

    public List<StavkaNarudzbeniceDTO> getStavkeNarudzbenice() {
        return stavkeNarudzbenice;
    }

    public void setStavkeNarudzbenice(List<StavkaNarudzbeniceDTO> stavkeNarudzbenice) {
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
