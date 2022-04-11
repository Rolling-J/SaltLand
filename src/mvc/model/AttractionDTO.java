package mvc.model;

import java.io.Serializable;

public class AttractionDTO implements Serializable {
	private static final long serialVersionUID = -4274700572038677000L;
	
	private int id;
	private String name ;	
	private String info;		
	private String tag; 
	private String ride;
	private String age; 	
	private String tall; 	
	private String filename;

	public AttractionDTO() {
		super();
	}
	
	public AttractionDTO(String name, String tag) {
		this.name = name;
		this.tag = tag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRide() {
		return ride;
	}

	public void setRide(String ride) {
		this.ride = ride;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTall() {
		return tall;
	}

	public void setTall(String tall) {
		this.tall = tall;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
