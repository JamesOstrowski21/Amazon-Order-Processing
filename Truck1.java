import java.security.SecureRandom;

/**
 * Class for Truck 1
 */
public class Truck1 implements Runnable{
    /**
     * random generator initialized
     */
    private static final SecureRandom generator = new SecureRandom();
    /**
     * Dock to truck 1 buffer
     */
    private final Buffer DockTruck1;

    /**
     * Constructor to initialize buffer
     * @param DockTruck1 Shipping Doc to Truck 1 buffer
     */
    public Truck1(Buffer DockTruck1){
        this.DockTruck1 = DockTruck1;
    }

    /**
     * Runs the thread
     */
    @Override
    public void run (){
        int x = 0;
        while(!Thread.currentThread().interrupted()) {
            try {
                Thread.sleep(generator.nextInt(10000));// random sleep 0-10 seconds
                String[] order = DockTruck1.blockingGet(); //gets order
                String[] newArray = new String[order.length + 1];
                for (int cnt = 0; cnt < order.length; cnt++) {
                    newArray[cnt] = order[cnt];
                }
                newArray[newArray.length - 1] = "Delivered by Truck 1"; //appends truck 1 to information on order

                System.out.println("Address: " + newArray[0]); //Displays all information to the screen
                System.out.println("Name: " + newArray[4]);
                System.out.println("Item: " + newArray[5]);
                System.out.println("Product Category: " + newArray[6]);
                System.out.println("Shipping Center: " + newArray[7]);
                System.out.println("Center Section: " + newArray[8]);
                System.out.println("Truck: " + newArray[9]);
                System.out.println();

                x++;
                if(x == 10){
                    System.out.println("Truck 1 no more deliveries");
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

    }
}