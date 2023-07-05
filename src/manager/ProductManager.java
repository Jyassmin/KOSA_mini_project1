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
    //private static final String PRODUCT_CSV_PATH = "C:\\Users\\user\\Desktop\\데일리_과제\\프로젝트\\KOSA_mini_project1\\data\\product.csv";
    private static final String PRODUCT_CSV_PATH = "/Users/kyle/work/KOSA_mini_project1/data/product.csv";
    private static ArrayList<Product> products;
    private static HashMap<Integer, Product> productsHash;
    private final String BAR_TABLE = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

    /*
         product.csv 파일의 데이터 읽어들여 products와 productHash에 채워준다.
    */
    ProductManager() throws IOException {
    }

    {
        products = new ArrayList<Product>();
        productsHash = new HashMap<Integer, Product>();

        File file = new File(PRODUCT_CSV_PATH);
        if (file.exists()) {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String sLine = null;
            while ((sLine = inFile.readLine()) != null) {
                String[] temp_arr = sLine.split(",");
                try {
                    int tempId = Integer.parseInt(temp_arr[0].replaceAll("\\D", "")); // csv-utf8을 읽을 시 첫 문장은 다음과 같이 처리해야 int로 형변환 가능
                    addToList(new Product(
                            tempId,
                            temp_arr[1].trim(),
                            temp_arr[2].trim(),
                            Integer.parseInt(temp_arr[3].trim()),
                            temp_arr[4].trim(),
                            Integer.parseInt(temp_arr[5].trim()),
                            Long.parseLong(temp_arr[6].trim())));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
            inFile.close();
        }
    }

    /*
        넘겨받은 product id로 해당 product의 name을 return해준다(모든 제품을 출력하는 show메소드에서 사용)
     */
    public String getProductName(int id) {
        return productsHash.get(id).getName();
    }

    /*
         ArrayList와 hashmap에 model.Product 객체를 저장하는 메소드.(csv 읽어들이기와 등록에서 사용)
    */
    private void addToList(Product p) {
        products.add(p);
        productsHash.put(p.getId(), p);
    }

    /*
        제품명, 브랜드, 사이즈, 색상, 재고 그리고 가격 정보를 input으로 받고
         새로운 model.Product 객체를 생성 후 데이터에 넣어준다.(사이즈, 재고, 가격은 숫자로 받기에 문자열을 입력할 시 재입력하도록 처리)
     */
    public void add() throws IOException { // 한 줄씩 등록
        String name = StringUtils.printAndGetInput("제품명: ");

        String brand = StringUtils.printAndGetInput("브랜드: ");

        String size_str;
        do {
            size_str = StringUtils.printAndGetInput("사이즈: ");
        } while (!StringUtils.containsOnlyNumbers(size_str));
        int size = Integer.parseInt(size_str);


        String color = StringUtils.printAndGetInput("색상: ");

        String stock_str;
        do {
            stock_str = StringUtils.printAndGetInput("재고: ");
        } while (!StringUtils.containsOnlyNumbers(stock_str));
        int stock = Integer.parseInt(stock_str);

        String cost_str;
        do {
            cost_str = StringUtils.printAndGetInput("가격: ");
        } while (!StringUtils.containsOnlyNumbers(cost_str));
        long cost = Long.parseLong(cost_str);

        int newId = products.get(products.size() - 1).getId() + 1;

        Product p = new Product(newId, name, brand, size, color, stock, cost);
        addToList(p);
    }

    /*
        현재 ArrayList에 저장된 모든 상품을 출력.
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
        새로운 정보들로 데이터를 업데이트해준다.(현재 제품정보를 보여주며, 수정 시 실수를 방지)
     */
    public void edit() {
        String id_str;
        do {
            id_str = StringUtils.printAndGetInput("수정할 제품의 ID를 입력해주세요: ");
        } while (!StringUtils.containsOnlyNumbers(id_str));
        int id = Integer.parseInt(id_str);

        Product p1 = productsHash.get(id);
        int idx = products.indexOf(p1);
        if (idx == -1) {
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
        String name = StringUtils.printAndGetInput("제품명: ");

        String brand = StringUtils.printAndGetInput("브랜드: ");

        String size_str;
        do {
            size_str = StringUtils.printAndGetInput("사이즈: ");
        } while (!StringUtils.containsOnlyNumbers(size_str));
        int size = Integer.parseInt(size_str);

        String color = StringUtils.printAndGetInput("색상: ");

        String stock_str;
        do {
            stock_str = StringUtils.printAndGetInput("재고: ");
        } while (!StringUtils.containsOnlyNumbers(stock_str));
        int stock = Integer.parseInt(stock_str);

        String cost_str;
        do {
            cost_str = StringUtils.printAndGetInput("가격: ");
        } while (!StringUtils.containsOnlyNumbers(cost_str));
        int cost = Integer.parseInt(cost_str);

        Product p = new Product(id, name, brand, size, color, stock, cost);

        products.set(idx, p); // products ArrayList에 수정
        productsHash.put(id, p);    // products HashMap에 수정
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

            for (Product item : products) {
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

    /*
        삭제할 제품의 id를 입력받아 ArrayList와 HashMap에서 해당 제품을 삭제한다.
    */
    public void remove() {
        String id_str;
        do {
            id_str = StringUtils.printAndGetInput("삭제할 제품의 ID를 입력해주세요: ");
        } while (!StringUtils.containsOnlyNumbers(id_str));
        int id = Integer.parseInt(id_str);

        String input = StringUtils.printAndGetInput("정말 삭제하시겠습니까? 맞다면 y, 아니라면 n를 입력해주세요");

        if (input.equals("y")) { // String 비교에서는 equals!
            Product p = productsHash.get(id);
            products.remove(p);
            productsHash.remove(id);
        }
    }

    /*
        제품의 id를 입력받아, 해당 제품의 정보만 출력한다.(상세검색-제품번호)
    */
    public void showProductById() {
        String id_str;
        do {
            id_str = StringUtils.printAndGetInput("검색할 제품의 번호를 입력해주세요: ");
        } while (!StringUtils.containsOnlyNumbers(id_str));
        int id = Integer.parseInt(id_str);

        Product p = productsHash.get(id);
        if (p == null) {
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

    /*
        검색할 브랜드명을 입력받아, 해당 브랜드 제품들의 정보를 출력한다.
    */
    public void showBrand() {
        String brand = StringUtils.printAndGetInput("검색할 브랜드명을 입력해주세요: ");

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

    /*
        제품명을 입력받아, 해당 제품의 제품정보를 출력한다.
    */
    public void showProductName() {
        String name = StringUtils.printAndGetInput("검색할 제품명을 입력해주세요: ");

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

    /*
        제품 구매기능.
        제품의 번호를 입력받아 구매하고, 현재 제품을 구매하는 고객의 order에 구매제품의 정보를 저장한다.
        이를 위해 현재 고객의 Uid와 주문정보를 저장을 위해 index값을 받아온다.(index+1 위치에 주문정보 저장)
    */
    public Order orderProduct(int lastOrderId, int currentUid) {
        String id_str;
        do {
            id_str = StringUtils.printAndGetInput("구매할 제품의 번호를 입력해주세요: ");
        } while (!StringUtils.containsOnlyNumbers(id_str));
        int id = Integer.parseInt(id_str);

        Product p = productsHash.get(id);
        if (p == null) {
            System.out.println("* 구매하실 제품번호가 존재하지 않습니다.");

        } else {
            String quantity_str;
            do {
                quantity_str = StringUtils.printAndGetInput("수량을 입력해주세요: ");
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

                Order o = new Order(lastOrderId + 1, currentUid, id, dateFormat.format(date), productsHash.get(id).getCost() * quantity, quantity);
                System.out.println("* 구매완료.");
                return o;
            }
        }
        return null;
    }
}
