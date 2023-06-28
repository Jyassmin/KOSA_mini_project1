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

    public void displayMenu() throws IOException{
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while(flag) {
            // Display main menu
            /*
                1. 쇼핑몰
                2. 고객 관리
                3. 제품 관리
                0. 종료
             */
            menu.displayMainMenu();
            // Get input
            int input = sc.nextInt();

            switch (input) {
                case 0 -> {
                    saveDataToFile();
                    flag = false;
                }
                // 쇼핑몰
                case 1 -> {
                    // Display "Shopping" menu
                    menu.displayShoppingMenu();
                    input = sc.nextInt();
                    switch (input) {
                        case 0:
                            // back to Main menu
                            break;
                        case 1:
                            // 전체 제품
                            break;
                        case 2:
                            boolean backToPreviousMenu = false;
                            // 검색
                            menu.displaySearchMenu();
                            input = sc.nextInt();

                            switch (input) {
                                case 0:
                                    // 이전 메뉴로
                                    backToPreviousMenu = true;
                                    break;
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
                            if (!backToPreviousMenu)
                                break;
                    }
                }
                // 고객 관리
                case 2 -> {
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
                        case 0:
                            break;
                        case 1:
                            customerManager.add();
                            break;
                        case 2:
                            customerManager.edit();
                            break;
                        case 3:
                            customerManager.remove();
                            break;
                        case 4:
                            customerManager.show();
                            break;
                    }
                }
                // 제품 관리
                case 3 -> {
                    menu.displayProductMenu();
                    input = sc.nextInt();
                    switch (input) {
                        case 0:
                            break;
                        case 1:
                            productManager.add();
                            break;
                        case 2:
                            productManager.edit();
                            break;
                        case 3:
                            productManager.remove();
                            break;
                    }
                }
            }
        }
    }
}
