import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test08 {
    public static void main(String[] args) {
        ProductManage pm = new ProductManage();

        pm.addProduct(new Product("123", "可乐", 3));
        pm.addProduct(new Product("456", "雪碧", 5));
        pm.addProduct(new Product("789", "百事", 9));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== 简单商品管理系统 ===");
            System.out.println("1. 添加商品");
            System.out.println("2. 按 ID 查询商品");
            System.out.println("3. 列出所有商品");
            System.out.println("0. 退出");
            System.out.print("请选择: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("请输入编号");
                    String id = sc.next();
                    System.out.println("请输入名字");
                    String name = sc.next();
                    System.out.println("请输入价格");
                    double price = sc.nextDouble();
                    if (id == null || name == null || price < 0) {
                        System.out.println("非法输入");
                        break;
                    }
                    if (pm.addProduct(new Product(id, name, price))) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("该编号已存在");
                    }
                    break;
                case 2:
                    System.out.println("请输入编号");
                    id = sc.next();
                    Product product = pm.findById(id);
                    System.out.println(product == null ? "不存在该编号的商品" : product);
                    break;
                case 3:
                    List<Product> all = pm.findAll();
                    if (all.isEmpty()) {
                        System.out.println("商品列表为空。");
                    } else {
                        System.out.println("当前所有商品：");
                        for (Product ap : all)
                            System.out.println(ap);
                    }
                    break;
                case 0:
                    System.out.println("退出程序");
                    sc.close();
                    return;
                default:
                    System.out.println("无效选择，请重试");
            }
        }
    }
}

class Product {
    private String id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, price: %.2f", id, name, price);
    }
}

class ProductManage {
    private final List<Product> products = new ArrayList<>();

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }

    public Product findByName(String name) {
        for (Product p : products) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }

    public List<Product> findAll() {
        return products;
    }

    public boolean addProduct(Product p) {
        if (p == null)
            return false;
        if (findById(p.getId()) == null) {
            products.add(p);
            return true;
        } else
            return false;
    }
}