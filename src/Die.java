public class Die {
    private int minRange;
    private int maxRange;

    public Die(int min, int max){
        this.minRange=min;
        this.maxRange = max;
    }
    public Die(){
        this(1,6);

    }
    public int Roll(){
        return Main.rnd.nextInt(this.maxRange-this.minRange+1)+this.minRange;
    }
}
