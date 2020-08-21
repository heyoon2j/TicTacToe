import java.util.Scanner;

public class HumanPlayer extends Player {

    HumanPlayer(String name) {
        super(name);
    }

    public void getInput(char[][] board) {
        // Input Position
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.printf("%s Input Position(ex> 1 2) : ", this.getName());
            String[] strPos = sc.nextLine().split(" ");

            try {
                int x = Integer.parseInt(strPos[0]);
                int y = Integer.parseInt(strPos[1]);

                if (!TictactoeRule.isRangeOfBoard(x, y)) {
                    System.out.println("Out Of Range, Please Input position(0~2 0~2).");
                    continue;
                }
                if (!TictactoeRule.isEnabled(board, x, y)) {
                    System.out.println("Stone exists on current position, Please Input other position.");
                    continue;
                }

                setPos(new Position(x, y));
                return;

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
