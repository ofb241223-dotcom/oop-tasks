import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Test30 {
    public static void main(String[] args) {
        Company company = new Company();
        while (true) {
            System.out.println("====== 欢迎进入员工工资管理系统 ======");
            System.out.println("请输入选项：");
            System.out.println(" 1. 添加全职员工 \n 2. 添加兼职员工 \n 3. 添加销售员 \n 4. 删除员工 \n 5. 获取所有员工信息 " +
                    "\n 6. 计算总工资 \n 7. 员工按工资从低到高排序 \n 8. 查找最高/最低工资员工 \n 9. 数据统计 \n 0. 退出系统");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            String id, name;
            double baseSalary, bonus, workHours, hourlySalary, saleVolume;
            switch (option) {
                case 1:
                    System.out.println("请输入ID");
                    id = sc.next();
                    System.out.println("请输入姓名");
                    name = sc.next();
                    System.out.println("请输入基本工资");
                    baseSalary = sc.nextDouble();
                    System.out.println("请输入绩效奖金");
                    bonus = sc.nextDouble();
                    company.employees.add(new FullTimeEmployee(id, name, baseSalary, bonus));
                    System.out.println("添加成功");
                    break;
                case 2:
                    System.out.println("请输入ID");
                    id = sc.next();
                    System.out.println("请输入姓名");
                    name = sc.next();
                    System.out.println("请输入工作时间（h）");
                    workHours = sc.nextDouble();
                    System.out.println("请输入时薪");
                    hourlySalary = sc.nextDouble();
                    company.employees.add(new PartTimeEmployee(id, name, 0, workHours, hourlySalary));
                    System.out.println("添加成功");
                    break;
                case 3:
                    System.out.println("请输入ID");
                    id = sc.next();
                    System.out.println("请输入姓名");
                    name = sc.next();
                    System.out.println("请输入基础工资");
                    baseSalary = sc.nextDouble();
                    System.out.println("请输入销售额");
                    saleVolume = sc.nextDouble();
                    company.employees.add(new SalesEmployee(id, name, baseSalary, saleVolume));
                    System.out.println("添加成功");
                    break;
                case 4:
                    System.out.println("请输入员工ID：");
                    id = sc.next();
                    company.deleteEmployee(id);
                    break;
                case 5:
                    for (Employee e : company.employees) {
                        e.getEmployeeInfo();
                    }
                    break;
                case 6:
                    System.out.println("所有员工的总工资为：" + company.calculateAllSalary());
                    break;
                case 7:
                    company.sortEmployeesBySalary();
                    break;
                case 8:
                    company.findHighestAndLowestSalary();
                    break;
                case 9:
                    company.statistics();
                    break;
                case 0:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("输入不合法，请重新输入");
                    break;
            }
        }
    }
}

abstract class Employee {
    private String id;
    private String name;
    private double baseSalary;

    public Employee() {
    }

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
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

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    abstract public double calculateSalary();

    public abstract String getEmployeeType();

    public void getEmployeeInfo() {// （获取员工信息）
        // 员工 ID、姓名、基础工资
        System.out.println("员工ID ： " + id);
        System.out.println("员工姓名：" + name);
        System.out.println("员工类型：" + getEmployeeType());
        System.out.println("员工基础工资：" + baseSalary);
        System.out.println("实际工资：" + calculateSalary());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class FullTimeEmployee extends Employee {
    private double bonus;

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    FullTimeEmployee() {
    }

    public FullTimeEmployee(String id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }

    @Override
    public String getEmployeeType() {
        return "全职员工";
    }
}

class PartTimeEmployee extends Employee {
    private double workHours;
    private double hourlySalary;

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    PartTimeEmployee() {
    }

    public PartTimeEmployee(String id, String name, double baseSalary, double workHours, double hourlySalary) {
        super(id, name, 0);
        this.workHours = workHours;
        this.hourlySalary = hourlySalary;
    }

    @Override
    public double calculateSalary() {
        return hourlySalary * workHours;
    }

    @Override
    public String getEmployeeType() {
        return "兼职员工";
    }
}

class SalesEmployee extends Employee {
    private double saleVolume;

    public double getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(double saleVolume) {
        this.saleVolume = saleVolume;
    }

    public SalesEmployee(String id, String name, double baseSalary, double saleVolume) {
        super(id, name, baseSalary);
        this.saleVolume = saleVolume;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + saleVolume * 0.05;
    }

    @Override
    public String getEmployeeType() {
        return "销售员";
    }
}

class Company {
    List<Employee> employees;

    public Company() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void deleteEmployee(String id) {
        boolean removeFlag = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                removeFlag = employees.remove(employees.get(i));
                break;
            }
        }
        if (removeFlag == true) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    public double calculateAllSalary() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.calculateSalary();
        }
        return totalSalary;
    }

    public void sortEmployeesBySalary() {
        if (employees.isEmpty()) {
            System.out.println("暂无员工数据");
            return;
        }

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Double.compare(o1.calculateSalary(), o2.calculateSalary());
            }
        });

        System.out.println("排序结果为：");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(i + 1 + ": " + employees.get(i).getName());
        }
    }

    public void findHighestAndLowestSalary() {
        if (employees.isEmpty()) {
            System.out.println("暂无员工数据");
            return;
        }

        Employee HighestSalaryEmployee = employees.get(0);
        Employee LowestSalaryEmployee = employees.get(0);

        for (Employee employee : employees) {
            if (employee.calculateSalary() > HighestSalaryEmployee.calculateSalary()) {
                HighestSalaryEmployee = employee;
            }
            if (employee.calculateSalary() < LowestSalaryEmployee.calculateSalary()) {
                LowestSalaryEmployee = employee;
            }
        }
        System.out.println("工资最高的员工：");
        HighestSalaryEmployee.getEmployeeInfo();
        System.out.println("工资最低的员工：");
        LowestSalaryEmployee.getEmployeeInfo();
    }

    public void statistics() {
        if (employees.isEmpty()) {
            System.out.println("暂无员工数据");
            return;
        }

        double totalSalary = 0;
        double avgSalary = totalSalary / employees.size();
        double maxSalary = 0;
        double minSalary = 0;
        int fullTimeCount = 0;
        int partTimeCount = 0;
        int salesCount = 0;
        for (Employee employee : employees) {
            totalSalary += employee.calculateSalary();
            if (employee.calculateSalary() > maxSalary) {
                maxSalary = employee.calculateSalary();
            }
            if (employee.calculateSalary() < minSalary) {
                minSalary = employee.calculateSalary();
            }
            if (employee.getEmployeeType() == "全职员工") {
                fullTimeCount++;
            } else if (employee.getEmployeeType() == "兼职员工") {
                partTimeCount++;
            } else if (employee.getEmployeeType() == "销售员") {
                salesCount++;
            }
        }

        System.out.println("统计结果如下：");
        System.out.printf("员工总数：    %d 人\n", employees.size());
        System.out.printf("总工资：      ¥%.2f\n", totalSalary);
        System.out.printf("平均工资：    ¥%.2f\n", avgSalary);
        System.out.printf("最高工资：    ¥%.2f\n", maxSalary);
        System.out.printf("最低工资：    ¥%.2f\n", minSalary);
        System.out.printf("全职员工：    %d 人\n", fullTimeCount);
        System.out.printf("兼职员工：    %d 人\n", partTimeCount);
        System.out.printf("销售员：      %d 人\n", salesCount);
    }
}