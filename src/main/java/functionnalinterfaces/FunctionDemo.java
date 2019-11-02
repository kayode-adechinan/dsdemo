package functionnalinterfaces;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {

      /*  Function<Long, Long> adderWithoutLambda = new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                return aLong + 3;
            }
        };
        Long result = adderWithoutLambda.apply((long) 8);
        System.out.println("resultLambda = " + result);

        Function<Long, Long> adder = (value) -> value + 3;
        Long resultLambda = adder.apply((long) 8);
        System.out.println("resultLambda = " + resultLambda);*/

        System.out.println(testWithFunction(2, String::valueOf));


    }


    public static String testWithFunction(int a, Function<Integer, String> function){
        return function.apply(a);
    }





}



