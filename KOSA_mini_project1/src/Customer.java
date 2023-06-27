public class Customer {
	int id;
	String email;
	String password;
	String name;
	String nickname;
	int age;
	String address;
	
	Customer (int id,
			String email,
			String password,
			String name,
			String nickname,
			int age,
			String address) {
		
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.age = age;
		this.address = address;
	}
	
	public void edit(
			String email,
			String password,
			String name,
			String nickname,
			int age,
			String address) {
		
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.age = age;
		this.address = address;
		
	}
	
	public void getinfo() {
		System.out.printf("%s %s %s %s %s %d %s", this.id, this.email, this.password, this.name, this.nickname, this.age, this.address);
	}
}



