import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// hash 처리 해야줘야함
public class ProductManager { // main에서 한 번 실행시켜 ArrayList생성하고, methods로 조작.(so, static으로)
	private static String Customer_CSV_Path = "/Users/kyle/work/KOSA_mini_project/Kosa_mini_project1/data/product.csv";
	private static ArrayList<Product> products;
	private static HashMap<Integer, Product> productsHash;

	ProductManager() throws IOException {} {
		products = new ArrayList<Product>();
		productsHash = new HashMap<Integer, Product>();

		File file = new File(Customer_CSV_Path);
		if(file.exists()) {
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;
		    while( (sLine = inFile.readLine()) != null ) {
				String[] temp_arr = sLine.split(","); 
		        try{
		        	addToList(new Product(
		        			Integer.parseInt(temp_arr[0].trim()),
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
		    	
			    // System.out.println(sLine); //읽어들인 문자열을 출력 합니다.
		    }
		    inFile.close();
		}
    } 	
	
	private void addToList(Product p) {
		products.add(p);
        // ********나중에 uncomment!!!!!!!!!!!!!
		// productsHash.put(Integer.valueOf(p.getId()), p);

	}
	
	public void add() throws IOException{ // 한 줄씩 등록
		Scanner sc = new Scanner(System.in);
		
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
		
		int newId = products.get(products.size()-1).id + 1;
		
		Product p = new Product(newId, name, brand, size, color, stock, cost);
		addToList(p);
	}
	
	public void show() { // 현재 모든 data 출력(모든제품show)
		System.out.println("id   name   brand   size   color   stock   cost");
		for (Product e : products)
			System.out.printf("%s %s %s %d %s %d %d\n", e.id, e.name, e.brand, e.size, e.color, e.stock, e.cost);
	}
	
	public void delete(int index) { // id를 받아 삭제
		products.remove(index-1); // id가 1부터 시작해서 1 빼줌
	}
	
	public void edit() { // 수정
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 고객의 ID를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());
		
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
		
		// 수정 products.set(id-1, p); // id가 1부터 시작해서 1 빼줌
		productsHash.put(id, p);
	}
	
	public void saveToFile() {
		File file = new File(Customer_CSV_Path);
		BufferedWriter writer = null;
	    try {
	    	if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
	    	writer = new BufferedWriter(new FileWriter(Customer_CSV_Path, false));
	    	
			for(Product item : products) {
				String str;
				str = item.id + "," + item.name + "," + item.brand + "," + String.valueOf(item.size) + "," + item.color + "," + String.valueOf(item.stock) + "," + String.valueOf(item.cost) + "\n";
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
		System.out.print("삭제할 고객의 ID를 입력해주세요: ");
		String id = sc.nextLine();
		
		System.out.println("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요");
		String input = sc.nextLine();
		if (input.equals("y")) {
			Product p = productsHash.get(id);
			int idx = products.indexOf(p);
			products.remove(idx);
			productsHash.remove(id);
		}
	}

}