public class Menu {
    Menu(){}

    public void loginOrRegisterMenu(){
        System.out.println("-------------------------");
        System.out.println("[Login or Register]");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("0. 종료");
        System.out.println("-------------------------");
    }

    public void displayMainMenu(){
        System.out.println("-------------------------");
        System.out.println("[MAIN MENU]");
        System.out.println("1. 쇼핑몰");
        System.out.println("2. 고객 관리");
        System.out.println("3. 제품 관리");
        System.out.println("0. 종료");
        System.out.println("-------------------------");
    }

    public void displayShoppingMenu(){
        System.out.println("-------------------------");
        System.out.println("[MAIN MENU] > [1. SHOPPING MENU]");
        System.out.println("1. 전체 제품");
        System.out.println("2. 검색");
        System.out.println("0. 이전 메뉴로");
        System.out.println("-------------------------");
    }

    public void displaySearchMenu(){
        System.out.println("-------------------------");
        System.out.println("[MAIN MENU] > [1. SHOPPING MENU] > [2. SEARCH MENU]");
        System.out.println("1. 제품 번호");
        System.out.println("2. 브랜드");
        System.out.println("3. 제품명");
        System.out.println("0. 이전 메뉴로");
        System.out.println("-------------------------");
    }

    public void displayCustomerMenu(){
        System.out.println("-------------------------");
        System.out.println("[MAIN MENU] > [1. CUSTOMER MENU]");
        System.out.println("1. 등록");
        System.out.println("2. 수정");
        System.out.println("3. 탈퇴");
        System.out.println("4. 모든 회원 확인");
        System.out.println("0. 이전 메뉴로");
        System.out.println("-------------------------");
    }

    public void displayProductMenu(){
        System.out.println("-------------------------");
        System.out.println("[MAIN MENU] > [1. PRODUCT MENU]");
        System.out.println("1. 등록");
        System.out.println("2. 수정");
        System.out.println("3. 삭제");
        System.out.println("4. 모든 제품 확인");
        System.out.println("0. 이전 메뉴로");
        System.out.println("-------------------------");
    }


}
