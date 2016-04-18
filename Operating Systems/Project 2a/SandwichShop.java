/*
The Sandwich Shop Problem

The sandwich shop in this problem is very small. It has a single employee and there is room for only one customer to be in the shop at a time. This problem contains two types of threads. There is a single thread modeling an employee and there are multiple threads modeling customers.

The employee waits in the shop until a customer places an order. Once a customer places an order the employee takes a random amount of time to make the sandwich. When the sandwich is made, the employee carries the sandwich to the cash register and waits for the customer to pay. Once the customer pays, the employee waits for the next order.

Each customer thread takes a random amount of time to travel to the sandwich shop. After travelling the the customer arrives at the sandwich shop. If the shop is not empty the customer must wait until the shop is empty before leaving. The sandwiches are excellent, so the customer will wait as long as it takes to get into the shop. When the shop is empty, one of the waiting customers enters the shop and places an order. The customer then browses around the shop for a random amount of time. After browsing, the customer goes to the cash register and waits for the employee to bring the sandwich. When the employee arrives at the cash register, the customer pays for the sandwich and leaves the shop.

Your solution for the employee will augment the run methods in the EmployeeThread class and the CustomerThreaad class to enforce the synchronization constraints just described. Each of these run methods currently contains method calls that indicate what the thread is doing and provide the random waits that have been described. Once synchronized these methods must be invoked as described below.

The EmployeeThread must invoke the method waitForCustomer() to indicate that it is waiting for a customer to order. It must invoke the makeSanwich() method when it begins making a sandwich for the customer. When the sandwich is finished being made, the EmployeeThread must invoke the atCashRegister() method to indicate that the employee is waiting for payment. Finally the EmployeeThread must invoke the aymentAccepted() method to indicate that the customer's payment has been taken.

The CustomerThread must invoke the method travelToShop() when it is first started. When the customer arrives at the shop it must invoke the arriveAtShop() method. When the shop is empty and the customer has entered it must invoke the placeOrder() method. After the order has been placed, the CustomerThread must invoke the browseShop() method. When the customer is finished browsing it must invoke the atRegister() method. Finally, after the employee has taken the customer's payment the CustomerThread must invoke the leaveShop() method.
*/

public class SandwichShop {
	
	static Semaphore enterShop = new Semaphore(1);
	static Semaphore order = new Semaphore(0);
	static Semaphore pay = new Semaphore(0);
	
    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
        }

        int num = 0;
        try {
            num = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            printUsage();
        }

        System.out.println("Customer:\t\t\t\t\t\t\t\t\t\t\t| Employee:");
        System.out.print("Traveling\tArrived\t\tOrdering\tBrowsing\tAt Register\tLeaving");
        System.out.println("\t\t| Waiting\tSandwich Making\t\tAt Register\tPayment Accepted");
        System.out .println("---------------------------------------------------"
                        + "---------------------------------------------+--------"
                        + "-------------------------------------------------------------------");

        Thread emp = new EmployeeThread();
        emp.start();

        Thread[] custs = new Thread[num];
        for (int i = 0; i < num; i++) {
            custs[i] = new CustomerThread(i);
            custs[i].start();
        }
        for (int i = 0; i < num; i++) {
            try {
                custs[i].join();
            }
            catch (InterruptedException e) {
            }
        }

        System.exit(0);
    }

    private static void printUsage() {
        System.out.println("Usage: java SandwichShop <num>");
        System.out.println("  <num>: Number of customers.");
        System.exit(-1);
    }

    public static void randomSleep(int max) {
        try {
            Thread.sleep((int) (Math.random() * max));
        }
        catch (InterruptedException e) {
        }
    }
}

class CustomerThread extends Thread {

    private int id;

    public CustomerThread(int id) {
        this.id = id;
    }

    public void run() {
        travelToShop();
        arriveAtShop();
        SandwichShop.enterShop.acquire(); //Make sure only customer in shop
        placeOrder();
        SandwichShop.order.release(); //Notify order to employee
        browseShop();
        atRegister();
        SandwichShop.pay.release(); //Notify payment to employee
        SandwichShop.order.acquire(); //Wait for employee to complete order
        leaveShop();
        SandwichShop.enterShop.release();
    }

    private void travelToShop() {
        System.out.println("Customer " + id + "\t\t\t\t\t\t\t\t\t\t\t|");
        SandwichShop.randomSleep(1000);
    }

    private void arriveAtShop() {
        System.out.println("\t\tCustomer " + id + "\t\t\t\t\t\t\t\t\t|");
    }

    private void placeOrder() {
        System.out.println("\t\t\t\tCustomer " + id + "\t\t\t\t\t\t\t|");
    }

    private void browseShop() {
        System.out.println("\t\t\t\t\t\tCustomer " + id + "\t\t\t\t\t|");
        SandwichShop.randomSleep(1000);
    }

    private void atRegister() {
        System.out.println("\t\t\t\t\t\t\t\tCustomer " + id + "\t\t\t|");
    }

    private void leaveShop() {
        System.out.println("\t\t\t\t\t\t\t\t\t\tCustomer " + id + "\t|");
    }
}

class EmployeeThread extends Thread {

    public void run() {
        while (true) {
            waitForCustomer();
            SandwichShop.order.acquire();//Wait for customer order
            makeSandwich();
            atCashRegister();
            SandwichShop.pay.acquire();//Wait for customer payment
            paymentAccepted();
            SandwichShop.order.release();//Notify customer of completed order
        }
    }

    private void waitForCustomer() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| Employee");
    }

    private void makeSandwich() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| \t\tEmployee");
        SandwichShop.randomSleep(1000);
    }

    private void atCashRegister() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| \t\t\t\t\tEmployee");
    }

    private void paymentAccepted() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| \t\t\t\t\t\t\tEmployee");
    }
}