package day03.task4;

public class WaitingDto {
	private int num;
	private String tel;
	private int count;
	
	public WaitingDto () {}

	public WaitingDto(int num, String tel, int count) {
		super();
		this.num = num;
		this.tel = tel;
		this.count = count;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "WaitingDto [num=" + num + ", tel=" + tel + ", count=" + count + "]";
	}
	
	
}