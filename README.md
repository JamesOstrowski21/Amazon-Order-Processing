# Amazon-Order-Processing

#bProblem Statement
Using an array Blocking Queue as a buffer, construct a program that simulates the order process of amazon.com. 
Starting from when you place the order to when it is delivered to your house.

# Devloper Documentation
The program consists of several classes; Buffer.java, BlockingBuffer.java, WebServer.Java, Section1.java, Section2.java, 
Section3.java, ShippingCenter.java, ShippingDock.java, Truck1.java, Truck2.java, and OrderProcessingDriver.java. The Buffer 
class serves as an interface used within BlockingBuffer.java. BlockingBuffer.Java sets up the blockingPut() and blockingGet() 
methods for adding and removing orders from the buffer. WebServer.java reads in the provided csv file containing the order 
information and adds them to the buffer shared between the server and the shipping Center. ShippingCenter.java takes the orders 
from the buffer and adds them to one of the 3 buffers for each Section class based on the starting letter of the orders product 
category. All 3 Section classes then add the orders to the dock buffer. The ShippingDock.java then places 4 orders in each buffer 
for the delivery trucks. The Truck classes take the orders have a random time frame from pickup to delivery and then output order 
information to the screen after delivery. The OrderProcessingDriver class creates each buffer needed and executes the threads for 
each nod

