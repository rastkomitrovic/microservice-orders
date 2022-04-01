package com.fon.konstrukcije.microservice.orders.mapper;

import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.KlijentDTO;
import com.fon.konstrukcije.microservice.orders.entity.Klijent;

@Service
public class KlijentMapper {
	
	public KlijentDTO toDTO (Klijent klijent) {
		
		KlijentDTO dto = new KlijentDTO();
		
		dto.setId(klijent.getId());
		dto.setAdresa(klijent.getAdresa());
		dto.setBrojTelefona(klijent.getBrojTelefona());
		dto.setEmail(klijent.getEmail());
		dto.setIme(klijent.getIme());
		dto.setPrezime(klijent.getPrezime());
		
		return dto;
	}
	
	public Klijent toEntity(KlijentDTO dto) {
		
		Klijent klijent = new Klijent();
		
		klijent.setId(dto.getId());
		klijent.setAdresa(dto.getAdresa());
		klijent.setBrojTelefona(dto.getBrojTelefona());
		klijent.setEmail(dto.getEmail());
		klijent.setIme(dto.getIme());
		klijent.setPrezime(dto.getPrezime());
		
		return klijent;
	}

}
