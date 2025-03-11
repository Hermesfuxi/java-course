package demo.hermes.java.network.threads.threadsafe;

/**
 * 以卖票为例，多窗口卖火车票
 * 出现重复或负数现象：重复票或不存在票
 * 使用 同步代码块 解决线程安全问题：使用了一个锁对象（或同步锁、或对象锁、或对象监视器），先抢到锁的线程先执行，其他线程进入阻塞，等待锁被释放后再抢夺
 * 程序频繁的判断锁、释放锁，程序效率会降低
 */
public class TicketRunnableV1 implements Runnable {
    private volatile Integer ticketNum = 100;

    // 设置线程任务
    @Override
    public void run() {
        trueImpl();
//        errorImpl();
    }

    // 正确实现
    private void trueImpl(){
        while (ticketNum > 0){
            sleepTime();
            synchronized (ticketNum){
                if(ticketNum > 0){
                    System.out.println("窗口" + Thread.currentThread().getName() + " --> 正在卖第" + ticketNum + "张火车票");
                    ticketNum --;
                }
            }
        }
    }

    // 错误实现： 只能保证没有重复票，无法避免负数票，因为同步代码的逻辑没有覆盖逻辑判断
    private void errorImpl(){
        while (ticketNum > 0){
            sleepTime();
            synchronized (ticketNum){
                System.out.println("窗口" + Thread.currentThread().getName() + " --> 正在卖第" + ticketNum + "张火车票");
                ticketNum --;
            }
        }
    }

    private void sleepTime(){
        // 使用线程 sleep 增加安全问题出现的概率
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
