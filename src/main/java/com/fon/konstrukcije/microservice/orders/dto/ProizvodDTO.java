package com.fon.konstrukcije.microservice.orders.dto;

import com.fon.konstrukcije.microservice.orders.entity.eum.JedinicaMere;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProizvodDTO {

    @NotNull(message = "Id proizvoda ne sme biti null")
    private Integer id;

    private String naziv;

    private String tipProizvoda;

    private JedinicaMere jedinica;

    public ProizvodDTO(){

    }

    public ProizvodDTO(Integer id, String naziv, String tipProizvoda, JedinicaMere jedinica) {
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
        ProizvodDTO proizvodDTO = (ProizvodDTO) o;
        return Objects.equals(id, proizvodDTO.id);
    }

    @Override
    public String toString() {
        return "ProizvodDTO{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tipProizvoda='" + tipProizvoda + '\'' +
                ", jedinica=" + jedinica +
                '}';
    }
}
