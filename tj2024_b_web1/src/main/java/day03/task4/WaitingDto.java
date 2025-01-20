package day03.task4;

public class WaitingDto {
	private String name;
	private int people;
	private String phone;
	public WaitingDto() {}
	public WaitingDto(String name, int num, String phone) {
		super();
		this.name = name;
		this.people = num;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return people;
	}
	public void setNum(int num) {
		this.people = num;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "WaitingDto [name=" + name + ", num=" + people + ", phone=" + phone + "]";
	}

}
