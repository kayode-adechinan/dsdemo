package functionnalinterfaces;

import java.util.function.Consumer;

public class ConsummerDemo {
    public static void main(String[] args) {
        Consumer<Integer> consumer = (value) -> System.out.println(value);
        consumer.accept(8);

        testConsummer("Hi", System.out::println);
    }


    public static void testConsummer (String data, Consumer<String> consumer){
        consumer.accept(data);
    }
}
