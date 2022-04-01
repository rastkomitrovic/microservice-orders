package com.fon.konstrukcije.microservice.orders.entity;

import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "proizvod")
public class Proizvod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "naziv", nullable = false, unique = false)
    private String naziv;

    @Column(name = "tip_proizvoda", nullable = false)
    private String tipProizvoda;

    @Column(name = "jedinica", nullable = false)
    @Enumerated(EnumType.STRING)
    private JedinicaMere jedinica;

    public Proizvod() {

    }

    public Proizvod(Integer id, String naziv, String tipProizvoda, JedinicaMere jedinica) {
        this.id = id;
        this.naziv = naziv;
        this.tipProizvoda = tipProizvoda;
        this.jedinica = jedinica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(String tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    public JedinicaMere getJedinica() {
        return jedinica;
    }

    public void setJedinica(JedinicaMere jedinica) {
        this.jedinica = jedinica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proizvod proizvod = (Proizvod) o;
        return Objects.equals(id, proizvod.id);
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tipProizvoda='" + tipProizvoda + '\'' +
                ", jedinica=" + jedinica +
                '}';
    }
}
