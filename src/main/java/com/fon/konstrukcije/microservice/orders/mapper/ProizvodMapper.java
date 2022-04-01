package com.fon.konstrukcije.microservice.orders.mapper;

import org.springframework.stereotype.Service;

import com.fon.konstrukcije.microservice.orders.dto.ProizvodDTO;
import com.fon.konstrukcije.microservice.orders.entity.Proizvod;

@Service
public class ProizvodMapper {
	
	public ProizvodDTO toDTO(Proizvod proizvod) {
		
		ProizvodDTO dto  = new ProizvodDTO();
		
		dto.setId(proizvod.getId());
		dto.setJedinica(proizvod.getJedinica());
		dto.setNaziv(proizvod.getNaziv());
		dto.setTipProizvoda(proizvod.getTipProizvoda());
		
		return  dto;
	}
	
	public Proizvod toEntity(ProizvodDTO dto) {
		
		Proizvod proizvod = new Proizvod();
		
		proizvod.setId(dto.getId());
		proizvod.setJedinica(dto.getJedinica());
		proizvod.setNaziv(dto.getNaziv());
		proizvod.setTipProizvoda(dto.getTipProizvoda());
		
		return proizvod;
	}

}
