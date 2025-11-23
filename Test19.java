import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class Test19 {
    public static void main(String[] args) {
        getMethodAndFieldInAnyClass(new Student());
        System.out.println("----------------------------------");
        getMethodAndFieldInAnyClass(new Worker());
    }

    public static void getMethodAndFieldInAnyClass(Object obj) {
        Class clazz = obj.getClass();
        System.out.println(obj.getClass().getName() + "类中的所有方法是：");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println(method.getName());
        }
        System.out.println();
        System.out.println(obj.getClass().getName() + "类中的所有属性是：");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName());
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    };

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

class Worker {
    private String name;
    private int age;

    public Worker() {
    }

    public Worker(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Worker worker = (Worker) o;
        return age == worker.age && Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}