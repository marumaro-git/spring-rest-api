package sample.api.sample;

import java.io.Serializable;

public class SampleResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String cd;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
