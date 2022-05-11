import java.security.SecureRandom;

/**
 * Class for Section 2
 */
public class Section2 implements Runnable{
    /**
     * random generator initialized
     */
    private static final SecureRandom generator = new SecureRandom();
    /**
     * Center to Section 2 buffer
     */
    private final Buffer CenterSection2;
    /**
     * Section 2 to dock buffer
     */
    private final Buffer SectionDock;

    /**
     * Constructor to initialize buffers
     * @param CenterSection2 Shipping center to Section 2 buffer
     * @param SectionDock Section 2 to Shipping Dock buffer
     */
    public Section2(Buffer CenterSection2, Buffer SectionDock){
        this.CenterSection2 = CenterSection2;
        this.SectionDock = SectionDock;
    }

    /**
     * Runs the Thread
     */
    @Override
    public void run(){
        while(CenterSection2 != null) {
            try {
                String[] order = CenterSection2.blockingGet();//get order from buffer
                String[] newArray = new String[order.length + 1]; //adds to order
                for (int cnt = 0; cnt < order.length; cnt++) {
                    newArray[cnt] = order[cnt];
                }
                newArray[newArray.length - 1] = "Section 2"; //Adds section 2 to order
                Thread.sleep(generator.nextInt(5000)); //Random sleep
                SectionDock.blockingPut(newArray);

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
