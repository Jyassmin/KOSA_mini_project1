import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class CustomerManager { // main에서 한 번 실행시켜 ArrayList생성하고, methods로 조작.(so, static으로)
	private static ArrayList<Customer> customers;
	private static HashMap<String, Customer> customersHash;
	
	CustomerManager() throws IOException {} { 
		customers = new ArrayList<Customer>();
		customersHash = new HashMap<String, Customer>();
		
		String path = "/Users/kyle/Repository/KOSA_mini_project1/KOSA_mini_project1/data/customer.csv";
		File file = new File(path);
		if(file.exists()) {
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;
		    while( (sLine = inFile.readLine()) != null ) {
				String[] temp_arr = sLine.split(","); 
		        try{
		        	addToList(new Customer(
		        			temp_arr[0].trim(),
		        			temp_arr[1].trim(),
		            		temp_arr[2].trim(),
		            		temp_arr[3].trim(),
		            		temp_arr[4].trim(),
		            		Integer.parseInt(temp_arr[5].trim()),
		            		temp_arr[6].trim()));
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
		    	
			    // System.out.println(sLine); //읽어들인 문자열을 출력 합니다.
		    }
		    inFile.close();
		}
    } 	
	
	private void addToList(Customer c) {
		customers.add(c);
		customersHash.put(c.id, c);
	}
	
	public void add() throws IOException{ // 한 줄씩 등록
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름: ");
		String name = sc.nextLine(); //br.readLine();
		
		System.out.print("닉네임: ");
		String nickname = sc.nextLine(); //br.readLine();
		
		System.out.print("주소: ");
		String address = sc.nextLine(); //br.readLine();
		
		System.out.print("나이: ");
		Integer age = Integer.parseInt(sc.nextLine()); //br.readLine());
		
		System.out.print("비밀번호: ");
		String password = sc.nextLine(); //br.readLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine(); //br.readLine();
		
		int newId = Integer.parseInt(customers.get(customers.size()-1).id) + 1;
		
		Customer c = new Customer(String.valueOf(newId), email, password, name, nickname, age, address);
		addToList(c);
	}
	
	public void show() { // 현재 모든 data 출력(모든제품show)
		System.out.println("id   email   password   name   nickname   age   address");
		for (Customer e : customers)
			System.out.printf("%s %s %s %s %s %d %s\n", e.id, e.email, e.password, e.name, e.nickname, e.age, e.address);
	}
	
	public void delete(String index) { // id를 받아 삭제 
		customers.remove(Integer.parseInt(index)-1); // id가 1부터 시작해서 1 빼줌 
	}
	
	public void edit() { // 수정
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 고객의 ID를 입력해주세요: ");
		String id = sc.nextLine(); //br.readLine();
		
		System.out.println("아래에 수정될 내용을 적어주세요: ");
		
		System.out.print("이름: ");
		String name = sc.nextLine(); //br.readLine();
		
		System.out.print("닉네임: ");
		String nickname = sc.nextLine(); //br.readLine();
		
		System.out.print("주소: ");
		String address = sc.nextLine(); //br.readLine();
		
		System.out.print("나이: ");
		Integer age = Integer.parseInt(sc.nextLine()); //br.readLine());
		
		System.out.print("비밀번호: ");
		String password = sc.nextLine(); //br.readLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine(); //br.readLine();
		
		Customer c = new Customer(id, email, password, name, nickname, age, address);
		
		customers.set(Integer.parseInt(id)-1, c); // id가 1부터 시작해서 1 빼줌 
		customersHash.put(id, c);
	}
	
	public void saveToFile() {
		String path = "/Users/kyle/Repository/KOSA_mini_project1/KOSA_mini_project1/data/customer.csv";
		File file = new File(path);
		BufferedWriter writer = null;
	    try {
	    	if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
	    	writer = new BufferedWriter(new FileWriter(path, false));
	    	
			for(Customer item : customers) {
				String str;
				str = item.id + "," + item.email + "," + item.password + "," + item.name + "," + item.nickname + "," + String.valueOf(item.age) + "," + item.address + "\n";
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
		String id = sc.nextLine(); //br.readLine();
		
		System.out.println("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요");
		String input = sc.nextLine(); //br.readLine();
		if (input.equals("y")) {
			Customer c = customersHash.get(id);
			int idx = customers.indexOf(c);
			customers.remove(idx);
			customersHash.remove(id);
		}
	}

}
