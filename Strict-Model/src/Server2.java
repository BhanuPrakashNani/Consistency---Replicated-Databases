import java.rmi.registry.*; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;

public class Server2 extends ImplExample2 { 
   public Server2() {} 
   public static void main(String args[]) { 
	   List<Student> list = null;

      try { 
         // Instantiating the implementation class 
         ImplExample2 obj = new ImplExample2(); 
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi4", "root", "asdf;lkj");

         Statement stmt = conn.createStatement();

         // Exporting the object of implementation class (
         // here we are exporting the remote object to the stub) 
         Hello stubk = (Hello) UnicastRemoteObject.exportObject(obj,0);  
//         Hello stub = new ImplExample();

//         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(null);
//         Naming.bind("rmi://localhost:5000/sonoo",stub);  
         registry.bind("Hello2", stubk);  
         System.out.println("Server2 ready");
         Hello stub_self = (Hello) registry.lookup("Hello2");
         Thread.sleep(2000);
         Hello stub_s1 = (Hello) registry.lookup("Hello");
         System.out.println("lookup server2 ");

         Random rand = new Random();
         int t =0,x = 0;

         while(Config.SAFE) {
             Thread.sleep(2000);
	             System.out.println("HElllo man DBSTATUS1 "+stub_s1.dbstatus(0)+" "+stub_s1.dbstatus(1)+" "+stub_s1.dbstatus(2)+" "+stub_s1.dbstatus(3)+ " is Write "+stub_s1.isWrite());

        	 if(stub_s1.dbstatus(3) == 1) {
        		  System.out.println("???ASSAS??AS?SA?A");

        		  if(stub_s1.isWrite()) {
//        		  list = (List<Student>)stub_s1.getStudents();
//         	      String insert = "INSERT INTO samplermi(id, name, branch, percentage, email) values("+list.get(list.size()-1).getId()+",'"+list.get(list.size()-1).getName()+"','"+ list.get(list.size()-1).getBranch()+"',"+list.get(list.size()-1).getPercent()+",'"+ list.get(list.size()-1).getEmail()+ "')";
        			 String exec = stub_s1.getStmt();
        			 int count=stmt.executeUpdate(exec);
        		 }
         	      stub_s1.notify(3);
         	      System.out.println("Replicated Server 1 to Server 2");
         	  }
        	 if(stub_self.dbstatus() == 0 && stub_s1.dbstatus() == 0 && stub_s1.getStatus() == 0) {
        		 stub_self.setStatus();
        		 
	             System.out.println("T x is "+t+"  "+x);
	             
	             switch(rand.nextInt(2)) {
		            case 1:
		            	 stub_self.setWrite();
		            	 stub_self.write(x);
		            	 break;
		            case 0:
		            	stub_self.setRead();
		            	stub_self.setDbStatus();
		            	Student s = read(x);
		       
		                if(s != null) 
		             	   System.out.println("I am reading :"+s.getId());
		                else
		             	   System.out.println("READ NULL");
		                break;
		            default:
		            	System.out.println("NOTHING");
	             }
	            	System.out.println("I am a girl");

	             x = rand.nextInt(8);
        		 stub_self.notify(3);
        		 t++;
        	 }
        	 System.out.println("We didnt update");
//        	 System.out.println("Server 2 We didnt update");
         }
     } catch (Exception e) { 
         System.err.println("Server 2 exception: " + e.toString()); 
         e.printStackTrace(); 
      }
      

   } 

   
	public static Student read(int t)throws Exception, ClassNotFoundException {
		  t = t % 7;
		   
	      // JDBC driver name and database URL 
	      String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi4";  
	      
	      // Database credentials 
	      String USER = "root"; 
	      String PASS = "asdf;lkj";  
	      
	      Connection conn = null; 
	      Statement stmt = null;  

	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	      System.out.println("Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("Creating statement..."); 
	      	
	      stmt = conn.createStatement();  
	      String sql = "SELECT * FROM samplermi where id ="+t; 
	      ResultSet rs = stmt.executeQuery(sql);  
	      
	      //Extract data from result set
	         // Retrieve by column name
	      	if(rs.next()) {
	         int id  = rs.getInt("id"); 
	         
	         String name = rs.getString("name"); 
	         String branch = rs.getString("branch"); 
	         
	         int percent = rs.getInt("percentage"); 
	         String email = rs.getString("email");  
	         
	         // Setting the values 
	         Student st = new Student(); 
	         st.setID(id); 
	         st.setName(name); 
	         st.setBranch(branch); 
	         st.setPercent(percent); 
	         st.setEmail(email); 
	    


	 		 try {
	 		      FileWriter logwtr = new FileWriter("Server1.log",true);
	 		      BufferedWriter bw = new BufferedWriter(logwtr);
	 		      PrintWriter pw = new PrintWriter(bw);
	 		      System.out.println("LOGGING");

	 		      pw.println("P2: Read id: "+id +"  Percent: "+ percent);

//	 		      logwtr.append();
	 	          pw.flush();
	 		      logwtr.close();
	 		      
//	 		      System.out.println("Successfully wrote to the file.");
	 		    } catch (IOException e) {
	 		      System.out.println("An error occurred.");
	 		      e.printStackTrace();
	 		    }
	 		 
	 		 return st;
	      	}
	 		 try {
	 		      FileWriter logwtr = new FileWriter("Server1.log",true);
	 		      BufferedWriter bw = new BufferedWriter(logwtr);
	 		      PrintWriter pw = new PrintWriter(bw);
	 		      System.out.println("LOGGIGN");

	 		      pw.println("P2:  Read id: "+t +" NULL");

//	 		      logwtr.append();
	 	          pw.flush();
	 		      logwtr.close();
	 		      
//	 		      System.out.println("Successfully wrote to the file.");
	 		    } catch (IOException e) {
	 		      System.out.println("An error occurred.");
	 		      e.printStackTrace();
	 		    }
	     return null;
		
	}
}