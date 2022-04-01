package com.fon.konstrukcije.microservice.orders.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "narudzbenica")
public class Narudzbenica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broj_narudzbenice", nullable = false, unique = true)
    private Integer brojNarudzbenice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "klijent_id", nullable = false)
    private Klijent klijent;

    @Basic
    @Column(name = "datum_kreiranja", nullable = false)
    private LocalDateTime datumKreiranja;

    @Basic
    @Column(name = "datum_azuriranja", nullable = true)
    private LocalDateTime datumAzuriranja;

    @Column(name = "ukupno", nullable = false)
    private Double ukupno;

    @OneToMany(mappedBy = "id.brojNarudzbenice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("id.rb")
    private Set<StavkaNarudzbenice> stavkeNarudzbenice;

    public Narudzbenica() {

    }

    public Narudzbenica(Integer brojNarudzbenice, Klijent klijent, LocalDateTime datumKreiranja, LocalDateTime datumAzuriranja, Double ukupno, Set<StavkaNarudzbenice> stavkeNarudzbenice) {
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

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
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

    public Set<StavkaNarudzbenice> getStavkeNarudzbenice() {
        return stavkeNarudzbenice;
    }

    public void setStavkeNarudzbenice(Set<StavkaNarudzbenice> stavkeNarudzbenice) {
        this.stavkeNarudzbenice = stavkeNarudzbenice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narudzbenica that = (Narudzbenica) o;
        return Objects.equals(brojNarudzbenice, that.brojNarudzbenice);
    }

    @Override
    public String toString() {
        return "Narudzbenica{" +
                "brojNarudzbenice=" + brojNarudzbenice +
                ", datumKreiranja=" + datumKreiranja +
                ", datumAzuriranja=" + datumAzuriranja +
                ", ukupno=" + ukupno +
                ", stavkeNarudzbenice=" + stavkeNarudzbenice +
                '}';
    }
}
