import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Teacher {
	public class CustomerItem {
		private int id = -1;
		private String name;
		private String phoneNumber;
		private String address;
		
		CustomerItem() { } // 초기화 
		
		CustomerItem(int id, String name, String phoneNumber, String address) { // 생성자 
			this.id = id;
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.address = address;
		}
		
		public int getId() { return id; }
		public void setId(int id) { this.id = id; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		public String getPhoneNumber() { return phoneNumber; }
		public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
		public String getAddress() { return address; }
		public void setAddress(String address) { this.address = address; }
	}
	
	public class CustomerManager {
		private ArrayList<CustomerItem> customerList;
		private HashMap<Integer,CustomerItem> customerHash; // 검색용 
		
		CustomerManager() throws IOException {} { 
			customerList = new ArrayList<CustomerItem>();
			customerHash = new HashMap<Integer,CustomerItem>();
			
			String path = "customer.csv";
			File file = new File(path);
			if(file.exists()) {
			    BufferedReader inFile = new BufferedReader(new FileReader(file));
			    String sLine = null;
			    while( (sLine = inFile.readLine()) != null ) {
					String[] arr = sLine.split(","); 
			        try{
			            int id = Integer.parseInt(arr[0].trim());
			            String name = arr[1].trim();
			    		String phoneNumber = arr[2].trim();
			    		String address = arr[3].trim();
//			            System.out.println(id); 
			            CustomerItem item = new CustomerItem(id, name, phoneNumber, address);
			            addCustomer(item);
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			        }
			    	
				    System.out.println(sLine); //읽어들인 문자열을 출력 합니다.
			    }
			}
	    } 
		
		public void addCustomer(CustomerItem item) {
			customerList.add(item);
			customerHash.put(item.getId(), item);
		}
		
		public CustomerItem getCutomerInfo(int id) {  
			return customerHash.get(id);
		}
		
		public void saveToFile() {
			String path = "customer.csv";
			File file = new File(path);
			BufferedWriter writer = null;
		    try {
		    	if (file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				} else {
					System.out.println("File already exists.");
				}
		    	writer = new BufferedWriter(new FileWriter(path, true));
				for(CustomerItem item : customerList) {
					String str;
					str = item.getId() + "," + item.getName() + "," + 
						  item.getPhoneNumber() + "," + item.getAddress();
					writer.append(str);
				}
			    writer.close();			
			} catch (IOException e) {
			  System.out.println("An error occurred.");
			  e.printStackTrace();
			} 
		}		
	}
	
	public static void main(String[] args) {
	}
}