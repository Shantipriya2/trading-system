package com.acme.mytrader.price;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.acme.mytrader.execution.ExecutionServiceImpl;

//Implementation class for PriceSource
public class PriceSourceImpl implements PriceSource{
	private String security;
	private double price;
	private String option;
	private int volume;
	private List<PriceListener> listener = new ArrayList<>();
	@Override
	public void addPriceListener(PriceListener listener) {
		// TODO Auto-generated method stub
		this.listener.add(listener);
	}

	@Override
	public void removePriceListener(PriceListener listener) {
		// TODO Auto-generated method stub
		this.listener.remove(listener);
	}

	public void setPrice(String security, double price, String option, int volume) {
        this.security = security;
        this.price = price;
        this.option = option;
        this.volume = volume;
        for (PriceListener listener : this.listener) { 
        	double execPrice = readInput(this.price, this.option);
        	ExecutionServiceImpl exeService = new ExecutionServiceImpl();
        	if(execPrice!=0){
       		if(this.option.equals("BUY")){
       			exeService.buy(this.security, execPrice, this.volume);       			
       		}else if(this.option.equals("SELL")){
       			exeService.sell(this.security, execPrice, this.volume);    
       		}    
       		listener.priceUpdate(this.security, execPrice);   
        	}else{
        		System.out.println(this.option+" order not executed.");
        	}
        }
    }
	
	public double readInput(double price, String option) {
		try (FileReader reader = new FileReader("\\D:\\PriceSource.txt");
          BufferedReader br = new BufferedReader(reader)) {
           String line;
           double currPrice;
           while ((line = br.readLine()) != null) {
           	currPrice = Double.parseDouble(line.trim());
           	System.out.println("Current Price : $"+currPrice);
           	if(option.equals("BUY")){
           		if(currPrice<=price){
           			return currPrice;
           		}
           	}else if(option.equals("SELL")){
           		if(currPrice>=price){
           			return currPrice;
           		}         		
           	}           	
               Thread.sleep(5000);
           }

		} catch (IOException e) {
           System.err.format("IOException: %s%n", e);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	}
}
