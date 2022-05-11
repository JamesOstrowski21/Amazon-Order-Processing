// Fig. 23.14: BlockingBuffer.java
// Creating a synchronized buffer using an ArrayBlockingQueue.

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Blocking buffer class used for all buffers
 */

public class BlockingBuffer implements Buffer {
   private  final ArrayBlockingQueue<String[]> buffer; // shared buffer

   /**
    * Constructor for all buffers used
    * @param capacity - how many items the buffer can hold
    */
   public BlockingBuffer(int capacity) {
      buffer = new ArrayBlockingQueue<String[]>(capacity);
   }

   /**
    * Places order in buffer
    */
   // place order into buffer
   @Override
   public void blockingPut(String[] order) throws InterruptedException {
      buffer.put(order); // place order in buffer
   }

   /**
    * Removes order from buffer
    * @return returns order as string array
    * @throws InterruptedException
    */
   // return order from buffer
   @Override
   public String[] blockingGet() throws InterruptedException {
      String[] readOrder = buffer.take(); // remove order from buffer

      return readOrder;
   } 
} 
