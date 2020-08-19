public class TictactoeRule {
    public static boolean chkVictoryCondition(char[][] board, Position pos){
        return false;
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
}
