

class Box<T> {

    private T item;

    public void put(T item) {
    	this.item = item;
    }

    public Object get() {
        return this.item;
    }

}