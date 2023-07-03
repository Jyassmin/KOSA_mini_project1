package model;

/*
	Model data class
 */
public class Customer {

	// region members
	private int id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private int age;
	private String address;
	private boolean isSuperUser;
	// endregion

	// region constructors
	Customer(){}
	public Customer(
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
	public Customer(
			int id,
			String email,
			String password,
			String name,
			String nickname,
			int age,
			String address,
			boolean isSuperUser) {

		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.age = age;
		this.address = address;
		this.isSuperUser = isSuperUser;
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
	public boolean getIsSuperUser() {return this.isSuperUser;}
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
	public void setIsSuperUser(boolean isSuperUser){
		this.isSuperUser = isSuperUser;
	}
	//endregion
}



