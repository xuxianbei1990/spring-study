package com.spring.jdbc;

//二口女 阴阳师
public class ErKouNv {
	private Long playId;
	private String playKill;
	private String playName;
	
	public ErKouNv(){
		
	}
	
	public ErKouNv(Long playId, String playKill, String playName){
		this.playId = playId;
		this.playKill = playKill;
		this.playName = playName;
	}

	public Long getPlayId() {
		return playId;
	}

	public void setPlayId(Long playId) {
		this.playId = playId;
	}

	public String getPlayKill() {
		return playKill;
	}

	public void setPlayKill(String playKill) {
		this.playKill = playKill;
	}

	public String getPlayName() {
		return playName;
	}

	public void setPlayName(String playName) {
		this.playName = playName;
	}
	
	

}
