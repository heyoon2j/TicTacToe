public abstract class Player implements Inputable {
    private String name;
    private int winNum;
    private int loseNum;
    private Position pos;

    Player(){}
    Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinNum() {
        return winNum;
    }

    public void setWinNum(int winNum) {
        this.winNum = winNum;
    }

    public int getLoseNum() {
        return loseNum;
    }

    public void setLoseNum(int loseNum) {
        this.loseNum = loseNum;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    @Override
    abstract public void getInput();
}