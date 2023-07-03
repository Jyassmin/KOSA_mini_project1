public class Menu {
    private final String str_bar;
    Menu(){
        str_bar = "─────────────────────────";
    }

    /*
        로그인 & 회원가입 메뉴 display
     */
    public void loginOrRegisterMenu(){
        System.out.println(str_bar);
        System.out.println("[Login or Register]");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("0. 프로그램 종료");
        System.out.println(str_bar);
    }

    /*
       Manager role의 메인 메뉴 display
    */
    public void displayMainMenu(){
        System.out.println(str_bar);
        System.out.println("[MANAGER MENU]");
        System.out.println("1. 쇼핑몰");
        System.out.println("2. 고객 관리");
        System.out.println("3. 제품 관리");
        System.out.println("4. 주문 관리");
        System.out.println("0. 프로그램 종료");
        System.out.println(str_bar);
    }

    /*
       Customer role의 메인 메뉴 display
    */
    public void displayShoppingMenu(boolean isSupper){
        System.out.println(str_bar);
        System.out.println("[SHOPPING MENU]");
        System.out.println("1. 전체 제품");
        System.out.println("2. 상세 검색");
        System.out.println("3. 주문 확인");
        System.out.println("4. 정보 수정");
        System.out.println("5. 탈퇴");
        if (isSupper)
            System.out.println("0. 이전 메뉴로");
        else
            System.out.println("0. 프로그램 종료");
        System.out.println(str_bar);
    }

    public void displaySearchMenu(boolean isSupper){
        System.out.println(str_bar);
        if (isSupper)
            System.out.println("[MANAGER MENU] > [1. SHOPPING MENU] > [2. SEARCH MENU]");
        else
            System.out.println("[SHOPPING MENU] > [2. SEARCH MENU]");
        System.out.println("1. 제품 번호");
        System.out.println("2. 브랜드");
        System.out.println("3. 제품명");
        System.out.println("0. 이전 메뉴로");
        System.out.println(str_bar);
    }

    /*
       customer 관리 메뉴 display
    */
    public void displayCustomerMenu(){
        System.out.println(str_bar);
        System.out.println("[MANAGER MENU] > [2. CUSTOMER MENU]");
        System.out.println("1. 등록");
        System.out.println("2. 탈퇴");
        System.out.println("3. 모든 회원 확인");
        System.out.println("0. 이전 메뉴로");
        System.out.println(str_bar);
    }

    /*
       product 관리 메뉴 display
    */
    public void displayProductMenu(){
        System.out.println(str_bar);
        System.out.println("[MANAGER MENU] > [3. PRODUCT MENU]");
        System.out.println("1. 등록");
        System.out.println("2. 수정");
        System.out.println("3. 삭제");
        System.out.println("4. 모든 제품 확인");
        System.out.println("0. 이전 메뉴로");
        System.out.println(str_bar);
    }

    /*
       order 관리 메뉴 display
    */
    public void displayOrderMenu(){
        System.out.println(str_bar);
        System.out.println("[MANAGER MENU] > [4. ORDER MENU]");
        System.out.println("1. 모든 주문 보기");
        System.out.println("2. 고객별로 보기");
        System.out.println("0. 이전 메뉴로");
        System.out.println(str_bar);
    }

    /*
       구매 여부 메뉴 display
    */
    public void displayOrderDecision() {
        System.out.println(str_bar);
        System.out.println("* 구매를 원하시면 1번을 눌러주세요.");
        System.out.println("1. 구매하기");
        System.out.println("0. 이전 메뉴로");
        System.out.println(str_bar);
    }
}
