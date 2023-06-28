public class Customer {

	// region members
	private int id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private int age;
	private String address;
	// endregion

	// region constructors
	Customer(){}
	Customer (
			int id,
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
	// endregion

	// region getters
	public int getId(){
		return this.id;
	}
	public String getEmail(){
		return this.email;
	}
	public String getPassword(){
		return this.password;
	}
	public String getName(){
		return this.name;
	}
	public String getNickname(){
		return this.nickname;
	}
	public int getAge(){
		return this.age;
	}
	public String getAddress(){
		return this.address;
	}
	//endregion

	// region setters
	public void setId(int id){
		this.id = id;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setAddress(String address){
		this.address = address;
	}
	//endregion


	// region methods
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

	/*@Override
	public int hashCode() {return (email+password+name+nickname+age+address).hashCode();}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			Customer cust = (Customer) obj;
			return email.equals(cust.email) && password.equals(cust.password) && name.equals(cust.name)&&
					nickname.equals(cust.nickname) && age == cust.age && address.equals(cust.address);
		}else {
			return false;
		}
	}*/

	public void getinfo() {
		System.out.printf("%s %s %s %s %s %d %s", this.id, this.email, this.password, this.name, this.nickname, this.age, this.address);
	}
	// endregion
}



