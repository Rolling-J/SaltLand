package dto;

import java.io.Serializable;

public class Ticket implements Serializable{
	private static final long serialVersionUID = -4274700572038677000L;
	
	private int reserve_num;
	private String id;
	private String visit_date;
	private int adult;
	private int teenager;
	private int children;
	private int charge;
	private String reserve_time;
	
	public Ticket() {
		super();
	}
	
	
	public int getReserve_num() {
		return reserve_num;
	}

	public void setReserve_num(int reserve_num) {
		this.reserve_num = reserve_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVisit_date() {
		return visit_date;
	}

	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getTeenager() {
		return teenager;
	}

	public void setTeenager(int teenager) {
		this.teenager = teenager;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	
}
