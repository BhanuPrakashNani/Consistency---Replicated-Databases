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

public class Server2 extends DB4STUB { 
   public Server2() {} 
   public static void main(String args[]) { 
	   //List<Student> list = null;

      try { 
         // Instantiating the implementation class 
         DB4STUB obj = new DB4STUB(); 
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi4", "root", "bhanuprakash");

         Statement stmt = conn.createStatement();

         // Exporting the object of implementation class (
         // here we are exporting the remote object to the stub) 
         DBRemote stubk = (DBRemote) UnicastRemoteObject.exportObject(obj,0);  
//         Hello stub = new ImplExample();

//         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(null);
         DBRemote stub_arr[] = new DBRemote[5]; 
//         Naming.bind("rmi://localhost:5000/sonoo",stub);  
         registry.bind("Hello4", stubk);  
         System.out.println("Server2 ready");
         DBRemote stub_self = (DBRemote) registry.lookup("Hello4");
         Thread.sleep(6000);
         DBRemote stub_s1 = (DBRemote) registry.lookup("Hello1");
         DBRemote stub_s2 = (DBRemote) registry.lookup("Hello2");
         DBRemote stub_s3 = (DBRemote) registry.lookup("Hello3");
         DBRemote stub_s5 = (DBRemote) registry.lookup("Hello5");

         stub_arr[0] = stub_s1;
         stub_arr[1] = stub_s2;
         stub_arr[2] = stub_s3;
         stub_arr[3] = stub_self;
         stub_arr[4] = stub_s5;
         System.out.println("lookup server2 ");

//         System.out.println(stub2.notify());
//         while(true) {
//             if(stub2.getstatus() ==1)
//        	 System.out.println(stub2.getMessage());
//
//         }
         
         read r = new read(stub_self);
	     Thread tr = new Thread(r);
	     tr.start();
	     
	     write w = new write(stub_self, stmt);
	     Thread tw = new Thread(w);
	     tw.start();
	     
         Random rand = new Random();
         int t =0, x = 0, c = 0;
         boolean idExists = false;
         Thread.sleep(5000);
         String name = "Process 4";
         String branch = "cse";
	     int percent = 01;
	     String email = "bhanu.gmail";

         while(stub_self.isSafe()) {
             Thread.sleep(10000);
             Student s = new Student();
             t = t%7;
             String query = "SELECT * FROM samplermi";
             ResultSet rs = stmt.executeQuery(query);
             while(rs.next()) {
		    	  if(t == rs.getInt("id")) {
		    		  idExists = true;
		    		  percent = rs.getInt("percentage")+2;
		    		  break;
		    	  }
		      }

             s.setID(t); 
	         s.setName(name); 
	         s.setBranch(branch); 
	         s.setPercent(percent); 
	         s.setEmail(email); 
	         s.setClock(c);
	         percent = 1;
//	         switch(rand.nextInt(2)) {
//	            case 1:
//	            	  stub_s1.addQobj(s);
//	            	  stub_s2.addQobj(s);
//	            	  stub_s3.addQobj(s);
//	            	  stub_s5.addQobj(s);
//
//	            	  stub_self.request(s); // request to write
//	       	       	  break;
//	            case 0:
//					//if(!Config.synchStart[3]) {
//					x = rand.nextInt(7);
//	            	Student st = stub_self.read(x);
//					//} 
//					//else{
//						//System.out.println("cant read");// read from db
//					//} // read from db
//	               break;
//	            default:
//	            	System.out.println("NOTHING");
//	         }
	         
	         //sync with other dbs
	         int tempStatus = stub_self.dbstatus(3)+stub_s1.dbstatus(3)+stub_s5.dbstatus(3);
	         
	         if(tempStatus > 0) {
	        	 System.out.println("Writer 2 inside loop1 ");
	        	 Queue<Student> q = stub_self.getQobj();
	        	 syncDB synch = new syncDB(q, tempStatus, stub_arr,3);
	        	 Thread thrd_sync = new Thread(synch);
	        	 thrd_sync.start();
	         }
	         
	         System.out.println("WRITER 2 "+t); 
	         t++;
	         c++;
	         
         }
     } 
      catch (Exception e) { 
         System.err.println("Server 2 exception: " + e.toString()); 
         e.printStackTrace(); 
      }
      

   }
}



