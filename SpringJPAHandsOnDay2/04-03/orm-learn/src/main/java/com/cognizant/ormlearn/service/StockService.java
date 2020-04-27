package com.cognizant.ormlearn.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.exception.StockNotFoundException;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getStockByCodeAndDate(String code,Date dateStart, Date dateEnd) throws StockNotFoundException{
		List<Stock> stockList = stockRepository.findByCodeAndDateBetween(code,dateStart, dateEnd );
		if(stockList.isEmpty())
			throw new StockNotFoundException("No Stock Data Found");
		return stockList;
		
	}
	public List<Stock> getStockByCodeAndClose(String code,BigDecimal close) throws StockNotFoundException{
		List<Stock> stockList = stockRepository.findByCodeAndCloseGreaterThan(code, close);
		if(stockList.isEmpty())
			throw new StockNotFoundException("No Stock Data Found");
		return stockList;
		
	}
	
	public List<Stock> getTop3StockByVolume() throws StockNotFoundException{
		List<Stock> stockList = stockRepository.findFirst3ByOrderByVolumeDesc();
		if(stockList.isEmpty())
			throw new StockNotFoundException("No Stock Data Found");
		return stockList;
		
	}
	public List<Stock> getByCodeBottom3LowestStock(String code) throws StockNotFoundException{
		List<Stock> stockList = stockRepository.findFirst3ByCodeOrderByClose(code);
		if(stockList.isEmpty())
			throw new StockNotFoundException("No Stock Data Found");
		return stockList;
		
	}

}
