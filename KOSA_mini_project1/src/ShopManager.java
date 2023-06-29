import java.io.IOException;
import java.util.Scanner;

public class ShopManager{
    private final Menu menu;
    private final CustomerManager customerManager;
    private final ProductManager productManager;
    private Customer currentCustomer;
    private  Scanner sc;

    ShopManager() throws IOException {
        // initialize class members
        menu = new Menu();
        customerManager = new CustomerManager();
        productManager = new ProductManager();
		sc = new Scanner(System.in);

        startProgram();
        // save the data whenever user chooses to end the program
        saveDataToFile();
    }

    void startProgram() throws IOException {
        int runProgram = 0;
        do{
            currentCustomer = null;
            loginOrRegister();

            // display appropriate menu depends on user role: manager or customer
            if(currentCustomer.getIsSuperUser()){
                runProgram = displayManagerMenu();
            } else {
                runProgram = displayCustomerMenu();
            }
        } while(runProgram != 0);
    }

    private void loginOrRegister(){
        do
        {
            menu.loginOrRegisterMenu();

            int input = Integer.parseInt(sc.nextLine());
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
                    return;
                }
            }
        }while(currentCustomer == null);
    }

    // region MAIN MENU
    private int displayManagerMenu() throws IOException {
        int input;

        // 관리자 메뉴
        do {
            // Display main menu
                /*
                   1. 쇼핑몰
                   2. 고객 관리
                   3. 제품 관리
                   0. 종료
                 */
            menu.displayMainMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> handleShoppingMenu();
                case 2 -> handleCustomerMenu();
                case 3 -> handleProductMenu();
            }
        } while (input != 0);

        return input;
    }

    private int displayCustomerMenu(){
        int input;

        // 사용자 메뉴
        do {
            // Display main menu
                /*
                    1. 전체 제품
                    2. 검색
                    3. 정보 수정
                    4. 탈퇴
                    0. 종료
                */
            menu.displayCustomerShoppingMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> productManager.show();
                case 2 -> handleSearchMenu();
                case 3 -> customerManager.edit(currentCustomer);
                case 4 -> {
                    customerManager.remove(currentCustomer.getId());
                    return input;
                }
            }

        } while (input != 0);

        return input;
    }
    // endregion

    private void saveDataToFile(){
        // saving data to files
        customerManager.saveToFile();
        productManager.saveToFile();
    }

    /*
        Description: displays appropriate shopping menu and get input from user. Then do different types of actions
                    depends on user input
    */
    private void handleShoppingMenu() {
        int input;

        do {
            // Display "Shopping" menu
            /*
                1. 전체 제품
                2. 검색
                0. 이전 메뉴로
            */
            menu.displayShoppingMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    productManager.show();
                    displayOrderDecision();
                }
                case 2 -> handleSearchMenu();
            }
        } while (input != 0);
    }

    /*
        Description: displays appropriate search menu and get input from user. Then do different types of actions
                    depends on user input
    */
    private void handleSearchMenu() {
        int input;

        do {
            // Display "Search" menu
            /*
                1. 제품번호
                2. 브랜드
                3. 제품명
                0. 이전 메뉴로
             */
            menu.displaySearchMenu();
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

    private void handleCustomerMenu() throws IOException {
        int input;

        do {
            // Display "Customer" menu
            /*
                1. 등록
                2. 탈퇴
                3. 모든 회원 확인
                0. 이전 메뉴로
             */
            menu.displayCustomerMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> customerManager.add();
                case 2 -> customerManager.remove();
                case 3 -> customerManager.show();
            }
        } while (input != 0);
    }

    private void handleProductMenu() throws IOException {
        int input;

        do {
            // Display "Product" menu
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

    private void displayOrderDecision() {
        int input;

        do {
            // Display "Product" menu
            /*
                * 구매를 원하시면 1번을 눌러주세요.
                1. 구매하기
                0. 이전 메뉴로
             */
            menu.displayOrderDecision();
            input = sc.nextInt();

            if (input == 1)
                productManager.orderProduct();
            else if (input != 0)
                System.out.println("잘못된 입력입니다.");

        } while (input != 0);
    }
}
