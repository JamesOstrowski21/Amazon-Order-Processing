import java.security.SecureRandom;

/**
 * Class for Section 3
 */
public class Section3 implements Runnable{
    /**
     * Random generator initialized
     */
    private static final SecureRandom generator = new SecureRandom();
    /**
     * Shipping Center to Section 3 buffer
     */
    private final Buffer CenterSection3;
    /**
     * Section 3 to shipping dock buffer
     */
    private final Buffer SectionDock;

    /**
     * Constructor to initialize buffers
     * @param CenterSection3 Shipping Center to Section 3 buffer
     * @param SectionDock Section 3 to Shipping Doc Buffer
     */
    public Section3(Buffer CenterSection3, Buffer SectionDock){
        this.CenterSection3 = CenterSection3;
        this.SectionDock = SectionDock;
    }

    /**
     * Runs the thread
     */
    @Override
    public void run(){
        while(CenterSection3 != null) {
            try {
                String[] order = CenterSection3.blockingGet();//gets order from buffer
                String[] newArray = new String[order.length + 1]; //add to order
                for (int cnt = 0; cnt < order.length; cnt++) {
                    newArray[cnt] = order[cnt];
                }
                newArray[newArray.length - 1] = "Section 3"; //add section 3 to order information
                Thread.sleep(generator.nextInt(5000)); //random sleep time
                SectionDock.blockingPut(newArray);

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
