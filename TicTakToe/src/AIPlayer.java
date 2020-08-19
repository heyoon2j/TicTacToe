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

    @Override
    public void getInput() {

        if (this.level == AILevel.BEGINNER) {
            this.getInput_Beginner();
        } else {
            this.getInput_Expert();
        }

    }

    void getInput_Beginner() {
        int x = (int)(Math.random()*3);
        int y = (int)(Math.random()*3);

        setPos(new Position(x, y));
        System.out.printf("AI Input Position : %d %d\n", x, y);
    }

    void getInput_Expert() {
        //
    }

}
