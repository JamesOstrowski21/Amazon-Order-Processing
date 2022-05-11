import java.security.SecureRandom;

/**
 * Class for Section 1
 */
public class Section1 implements Runnable{
    /**
     * random generator intialize
     */
    private static final SecureRandom generator = new SecureRandom();
    /**
     * Center to Section 1 buffer
     */
    private final Buffer CenterSection1;
    /**
     * Section to dock buffer
     */
    private final Buffer SectionDock;

    /**
     * constructor to initialize buffers
     * @param CenterSection1 Shipping Center to Section 1 buffer
     * @param SectionDock Section 1 to Dock buffer
     */
    public Section1(Buffer CenterSection1, Buffer SectionDock){
        this.CenterSection1 = CenterSection1;
        this.SectionDock = SectionDock;
    }

    /**
     * Run the thread.
     */
    @Override
    public void run(){
        while(CenterSection1 != null) {
            try {
                String[] order = CenterSection1.blockingGet();// gets order
                String[] newArray = new String[order.length + 1]; //adds to order
                for (int cnt = 0; cnt < order.length; cnt++) {
                    newArray[cnt] = order[cnt];
                }
                newArray[newArray.length - 1] = "Section 1"; //adds section 1 to order
                Thread.sleep(generator.nextInt(5000)); //random sleep time 0-5 seconds
                SectionDock.blockingPut(newArray); //adds to Dock buffer

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
