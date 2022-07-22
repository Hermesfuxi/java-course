package demo.hermes.java.network.threads.threadsafe;

/**
 * 以卖票为例，多窗口卖火车票
 * 出现重复或负数现象：重复票或不存在票
 */
public class TicketRunnableV0 implements Runnable {
    private Integer ticketNum = 100;

    // 设置线程任务
    @Override
    public void run() {
        while (ticketNum > 0){
            // 使用线程 sleep 增加安全问题出现的概率 ？ TODO 搞明白为什么
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口" + Thread.currentThread().getName() + " --> 正在卖第" + ticketNum + "张火车票");
            ticketNum --;
        }
    }
}
