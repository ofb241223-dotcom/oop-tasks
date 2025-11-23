public class Test07 {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setMoney(-1);
        bankAccount.setMoney(91.91);
        bankAccount.checkBalance();
        bankAccount.deposit(91);
        bankAccount.checkBalance();
        bankAccount.withdraw(10000);
        bankAccount.withdraw(91);
        bankAccount.checkBalance();
    }
}
