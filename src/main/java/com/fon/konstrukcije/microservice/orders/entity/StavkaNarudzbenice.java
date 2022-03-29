package com.fon.konstrukcije.microservice.orders.entity;

import com.fon.konstrukcije.microservice.orders.entity.embedded.StavkaNarudzbeniceEmbeddedId;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stavkanarudzbenice")
public class StavkaNarudzbenice {

    @EmbeddedId
    private StavkaNarudzbeniceEmbeddedId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("brojNarudzbenice")
    @JoinColumn(name = "broj_narudzbenice", nullable = false)
    private Narudzbenica narudzbenica;

    @Column(name = "kolicina", nullable = false)
    private Integer kolicina;

    @Column(name = "cena", nullable = false)
    private Double cena;

    @Column(name = "ukupna_cena",nullable = false)
    private Double ukupnaCena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proizvod_id", nullable = false)
    private Proizvod proizvod;

    public StavkaNarudzbenice(){

    }

    public StavkaNarudzbenice(StavkaNarudzbeniceEmbeddedId id, Narudzbenica narudzbenica, Integer kolicina, Double cena, Double ukupnaCena, Proizvod proizvod) {
        this.id = id;
        this.narudzbenica = narudzbenica;
        this.kolicina = kolicina;
        this.cena = cena;
        this.ukupnaCena = ukupnaCena;
        this.proizvod = proizvod;
    }

    public StavkaNarudzbeniceEmbeddedId getId() {
        return id;
    }

    public void setId(StavkaNarudzbeniceEmbeddedId id) {
        this.id = id;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
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

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbenice that = (StavkaNarudzbenice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbenice{" +
                "id=" + id +
                ", narudzbenica=" + narudzbenica +
                ", kolicina=" + kolicina +
                ", cena=" + cena +
                ", ukupnaCena=" + ukupnaCena +
                ", proizvod=" + proizvod +
                '}';
    }
}
