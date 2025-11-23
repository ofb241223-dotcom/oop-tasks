import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Test32 {
    static BankAccountManagement bam = new BankAccountManagement();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("欢迎进入银行账户管理系统\n");
        System.out.println("请选择运行模式：");
        System.out.println("1. 交互模式");
        System.out.println("2. 多线程测试模式");
        int mode = sc.nextInt();
        if (mode == 1) {
            interactiveMode();
        } else if (mode == 2) {
            multiThreadTest();
        }
    }

    private static void interactiveMode() {
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    bam.displayAllAccounts();
                    break;
                case 6:
                    deleteAccount();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("输入不合法，请重新输入");
            }
        }
    }

    private static void showMenu() {
        System.out.println(" 1. 创建账户 \n 2. 存款 \n 3. 取款 \n 4. 查询余额 \n" +
                " 5. 显示所有账户 \n 6. 删除账户 \n 0. 退出系统");
    }

    private static void createAccount() {
        System.out.print("请输入账户号：");
        String account = sc.nextLine();
        System.out.print("请输入ID：");
        String id = sc.nextLine();
        System.out.print("请输入用户名：");
        String userName = sc.nextLine();
        System.out.print("请输入初始余额：");
        double balance = sc.nextDouble();
        sc.nextLine();
        bam.addBankAccount(new BankAccount(account, id, userName, balance));
    }

    private static void deposit() {
        System.out.print("请输入账户ID：");
        String id = sc.nextLine();
        System.out.print("请输入存款金额：");
        double amount = sc.nextDouble();
        sc.nextLine();
        bam.deposit(id, amount);
    }

    private static void withdraw() {
        System.out.print("请输入账户ID：");
        String id = sc.nextLine();
        System.out.print("请输入取款金额：");
        double amount = sc.nextDouble();
        sc.nextLine();
        bam.withdraw(id, amount);
    }

    private static void checkout() {
        System.out.print("请输入账户ID：");
        String id = sc.nextLine();

        bam.checkout(id);
    }

    private static void deleteAccount() {
        System.out.print("请输入要删除的账户ID：");
        String id = sc.nextLine();

        bam.deleteAccount(id);
    }

    private static void multiThreadTest() {
        bam.clearAllAccounts();
        System.out.println("开始多线程测试...");
        bam.addBankAccount(new BankAccount("6217000010000001", "001", "张三", 10000));
        System.out.println("账户001初始余额：10000");
        System.out.println("模拟10个线程同时操作账户001：");
        System.out.println("- 4个线程存款100（编号0,3,6,9）");
        System.out.println("- 3个线程取款50（编号1,4,7）");
        System.out.println("- 3个线程查询余额（编号2,5,8）");

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            int index = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程-" + index + " 开始执行");

                    if (index % 3 == 0) {
                        bam.deposit("001", 100);
                    } else if (index % 3 == 1) {
                        bam.withdraw("001", 50);
                    } else {
                        bam.checkout("001");
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads[i] = new Thread(task);
            threads[i].start();
        }

        System.out.println("主线程等待所有子线程完成...\n");
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("所有线程执行完毕！");
        System.out.println("期望最终余额：10250 (10000 + 400 - 150)");
        System.out.println("最终结果：");
        bam.displayAllAccounts();
    }

    static class BankAccount {
        String account;
        String id;
        String userName;
        double balance;

        public BankAccount(String account, String id, String userName, double balance) {
            this.account = account;
            this.id = id;
            this.userName = userName;
            this.balance = balance;
        }

        public synchronized void depositMoney(double amount) {
            if (amount <= 0) {
                System.out.println("存款金额必须大于0");
                return;
            }
            balance += amount;
            System.out.println("存款成功，该账户当前余额：" + balance);
        }

        public synchronized boolean withdrawMoney(double amount) {
            if (amount <= 0) {
                System.out.println("取款金额必须大于0");
                return false;
            }
            if (amount > balance) {
                System.out.println("余额不足");
                return false;
            }
            balance -= amount;
            System.out.println("取款成功，该账户当前余额：" + balance);
            return true;
        }

        public synchronized double getBalance() {
            return balance;
        }

        public synchronized void setBalance(double balance) {
            this.balance = balance;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            BankAccount that = (BankAccount) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public void displayInfo() {
            System.out.println("账号：" + account);
            System.out.println("ID：" + id);
            System.out.println("用户名：" + userName);
            System.out.println("余额：" + balance);
        }
    }

    static class BankAccountManagement {
        private HashMap<String, BankAccount> accounts;
        private String FILE_NAME = "bankAccounts.txt";

        public BankAccountManagement() {
            accounts = new HashMap<>();
            loadFromFile();
        }

        public void addBankAccount(BankAccount bankAccount) {
            synchronized (accounts) {
                if (accounts.containsKey(bankAccount.getId())) {
                    System.out.println("该账户ID已存在");
                    return;
                }
                accounts.put(bankAccount.getId(), bankAccount);
                System.out.println("添加成功");
            }
            saveToFile();
        }

        public void deposit(String id, double amount) {
            BankAccount account;

            synchronized (accounts) {
                account = accounts.get(id);
            }

            if (account == null) {
                System.out.println("该账户不存在");
                return;
            }

            account.depositMoney(amount);
            saveToFile();
        }

        public void withdraw(String id, double amount) {
            BankAccount account;

            synchronized (accounts) {
                account = accounts.get(id);
            }

            if (account == null) {
                System.out.println("该账户不存在");
                return;
            }

            if (account.withdrawMoney(amount)) {
                saveToFile();
            }
        }

        public void checkout(String id) {
            BankAccount account;

            synchronized (accounts) {
                account = accounts.get(id);
            }

            if (account == null) {
                System.out.println("该账户不存在");
                return;
            }

            System.out.println("该账户余额为：" + account.getBalance());
        }

        public void displayAllAccounts() {
            synchronized (accounts) {
                if (accounts.isEmpty()) {
                    System.out.println("暂无账户数据");
                    return;
                }

                System.out.println("\n====== 所有账户信息 ======");
                System.out.println("共有 " + accounts.size() + " 个账户\n");

                int index = 1;
                for (BankAccount account : accounts.values()) {
                    System.out.println("【账户 " + index + "】");
                    account.displayInfo();
                    System.out.println();
                    index++;
                }
            }
        }

        public void deleteAccount(String id) {
            synchronized (accounts) {
                BankAccount removed = accounts.remove(id);
                if (removed != null) {
                    System.out.println("已删除该账户");
                    saveToFile();
                } else {
                    System.out.println("账户不存在");
                }
            }
        }

        public void saveToFile() {
            FileWriter fw = null;
            try {
                fw = new FileWriter(FILE_NAME);

                synchronized (accounts) {
                    for (BankAccount account : accounts.values()) {
                        String line = account.getAccount() + "," +
                                account.getId() + "," +
                                account.getUserName() + "," +
                                account.getBalance() + "\n";
                        fw.write(line);
                    }
                }

                System.out.println("数据已保存");
            } catch (IOException e) {
                System.out.println("保存失败：" + e.getMessage());
            } finally {
                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void loadFromFile() {
            File file = new File(FILE_NAME);

            FileReader fr = null;
            try {
                fr = new FileReader(FILE_NAME);
                char[] buffer = new char[1024];
                int length;
                StringBuilder sb = new StringBuilder();

                while ((length = fr.read(buffer)) != -1) {
                    sb.append(buffer, 0, length);
                }

                String allContent = sb.toString();
                String[] lines = allContent.split("\n");

                synchronized (accounts) {
                    accounts.clear();

                    for (String line : lines) {
                        line = line.trim();
                        if (line.isEmpty()) {
                            continue;
                        }
                        String[] parts = line.split(",");
                        if (parts.length == 4) {
                            String account = parts[0];
                            String id = parts[1];
                            String userName = parts[2];
                            double balance = Double.parseDouble(parts[3]);
                            accounts.put(id, new BankAccount(account, id, userName, balance));
                        }
                    }
                }

                System.out.println("成功加载 " + accounts.size() + " 个账号");
            } catch (IOException e) {
                System.out.println("读取失败：" + e.getMessage());
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void clearAllAccounts() {
            synchronized (accounts) {
                accounts.clear();
                System.out.println("已清空所有账户");
            }
            saveToFile();
        }
    }
}