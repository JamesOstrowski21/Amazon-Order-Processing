import java.security.SecureRandom;

/**
 * Class for truck 2
 */
public class Truck2 implements Runnable{
    /**
     * random generator initialized
     */
    private static final SecureRandom generator = new SecureRandom();
    /**
     * Shipping Doc to Truck 2 buffer
     */
    private final Buffer DockTruck2;

    /**
     * Constructor to initialize buffer
     * @param DockTruck2 Shipping Doc to Truck 2 buffer
     */
    public Truck2(Buffer DockTruck2){
        this.DockTruck2 = DockTruck2;
    }

    /**
     * Runs the thread
     */
    @Override
    public void run (){
        int y = 0;
        while(!Thread.currentThread().interrupted()){
            try {
                Thread.sleep(generator.nextInt(10000)); // random sleep 0-10 seconds
                String[] order = DockTruck2.blockingGet(); //get order
                String[] newArray = new String[order.length + 1]; // appends order
                for (int cnt = 0; cnt < order.length; cnt++) {
                    newArray[cnt] = order[cnt];
                }
                newArray[newArray.length - 1] = "Delivered by Truck 2"; //adds truck 2 to information on order

                System.out.println("Address: "+newArray[0]); //Displays order information to the screeen
                System.out.println("Name: "+newArray[4]);
                System.out.println("Item: "+newArray[5]);
                System.out.println("Product Category: "+newArray[6]);
                System.out.println("Shipping Center: "+newArray[7]);
                System.out.println("Center Section: "+newArray[8]);
                System.out.println("Truck: "+newArray[9]);
                System.out.println();

                y++;

                if (y == 8){
                    System.out.println("Truck 2 no more deliveries");
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
