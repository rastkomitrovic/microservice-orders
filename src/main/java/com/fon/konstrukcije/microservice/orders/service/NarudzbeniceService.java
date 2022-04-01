package com.fon.konstrukcije.microservice.orders.service;

import com.fon.konstrukcije.microservice.orders.repository.NarudzbeniceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NarudzbeniceService {

    @Resource
    private NarudzbeniceRepository repository;

}
