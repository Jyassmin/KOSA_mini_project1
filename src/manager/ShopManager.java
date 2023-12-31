package manager;

import model.*;
import ui.Menu;

import java.io.IOException;
import java.util.Scanner;

/*
    쇼핑몰 전체를 관리해주는 class
 */
public class ShopManager {
    private final Menu menu;
    private final CustomerManager customerManager;
    private final ProductManager productManager;
    private final OrderManager orderManager;
    private Customer currentCustomer;

    /*
        customer, product, order 각각의 Manager 클래스를 객체로 만듦으로써 정보를 가져오고
        프로그램을 시작한다
     */
    public ShopManager() throws IOException {
        // initialize class members
        menu = new Menu();
        customerManager = new CustomerManager();
        productManager = new ProductManager();
        orderManager = new OrderManager();

        startProgram();
        // save the data whenever user chooses to end the program
        saveDataToFile();
    }

    /*
       프로그램이 시작되면 input 0을 받을 때까지 프로그램을 반복한다
    */
    void startProgram() throws IOException {
        int runProgram = 0;
        do {
            currentCustomer = null;
            runProgram = loginOrRegister();

            // display appropriate menu depends on user role: manager or customer
            if (currentCustomer != null && currentCustomer.getIsSuperUser()) {
                runProgram = displayManagerMenu();
            } else if (runProgram != 0) {
                runProgram = displayShoppingMenu();
            }
        } while (runProgram != 0);
    }

    /*
       로그인 혹은 회원가입 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private int loginOrRegister() {
        do {
            Scanner scc = new Scanner(System.in);
            menu.loginOrRegisterMenu();

            int input = Integer.parseInt(scc.nextLine());
            switch (input) {
                // login
                case 1 -> {
                    currentCustomer = customerManager.login();
                    if (currentCustomer == null)
                        System.out.println("* 로그인에 실패했습니다.\n");
                }
                // register
                case 2 -> {
                    customerManager.register();
                }
                case 0 -> {
                    return 0;
                }
            }
        } while (currentCustomer == null);

        return 1;
    }

    // region MAIN MENU
    /*
       Manager 메인 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private int displayManagerMenu() throws IOException {
        int input;
        Scanner sc = new Scanner(System.in);
        // 관리자 메뉴
        do {
            // Display main menu
            /*
               1. 쇼핑몰
               2. 고객 관리
               3. 제품 관리
               4. 주문 관리
               0. 종료
             */
            menu.displayMainMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    int val = displayShoppingMenu();
                    // val이 5란 의미는 회원이 탈퇴했다는 뜻
                    if (val == 5) {
                        return val;
                    }
                }
                case 2 -> handleCustomerMenu();
                case 3 -> handleProductMenu();
                case 4 -> handleOrderMenu();
            }
        } while (input != 0);

        return input;
    }

    /*
       적절한 model.Customer 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
   */
    private int displayShoppingMenu() {
        int input;
        Scanner sc = new Scanner(System.in);
        // 사용자 메뉴
        do {
            menu.displayShoppingMenu(currentCustomer.getIsSuperUser());
            input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    productManager.show();
                    displayOrderDecision();
                }
                case 2 -> handleSearchMenu();
                case 3 -> orderManager.showOrderByCustomer(customerManager, productManager, currentCustomer);
                case 4 -> customerManager.edit(currentCustomer);
                case 5 -> {
                    customerManager.remove(currentCustomer.getId(), orderManager);
                    return input;
                }

            }

        } while (input != 0);

        return input;
    }
    // endregion

    private void saveDataToFile() {
        // saving data to files
        customerManager.saveToFile();
        productManager.saveToFile();
        orderManager.saveToFile();
    }

    /*
        적절한 검색 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private void handleSearchMenu() {
        int input;
        Scanner sc = new Scanner(System.in);
        do {
            // Display "Search" menu
            /*
                1. 제품번호
                2. 브랜드
                3. 제품명
                0. 이전 메뉴로
             */
            menu.displaySearchMenu(currentCustomer.getIsSuperUser());
            input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    productManager.showProductById();
                    displayOrderDecision();
                }
                case 2 -> {
                    productManager.showBrand();
                    displayOrderDecision();
                }
                case 3 -> {
                    productManager.showProductName();
                    displayOrderDecision();
                }
            }
        } while (input != 0);
    }

    /*
        적절한 customer 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private void handleCustomerMenu() throws IOException {
        int input;
        Scanner sc = new Scanner(System.in);
        do {
            // Display "model.Customer" menu
            /*
                1. 등록
                2. 탈퇴
                3. 모든 회원 확인
                0. 이전 메뉴로
             */
            menu.displayCustomerMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> customerManager.register();
                case 2 -> customerManager.remove(orderManager);
                case 3 -> customerManager.show();
            }
        } while (input != 0);
    }

    /*
        적절한 product 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private void handleProductMenu() throws IOException {
        int input;
        Scanner sc = new Scanner(System.in);
        do {
            // Display "model.Product" menu
            /*
                1. 등록
                2. 수정
                3. 삭제
                4. 모든 제품 확인
                0. 이전 메뉴로
             */
            menu.displayProductMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> productManager.add();
                case 2 -> productManager.edit();
                case 3 -> productManager.remove();
                case 4 -> productManager.show();
            }
        } while (input != 0);
    }

    /*
        적절한 order 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private void handleOrderMenu() throws IOException {
        int input;
        Scanner sc = new Scanner(System.in);
        do {
            // Display "model.Order" menu
            /*
                1. 모든 주문 보기
                2. 고객별로 보기
                0. 이전 메뉴로
             */
            menu.displayOrderMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> orderManager.showAll(customerManager, productManager);
                case 2 -> orderManager.showOrderByManager(customerManager, productManager);
            }
        } while (input != 0);
    }

    /*
        적절한 order 여부 메뉴를 display하고 사용자로부터 입력을 받은 다음 사용자 입력에 따라 다른 유형의 동작을 수행
    */
    private void displayOrderDecision() {
        int input;
        Scanner sc = new Scanner(System.in);
        do {
            // Display "model.Product" menu
            /*
                * 구매를 원하시면 1번을 눌러주세요.
                1. 구매하기
                0. 이전 메뉴로
             */
            menu.displayOrderDecision();
            input = sc.nextInt();

            if (input == 1) {
                Order o = productManager.orderProduct(orderManager.getLastOrderID(), currentCustomer.getId());
                if (o != null)
                    orderManager.addToList(o);
            } else if (input != 0)
                System.out.println("잘못된 입력입니다.");

        } while (input != 0);
    }
}
