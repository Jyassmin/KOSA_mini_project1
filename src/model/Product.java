package model;

public class Product { // model.Customer.csv에서 하나의 행을 받아 객체로 생성(고객정보 단위)
	private int id;
	private String name;
	private String brand;
	private int size;
	private String color;
	private int stock;
	private long cost;

	
	public Product(int id,
				   String name,
				   String brand,
				   int size,
				   String color,
				   int stock,
				   long cost) {
		
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.color = color;
		this.stock = stock;
		this.cost = cost;
	}
		// endregion

	// region getters
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getBrand(){
		return this.brand;
	}
	public int getSize(){
		return this.size;
	}
	public String getColor(){
		return this.color;
	}
	public int getStock(){
		return this.stock;
	}
	public long getCost(){
		return this.cost;
	}
	//endregion

	// region setters
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setbrand(String brand){
		this.brand = brand;
	}
	public void setSize(int size){
		this.size = size;
	}
	public void setColor(String color){
		this.color = color;
	}
	public void setStock(int stock){
		this.stock = stock;
	}
	public void setCost(long cost){
		this.cost = cost;
	}
	//endregion
	
}
