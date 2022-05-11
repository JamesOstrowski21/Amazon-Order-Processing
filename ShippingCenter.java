/**
 * Class for the shipping center
 */
public class ShippingCenter implements Runnable{
    /**
     * Web Server to Center buffer
     */
    private final Buffer WebCenter;
    /**
     * Center to Section 1 buffer
     */
    private final Buffer CenterSection1;
    /**
     * Center to section 2 buffer
     */
    private final Buffer CenterSection2;
    /**
     * Center to Section 3 Buffer
     */
    private final Buffer CenterSection3;

    /**
     * Constructor to initialize buffers
     * @param WebCenter Web server and Shipping Center buffer
     * @param centerSection1 Shipping Center and Section 1 buffer
     * @param CenterSection2 Shipping Center and Section 2 buffer
     * @param CenterSection3 Shipping Center and Section 3 buffer
     */
    public ShippingCenter(Buffer WebCenter, Buffer centerSection1,Buffer CenterSection2, Buffer CenterSection3){
        this.WebCenter = WebCenter;
        this.CenterSection1 = centerSection1;
        this.CenterSection2 = CenterSection2;
        this.CenterSection3 = CenterSection3;
    }

    /**
     * Runs the Thread
     */
    @Override
    public void run(){
        while(!Thread.currentThread().interrupted()) {
            try {
                String[] order = WebCenter.blockingGet(); // gets order from web server
                String[] newArray = new String[order.length + 1]; //appends string
                for (int cnt = 0; cnt < order.length; cnt++) {
                    newArray[cnt] = order[cnt];
                }
                newArray[newArray.length - 1] = "Shipping Center 1"; //adds shipping center 1 to information
                char start = newArray[6].charAt(0); //gets first letter of Category
                if(start >= 'A'&& start <= 'E'){ //Next 6 lines add each order to the correct buffer
                    CenterSection1.blockingPut(newArray);
                } else if(start >= 'F' && start <= 'S'){
                    CenterSection2.blockingPut(newArray);
                }else if(start >= 'T' && start <= 'Z'){
                    CenterSection3.blockingPut(newArray);
                }

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }


    }
}
