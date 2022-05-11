import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Driver class for order processing program
 */

public class OrderProcessingDriver {
    public static void main(String[] args) throws InterruptedException {
        // create new thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();

        // create BlockingBuffer to store orders
        Buffer WebCenter = new BlockingBuffer(1);
        Buffer CenterSection1 = new BlockingBuffer(1);
        Buffer CenterSection2 = new BlockingBuffer(1);
        Buffer CenterSection3 = new BlockingBuffer(1);
        Buffer SectionDock = new BlockingBuffer(20);
        Buffer DockTruck1 = new BlockingBuffer(4);
        Buffer DockTruck2 = new BlockingBuffer(4);

        //Executes each thread
        executorService.execute(new WebServer(WebCenter));
        executorService.execute(new ShippingCenter(WebCenter,CenterSection1,CenterSection2,CenterSection3));
        executorService.execute(new Section1(CenterSection1,SectionDock));
        executorService.execute(new Section2(CenterSection2,SectionDock));
        executorService.execute(new Section3(CenterSection3,SectionDock));
        executorService.execute(new ShippingDock(SectionDock,DockTruck1, DockTruck2));
        executorService.execute(new Truck1(DockTruck1));
        executorService.execute(new Truck2(DockTruck2));



        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}

