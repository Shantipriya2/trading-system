package com.acme.mytrader.price;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Implementation class for PriceListener
public class PriceListenerImpl  implements PriceListener{

	@Override
	public void priceUpdate(String security, double price) {
		// TODO Auto-generated method stub
		String str = security+"->"+price;
		try {
		      File tfile = new File("\\D:\\TradingSummary.txt");
		      if (tfile.createNewFile()) {
		        System.out.println("File created: " + tfile.getName());		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(tfile));
		        writer.write(str);		         
		        writer.close();
		      } else {
		        System.out.println("File updated: " + tfile.getName());
		        BufferedWriter writer = new BufferedWriter(new FileWriter(tfile, true));
		        writer.newLine();
		        writer.append(str);		         
		        writer.close();
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
