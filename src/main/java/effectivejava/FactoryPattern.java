package effectivejava;


interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

class ShapeFactory {

    //use getShape method to get object of type shape
    public static Shape getShape(String shapeType){

        return switch (shapeType) {
            case "CIRCLE" -> new Circle();
            case "RECTANGLE" -> new Rectangle();
            case "SQUARE" -> new Square();
            default -> null;
        };

    }
}


enum SingletonShapeFactory {

    INSTANCE;

    //use getShape method to get object of type shape
    Shape getShape(String shapeType){
        return switch (shapeType) {
            case "CIRCLE" -> new Circle();
            case "RECTANGLE" -> new Rectangle();
            case "SQUARE" -> new Square();
            default -> throw new IllegalArgumentException("please provided a valid shape type");
        };

    }
}

public class FactoryPattern {

    public static void main(String[] args) {


        //get an object of Circle and call its draw method.
        Shape shape1 = ShapeFactory.getShape("CIRCLE");

        //call draw method of Circle
        shape1.draw();

        //get an object of Rectangle and call its draw method.
        Shape shape2 = ShapeFactory.getShape("RECTANGLE");

        //call draw method of Rectangle
        shape2.draw();

        //get an object of Square and call its draw method.
        Shape shape3 = ShapeFactory.getShape("SQUARE");

        //call draw method of square
        shape3.draw();

        // get an object of Circle from SingletonFactory
        Shape shape4 = SingletonShapeFactory.INSTANCE.getShape("CIRCLE");
        shape4.draw();

        Shape shape5 = SingletonShapeFactory.INSTANCE.getShape("RECTANGLE");
        shape5.draw();


    }
}
