package com.fon.konstrukcije.microservice.orders.entity.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StavkaNarudzbeniceEmbeddedId implements Serializable {

    @Column(name = "broj_narudzbenice", nullable = false)
    private Integer brojNarudzbenice;

    @Column(name = "rb", nullable = false)
    private Integer rb;

    public StavkaNarudzbeniceEmbeddedId(){

    }

    public StavkaNarudzbeniceEmbeddedId(Integer brojNarudzbenice, Integer rb) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.rb = rb;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbeniceEmbeddedId that = (StavkaNarudzbeniceEmbeddedId) o;
        return Objects.equals(brojNarudzbenice, that.brojNarudzbenice) && Objects.equals(rb, that.rb);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbeniceEmbeddedId{" +
                "brojNarudzbenice=" + brojNarudzbenice +
                ", rb=" + rb +
                '}';
    }
}
