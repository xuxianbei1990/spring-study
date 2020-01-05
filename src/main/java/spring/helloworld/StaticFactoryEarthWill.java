package spring.helloworld;

import spring.helloworld.impl.*;

public class StaticFactoryEarthWill {
	private static EarthWill earthwill = null;
	public static EarthWill getInstance(){
		if (earthwill == null){
			earthwill = new FireChris();
		}
		return earthwill;
	}

}
