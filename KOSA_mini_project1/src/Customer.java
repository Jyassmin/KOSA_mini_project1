public class Customer {
	String id;
	String email;
	String password;
	String name;
	String nickname;
	int age;
	String address;
	
	Customer (
			String id,
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
	
	public int hashCode() {return (email+password+name+nickname+age+address).hashCode();}
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			Customer cust = (Customer) obj;
			return email.equals(cust.email) && password.equals(cust.password) && name.equals(cust.name)&&
					nickname.equals(cust.nickname) && age == cust.age && address.equals(cust.address);
		}else {
			return false;
		}
	}
	
	public void getinfo() {
		System.out.printf("%s %s %s %s %s %d %s", this.id, this.email, this.password, this.name, this.nickname, this.age, this.address);
	}
}



