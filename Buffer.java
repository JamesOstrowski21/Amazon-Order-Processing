// Fig. 23.9: Buffer.java
// Buffer interface specifies methods called by Producer and Consumer.

/**
 * Buffer interface used in blocking buffer
 */
public interface Buffer {
   /**
    * Places order into buffer
    * @param order order as string array
    * @throws InterruptedException
    */
   // place String[] order into Buffer
   public void blockingPut(String[] order) throws InterruptedException;

   /**
    * removes order from buffer
    * @return returns order as string array
    * @throws InterruptedException
    */
   // return string[] from Buffer
   public String[] blockingGet() throws InterruptedException;


}
