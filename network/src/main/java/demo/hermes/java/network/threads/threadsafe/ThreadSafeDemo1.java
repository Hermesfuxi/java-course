package demo.hermes.java.network.threads.threadsafe;

/**
 * 多线程安全问题模拟
 */
public class ThreadSafeDemo1 {

    public static void main(String[] args) {
//        TicketRunnableV0 ticketRunnableV0 = new TicketRunnableV0();
//        TicketRunnableV1 ticketRunnableV1 = new TicketRunnableV1();
//        TicketRunnableV2 ticketRunnableV2 = new TicketRunnableV2();
        TicketRunnableV3 ticketRunnableV3 = new TicketRunnableV3();

        // 电脑性能比较好的，线程数加大
        int threadNum = 100;
        for (int i = 0; i < threadNum; i++) {
//            Thread thread = new Thread(ticketRunnableV0);
//            Thread thread = new Thread(ticketRunnableV1);
//            Thread thread = new Thread(ticketRunnableV2);
            Thread thread = new Thread(ticketRunnableV3);
            thread.start();
        }


    }
}
