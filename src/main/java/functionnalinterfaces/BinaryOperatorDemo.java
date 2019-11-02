package functionnalinterfaces;

import java.util.function.BinaryOperator;

interface Calculator{
    int operation(int a, int b);
}

class Add implements Calculator{
    public int operation(int a, int b) {
        return a + b;
    }
}

class Minus implements Calculator{
    public int operation(int a, int b) {
        return a - b;
    }
}

public class BinaryOperatorDemo {

    public static void main(String[] args) {
     /*   BinaryOperator<Integer> binaryOperator =
                (value1, value2) -> value1+value2;


        System.out.println(binaryOperator.apply(1, 2));*/

        Add add = new Add();
        Minus minus = new Minus();
        testCalculator(1, 2, add);
        testCalculator(3, 2, minus);
        testCalculator(4, 5, (a,b)-> a+b);

        testCalculatorWithBinaryOperator(4, 5, (a,b)-> a+b);
    }


    public static void testCalculator(int a, int b, Calculator calculator){
        System.out.println(calculator.operation(a, b));
    }

    public static void testCalculatorWithBinaryOperator(int a, int b, BinaryOperator<Integer> binaryOperator){
        System.out.println(binaryOperator.apply(a, b));

    }


}
