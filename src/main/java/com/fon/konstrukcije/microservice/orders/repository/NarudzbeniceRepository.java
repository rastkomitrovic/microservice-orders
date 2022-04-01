package com.fon.konstrukcije.microservice.orders.repository;

import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NarudzbeniceRepository extends PagingAndSortingRepository<Narudzbenica, Integer> {
	
	@Query(value = "SELECT n FROM Narudzbenica n JOIN FETCH n.klijent AS k WHERE n.brojNarudzbenice=:search OR concat(k.ime, ' ', k.prezime) like concat('%',:search,'%') OR n.ukupno=:search OR DATE_FORMAT(n.datumKreiranja, '%Y-%m-%d %T') LIKE concat('%',:search,'%') OR (n.datumAzuriranja IS NOT NULL AND DATE_FORMAT(n.datumAzuriranja, '%Y-%m-%d %T') LIKE concat('%',:search,'%'))", nativeQuery = true)
	Page<Narudzbenica> findAllBySearch(Pageable paging, String search);
	
	@Override
	@Query("SELECT n from Narudzbenica n join fetch n.klijent join fetch n.stavkeNarudzbenice st join fetch st.proizvod WHERE n.brojNarudzbenice =:id")
	Optional<Narudzbenica> findById(Integer id);
}
 