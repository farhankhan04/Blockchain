class SimpleCounter {


    public static SimpleCounter getInstance() {
        return instance;
    }

    private SimpleCounter(){

    }
    private static SimpleCounter instance = new SimpleCounter();

    public int counter;
}