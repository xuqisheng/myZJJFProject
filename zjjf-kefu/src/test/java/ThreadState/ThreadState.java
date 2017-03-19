package ThreadState;

/**
 * Created by hwdd1 on 2017/2/20 0020.
 */
public class ThreadState {
    public static void main(String[] args) {
       new Thread(new TimeWaiting(),"TimeWatingThread").start();
       new Thread(new Waiting(),"WatingThread").start();

       new Thread(new Blocked(),"BlockedThread-1").start();
       new Thread(new Blocked(),"BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
           synchronized (Blocked.class){
               SleepUtils.second(100);
           }
        }
    }

}
