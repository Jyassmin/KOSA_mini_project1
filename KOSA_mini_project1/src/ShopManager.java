import java.io.IOException;
import java.util.Scanner;

public class ShopManager{
    private final Menu menu;
    private final CustomerManager customerManager;
    private final ProductManager productManager;

    ShopManager() throws IOException {
        menu = new Menu();
        customerManager = new CustomerManager();
        productManager = new ProductManager();

        displayMenu();
    }

    private void saveDataToFile(){
        customerManager.saveToFile();
        productManager.saveToFile();
    }

    private void displayMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int input;

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
                case 0 -> saveDataToFile();
                case 1 -> handleShoppingMenu();
                case 2 -> handleCustomerMenu();
                case 3 -> handleProductMenu();
            }
        } while (input != 0);
    }

    private void handleShoppingMenu() {
        Scanner sc = new Scanner(System.in);
        int input;

        do {
            // Display "Shopping" menu
            menu.displayShoppingMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> productManager.show();
                case 2 -> handleSearchMenu();
            }
        } while (input != 0);
    }

    private void handleSearchMenu() {
        Scanner sc = new Scanner(System.in);
        int input;

        do {
            // Display "Search" menu
            /*
                1. 제품번호
                2. 브랜드
                3. 제품명=
                0. 이전 메뉴로
             */
            menu.displaySearchMenu();
            input = sc.nextInt();

            switch (input) {
                case 1:
                    // 제품번호
                    break;
                case 2:
                    // 브랜드
                    break;
                case 3:
                    // 제품명
                    break;
            }
        } while (input != 0);
    }

    private void handleCustomerMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int input;

        do {
            // Display "Customer" menu
            /*
                1. 등록
                2. 수정
                3. 탈퇴
                4. 모든 회원 확인
                0. 이전 메뉴로
             */
            menu.displayCustomerMenu();
            input = sc.nextInt();

            switch (input) {
                case 1 -> customerManager.add();
                case 2 -> customerManager.edit();
                case 3 -> customerManager.remove();
                case 4 -> customerManager.show();
            }
        } while (input != 0);
    }

    private void handleProductMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
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
            }
        } while (input != 0);
    }


}
