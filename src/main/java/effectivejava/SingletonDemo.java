package effectivejava;

// https://dzone.com/articles/java-singletons-using-enum

class NoSingleton {

    int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

}

enum Singleton {
    INSTANCE;
    int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}




public class SingletonDemo {


    public static void main(String[] args) {


        NoSingleton noSingleton1 = new NoSingleton();
        NoSingleton noSingleton2 = new NoSingleton();

        System.out.println(noSingleton1.hashCode());
        System.out.println(noSingleton2.hashCode());

        noSingleton1.setValue(2);
        System.out.println(noSingleton1.getValue());
        System.out.println(noSingleton2.getValue());

        // ------------------------------------

        Singleton singleton1 = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;

        System.out.println(singleton1.hashCode());
        System.out.println(singleton2.hashCode());

        singleton1.setValue(2);
        System.out.println(singleton1.getValue());
        System.out.println(singleton2.getValue());
    }
}
