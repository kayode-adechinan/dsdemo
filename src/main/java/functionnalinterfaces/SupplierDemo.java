package functionnalinterfaces;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> (int) (Math.random() * 1000D);
        System.out.println(supplier.get());
    }
}
