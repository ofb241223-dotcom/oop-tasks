import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + "（年龄：" + age + "）";
    }
}

public class Test28 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("张三", 25));
        list.add(new Person("李四", 20));
        list.add(new Person("王五", 30));
        list.add(new Person("赵六", 22));

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });

        // 输出排序结果
        System.out.println("按年龄升序排序结果：");
        for (Person p : list) {
            System.out.println(p);
        }
    }
}