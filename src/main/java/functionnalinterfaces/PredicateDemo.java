package functionnalinterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static functionnalinterfaces.EmployeePredicates.filterEmployees;
import static functionnalinterfaces.EmployeePredicates.isAgeMoreThan;


class Employee {

    private String name;
    private Integer age;
    private String gender;

    public Employee(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class EmployeePredicates
{
    public static Predicate<Employee> isAdultMale() {
        return p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("M");
    }

    public static Predicate<Employee> isAdultFemale() {
        return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("F");
    }

    public static Predicate<Employee> isAgeMoreThan(Integer age) {
        return p -> p.getAge() > age;
    }

    public static List<Employee> filterEmployees (List<Employee> employees,
                                                  Predicate<Employee> predicate)
    {
        return employees.stream()
                .filter( predicate )
                .collect(Collectors.<Employee>toList());
    }
}

public class PredicateDemo {

    public static void main(String[] args) {


        Employee e1 = new Employee("josh", 30, "M");
        Employee e2 = new Employee("mylene", 32, "F");
        Employee e3 = new Employee("rob", 15, "M");




        List<Employee> employees = new ArrayList<Employee>();
        employees.addAll(Arrays.asList(new Employee[]{e1,e2,e3}));

        System.out.println( filterEmployees(employees, EmployeePredicates.isAdultMale()) );

        System.out.println( filterEmployees(employees, EmployeePredicates.isAdultFemale()) );

        System.out.println( filterEmployees(employees, isAgeMoreThan(35)) );

        //Employees other than above collection of "isAgeMoreThan(35)"
        //can be get using negate()
        System.out.println(filterEmployees(employees, isAgeMoreThan(35).negate()));

        Predicate<String> predicate = (value) -> value.startsWith("j");
        System.out.println(predicate.test("jacob"));

    }
}


