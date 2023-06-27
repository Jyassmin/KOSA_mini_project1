
import java.io.*;
import java.util.*;

public class Main {
    public static final String delimiter = ",";

    public static void displayCusMan() {
        System.out.println("1. ");
        System.out.println("2. ");
        System.out.println("3. ");

        Scanner sc = new Scanner(System.in); // 삭제 얘정
        int input = sc.nextInt();

        switch(input) {
            case 1:
                break;
            case 2:
                displayCusMan();
                break;
            case 3:
                break;
        }
    }


    public static void main(String[] args) throws IOException { // Main methods
        CustomerManager cm = new CustomerManager(); // customers(ArrayList생성). 작업은 메소드로!
        boolean flag = true;

        while(flag) {
            System.out.println("1. 쇼핑몰");
            System.out.println("2. 고객 관리");
            System.out.println("3. 제품 관리");
            System.out.println("0. 종료");

            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();

            switch(input) {
                case 0:
                    cm.saveToFile();
                    flag = false;
                    break;
                case 1:
                    break;
                case 2:
                    System.out.println("1. 등록");
                    System.out.println("2. 수정");
                    System.out.println("3. 탈퇴");
                    System.out.println("0. 이전 메뉴로");
                    input = sc.nextInt();

                    switch(input) {
                        case 0:
                            break;
                        case 1:
                            cm.add();
                            break;
                        case 2:
                            cm.edit();
                            break;
                        case 3:
                            cm.remove();
                            break;
                    }

                    break;
                case 3:
                    break;
            }
        }

        //cm.saveToFile();
        cm.show();

    }
}
