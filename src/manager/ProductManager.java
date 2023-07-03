package manager;

import model.Product;
import model.Order;
import java.io.*;
import java.util.*;
import java.text.*;
import util.StringUtils;

/*
    model.Product class의 관련된 기능들을 구현한 클래스
*/
public class ProductManager {
	private static final String PRODUCT_CSV_PATH = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\data\\product.csv";
	//private static final String PRODUCT_CSV_PATH = "/Users/kyle/work/KOSA_mini_project1/data/product.csv";
	private static ArrayList<Product> products;
	private static HashMap<Integer, Product> productsHash;
	private final String BAR_TABLE = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

	/*
		 product.csv 파일에서 읽어드린 데이터로 products와 productHash에 채워준다
	*/
	ProductManager() throws IOException {} {
		Scanner sc = new Scanner(System.in);
		products = new ArrayList<Product>();
		productsHash = new HashMap<Integer, Product>();
		sc = new Scanner(System.in);

		File file = new File(PRODUCT_CSV_PATH);
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

	/*
		넘겨받은 product id로 해당 product의 name을 return해준다
	 */
	public String getProductName(int id){
		return productsHash.get(id).getName();
	}

	/*
	 	model.Product ArrayList와 hashmap에 넘겨받은 model.Product 객체 저장하는 메소드
	*/
	private void addToList(Product p) {
		products.add(p);
		productsHash.put(p.getId(), p);
	}

	/*
		제품명, 브랜드, 사이즈, 색상, 재고 그리고 가격 정보를 input으로 받고
	 	새로운 model.Product 객체를 생성 후 데이터에 넣어준다
	 */
	public void add() throws IOException{ // 한 줄씩 등록
		Scanner sc = new Scanner(System.in);

		System.out.print("제품명: ");
		String name = sc.nextLine();
		
		System.out.print("브랜드: ");
		String brand = sc.nextLine();

		String size_str;
		do{
			System.out.print("사이즈: ");
			size_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(size_str));
		int size = Integer.parseInt(size_str);

		System.out.print("색상: ");
		String color = sc.nextLine(); //br.readLine());

		String stock_str;
		do{
			System.out.print("재고: ");
			stock_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(stock_str));
		int stock = Integer.parseInt(stock_str);

		String cost_str;
		do{
			System.out.print("가격: ");
			cost_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(cost_str));
		long cost = Long.parseLong(cost_str);
		
		int newId = products.get(products.size()-1).getId() + 1;
		
		Product p = new Product(newId, name, brand, size, color, stock, cost);
		addToList(p);
	}

	/*
		현재 모든 data 출력
	 */
	public void show() {
		System.out.println(BAR_TABLE);
		System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		System.out.println(BAR_TABLE);
		for (Product e : products) {
			System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
					e.getId(), e.getName(), e.getBrand(), e.getSize(), e.getColor(), e.getStock(), e.getCost());
		}
		System.out.println(BAR_TABLE);
	}

	/*
		수정할 제품의 id를 입력받고 해당 제품의 수정될 내용들을 입력받은 뒤,
		새로운 정보들로 데이터를 업데이트해준다
	 */
	public void edit() { // 수정
		Scanner sc = new Scanner(System.in);

		String id_str;
		do{
			System.out.print("수정할 제품의 ID를 입력해주세요: ");
			id_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(id_str));
		int id = Integer.parseInt(id_str);

		Product p1 = productsHash.get(id);
		int idx = products.indexOf(p1);
		if(idx == -1) {
			System.out.println("* 입력하신 제품이 존재하지 않습니다.\n");
			return;
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ [ 제 품 정 보 ] ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.printf("| %-20s %-20s %-20s %-20s %-20s %-20s\n",
			"name", "brand", "size", "color", "stock", "cost");
		System.out.printf("| %-20s %-20s %-20s %-20s %-20s %-20s\n",
			p1.getName(), p1.getBrand(), p1.getSize(), p1.getColor(), p1.getStock(), p1.getCost());
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

		System.out.println("* 아래에 수정될 내용을 적어주세요");
		System.out.print("제품명: ");
		String name = sc.nextLine();
		
		System.out.print("브랜드: ");
		String brand = sc.nextLine();


		String size_str;
		do{
			System.out.print("사이즈: ");
			size_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(size_str));
		int size = Integer.parseInt(size_str);
		
		System.out.print("색상: ");
		String color = sc.nextLine();

		String stock_str;
		do{
			System.out.print("재고: ");
			stock_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(stock_str));
		int stock = Integer.parseInt(stock_str);

		String cost_str;
		do{
			System.out.print("가격: ");
			cost_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(cost_str));
		int cost = Integer.parseInt(cost_str);
		
		Product p = new Product(id, name, brand, size, color, stock, cost);

		products.set(idx, p); // products ArrayList에 수정
		productsHash.put(id, p);	// products HashMap에 수정
	}

	/*
		ProductList에 저장되어 있는 데이터들로 product.csv 파일을 덮어쓴다
	*/
	public void saveToFile() {
		File file = new File(PRODUCT_CSV_PATH);
		BufferedWriter writer = null;
	    try {
	    	if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
	    	writer = new BufferedWriter(new FileWriter(PRODUCT_CSV_PATH, false));
	    	
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
		Scanner sc = new Scanner(System.in);

		String id_str;
		do{
			System.out.print("삭제할 제품의 ID를 입력해주세요: ");
			id_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(id_str));
		int id = Integer.parseInt(id_str);
		
		System.out.print("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요");
		String input = sc.nextLine();

		if (input.equals("y")) { // String 비교에서는 equals!
			Product p = productsHash.get(id);
			products.remove(p);
			productsHash.remove(id);
		}
	}

	public void showProductById() {
		Scanner sc = new Scanner(System.in);

		String id_str;
		do{
			System.out.print("검색할 제품의 번호를 입력해주세요: ");
			id_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(id_str));
		int id = Integer.parseInt(id_str);

		Product p = productsHash.get(id);
		if(p == null) {
			System.out.println("* 입력하신 제품이 존재하지 않습니다.\n");
			return;
		}
		System.out.println(BAR_TABLE);
		System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		System.out.println(BAR_TABLE);
		System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				p.getId(), p.getName(), p.getBrand(), p.getSize(), p.getColor(), p.getStock(), p.getCost());
		System.out.println(BAR_TABLE);
	}

	public void showBrand() {
		Scanner sc = new Scanner(System.in);

		System.out.print("검색할 브랜드명을 입력해주세요: ");
		String brand = sc.nextLine();

		System.out.println(BAR_TABLE);
		System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		System.out.println(BAR_TABLE);
		boolean flag = false;
		for (Product e : products) {
			if (brand.equals(e.getBrand())) {
				System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				e.getId(), e.getName(), e.getBrand(), e.getSize(), e.getColor(), e.getStock(), e.getCost());
				flag = true;
			}
		}
		if (!flag)
			System.out.println("* 검색한 브랜드 제품이 존재하지 않습니다.");
		System.out.println(BAR_TABLE);
	}

	public void showProductName() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 제품명을 입력해주세요: ");
		String name = sc.nextLine();

		System.out.println(BAR_TABLE);
		System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				"id", "name", "brand", "size", "color", "stock", "cost");
		System.out.println(BAR_TABLE);
		boolean flag = false;
		for (Product e : products) {
			if (name.equals(e.getName())) {
				System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s\n",
				e.getId(), e.getName(), e.getBrand(), e.getSize(), e.getColor(), e.getStock(), e.getCost());
				flag = true;
			}
		}
		if (!flag)
			System.out.println("* 검색한 제품명이 존재하지 않습니다.");
		System.out.println(BAR_TABLE);
	}

	public Order orderProduct(int lastOrderId, int currentUid) {
		Scanner sc = new Scanner(System.in);

		String id_str="";
		do{
			System.out.print("구매할 제품의 번호를 입력해주세요: ");
			id_str = sc.nextLine();
		} while (!StringUtils.containsOnlyNumbers(id_str));
		int id = Integer.parseInt(id_str);

		Product p = productsHash.get(id);
		if (p == null) {
			System.out.println("* 구매하실 제품번호가 존재하지 않습니다.");

		} else {
			String quantity_str;
			do{
				System.out.print("수량을 입력해주세요: ");
				quantity_str = sc.nextLine();
			} while (!StringUtils.containsOnlyNumbers(quantity_str));
			int quantity = Integer.parseInt(quantity_str);

			if (p.getStock() - quantity < 0)
				System.out.println("* 재고가 충분하지 않습니다: ");
			else {
				int idx = products.indexOf(p);
				p.setStock(p.getStock() - quantity);

				if (p.getStock() != 0) {
					products.set(idx, p);
					productsHash.put(id, p);

				} else {
					// 만약 재고가 0이라면 해당제품 삭제
					// why? 상품리스트에서 보이지 않도록 & 실제 비지니스에서 새로운 상품을 들여올 때 수량이 없던 기존제품을 늘려주는 것보다, 새로 등록하는 것이 더 편리.
					products.remove(idx);
					productsHash.remove(id);
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();

				Order o = new Order(lastOrderId+1, currentUid, id, dateFormat.format(date), productsHash.get(id).getCost()*quantity, quantity);
				System.out.println("* 구매완료.");
				return o;
			}
		}
		return null;
	}
}





