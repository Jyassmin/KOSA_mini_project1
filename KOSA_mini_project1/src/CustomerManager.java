import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// command alt o
// command e
// commad shift enter

public class CustomerManager { // main에서 한 번 실행시켜 ArrayList생성하고, methods로 조작.(so, static으로)

	private static final String Customer_CSV_Path = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\KOSA_mini_project1\\data\\customer.csv";
	private static ArrayList<Customer> customers;
	private static HashMap<Integer, Customer> customersHash;
	private final Scanner sc;

	CustomerManager() throws IOException {} {
		customers = new ArrayList<Customer>();
		customersHash = new HashMap<Integer, Customer>();
		sc = new Scanner(System.in);

		File file = new File(Customer_CSV_Path);
		if(file.exists()) {
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;
		    while( (sLine = inFile.readLine()) != null ) {
				String[] temp_arr = sLine.split(",");
		        try{
					int tempId = Integer.parseInt(temp_arr[0].replaceAll("\\D", ""));
		        	addToList(new Customer(
							tempId,
		        			temp_arr[1].trim(),
		            		temp_arr[2].trim(),
		            		temp_arr[3].trim(),
		            		temp_arr[4].trim(),
		            		Integer.parseInt(temp_arr[5].trim()),
		            		temp_arr[6].trim(),
							Boolean.parseBoolean(temp_arr[7].trim())));
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
		    }
		    inFile.close();
		}
    }

	public String getCustomerName(int id){
		return customersHash.get(id).getName();
	}

	private boolean isCustomerExists(String nickname) {
		for (Customer customer : customers) {
			if (customer.getNickname().equals(nickname)) {
				return true; // Customer with matching nickname found
			}
		}
		return false; // Customer not found
	}
	private Customer isCustomerExists(String nickname, String password) {
		for (Customer customer : customers) {
			if (customer.getNickname().equals(nickname) && customer.getPassword().equals(password)) {
				return customer; // Customer with matching properties found
			}
		}
		return null; // Customer not found
	}
	
	private void addToList(Customer c) {
		customers.add(c);
		customersHash.put(c.getId(), c);
	}

	public Customer login(){
		System.out.print("닉네임: ");
		String inputNickname = sc.nextLine();

		System.out.print("비밀번호: ");
		String inputPassword = sc.nextLine();

		return isCustomerExists(inputNickname, inputPassword);
	}

	public void register(){

		System.out.print("이름: ");
		String name = sc.nextLine();

		System.out.print("닉네임: ");
		String nickname = sc.nextLine();
		// checking if the nickname already exist in database
		// if it is, ask for different nickname otherwise, continue
		while(isCustomerExists(nickname))
		{
			System.out.println("* 동일한 닉네임이 존재합니다. 다른 닉네임을 입력해주세요\n");
			System.out.print("닉네임: ");
			nickname = sc.nextLine();
		}

		System.out.print("주소: ");
		String address = sc.nextLine();

		System.out.print("나이: ");
		int age = Integer.parseInt(sc.nextLine());

		System.out.print("비밀번호: ");
		String password = sc.nextLine();

		System.out.print("이메일: ");
		String email = sc.nextLine();

		System.out.print("관리자면 y, 아니라면 n: ");
		boolean isSuperUser = false;
		String input = sc.nextLine();
		if(input.equals("y"))
			isSuperUser = true;

		int newId = 0;
		if(customers.size() != 0)
			newId = customers.get(customers.size()-1).getId() + 1;

		Customer c = new Customer(newId, email, password, name, nickname, age, address, isSuperUser);
		addToList(c);
	}
	
	public void add() throws IOException{ // 한 줄씩 등록
		System.out.print("이름: ");
		String name = sc.nextLine(); //br.readLine();
		
		System.out.print("닉네임: ");
		String nickname = sc.nextLine(); //br.readLine();
		
		System.out.print("주소: ");
		String address = sc.nextLine(); //br.readLine();
		
		System.out.print("나이: ");
		int age = Integer.parseInt(sc.nextLine()); //br.readLine());
		
		System.out.print("비밀번호: ");
		String password = sc.nextLine(); //br.readLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine(); //br.readLine();

		System.out.print("관리자면 y, 아니라면 n: ");
		boolean isSuperUser = false;
		String input = sc.nextLine();
		if(input.equals("y"))
			isSuperUser = true;

		int newId = customers.get(customers.size()-1).getId() + 1;

		Customer c = new Customer(newId, email, password, name, nickname, age, address, isSuperUser);
		addToList(c);
	}
	
	public void show() { // 현재 모든 data 출력(모든제품show)
		System.out.printf("%-5s %-20s %-9s %-20s %-4s %-30s %15s\n",
				"id", "email", "name", "nickname", "age", "address", "isSuperUser");
		for (Customer e : customers) {
			System.out.printf("%-5s %-20s %-9s %-20s %-4s %-30s %15s\n",
					e.getId(), e.getEmail(), e.getName(), e.getNickname(), String.valueOf(e.getAge()), e.getAddress(), e.getIsSuperUser());
		}
	}
	
	public void edit(Customer currentCustomer) { // 현재 사용자 정보 수정
		if(currentCustomer != null){

			System.out.println(("---------현재정보----------"));
			System.out.printf("%-15s %-15s %-15s %-4s %-30s\n",
				"name", "nickname", "password", "age", "address");
			System.out.printf("%-15s %-15s %-15s %-4s %-30s\n",
				currentCustomer.getName(), currentCustomer.getNickname(), currentCustomer.getPassword(), currentCustomer.getAge(), currentCustomer.getAddress());
			System.out.println(("-------------------------"));

			int id = currentCustomer.getId();
			System.out.println("아래에 수정될 내용을 적어주세요");

			System.out.print("이름: ");
			String name = sc.nextLine();

			System.out.print("닉네임: ");
			String nickname = sc.nextLine();

			System.out.print("주소: ");
			String address = sc.nextLine();

			System.out.print("나이: ");
			int age = Integer.parseInt(sc.nextLine());

			System.out.print("비밀번호: ");
			String password = sc.nextLine();

			System.out.print("이메일: ");
			String email = sc.nextLine();

			int idx = customers.indexOf(currentCustomer);
			currentCustomer = new Customer(id, email, password, name, nickname, age, address);
			customers.set(idx, currentCustomer); // customers ArrayList에 수정
			customersHash.put(id, currentCustomer);	// customers HashMap에 수정
		}
	}
	
	public void saveToFile() {
		File file = new File(Customer_CSV_Path);
		BufferedWriter writer = null;
	    try {
	    	if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
	    	writer = new BufferedWriter(new FileWriter(Customer_CSV_Path, false));
	    	
			for(Customer item : customers) {
				String str;
				str = item.getId() + "," +
						item.getEmail() + "," +
						item.getPassword() + "," +
						item.getName() + "," +
						item.getNickname() + "," +
						String.valueOf(item.getAge()) + "," +
						item.getAddress() + "," +
						item.getIsSuperUser() + "\n";
				writer.append(str);
			}
		    writer.close();			
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		} 
	}

	public void remove() {
		System.out.print("삭제할 고객의 ID를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());

		System.out.print("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요: ");
		String input = sc.nextLine();

		if (input.equals("y")) {
			Customer c = customersHash.get(id);
			customers.remove(c);
			customersHash.remove(id);
		}
	}

	public void remove(int id){
		System.out.print("정말 탈퇴하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요: ");
		String input = sc.nextLine();

		if (input.equals("y")) {
			Customer c = customersHash.get(id);
			customers.remove(c);
			customersHash.remove(id);
		}
	}
}
