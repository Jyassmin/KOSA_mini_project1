import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class ProductManager {
	private static final String Product_CSV_Path = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\KOSA_mini_project1\\data\\product.csv";
	private static ArrayList<Product> products;
	private static HashMap<Integer, Product> productsHash;
	private final Scanner sc;

	ProductManager() throws IOException {} {
		products = new ArrayList<Product>();
		productsHash = new HashMap<Integer, Product>();
		sc = new Scanner(System.in);

		File file = new File(Product_CSV_Path);
		if(file.exists()) {
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;
		    while( (sLine = inFile.readLine()) != null ) {
				String[] temp_arr = sLine.split(","); 
		        try{
					int tempId = Integer.parseInt(temp_arr[0].replaceAll("\\D", ""));
		        	addToList(new Product(
		        			tempId,
		        			temp_arr[1].trim(),
		            		temp_arr[2].trim(),
		            		Integer.parseInt(temp_arr[3].trim()),
		            		temp_arr[4].trim(),
		            		Integer.parseInt(temp_arr[5].trim()),
		            		Long.parseLong(temp_arr[6].trim())));
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
		    }
		    inFile.close();
		}
    }

	public String getProductName(int id){
		return productsHash.get(id).getName();
	}
	
	private void addToList(Product p) {
		products.add(p);
		productsHash.put(p.getId(), p);
	}
	
	public void add() throws IOException{ // 한 줄씩 등록
		System.out.print("제품명: ");
		String name = sc.nextLine();
		
		System.out.print("브랜드: ");
		String brand = sc.nextLine();
		
		System.out.print("사이즈: ");
		int size = Integer.parseInt(sc.nextLine());
		
		System.out.print("색상: ");
		String color = sc.nextLine(); //br.readLine());
		
		System.out.print("재고: ");
		int stock = Integer.parseInt(sc.nextLine());
		
		System.out.print("가격: ");
		long cost = Long.parseLong(sc.nextLine());
		
		int newId = products.get(products.size()-1).getId() + 1;
		
		Product p = new Product(newId, name, brand, size, color, stock, cost);
		addToList(p);
	}

	// TODO: 2023/06/29 display style
	public void show() { // 현재 모든 data 출력(모든제품show)
		System.out.println(("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"));
		System.out.printf("| %-4s|  %-12s %-10s %-5s %-10s %-5s %-15s |\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		System.out.println(("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"));
		for (Product e : products) {
			System.out.printf("| %-4s|  %-12s %-10s %-5s %-10s %-5s %-15s |\n",
					e.getId(), e.getName(), e.getBrand(), e.getSize(), e.getColor(), e.getStock(), e.getCost());
		}
		System.out.println(("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"));
	}
	
	public void edit() { // 수정
		System.out.print("수정할 제품의 ID를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());

		Product p1 = productsHash.get(id);
		int idx = products.indexOf(p1);
		if(idx == -1) {
			System.out.println("* 입력하신 제품이 존재하지 않습니다.\n");
			return;
		}
		System.out.println(("---------현재정보----------"));
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n",
			"name", "brand", "size", "color", "stock", "cost");
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n",
			p1.getName(), p1.getBrand(), p1.getSize(), p1.getColor(), p1.getStock(), p1.getCost());
		System.out.println(("-------------------------"));

		System.out.println("아래에 수정될 내용을 적어주세요: ");
		System.out.print("제품명: ");
		String name = sc.nextLine();
		
		System.out.print("브랜드: ");
		String brand = sc.nextLine();
		
		System.out.print("사이즈: ");
		int size = Integer.parseInt(sc.nextLine());
		
		System.out.print("색상: ");
		String color = sc.nextLine();
		
		System.out.print("재고: ");
		int stock = Integer.parseInt(sc.nextLine());
		
		System.out.print("가격: ");
		int cost = Integer.parseInt(sc.nextLine());
		
		Product p = new Product(id, name, brand, size, color, stock, cost);

		products.set(idx, p); // products ArrayList에 수정
		productsHash.put(id, p);	// products HashMap에 수정
	}
	
	public void saveToFile() {
		File file = new File(Product_CSV_Path);
		BufferedWriter writer = null;
	    try {
	    	if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
	    	writer = new BufferedWriter(new FileWriter(Product_CSV_Path, false));
	    	
			for(Product item : products) {
				String str;
				str = item.getId() + "," +
						item.getName() + "," +
						item.getBrand() + "," +
						item.getSize() + "," +
						item.getColor() + "," +
						item.getStock() + "," +
						item.getCost() + "\n";
				writer.append(str);
			}
		    writer.close();			
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		} 
	}

	public void remove() {
		System.out.print("삭제할 제품의 ID를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());
		
		System.out.println("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요");
		String input = sc.nextLine();

		if (input.equals("y")) { // String 비교에서는 equals!
			Product p = productsHash.get(id);
			products.remove(p);
			productsHash.remove(id);
		}
	}

	public void showProductById() {
		System.out.print("검색할 제품의 번호를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());

		Product p = productsHash.get(id);
		if(p == null) {
			System.out.println("* 입력하신 제품이 존재하지 않습니다.\n");
			return;
		}

		System.out.printf("%-5s %-20s %-20s %-9s %-20s %-4s %-30s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		System.out.printf("%-5s %-20s %-20s %-9s %-20s %-4s %-30s\n",
				p.getId(), p.getName(), p.getBrand(), p.getSize(), p.getColor(), p.getStock(), p.getCost());
	}

	public void showBrand() {
		System.out.print("검색할 브랜드명을 입력해주세요: ");
		String brand = sc.nextLine();

		System.out.printf("%-5s %-20s %-20s %-9s %-20s %-4s %-30s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		boolean flag = false;
		for (Product e : products) {
			if (brand.equals(e.getBrand())) {
				System.out.printf("%-5s %-20s %-20s %-9s %-20s %-4s %-30s\n",
				e.getId(), e.getName(), e.getBrand(), e.getSize(), e.getColor(), e.getStock(), e.getCost());
				flag = true;
			}
		}
		if (!flag)
			System.out.println("* 검색한 브랜드 제품이 존재하지 않습니다.");
	}

	public void showProductName() {
		System.out.print("검색할 제품명을 입력해주세요: ");
		String name = sc.nextLine();

		System.out.printf("%-5s %-20s %-20s %-9s %-20s %-4s %-30s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		boolean flag = false;
		for (Product e : products) {
			if (name.equals(e.getName())) {
				System.out.printf("%-5s %-20s %-20s %-9s %-20s %-4s %-30s\n",
				e.getId(), e.getName(), e.getBrand(), e.getSize(), e.getColor(), e.getStock(), e.getCost());
				flag = true;
			}
		}
		if (!flag)
			System.out.println("* 검색한 제품명이 존재하지 않습니다.");
	}

	public void orderProduct() {
		System.out.print("구매할 제품의 번호를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());


		Product p = productsHash.get(id);
		if (p == null) {
			System.out.println("* 구매하실 제품번호가 존재하지 않습니다.");

		} else {
			int idx = products.indexOf(p);
			p.setStock(p.getStock() - 1);

			if (p.getStock() != 0) {
				products.set(idx, p);
				productsHash.put(id, p);

			} else {
				// 만약 재고가 0이라면 해당제품 삭제
				// why? 상품리스트에서 보이지 않도록 & 실제 비지니스에서 새로운 상품을 들여올 때 수량이 없던 기존제품을 늘려주는 것보다, 새로 등록하는 것이 더 편리.
				products.remove(idx);
				productsHash.remove(id);
			}
			System.out.println("* 구매완료.");
		}
	}
}





