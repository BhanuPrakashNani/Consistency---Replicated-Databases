import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.*; 
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Server3 extends DB4STUB { 
   public Server3() {} 
   public static void main(String args[]) { 
	   //List<Student> list = null;

      try { 
         // Instantiating the implementation class 
         DB5STUB obj = new DB5STUB(); 
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi5", "root", "asdf;lkj");

         Statement stmt = conn.createStatement();

         // Exporting the object of implementation class (
         // here we are exporting the remote object to the stub) 
         DBRemote stubk = (DBRemote) UnicastRemoteObject.exportObject(obj,0);  
//         Hello stub = new ImplExample();

//         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(null);
         DBRemote stub_arr[] = new DBRemote[5]; 
//         Naming.bind("rmi://localhost:5000/sonoo",stub);  
         registry.bind("Hello5", stubk);  
         System.out.println("Server3 ready");
         DBRemote stub_self = (DBRemote) registry.lookup("Hello5");
         Thread.sleep(2000);
         DBRemote stub_s1 = (DBRemote) registry.lookup("Hello1");
         DBRemote stub_s2 = (DBRemote) registry.lookup("Hello2");
         DBRemote stub_s3 = (DBRemote) registry.lookup("Hello3");
         DBRemote stub_s4 = (DBRemote) registry.lookup("Hello4");
         stub_arr[0] = stub_s1;
         stub_arr[1] = stub_s2;
         stub_arr[2] = stub_s3;
         stub_arr[3] = stub_s4;
         stub_arr[4] = stub_self;
         System.out.println("lookup server3 ");

//         System.out.println(stub2.notify());
//         while(true) {
//             if(stub2.getstatus() ==1)
//        	 System.out.println(stub2.getMessage());
//
//         }
         Random rand = new Random();
         int t =0, x = 0, c = 0;
         boolean idExists = false;
         Thread.sleep(5000);
         String name = "Process 5";
         String branch = "cse";
	     int percent = 01;
	     String email = "p5.gmail";

         while(stub_self.isSafe()) {
             Thread.sleep(5000);
             Student s = new Student();
             t = t%7;
             String query = "SELECT * FROM SAMPLERMI";
             ResultSet rs = stmt.executeQuery(query);
             while(rs.next()) {
		    	  if(t == rs.getInt("id")) {
		    		  idExists = true;
		    		  percent = rs.getInt("percentage")*7;
		    		  break;
		    	  }
		      }

             s.setID(0); 
	         s.setName(name); 
	         s.setBranch(branch); 
	         s.setPercent(percent); 
	         s.setEmail(email); 
	         s.setClock(c);
	         percent = 1;
	         switch(rand.nextInt(2)) {
	            case 1:
	            	  stub_s1.addQobj(s);
	            	  stub_s2.addQobj(s);
	            	  stub_s3.addQobj(s);
	            	  stub_s4.addQobj(s);
	            	  stub_self.request(s); // request to write
	       	       	  break;
	            case 0:
					if(!Config.synchStart[4]) {
					//x = rand.nextInt(7);
					x=0;
	            	Student st = stub_self.read(x);
					} 
					else{
						System.out.println("cant read");// read from db
					} // read from db
					x=0;
	            	Student st = stub_self.read(x);
	               break;
	            default:
	            	System.out.println("NOTHING");
	         }
	         
	         //sync with other dbs
	         int tempStatus = stub_self.dbstatus(4)+stub_s1.dbstatus(4)+stub_s4.dbstatus(4);
	         
	         if(tempStatus > 0) {
	        	 System.out.println("Writer 3 inside loop1 ");
	        	 Queue<Student> q = stub_self.getQobj();
	        	 syncDB synch = new syncDB(q, tempStatus, stub_arr,4);
	        	 Thread thrd_sync = new Thread(synch);
	        	 thrd_sync.start();
	         }
	         
	         System.out.println("WRITER 3 "+t); 
	         t++;
	         c++;
	         
         }
     } 
      catch (Exception e) { 
         System.err.println("Server 3 exception: " + e.toString()); 
         e.printStackTrace(); 
      }
      

   }
}



