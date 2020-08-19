public enum AILevel {
    BEGINNER("초보"),
    ADVANCED("중수"),
    MASTER("고수"),
    EXPERT("신");

    private final String title;

    AILevel(String title){
        this.title =title;
    }

    public String getTitle() {
        return title;
    }
}
