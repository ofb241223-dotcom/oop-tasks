import java.util.List;
import java.util.Scanner;

public class Test29 {
    public static void main(String[] args) {
        ShapeManager shapeManager = new ShapeManager();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("====== 欢迎进入图形计算系统 ======");
            System.out.println("请输入选项：");
            System.out.println(" 1. 添加圆形 \n 2. 添加矩形 \n 3. 添加三角形 \n 4. 显示所有图形信息 \n 5. 多态性演示 \n 6. 退出系统");
            int option;
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("请输入半径：");
                    double radius = sc.nextDouble();
                    shapeManager.addShape(new Circle(radius));
                    break;
                case 2:
                    System.out.println("请输入长，宽：");
                    double length = sc.nextDouble();
                    double width = sc.nextDouble();
                    shapeManager.addShape(new Rectangle(length, width));
                    break;
                case 3:
                    System.out.println("请输入三角形三边长：");
                    double a = sc.nextDouble();
                    double b = sc.nextDouble();
                    double c = sc.nextDouble();
                    shapeManager.addShape(new Triangle(a, b, c));
                    break;
                case 4:
                    shapeManager.displayAllShapes();
                    break;
                case 5:
                    shapeManager.demonstratePolymorphism();
                    break;
                case 6:
                    System.exit(0);
                    sc.close();
                default:
                    System.out.println("无效输入，请重新输入");
                    break;
            }
        }

    }
}

class Circle implements Shape {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getShapeName() {
        return "圆形";
    }

    @Override
    public void displayInfo() {
        System.out.println("====== 以下为该图形的信息 ======");
        System.out.println("图形名称： " + getShapeName());
        System.out.println("图形面积：" + calculateArea());
        System.out.println("图形周长：" + calculatePerimeter());
    }
}

class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle() {
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String getShapeName() {
        return "矩形";
    }

    @Override
    public void displayInfo() {
        System.out.println("====== 以下为该图形的信息 ======");
        System.out.println("图形名称： " + getShapeName());
        System.out.println("图形面积：" + calculateArea());
        System.out.println("图形周长：" + calculatePerimeter());
    }
}

class Triangle implements Shape {
    private double a;
    private double b;
    private double c;

    public Triangle() {
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public boolean isValid() {
        return (a > 0 && b > 0 && c > 0) && (a + b > c && a + c > b && b + c > a);
    }

    @Override
    public double calculateArea() {
        if (isValid()) {
            double p = calculatePerimeter() / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else {
            System.out.println("三角形不合法");
            return -1;
        }
    }

    @Override
    public double calculatePerimeter() {
        if (isValid()) {
            return (a + b + c);
        } else {
            System.out.println("三角形不合法");
            return -1;
        }
    }

    @Override
    public String getShapeName() {
        return "三角形";
    }

    @Override
    public void displayInfo() {
        if (isValid()) {
            System.out.println("====== 以下为该图形的信息 ======");
            System.out.println("图形名称： " + getShapeName());
            System.out.println("图形面积：" + calculateArea());
            System.out.println("图形周长：" + calculatePerimeter());
        } else {
            System.out.println("三角形不合法");
        }
    }
}

class ShapeManager {
    private List<Shape> shapes;

    public ShapeManager() {
        shapes = new java.util.ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("已添加图形: " + shape.getShapeName());
    }

    public void displayAllShapes() {
        System.out.println("====== 所有图形信息 =======");
        if (shapes.isEmpty()) {
            System.out.println("暂无图形");
            return;
        }

        for (int i = 0; i < shapes.size(); i++) {
            System.out.println("【图形 " + (i + 1) + "】");
            shapes.get(i).displayInfo();
        }
    }

    public void demonstratePolymorphism() {
        System.out.println("====== 多态特性演示 ======");

        if (shapes.isEmpty()) {
            System.out.println("暂无图形，使用默认图形演示多态性：");

            Shape[] defaultShapes = {
                    new Circle(5),
                    new Rectangle(4, 6),
                    new Triangle(3, 4, 5)
            };

            for (int i = 0; i < defaultShapes.length; i++) {
                Shape shape = defaultShapes[i];

                System.out.println("图形" + (i + 1) + ": " + shape.getShapeName());
                System.out.printf("面积: " + shape.calculateArea() + " ");
                System.out.printf("周长: " + shape.calculatePerimeter() + " ");

                System.out.print(" 实际类型: ");
                if (shape instanceof Circle) {
                    System.out.println("Circle 类");
                } else if (shape instanceof Rectangle) {
                    System.out.println("Rectangle 类");
                } else if (shape instanceof Triangle) {
                    System.out.println("Triangle 类");
                }
            }
            return;
        }

        System.out.println("使用已添加的图形演示多态性：");

        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);

            System.out.println("图形" + (i + 1) + ": " + shape.getShapeName() + " ");
            System.out.printf("面积: " + shape.calculateArea() + " ");
            System.out.printf("周长: " + shape.calculatePerimeter() + " ");

            System.out.print("实际类型: ");
            if (shape instanceof Circle) {
                System.out.println("Circle 类");
            } else if (shape instanceof Rectangle) {
                System.out.println("Rectangle 类");
            } else if (shape instanceof Triangle) {
                System.out.println("Triangle 类");
            }
        }
    }
}

interface Shape {
    double calculateArea();

    double calculatePerimeter();

    String getShapeName();

    void displayInfo();
}