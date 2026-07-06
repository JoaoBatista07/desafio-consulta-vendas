package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SalesReportDTO> reportSales(String minDate, String maxDate, String name, Pageable pageable){

		LocalDate minimo;
		LocalDate maximo;
		if (maxDate.isBlank() && !minDate.isBlank()){
			maximo = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minimo = LocalDate.parse(minDate);
		}
		else if (minDate.isBlank() && !maxDate.isBlank()){
			maximo = LocalDate.parse(maxDate);
			minimo = maximo.minusYears(1L);
		}
		else if(!minDate.isBlank() && !maxDate.isBlank()){
			minimo = LocalDate.parse(minDate);
			maximo = LocalDate.parse(maxDate);
		}
		else {
			maximo = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minimo = maximo.minusYears(6L);
		}
		Page<Sale> search11 = repository.salesReport1(minimo, maximo, name, pageable);
		return search11.map(x -> new SalesReportDTO(x));
	}

	public Page<SalesSummaryDTO> summarySales(String minDate, String maxDate, Pageable pageable){

		LocalDate minimo;
		LocalDate maximo;
		if (maxDate.isBlank() && !minDate.isBlank()){
			maximo = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minimo = LocalDate.parse(minDate);
		}
		else if (minDate.isBlank() && !maxDate.isBlank()){
			maximo = LocalDate.parse(maxDate);
			minimo = maximo.minusYears(1L);
		}
		else if(!minDate.isBlank() && !maxDate.isBlank()){
			minimo = LocalDate.parse(minDate);
			maximo = LocalDate.parse(maxDate);
		}
		else {
			maximo = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minimo = maximo.minusYears(1L);
		}

		Page<SalesSummaryDTO> search12 = repository.salesSummary(minimo, maximo, pageable);

		return search12;

	}

}
