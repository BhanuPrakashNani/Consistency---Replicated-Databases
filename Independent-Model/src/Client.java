import java.rmi.registry.LocateRegistry;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*; 
import java.sql.*;

public class Client {  
   private Client() {}  
   public static void main(String[] args)throws Exception, ClassNotFoundException {  
	   List<Student> list = null;
	   
	   Student s;
      try { 
    	  DB2STUB obj = new DB2STUB(); 
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi2", "root", "bhanuprakash");

          Statement stmt = conn.createStatement();

          // Exporting the object of implementation class (
          // here we are exporting the remote object to the stub) 
          DBRemote stubk = (DBRemote) UnicastRemoteObject.exportObject(obj,0);  
//          Hello stub = new ImplExample();

//          // Binding the remote object (stub) in the registry 
          Registry registry = LocateRegistry.getRegistry(null);
//          Naming.bind("rmi://localhost:5000/sonoo",stub);  
          registry.bind("Hello2", stubk);  
          System.out.println("Client ready");
         
         // Looking up the registry for the remote object 

         
         DBRemote stub_self = (DBRemote) registry.lookup("Hello2");
         Thread.sleep(8000);
         DBRemote stub = (DBRemote) registry.lookup("Hello1"); 
         DBRemote stub3 = (DBRemote) registry.lookup("Hello3");
         DBRemote stub4 = (DBRemote) registry.lookup("Hello4");
         DBRemote stub5 = (DBRemote) registry.lookup("Hello5");

         // Hello stub=(Hello)Naming.lookup("rmi://localhost:5000/sonoo");  
         System.out.println("lookup client");
         // Calling the remote method using the obtained object 
         DBRemote stub_arr[] = new DBRemote[5]; 
         stub_arr[0] = stub;
         stub_arr[1] = stub_self;
         stub_arr[2] = stub3;
         stub_arr[3] = stub4;
         stub_arr[4] = stub5;
         Random  rand = new Random();

         read r = new read(stub_self);
	     Thread tr = new Thread(r);
	     tr.start();
           
      // System.out.println(list); 
       int t=0;

      while(stub_self.isSafe()) {
    	  Thread.sleep(7000);

	         int tempStatus = stub4.dbstatus(1)+stub.dbstatus(1)+stub5.dbstatus(1);
//            
//				//if(!Config.synchStart[1]) {
//				int x = rand.nextInt(7);
//         	Student st = stub_self.read(x);
////				} 
////				else{
////					System.out.println("cant read");// read from db
////				}
	         if(tempStatus > 0) {
	        	 System.out.println("Client 1 inside loop1 ");
	        	 Thread.sleep(200);
	        	 Queue<Student> q = stub_self.getQobj();
	        	 syncDB synch = new syncDB(q, tempStatus, stub_arr,1); //ERROR: while notifying we are not notifying to the correct manager.
	        	 Thread thrd_sync = new Thread(synch);
	        	 thrd_sync.start();
	         }
	         
	         t++;
//    	  System.out.println("Server not updated");
      }

     
      } catch (Exception e) { 
          System.err.println("Client exception: " + e);
       }
      }
   
}