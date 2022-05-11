/**
 * Class for the Shipping Dock
 */
public class ShippingDock implements Runnable{
    /**
     * Sections to Dock buffer
     */
    private final Buffer SectionDock;
    /**
     * Dock to Truck 1 buffer
     */
    private final Buffer DockTruck1;
    /**
     * Dock to truck 2 buffer
     */
    private final Buffer DockTruck2;

    /**
     * Constructor to initialize buffers
     * @param SectionDock shared buffer between Sections and the doc
     * @param DockTruck1 shared buffer between doc and truck 1
     * @param DockTruck2 shared buffer between doc and truck 2
     */
    public ShippingDock(Buffer SectionDock, Buffer DockTruck1, Buffer DockTruck2){
        this.SectionDock = SectionDock;
        this.DockTruck1 = DockTruck1;
        this.DockTruck2 = DockTruck2;
    }

    /**
     * Runs thread
     */
    @Override
    public void run() {
        int x = 0;
        while(!Thread.currentThread().interrupted()) {
            try {
                if(x >=0 && x < 4){
                    String[] newArray = SectionDock.blockingGet();//gets order
                    DockTruck1.blockingPut(newArray); //puts first 4 orders on truck 1
                }
                if(x >= 4 && x <8) { //puts next 4 orders on truck 2
                    DockTruck2.blockingPut(SectionDock.blockingGet());
                }
                if(x == 7){ //resets back to truck 1
                    x = 0;
                }
                x++;

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
