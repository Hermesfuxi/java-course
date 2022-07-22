package demo.hermes.java.network.threads.threadsafe;

/**
 * 以卖票为例，多窗口卖火车票
 * 出现重复或负数现象：重复票或不存在票
 * 使用 同步方法锁 解决线程安全问题：锁对象 就是 实现类对象 this
 */
public class TicketRunnableV2 implements Runnable {
    private Integer ticketNum = 1000;

    // 设置线程任务
    @Override
    public void run() {
        while (ticketNum > 0){
            sleepTime();
            trueImpl();
        }

//        while (ticketNum > 0){
//            errorImpl2();
//        }

//        errorImpl1();
    }

    // 正确实现
    private synchronized void trueImpl(){
        if(ticketNum > 0){
            System.out.println("窗口" + Thread.currentThread().getName() + " --> 正在卖第" + ticketNum + "张火车票");
            ticketNum --;
        }
    }

    // 错误实现1
    private synchronized void errorImpl1(){
        while (ticketNum > 0){
            sleepTime();
            if(ticketNum > 0){
                System.out.println("窗口" + Thread.currentThread().getName() + " --> 正在卖第" + ticketNum + "张火车票");
                ticketNum --;
            }
        }
    }

    // 错误实现2：先抢锁，再 sleep，脉冲型轮询锁，效率低
    private synchronized void errorImpl2(){
        sleepTime();
        if(ticketNum > 0){
            System.out.println("窗口" + Thread.currentThread().getName() + " --> 正在卖第" + ticketNum + "张火车票");
            ticketNum --;
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
