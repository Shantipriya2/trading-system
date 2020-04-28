package com.acme.mytrader.execution;

//Implementation class for ExecutionService
public class ExecutionServiceImpl implements ExecutionService{

	@Override
	public void buy(String security, double price, int volume) {
		System.out.println("Buy Order executed for "+security+ " "+volume+" shares @ $"+price);	
	}

	@Override
	public void sell(String security, double price, int volume) {
		// TODO Auto-generated method stub
		System.out.println("Sell Order executed for "+security+ " "+volume+" shares @ $"+price);		
	}

}
