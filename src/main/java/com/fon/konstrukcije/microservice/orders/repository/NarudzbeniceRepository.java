package com.fon.konstrukcije.microservice.orders.repository;

import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NarudzbeniceRepository extends PagingAndSortingRepository<Narudzbenica, Integer> {
	
	@Query(value = "SELECT * FROM narudzbenica n INNER JOIN klijent k on n.klijent_id = k.id WHERE n.broj_narudzbenice=:search OR concat(k.ime, ' ', k.prezime) like concat('%',:search,'%') OR n.ukupno=:search OR DATE_FORMAT(n.datum_kreiranja, '%Y-%m-%d %T') LIKE concat('%',:search,'%') OR (n.datum_azuriranja IS NOT NULL AND DATE_FORMAT(n.datum_azuriranja, '%Y-%m-%d %T') LIKE concat('%',:search,'%'))", nativeQuery = true)
	Page<Narudzbenica> findAllBySearch(Pageable paging, String search);
	
	@Override
	@Query("SELECT n from Narudzbenica n join fetch n.klijent join fetch n.stavkeNarudzbenice st join fetch st.proizvod WHERE n.brojNarudzbenice =:id")
	Optional<Narudzbenica> findById(Integer id);
}
 