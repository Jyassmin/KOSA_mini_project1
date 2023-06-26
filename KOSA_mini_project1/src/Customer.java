
public class Customer {
	String id;
	String email;
	String password;
	String name;
	String nickname;
	int age;
	String address;
	
	Customer (String id,
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
	
	
	
	
	
	
}
