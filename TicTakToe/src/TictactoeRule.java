public class TictactoeRule {
    public static int[][] THREE_CHK_TYPE = {{1, 0}, {1, 1}, {0, 1},{1, -1}};
    public static boolean chkVictoryCondition(char[][] board, Position pos, char stone){
        boolean isTrue = true;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++){
                isTrue = true;

                for(int k = 1; k < j+1; k++){
                    if(!isRangeOfBoard(pos.getX()-THREE_CHK_TYPE[i][0]*k, pos.getY()-THREE_CHK_TYPE[i][1]*k)){
                        isTrue = false;
                        break;
                    }
                    if(board[pos.getY()-THREE_CHK_TYPE[i][1]*k][pos.getX()-THREE_CHK_TYPE[i][0]*k] != stone){
                        isTrue = false;
                        break;
                    }
                }
                if(!isTrue){
                    continue;
                }

                for(int k = j; k < 3; k++){
                    if(!isRangeOfBoard(pos.getX()+THREE_CHK_TYPE[i][0]*(k-j), pos.getY()+THREE_CHK_TYPE[i][1]*(k-j))) {
                        isTrue = false;
                        break;
                    }
                    if(board[pos.getY()+THREE_CHK_TYPE[i][1]*(k-j)][pos.getX()+THREE_CHK_TYPE[i][0]*(k-j)] != stone){
                        isTrue =false;
                        break;
                    }
                }

                if(isTrue) {
                    return true;
                }
            }
        }
        return isTrue;
    }

    public static boolean chkDrawCondition(char[][] board){
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar != Tictactoe.STONE[0] && aChar != Tictactoe.STONE[1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isRangeOfBoard(int x, int y){
        if(x < 0 || x > 2){
            return false;
        }
        if(y < 0 || y > 2){
            return false;
        }
        return true;
    }


    public static boolean isRangeOfBoard(Position pos){
        if(pos.getX() < 0 || pos.getX() > 2){
            return false;
        }
        if(pos.getY() < 0 || pos.getY() > 2){
            return false;
        }
        return true;
    }

    public static boolean isEnabled(char[][] board, Position pos){
        return (board[pos.getY()][pos.getX()] == 0);
    }

    public static boolean isEnabled(char[][] board, int x, int y){
        return (board[y][x] == 0);
    }
}
