public class Die {
    private int minRange;
    private int maxRange;

    /**
     * Die constructor
     * @param min minimum die range
     * @param max maximum die range
     */
    public Die(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
    }
    /**
     * Die constructor without params,
     * uses the above constructor with default parameters
     */
    public Die() {
        this(1, 6);

    }

    /**
     * Uses main.rnd.nextInt to create a random number
     * @return random number between this.minRange and this.maxRange
     */
    public int Roll() {

        return Main.rnd.nextInt(this.maxRange - this.minRange + 1) + this.minRange;
    }
}
