package effectivejava;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import static java.util.Comparator.comparingInt;

public class EffectiveJava {

    public static void main(String[] args) {

       // preferLambdaToAnonymousClass();

        System.out.println(Operation.PLUS.apply(5, 8));

        System.out.println(OperationWithLambda.PLUS.apply(8, 5));


    }


    /**
     * Prefer Lambda to Anonymous class in practice
     */
    public static void preferLambdaToAnonymousClass() {
        List<String> words = Arrays.asList("this", "that");

        // sorting with anonymous class
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        // sorting with lambda
        Collections.sort(words, (o1,o2) -> Integer.compare(o1.length(), o2.length()));

        // sorting with method reference
        Collections.sort(words, comparingInt(String::length));

        // sorting directly
        words.sort(comparingInt(String::length));
    }


    /**
     * Five kind of method references
     */
    public static void fiveKindOfMethodReferences (){

        /**
         * Static : Integer::parseInt   =   str -> Integer.parseInt(str)
         * Bound : Instant.now()::isAfter  = Instant then = Instant.now(); t -> then.isAfter(t)
         * Unbound : String::toLowerCase  = str -> str.toLowerCase()
         * Class Constructor : TreeMap<K,V>::new  = () -> new TreeMap<K,V>()
         * Array Constructor :  int[]::new   =  len -> new int[len]
         */

    }


}


enum Operation {

    PLUS ("+") {
        @Override
        public double apply(double x, double y) {
            return  x + y;
        }
    },

    MINUS ("-") {
        @Override
        public double apply(double x, double y) {
            return  x - y;
        }
    },

    TIMES ("*") {
        @Override
        public double apply(double x, double y) {
            return  x * y;
        }
    },

    DIVIDE ("/") {
        @Override
        public double apply(double x, double y) {
            return  x / y;
        }
    };

    private final String symbol;
    Operation(String symbol){this.symbol = symbol ;}
    public abstract double apply (double x, double y);
}


enum OperationWithLambda {

    PLUS ("+", (x, y) -> x + y),
    MINUS ("-", (x, y) -> x - y),
    TIMES ("*", (x, y) -> x * y),
    DIVIDE ("+", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    OperationWithLambda(String symbol, DoubleBinaryOperator op){
        this.symbol = symbol;
        this.op = op;
    }

    public double apply(double x, double y){
        return op.applyAsDouble(x, y);
    }


}
