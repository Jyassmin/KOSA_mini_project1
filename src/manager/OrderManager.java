package manager;

import model.Order;
import model.Customer;
import util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
    model.Order class의 관련된 기능들을 구현한 클래스
*/
public class OrderManager {

    //private static final String ORDER_CSV_PATH = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\data\\order.csv";
    private static final String ORDER_CSV_PATH = "/Users/kyle/work/KOSA_mini_project1/data/order.csv";
    private static ArrayList<Order> orders;
    private static HashMap<Integer, ArrayList<Order>> orderHash;
    private final String BAR_TABLE = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

    /*
        order.csv 파일에서 읽어드린 데이터로 orders와 orderHash에 채워준다
     */
    OrderManager() throws IOException {
        orders = new ArrayList<Order>();
        orderHash = new HashMap<Integer, ArrayList<Order>>();

        File file = new File(ORDER_CSV_PATH);
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

    /*
        model.Order ArrayList와 hashmap에 넘겨받은 model.Order 객체 저장하는 메소드
     */
    public void addToList(Order o) {
        orders.add(o);

        // adding to orders hashmap
        ArrayList<Order> orderList = orderHash.get(o.getUid());
        if (orderList == null) {
            orderList = new ArrayList<Order>();
            orderList.add(o);
            orderHash.put(o.getUid(), orderList);
        } else {
            orderHash.get(o.getUid()).add(o);
        }
    }

    /*
        모든 model.Order 보여주는 메소드
     */
    public void showAll(CustomerManager cm, ProductManager pm) {
        show(orders, cm, pm);
    }

    /*
        고객 한명의 Order를 보여주는 메소드
     */
    public void showOrderByManager(CustomerManager cm, ProductManager pm) {
        String tempId = "";
        do {
            tempId = StringUtils.printAndGetInput("고객의 ID를 입력해주세요: ");
        } while (!StringUtils.containsOnlyNumbers(tempId));
        int cid = Integer.parseInt(tempId);
        ArrayList<Order> customerOrders = orderHash.get(cid);

        show(customerOrders, cm, pm);
    }

    /*
        넘겨받은 model.Order List안에 있는 model.Order 출력
     */
    public void showOrderByCustomer(CustomerManager cm, ProductManager pm, Customer cc) {
        int cid = cc.getId();
        ArrayList<Order> customerOrders = orderHash.get(cid);
        if (customerOrders != null)
            show(customerOrders, cm, pm);
        else
            System.out.println("* 주문 내역이 존재하지 않습니다.");
    }


    /*
        ArrayList<Order>, CustomerManager, ProductManager 타입의 variable들을 넘겨받으며 넘겨받은 os variable에 들어있는 
        정보들을 출력한다. 각 order의 uid(user id)와 pid(product id), cm(CustomerManager)와 pm(Product Manager)를 이용해
        customer의 이름과 product의 이름 또한 출력해준다
     */
    public void show(ArrayList<Order> os, CustomerManager cm, ProductManager pm) {
        System.out.println((BAR_TABLE));
        System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                "id", "customer ID", "customer name", "product ID", "product name", "order date", "total amount", "quantity");
        System.out.println((BAR_TABLE));
        if (os != null)
            for (Order e : os) {
                System.out.printf("| %-5s|  %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                        e.getId(), e.getUid(), cm.getCustomerName(e.getUid()), e.getPid(), pm.getProductName(e.getPid()),
                        e.getOrderDate(), e.getTotalAmount(), e.getQuantity());
            }
        System.out.println(BAR_TABLE);
    }

    /*
        OrderList에 저장되어 있는 데이터들로 order.csv 파일을 덮어쓴다
    */
    public void saveToFile() {
        File file = new File(ORDER_CSV_PATH);
        BufferedWriter writer = null;
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            writer = new BufferedWriter(new FileWriter(ORDER_CSV_PATH, false));

            for (Order item : orders) {
                String str;
                str = item.getId() + "," +
                        item.getUid() + "," +
                        item.getPid() + "," +
                        item.getOrderDate() + "," +
                        item.getTotalAmount() + "," +
                        item.getQuantity() + "\n";
                writer.append(str);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*
        가지고 있는 Order들 중에 마지막 아이템의 Id return해주는 메소드
     */
    public int getLastOrderID() {
        if (orders.size() > 0)
            return orders.get(orders.size() - 1).getId();
        else
            return 0;
    }

    /*
        Uid(user id)를 넘겨받고 넘겨받은 Uid로 주문된 주문내역을 지우는 메소드
     */
    public void removeOrderByUid(int Uid) {
        if (orderHash.get(Uid) != null) {
            orderHash.remove(Uid);
            orders.removeIf(o -> o.getUid() == Uid);
        }
    }
}