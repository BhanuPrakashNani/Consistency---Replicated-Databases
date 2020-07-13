import java.rmi.registry.LocateRegistry; 
import java.rmi.*;
import java.rmi.registry.Registry; 
import java.util.*; 
import java.sql.*;

public class Client2 {  
   private Client2() {}  
   public static void main(String[] args)throws Exception, ClassNotFoundException {  
	   List<Student> list = null;
      try { 
    	  Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi3", "root", "asdf;lkj");
         // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
         Statement stmt = conn.createStatement();
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
         
         // Looking up the registry for the remote object 
         System.out.println("lookup client");
         Hello stub = (Hello) registry.lookup("Hello"); 
         Hello stub2 = (Hello) registry.lookup("Hello2");
         // Hello stub=(Hello)Naming.lookup("rmi://localhost:5000/sonoo");  

         // Calling the remote method using the obtained object 
         list = (List<Student>)stub.getStudents(); 
		 

           
      // System.out.println(list); 
       
      for (Student s:list) { 
          
          // System.out.println("bc "+s.getBranch()); 
          System.out.println("ID: " + s.getId()); 
          System.out.println("name: " + s.getName()); 
          System.out.println("branch: " + s.getBranch()); 
          System.out.println("percent: " + s.getPercent()); 
          System.out.println("email: " + s.getEmail());
      }
      while(Config.SAFE) {
    	  if(stub.dbstatus(1) == 1) {
    		  System.out.println("hi1");
    		  if(stub.isWrite()) {
//    		  list = (List<Student>)stub.getStudents();
//    	      String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values("+list.get(list.size()-1).getId()+",'"+list.get(list.size()-1).getName()+"','"+ list.get(list.size()-1).getBranch()+"',"+list.get(list.size()-1).getPercent()+",'"+ list.get(list.size()-1).getEmail()+ "')";
//    	      int count=stmt.executeUpdate(insert);
        		  System.out.println("hi2");

     			 String exec = stub.getStmt();
     			 System.out.println(exec);

     			 int count=stmt.executeUpdate(exec);

    		  }
    	      stub.notify(1);
    	      System.out.println("Replicated");
    	  }
    	  if(stub2.dbstatus(1) == 1) {
    		  System.out.println("hi3");
    		  if(stub2.isWrite()) {
        		  System.out.println("hi4");
//    		  list = (List<Student>)stub2.getStudents();
//    	      String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values("+list.get(list.size()-1).getId()+",'"+list.get(list.size()-1).getName()+"','"+ list.get(list.size()-1).getBranch()+"',"+list.get(list.size()-1).getPercent()+",'"+ list.get(list.size()-1).getEmail()+ "')";
//    	      int count=stmt.executeUpdate(insert);
     			 String exec = stub2.getStmt();
     			 System.out.println(exec);
     			 int count=stmt.executeUpdate(exec);
    		  }
    	      stub2.notify(1);
    	      System.out.println("Replicated Server 2");
    	  }
//    	  System.out.println("Server not updated");
      }

      
      
      
//      String msg="";
//      while(true) {
//    	  if(stub.getstatus() == 0) {
//    		  System.out.println("Enter your  message");
//    		  Scanner sc = new Scanner(System.in);
//    		  msg = sc.nextLine();
//    		  stub.sendMessage(msg);
//     
//    	  }
//      }
      } catch (Exception e) { 
          System.err.println("Client exception: " + e);
       }
      } 
}