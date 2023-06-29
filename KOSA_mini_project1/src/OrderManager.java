import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OrderManager { // main에서 한 번 실행시켜 ArrayList생성하고, methods로 조작.(so, static으로)

    private static final String Order_CSV_Path = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\KOSA_mini_project1\\data\\order.csv";
    private static ArrayList<Order> orders;
    private static HashMap<Integer, ArrayList<Order>> orderHash;
    private final Scanner sc;

    OrderManager() throws IOException
    {
        orders = new ArrayList<Order>();
        orderHash = new HashMap<Integer, ArrayList<Order>>();
        sc = new Scanner(System.in);

        File file = new File(Order_CSV_Path);
        if (file.exists()) {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String sLine = null;
            while ((sLine = inFile.readLine()) != null) {
                String[] temp_arr = sLine.split(",");
                try {
                    int tempId = Integer.parseInt(temp_arr[0].replaceAll("\\D", ""));
                    addToList(new Order(
                            tempId,
                            Integer.parseInt(temp_arr[1].trim()),
                            Integer.parseInt(temp_arr[2].trim()),
                            temp_arr[3].trim(),
                            Double.parseDouble(temp_arr[4].trim()),
                            Integer.parseInt(temp_arr[5].trim())));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
            inFile.close();
        }
    }

    private void addToList(Order o) {
        orders.add(o);

        // adding to orders hashmap
        ArrayList<Order> orderList = orderHash.get(o.getUid());
        if(orderList == null){
            orderList = new ArrayList<Order>();
            orderList.add(o);
            orderHash.put(o.getUid(), orderList);
        } else {
            orderHash.get(o.getUid()).add(o);
        }
    }

    public void showAll(CustomerManager cm, ProductManager pm){
        show(orders, cm, pm);
    }

    public void showOrderByCustomer(CustomerManager cm, ProductManager pm){
        System.out.print("고객의 ID를 입력해주세요: ");
        int cid = Integer.parseInt(sc.nextLine());
        ArrayList<Order> customerOrders = orderHash.get(cid);

        String userName = cm.getCustomerName(cid);
        show(customerOrders, cm, pm);
    }

    public void show(ArrayList<Order> os, CustomerManager cm,  ProductManager pm) {
        System.out.printf("%-5s %-10s %-20s %-9s %-20s %-20s %-4s %-30s\n",
                "id", "customer ID", "customer name", "product ID", "product name", "order date", "total amount", "quantity");
        for (Order e : os) {
            System.out.printf("%-5s %-10s %-20s %-9s %-20s %-20s %-4s %-30s\n",
                    e.getId(), e.getUid(), cm.getCustomerName(e.getUid()), e.getPid(), pm.getProductName(e.getPid()),
                    e.getOrderDate(), e.getTotalAmount(), e.getQuantity());
        }
    }
}