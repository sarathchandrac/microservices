package com.training.mobile.model;

public enum LOB {
	ONLINE(0), RETAIL(1), INDIRECT(2);
	private int value;
	
	private LOB(int value){
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
	
	
}
