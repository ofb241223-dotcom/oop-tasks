public class Test12 {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        Thread thread1 = new Thread(t1, "数字线程");
        Thread thread2 = new Thread(t2, "字母线程");

        thread1.start();
        thread2.start();
    }
}

class MyThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        for (char i = 'A'; i <= 'J'; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}