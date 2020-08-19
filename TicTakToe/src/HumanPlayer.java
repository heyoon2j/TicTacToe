import java.util.Scanner;

public class HumanPlayer extends Player {

    HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void getInput() {
        // Input Position
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Input Position(ex> 1 2) : ");
            String[] strPos = sc.nextLine().split(" ");

            try {
                // Pos(int x, int y)
                setPos(new Position(Integer.parseInt(strPos[0]), Integer.parseInt(strPos[1])));
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
