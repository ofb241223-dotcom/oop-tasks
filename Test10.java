import java.lang.reflect.Method;

public class Test10 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("BankAccount");
        Method method = clazz.getDeclaredMethod("deposit", double.class);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setMoney(100);
        System.out.println("初始余额：" + bankAccount.getMoney());
        method.invoke(bankAccount, 500);
        System.out.println("存款后余额：" + bankAccount.getMoney());

    }
}

class BankAccount {
    private double money;

    public BankAccount() {
    }

    public BankAccount(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            System.out.println("输入不合法，请重新输入");
        } else {
            this.money = money;
        }
    }

    public void deposit(double money) {
        if (money < 0) {
            System.out.println("输入不合法，请重新输入");
        } else {
            this.money += money;
        }

    }

    public void withdraw(double money) {
        if (money < 0 || money > this.money) {
            System.out.println("输入不合法，请重新输入");
        } else {
            this.money -= money;
        }
    }

    public void checkBalance() {
        System.out.println("您现有存款：" + this.money);
    }
}