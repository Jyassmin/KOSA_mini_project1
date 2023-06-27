import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CustomerManager { // main에서 한 번 실행시켜 ArrayList생성하고, methods로 조작.(so, static으로)
	private static ArrayList<Customer> customers;
	
	CustomerManager() throws IOException {} { 
		customers = new ArrayList<Customer>();
		
		String path = "/Users/kyle/Repository/KOSA_mini_project1/KOSA_mini_project1/data/customer.csv";
		File file = new File(path);
		if(file.exists()) {
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;
		    while( (sLine = inFile.readLine()) != null ) {
				String[] temp_arr = sLine.split(","); 
		        try{
		        	addToList(new Customer(Integer.parseInt(temp_arr[0]),
		            		temp_arr[1],
		            		temp_arr[2],
		            		temp_arr[3],
		            		temp_arr[4],
		            		Integer.parseInt(temp_arr[5]),
		            		temp_arr[6]));
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
	}
	
	public void add() { // 한 줄씩 등록
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름: ");
		String name = sc.nextLine();
		
		System.out.print("닉네임: ");
		String nickname = sc.nextLine();
		
		System.out.print("주소: ");
		String address = sc.nextLine();
		
		System.out.print("나이: ");
		Integer age = sc.nextInt();
		
		System.out.print("비밀번호: ");
		String password = sc.nextLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		int newId = customers.get(customers.size()-1).id + 1;
		
		Customer c = new Customer(newId, email, password, name, nickname, age, address);
		addToList(c);
	}
	
	public void show() { // 현재 모든 data 출력(모든제품show)
		System.out.println("id   email   password   name   nickname   age   address");
		for (Customer e : customers)
			System.out.printf("%d %s %s %s %s %d %s\n", e.id, e.email, e.password, e.name, e.nickname, e.age, e.address);
	}
	
	public void delete(String index) { // id를 받아 삭제 
		customers.remove(Integer.parseInt(index)-1); // id가 1부터 시작해서 1 빼줌 
	}
	
	public void edit(int index, String email, String password, String name, String nickname, int age, String address) { // 수정 
		Customer c = new Customer(index, email, password, name, nickname, age, address);
		customers.set(index-1, c); // id가 1부터 시작해서 1 빼줌 
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

}
