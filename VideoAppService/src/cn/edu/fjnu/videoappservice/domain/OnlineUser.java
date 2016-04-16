package cn.edu.fjnu.videoappservice.domain;

/**
 * 在线用户表
 * @author GaoFei
 *
 */
public class OnlineUser {
	private int id;
	private int user_id;
	private String address;
	private double lng;
	private double lat;
	private int time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "OnlineUser [id=" + id + ", user_id=" + user_id + ", address="
				+ address + ", lng=" + lng + ", lat=" + lat + ", time=" + time
				+ "]";
	}
	
}
