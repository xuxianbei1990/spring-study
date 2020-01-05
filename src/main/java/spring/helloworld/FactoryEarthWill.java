package spring.helloworld;

import spring.helloworld.impl.FireChris;

public class FactoryEarthWill  {

	public EarthWill getInstance(){
		return new FireChris("FactoryEarthWill");
	}

}
