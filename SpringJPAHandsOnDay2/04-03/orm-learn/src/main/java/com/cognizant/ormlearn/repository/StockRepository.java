package com.cognizant.ormlearn.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
	public List<Stock> findByCodeAndDateBetween(String code,Date dateStart, Date dateEnd);
	public List<Stock> findByCodeAndCloseGreaterThan(String code,BigDecimal close);
	public List<Stock> findFirst3ByOrderByVolumeDesc();
	public List<Stock> findFirst3ByCodeOrderByClose(String code);

}
