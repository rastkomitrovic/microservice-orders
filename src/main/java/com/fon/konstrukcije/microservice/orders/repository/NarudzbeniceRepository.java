package com.fon.konstrukcije.microservice.orders.repository;

import com.fon.konstrukcije.microservice.orders.entity.Narudzbenica;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NarudzbeniceRepository extends PagingAndSortingRepository<Narudzbenica, Integer> {
}
