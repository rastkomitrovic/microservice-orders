package com.fon.konstrukcije.microservice.orders.repository;

import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NarudzbeniceRepository extends PagingAndSortingRepository<Narudzbenica, Integer> {

    @Query("select n from Narudzbenica n join n.klijent as k where concat(n.brojNarudzbenice, '') like concat('%',:search,'%') or concat(k.ime, ' ', k.prezime ) like concat('%',:search,'%') or concat(n.ukupno,'') like concat('%',:search,'%') or function('date_format', n.datumKreiranja, '%Y-%m-%d %T') like concat('%',:search,'%') or( n.datumAzuriranja is not null and function('date_format', n.datumAzuriranja, '%Y-%m-%d %T') like concat('%',:search,'%'))")
    Page<Narudzbenica> findAllBySearch(Pageable paging, String search);

    @Override
    @Query("SELECT n from Narudzbenica n join fetch n.stavkeNarudzbenice st join fetch st.proizvod WHERE n.brojNarudzbenice =:id")
    Optional<Narudzbenica> findById(Integer id);
}
 