
import java.io.*;
import java.util.*;

public class Main {
   public static final String delimiter = ",";
   private static ArrayList<Customer> customers = new ArrayList<Customer>();
   private static ArrayList<Product> products = new ArrayList<Product>();
   
   public void displaymenu() {
	   System.out.println("1. 쇼핑몰");
	   System.out.println("2. 고객 관리");
	   System.out.println("3. 제품 관리");
	   
	   Scanner sc = new Scanner(System.in);
	   int input = sc.nextInt();
	   
	   switch(input) {
	   case 1:
		   break;
	   case 2:
		   break;
	   case 3:
		   break;
	   }
   }
   
   public static void customer_read(String csvFile) {
      try {
         File file = new File(csvFile);
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr); 
         String line = "";
         String[] tempArr;
         
         while((line = br.readLine()) != null) { // 한 줄씩 입력 받기 
            tempArr = line.split(delimiter);
            
            // Customer객체에 한 줄의 데이터를 넣어 인스턴스 생성 
            Customer c = new Customer (
            		tempArr[0],
            		tempArr[1],
            		tempArr[2],
            		tempArr[3],
            		tempArr[4],
            		Integer.parseInt(tempArr[5]),
            		tempArr[6]            		
            		);
            
            customers.add(c); // ArrayList에 인스턴스 추가 
         }
         br.close();
         
     } catch(IOException ioe) {
        ioe.printStackTrace();
     } 
   }
   
   public static void product_read(String csvFile) {
	      try {
	         File file = new File(csvFile);
	         FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr); 
	         String line = "";
	         String[] tempArr;
	         
	         while((line = br.readLine()) != null) { // 한 줄씩 입력 받기 
	            tempArr = line.split(delimiter);
	            
	            // Customer객체에 한 줄의 데이터를 넣어 인스턴스 생성 
	            Product p = new Product (
	            		tempArr[0],
	            		tempArr[1],
	            		tempArr[2],
	            		Integer.parseInt(tempArr[3]),
	            		tempArr[4],
	            		Integer.parseInt(tempArr[5]),
	            		Integer.parseInt(tempArr[6])            		
	            		);
	            
	            products.add(p); // ArrayList에 인스턴스 추가 
	         }
	         br.close();
	         
	     } catch(IOException ioe) {
	        ioe.printStackTrace();
	     } 
	   }
   
   public static void main(String[] args) {
      // csv file to read
      String customer_csv = "/Users/kyle/eclipse-workspace/KOSA_mini_project1/data/customer.csv";
      customer_read(customer_csv);
      String product_csv = "/Users/kyle/eclipse-workspace/KOSA_mini_project1/data/product.csv";
      product_read(product_csv);
      
      
      customers.get(0).edit("asdasdasd@gmail.com", "jkl", "하재민",	"재민",	23,	"서울특별시 마포구 성암로 192 (상암동)");

   }
}