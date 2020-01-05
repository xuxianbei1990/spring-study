package spring.helloworld.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import spring.helloworld.*;

public class SpringHelloWorld implements HelloWorld {
	private String constructor;
	private int age;
	private String usercode;
	private String userpswd;
	private StrutsHelloWorld strutshelloworld;
	private String[] names;
	private String[] namesList;
	private List<String> nameList;
	private Map map;
	private Properties props;
	
	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getNamesList() {
		return namesList;
	}

	public void setNamesList(String[] namesList) {
		this.namesList = namesList;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUserpswd() {
		return userpswd;
	}

	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}

	public StrutsHelloWorld getStrutshelloworld() {
		return strutshelloworld;
	}

	public void setStrutshelloworld(StrutsHelloWorld strutshelloworld) {
		this.strutshelloworld = strutshelloworld;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	
	public SpringHelloWorld(String usercode){
		super();
		this.constructor = usercode;
	}
	public SpringHelloWorld(String usercode, int age){
		super();
		this.age = age;
		this.constructor = usercode;
	}


public void sayHello(){
	String key = "";
	String str = "user: \n" +  usercode + "---constructor" + constructor + 
			" \nPwd:" + userpswd + "---Age" + age + 
			"\n strutshelloworld:" + strutshelloworld.getClass().getName() + 
			" \nmap:"+ map.get("hello")+ " \nprops:"+ props.getProperty("url");
	System.out.println("Spring Say Hello!!\n" + str);
}

}
