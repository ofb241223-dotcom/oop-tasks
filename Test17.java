public class Test17 {
    public static void main(String[] args) {
        Cook cook = new Cook();
        Foodie foodie = new Foodie();

        cook.setName("厨师");
        foodie.setName("吃货");

        cook.start();
        foodie.start();
    }
}

class Cook extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    if (Desk.foodFlag == 0) {
                        System.out.println("厨师做了一碗面");
                        Desk.lock.notifyAll();
                        Desk.foodFlag = 1;
                    } else {
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}

class Foodie extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    if (Desk.foodFlag == 0) {
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("吃货在吃面条，还能再吃" + --Desk.count + "碗");
                        Desk.lock.notifyAll();
                        Desk.foodFlag = 0;
                    }
                }
            }
        }
    }
}

class Desk {
    // 0 不能吃 1 能吃
    public static int foodFlag = 0;

    public static int count = 10;

    public static Object lock = new Object();
}