public class AIPlayer extends Player {

    //    private String level;
    private AILevel level;

    AIPlayer(AILevel level) {
        super("AI Player");
        this.level = level;
    }

    public AILevel getLevel() {
        return level;
    }

    public void setLevel(AILevel level) {
        this.level = level;
    }

    public void getInput(char[][] board) {

        if (this.level == AILevel.BEGINNER) {
            this.getInput_Beginner(board);
        } else {
            this.getInput_Expert(board);
        }

    }

    public void getInput_Beginner(char[][] board) {

        while (true) {
            int x = (int) (Math.random() * 3);
            int y = (int) (Math.random() * 3);

            if (!TictactoeRule.isEnabled(board, x, y)) {
                continue;
            }

            System.out.printf("AI Input Position : %d %d\n", x, y);
            setPos(new Position(x, y));
            return;
        }
    }

    public void getInput_Expert(char[][] board) {
        int x = 0;
        int y = 0;
        char curStone = this.getStone();

        for (int k = 0; k < 2; k++) {

            if (k == 1)
                this.setStone((this.getStone() == Tictactoe.STONE[0]) ? Tictactoe.STONE[1] : Tictactoe.STONE[0]);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!TictactoeRule.isEnabled(board, j, i)) {
                        continue;
                    }

                    if (chkThreeAll(board, j, i)) {    // First, Check My Attack Position
                        System.out.printf("AI Input Position : %d %d\n", j, i);
                        this.setStone(curStone);
                        setPos(new Position(j, i));
                        return;
                    }
                }
            }
            this.setStone(curStone);
        }

        for (int k = 0; k < 2; k++) {
            if (k == 0)
                this.setStone((this.getStone() == Tictactoe.STONE[0]) ? Tictactoe.STONE[1] : Tictactoe.STONE[0]);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!TictactoeRule.isEnabled(board, j, i)) {
                        continue;
                    }

                    if (chkTwoByTwo(board, j, i)) {    // Second, Check Defence Position
                        System.out.printf("AI Input Position : %d %d\n", j, i);
                        this.setStone(curStone);
                        setPos(new Position(j, i));
                        return;
                    }
                }
            }
            this.setStone(curStone);
        }

        // {1, 1}, {0, 0}, {2, 2}, {2, 0}, {0, 2}
        for(int i = 0; i < FIRST_POSITION.length; i++) {
            if (!TictactoeRule.isEnabled(board, FIRST_POSITION[i][0], FIRST_POSITION[i][1])) {
                continue;
            }
            System.out.printf("AI Input Position : %d %d\n", FIRST_POSITION[i][0], FIRST_POSITION[i][1]);
            setPos(new Position(FIRST_POSITION[i][0], FIRST_POSITION[i][1]));
            return;
        }

        getInput_Beginner(board);
    }

    private boolean chkThreeAll(char[][] board, int x, int y) {
        int count = 0;

        board[y][x] = this.getStone();

        for(int i = 0; i < THREE_ALL.length; i++){
            count = 0;
            for(int j = 0; j < THREE_ALL[i].length; j++){

                char stone = (THREE_ALL[i][j][1] == 1)?this.getStone():' ';
                int pos = THREE_ALL[i][j][0];

                if(changeNumToBoard(board, pos) == stone){
                    count++;
                }
            }
            if(count == THREE_ALL[i].length)
                return true;
        }
        board[y][x] = 0;
        return false;
    }

    private boolean chkTwoByTwo(char[][] board, int x, int y) {
        int count = 0;

        board[y][x] = this.getStone();

        for(int i = 0; i < TWO_BY_TWO.length; i++){
            count = 0;
            for(int j = 0; j < TWO_BY_TWO[i].length; j++){

                char stone = (TWO_BY_TWO[i][j][1] == 1)?this.getStone():' ';
                int pos = TWO_BY_TWO[i][j][0];

                if(changeNumToBoard(board, pos) == stone){
                    count++;
                }
            }
            if(count == TWO_BY_TWO[i].length)
                return true;
        }
        board[y][x] = 0;
        return false;
    }

    private char changeNumToBoard(char[][] board, int num){
        switch (num){
            case 0:
                return board[0][0];
            case 1:
                return board[0][1];
            case 2:
                return board[0][2];
            case 3:
                return board[1][0];
            case 4:
                return board[1][1];
            case 5:
                return board[1][2];
            case 6:
                return board[2][0];
            case 7:
                return board[2][1];
            case 8:
                return board[2][2];
            default:
                return board[1][1];
        }
    }

    // 1 means stone, 0 means space
    static final private int[][][] TWO_BY_TWO = new int[][][]{
            // Triangle
            {{0, 1}, {3, 1}, {4, 1}, {6, 0}, {8, 0}},
            {{0, 1}, {1, 1}, {4, 1}, {2, 0}, {8, 0}},
            {{2, 1}, {1, 1}, {4, 1}, {0, 0}, {6, 0}},
            {{2, 1}, {4, 1}, {5, 1}, {6, 0}, {8, 0}},
            {{3, 1}, {4, 1}, {6, 1}, {0, 0}, {2, 0}},
            {{4, 1}, {6, 1}, {7, 1}, {2, 0}, {8, 0}},
            {{4, 1}, {7, 1}, {8, 1}, {0, 0}, {6, 0}},
            {{4, 1}, {5, 1}, {8, 1}, {0, 0}, {2, 0}},
            // Rectangular
            {{0, 1}, {1, 1}, {3, 1}, {2, 0}, {6, 0}},
            {{1, 1}, {3, 1}, {4, 1}, {5, 0}, {7, 0}},
            {{1, 1}, {2, 1}, {5, 1}, {0, 0}, {8, 0}},
            {{1, 1}, {4, 1}, {5, 1}, {3, 0}, {7, 0}},
            {{3, 1}, {6, 1}, {7, 1}, {0, 0}, {8, 0}},
            {{3, 1}, {4, 1}, {7, 1}, {1, 0}, {5, 0}},
            {{5, 1}, {7, 1}, {8, 1}, {2, 0}, {6, 0}},
            {{4, 1}, {5, 1}, {7, 1}, {1, 0}, {3, 0}}
    };

    static final private int[][][] THREE_ALL = new int[][][]{
            {{0, 1}, {4, 1}, {8, 1}},
            {{2, 1}, {4, 1}, {6, 1}},
            {{1, 1}, {4, 1}, {7, 1}},
            {{3, 1}, {4, 1}, {5, 1}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{6, 1}, {7, 1}, {8, 1}},
            {{0, 1}, {3, 1}, {6, 1}},
            {{2, 1}, {5, 1}, {8, 1}}
    };

    static final private int[][] FIRST_POSITION = new int[][]{
            {1, 1}, {0, 0}, {2, 2}, {2, 0}, {0, 2}
    };
}
