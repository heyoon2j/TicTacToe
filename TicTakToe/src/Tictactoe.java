import java.util.Scanner;

public class Tictactoe implements Playable, Simulatable, Printable {
    public static final char[] STONE = {'x', 'o'};
    private char[][] board;
    private int winningCount;
    private Player player1;
    private Player player2;
    private Player curPlayer;

    Tictactoe() {
        this.board = new char[3][3];
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        curPlayer = player1;

        while (true) {

            // Input Postion
            curPlayer.getInput(this.board);

            // Put Stone on board
            char curStone = (curPlayer == player1) ? player1.getStone() : player2.getStone();
            board[curPlayer.getPos().getY()][curPlayer.getPos().getX()] = curStone;
            printStatus();

            // Check Victory Condition
            boolean winDrawCondition[] = new boolean[]{false, false};
            winDrawCondition[0] = (TictactoeRule.chkVictoryCondition(this.board, curPlayer.getPos(), curStone));
            if(!winDrawCondition[0]) {
                winDrawCondition[1] = (TictactoeRule.chkDrawCondition(this.board));
            }

            if(winDrawCondition[0] || winDrawCondition[1]){
                if(winDrawCondition[0]){
                    curPlayer.setWinNum(curPlayer.getWinNum() + 1);
                    System.out.printf("Winning Count : %d\n", this.winningCount);
                    System.out.printf(" %s %d : %d %s \n", player1.getName(), player1.getWinNum(), player2.getWinNum(), player2.getName());
                }

                if (curPlayer.getWinNum() == this.winningCount) {
                    break;
                }

                if(winDrawCondition[1]){
                    System.out.println("Game Draw~~~~!!!");
                }

                finish();
                continue;
            }

            curPlayer = (curPlayer == player1) ? player2 : player1;
        }
    }

    @Override
    public void printStatus() {
        System.out.println("-----------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0)
                    System.out.printf("%2c ", 32);
                else
                    System.out.printf("%2c ", board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-----------");
    }

    @Override
    public void initialize() {
        String playerName = null;
        String otherPlayerName = null;
        Scanner sc = new Scanner(System.in);
        int selectPlay = 0;

        System.out.println("########## Game Start ##########");
        System.out.println("");

        while(true) {
            try {
                System.out.print("Winning Count : ");
                this.winningCount = sc.nextInt();
                sc.nextLine();

                if(this.winningCount != 0){
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
            } finally {
                if(this.winningCount <= 0)
                    System.out.println("Input Number~!!, Over 0");
            }
        }

        System.out.println("-----------------------");
        System.out.println("|      Select Play     |");
        System.out.println("-----------------------");
        System.out.println("| 1. With Friend       |");
        System.out.println("| 2. With AI(Beginner) |");
        System.out.println("| 3. With AI(Expert)   |");
        System.out.println("-----------------------");

        while(true) {
            try {
                System.out.print("Select Play : ");
                selectPlay = sc.nextInt();
                sc.nextLine();

                if(selectPlay == 1 || selectPlay == 2 || selectPlay == 3) {
                    break;
                }

            }catch (Exception e){
                sc.nextLine();
            }finally {
                if(selectPlay != 1 && selectPlay != 2 && selectPlay != 3)
                    System.out.println("Input Error. Only Input '1' or '2' or '3'");
            }
        }
        System.out.print("Player Name : ");
        playerName = sc.nextLine();
        this.player1 = new HumanPlayer(playerName);
        this.player1.setStone(STONE[0]);

        if (selectPlay == 1) {
            System.out.print("Friend Player Name : ");
            playerName = sc.nextLine();
            this.player2 = new HumanPlayer(playerName);

        } else if (selectPlay == 2) {
            this.player2 = new AIPlayer(AILevel.BEGINNER);

        } else {
            this.player2 = new AIPlayer(AILevel.EXPERT);
        }
        this.player2.setStone(STONE[1]);
    }

    @Override
    public void finish() {
        System.out.println("Game Board Reset~~~~");
        reset();
        curPlayer = (curPlayer == player1) ? player2 : player1;     // 진 사람이 선이 된다.
    }

    @Override
    public void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

}
