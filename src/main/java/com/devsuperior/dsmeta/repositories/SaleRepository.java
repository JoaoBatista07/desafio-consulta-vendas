package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj FROM Sale obj " +
            "JOIN FETCH Seller obj2 " +
            "ON obj.seller.id = obj2.id " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate AND " +
            "UPPER(obj2.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Sale> salesReport1(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesSummaryDTO(obj.seller.name, SUM(obj.amount)) FROM Sale obj " +
            "JOIN obj.seller obj2 " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller.name")
    Page<SalesSummaryDTO> salesSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);


}
