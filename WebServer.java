import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


/**
 * Class for the Web Server
 */
public class WebServer implements Runnable{
    /**
     * Web server and Shipping Center Shared buffer
     */
    private final Buffer WebCenter;


    /**
     * Initializes WebCenter buffer
     * @param WebCenter Web Server and Shipping Center Shared buffer
     */
    public WebServer(Buffer WebCenter){
        this.WebCenter = WebCenter;
    }

    /**
     * runs the thread
     */
    @Override
    public void run() {
        String order; //stores order
        File file = new File("/iahome/j/jo/jostrowsk/IdeaProjects/SWD2022/jostrowsk_swd/oral_exam2/B05_Amazon_Order_Processing/src/Orders.csv"); //input file
        try {
            FileWriter out = new FileWriter("/iahome/j/jo/jostrowsk/IdeaProjects/SWD2022/jostrowsk_swd/oral_exam2/B05_Amazon_Order_Processing/src/backordered.csv"); //output file
            Scanner input = new Scanner(file);
            input.nextLine(); //skip headers for all orders
            while(input.hasNext()) { //while there are still orders to read
                order = input.nextLine(); //store order
                String[] orderData = order.split(","); //split into string array by delimeter
                if(orderData[1].equals("Washington") && orderData[2].equals("D.C.")){ //Remove Washington DC orders
                    out.write(order + "\r\n"); //Write to output file
                } else {
                    try {
                        WebCenter.blockingPut(orderData); //add to buffer
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            out.close(); //close files
            input.close();
        } catch (Exception e){

        }
        Thread.currentThread().interrupt();
        System.out.println("Out of orders"); //When the server runs out of order from file
    }
}
