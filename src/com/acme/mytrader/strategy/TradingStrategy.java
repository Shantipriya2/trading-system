package com.acme.mytrader.strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSourceImpl;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy extends JPanel{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args){		
		JTextField volume = new JTextField(10);
		JTextField price = new JTextField(10);
		JTextField currPrice = new JTextField(readCurrPrice());
	    final String BUY = "BUY";
		final String SELL = "SELL";
		final String IBM = "IBM";		
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("SECURITY:"));
	    String securityValues[] = { IBM};
	    JComboBox<String> sec = new JComboBox<>(securityValues);
	    sec.setEditable(false);
	    myPanel.add(sec);
	    myPanel.add(Box.createHorizontalStrut(15));
	    myPanel.add(new JLabel("CURRENT PRICE:"));	
	    currPrice.setEditable(false);
	    myPanel.add(currPrice);
	    myPanel.add(Box.createHorizontalStrut(15));
	    myPanel.add(new JLabel("VOLUME:"));
	    myPanel.add(volume);
	    myPanel.add(Box.createHorizontalStrut(15));
	    myPanel.add(new JLabel("PRICE:"));
	    myPanel.add(price);
	    myPanel.add(Box.createHorizontalStrut(15)); 
	    String action[] = { BUY, SELL };
	    JComboBox<String> cb = new JComboBox<>(action);
	  	cb.setEditable(false);
	  	myPanel.add(cb); 	  	
	    int result = JOptionPane.showConfirmDialog(null, myPanel,
	        "Order Details", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {	      	    
	      String inputOpt = cb.getSelectedItem().toString();
	      String inputSec = sec.getSelectedItem().toString();
	      int inputVol = Integer.parseInt(volume.getText());
	      double inputPrice= Double.parseDouble(price.getText());
	      System.out.println("SECURITY: " + inputSec+"\nVOLUME: " + inputVol+"\nINPUT PRICE: $" + inputPrice);
	      PriceSourceImpl pSource = new PriceSourceImpl();
	      PriceListenerImpl pListener = new PriceListenerImpl();
	      if(inputOpt.equals(BUY)||inputOpt.equals(SELL)){
	    	  pSource.addPriceListener(pListener);
	      }else {
	    	  pSource.removePriceListener(pListener);
	      }		
	      pSource.setPrice(inputSec,inputPrice,inputOpt, inputVol);	      
	    }
	}
	
	public static String readCurrPrice(){
		String value="";	
		try (FileReader reader = new FileReader("\\D:\\PriceSource.txt");				
		          BufferedReader br = new BufferedReader(reader)) {
					String line;
		           while ((line = br.readLine()) != null) {		        	   
		        	      String[] arrOfVal = line.split("\n"); 	
		        		  value=arrOfVal[0];
		        		  return value;            	   		            	   
		           }
				} catch (IOException e) {
		           System.err.format("IOException: %s%n", e);
				} 
		return value;
	}
}
