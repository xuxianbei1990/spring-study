package spring.helloworld.impl;

import spring.helloworld.EarthWill;

public class FireChris implements EarthWill {
	private String Owner;
	
	
	public FireChris(String Owner){
		this.Owner = Owner;
	}
	public FireChris(){
		
	}

	public void Kill() {
		// TODO Auto-generated method stub
		System.out.println(Owner + "ด๓ษ฿าง");

	}

}
