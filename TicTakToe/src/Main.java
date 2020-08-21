import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int selectNumber = 0;

        System.out.println("--------------------");
        System.out.println("|     Game Play     |");
        System.out.println("--------------------");
        System.out.println("| 1. Game Start     |");
        System.out.println("| 2. Game End       |");
        System.out.println("--------------------");

        while(true) {
            try {
                System.out.print("Select Number : ");
                selectNumber = sc.nextInt();
                sc.nextLine();

                if (selectNumber == 1 || selectNumber == 2) {
                    break;
                }
            }catch(Exception e){
                sc.nextLine();
            }finally {
                if(selectNumber != 1 && selectNumber != 2)
                    System.out.println("Input Error. Only Input '1' or '2'");
            }
        }

        if(selectNumber == 1){
            Tictactoe tictactoe = new Tictactoe();
            tictactoe.initialize();
            tictactoe.play();
        }

        sc.close();
        System.out.println("Game End!!!");
    }
}
