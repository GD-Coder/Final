package com.cooksys.pojo;

public enum Cities {
	
	MEMPHIS("Memphis"), KNOXVILLE("Knoxville"), CHATTANOOGA("Chattanooga"), NASHVILLE("Nashville");
	
	private String name;
	
	private Cities(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
