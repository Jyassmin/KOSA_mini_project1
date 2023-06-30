import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// command alt o
// command e
// commad shift enter

/*
    Customer class의 관련된 기능들을 구현한 클래스
*/
public class CustomerManager {

	//private static final String CUSTOMER_CSV_PATH = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\KOSA_mini_project1\\data\\customer.csv";
	private static final String CUSTOMER_CSV_PATH = "/Users/kyle/work/KOSA_mini_project/Kosa_mini_project1/data/customer.csv";
	private static ArrayList<Customer> customers;
	private static HashMap<Integer, Customer> customersHash;
	private final Scanner sc;
	private final String BAR_TABLE = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

	/*
	 	order.csv 파일에서 읽어드린 데이터로 customers와 customerHash에 채워준다
	*/
	CustomerManager() throws IOException {} {
		customers = new ArrayList<Customer>();
		customersHash = new HashMap<Integer, Customer>();
		sc = new Scanner(System.in);

		File file = new File(CUSTOMER_CSV_PATH);
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

	/*
	 	넘겨받은 customer의 id로 customer name을 return한다
	*/
	public String getCustomerName(int id){
		return customersHash.get(id).getName();
	}

	/*
	 	넘겨받은 customer의 nickname이 데이터에 이미 존재하는 이름인지 확인 후
		있다면 true, 없으면 false를 return한다
	*/
	private boolean isCustomerExists(String nickname) {
		for (Customer customer : customers) {
			if (customer.getNickname().equals(nickname)) {
				return true; // Customer with matching nickname found
			}
		}
		return false; // Customer not found
	}


	/*
	 	넘겨받은 customer의 nickname과 password가 데이터에 이미 존재하는지 확인 후
		있다면 customer 객체를, 없으면 null을 return한다
	*/
	private Customer isCustomerExists(String nickname, String password) {
		for (Customer customer : customers) {
			if (customer.getNickname().equals(nickname) && customer.getPassword().equals(password)) {
				return customer; // Customer with matching properties found
			}
		}
		return null; // Customer not found
	}

	/*
	 	Customer ArrayList와 hashmap에 넘겨받은 Customer 객체 저장하는 메소드
	*/
	private void addToList(Customer c) {
		customers.add(c);
		customersHash.put(c.getId(), c);
	}

	/*
	 	닉네임과 비밀번호 input 받고 닉네임과 비밀번호가 데이터에 존재하는지 확인 후
	 	존재한다면 true, 아니면 false를 return한다
	*/
	public Customer login(){
		System.out.print("닉네임: ");
		String inputNickname = sc.nextLine();

		System.out.print("비밀번호: ");
		String inputPassword = sc.nextLine();

		return isCustomerExists(inputNickname, inputPassword);
	}

	/*
		이름, 닉네임, 주소, 나이, 비밀번호, 이메일과 role 정보를 input으로 받고
	 	같은 닉네임의 customer가 데이터에 존재하지 않으면, 새로운 Customer 객체를 생성 후
	 	데이터에 넣어준다
	*/
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

	/*
		현재 모든 data 출력(모든사용자show)
 	*/
	public void show() {
		System.out.println(BAR_TABLE);
		System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-35s %-5s\n",
				"id", "email", "name", "nickname", "age", "address", "isSuperUser");
		System.out.println(BAR_TABLE);
		for (Customer e : customers) {
			System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-35s %-5s\n",
					e.getId(), e.getEmail(), e.getName(), e.getNickname(), String.valueOf(e.getAge()), e.getAddress(), e.getIsSuperUser());
		}
		System.out.println(BAR_TABLE);
	}

	/*
		현재 사용자 정보 수정
 	*/
	public void edit(Customer currentCustomer) {
		if(currentCustomer != null){

			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ [ 현 재 정 보 ] ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.printf("| %-15s %-20s %-20s %-10s %-30s\n",
				"name", "nickname", "password", "age", "address");
			System.out.printf("| %-15s %-20s %-20s %-10s %-30s\n",
				currentCustomer.getName(), currentCustomer.getNickname(), currentCustomer.getPassword(), currentCustomer.getAge(), currentCustomer.getAddress());
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

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

	/*
		CustomerList에 저장되어 있는 데이터들로 customer.csv 파일을 덮어쓴다
	*/
	public void saveToFile() {
		File file = new File(CUSTOMER_CSV_PATH);
		BufferedWriter writer = null;
	    try {
	    	if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
	    	writer = new BufferedWriter(new FileWriter(CUSTOMER_CSV_PATH, false));
	    	
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

	/*
		삭제할 Customer의 id를 입력받고 confirm이 되면 데이터에서 해당 customer를 삭제한다
	*/
	public void remove() {
		System.out.print("삭제할 고객의 ID를 입력해주세요: ");
		int id = Integer.parseInt(sc.nextLine());

		System.out.print("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요: ");
		String input = sc.nextLine();

		if (input.equals("y")) {
			removeFromList(id);
		}
	}

	/*
		현재 customer를 데이터에서 지운다
	*/
	public void remove(int id){
		System.out.print("정말 탈퇴하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요: ");
		String input = sc.nextLine();

		if (input.equals("y")) {
			removeFromList(id);
		}
	}
	
	/*
		넘겨받은 customer의 id로 arraylist와 hashmap에서 삭제한다
	 */
	public void removeFromList(int id)
	{
		Customer c = customersHash.get(id);
		customers.remove(c);
		customersHash.remove(id);
	}
}
