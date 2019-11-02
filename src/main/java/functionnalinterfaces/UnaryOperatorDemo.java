package functionnalinterfaces;


import java.util.function.UnaryOperator;

class Person {
    String name;
}

public class UnaryOperatorDemo {

    public static void main(String[] args) {

        UnaryOperator<Person> unaryOperator =
                (person) -> { person.name = "New Name"; return person; };

        Person person = unaryOperator.apply(new Person());

    }
}
