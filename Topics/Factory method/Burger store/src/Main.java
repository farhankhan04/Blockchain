class TestDrive {
    public static void main(String[] args) throws InterruptedException {
        BurgerFactory burgerFactory = new BurgerStore();
        burgerFactory.orderBurger("Chinese");
        burgerFactory.orderBurger("American");
        burgerFactory.orderBurger("Russian");
    }
}

abstract class BurgerFactory {

    abstract Burger createBurger(String type);

    Burger orderBurger(String type) throws InterruptedException {
        Burger burger = createBurger(type);
        if (burger == null) {
            System.out.println("Sorry, we are not able to create this kind of burger\n");
            return null;
        }
        System.out.printf("Making a %s Burger\n",  burger.getName());
        burger.putBun();
        burger.putCutlet();
        burger.putSauce();
        Thread.sleep(1500L);
        System.out.printf("Done a %s Burger\n\n", burger.getName());
        return burger;
    }
}

class BurgerStore extends BurgerFactory {
    @Override
    Burger createBurger(String type) {
        switch (type) {
            case "Chinese":
                return new ChineseBurger(type);
            case "American":
                return new AmericanBurger(type);
            case "Russian":
                return new RussianBurger(type);
            default:
                return null;
        }
    }
}

abstract class Burger {
    private String name;

    Burger(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void putBun() {
        System.out.println("Putting bun");
    }

    void putCutlet() {
        System.out.println("Putting cutlet");
    }

    void putSauce() {
        System.out.println("Putting sauce");
    }

}

class ChineseBurger extends Burger {
    ChineseBurger(String name) {
        super(name);
    }
}

class AmericanBurger extends Burger {
    AmericanBurger(String name) {
        super(name);
    }
}

class RussianBurger extends Burger {
    RussianBurger(String name) {
        super(name);
    }
}