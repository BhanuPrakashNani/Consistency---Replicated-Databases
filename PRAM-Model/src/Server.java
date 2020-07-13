import java.rmi.registry.*; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.rmi.*;

public class Server extends ImplExample { 
   public Server() {} 
   public static void main(String args[]) { 
	   List<Student> list = null;

      try {
     	 Class.forName("com.mysql.jdbc.Driver");
    	  // Instantiating the implementation class 
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi", "root", "bhanuprakash");

          Statement stmt = conn.createStatement();

         ImplExample obj = new ImplExample(); 
         // Exporting the object of implementation class (
         // here we are exporting the remote object to the stub) 
         Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0); 
//         Hello stub = new ImplExample();
//       
//         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(); 
//         Naming.bind("rmi://localhost:5000/sonoo",stub);  
         registry.bind("Hello", stub);  
         System.out.println("Server ready");
         Hello stub_self = (Hello) registry.lookup("Hello");
         Thread.sleep(3000);
         Hello stub_s2 = (Hello) registry.lookup("Hello2");

         System.out.println("lookup server");

//         System.out.println(stub2.notify());
//         while(true) {
//             if(stub2.getstatus() ==1)
//        	 System.out.println(stub2.getMessage());
//
//         }
         int t =0;
//         stub_self.addStudent(t);
         Thread.sleep(2000);

         while(true) {
             Thread.sleep(2000);
        		 stub_self.setStatus();
        		 stub_self.addStudent(t);
        		 stub_self.notify(2);
//                 stub_self.notify(2);
        		 t++;


        	 if(stub_s2.dbstatus(2) == 1) {

       		  list = (List<Student>)stub_s2.getStudents();
       	      String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values("+list.get(list.size()-1).getId()+",'"+list.get(list.size()-1).getName()+"','"+ list.get(list.size()-1).getBranch()+"',"+list.get(list.size()-1).getPercent()+",'"+ list.get(list.size()-1).getEmail()+ "')";
       	      int count=stmt.executeUpdate(insert);
       	      stub_s2.notify(2);
       	      System.out.println("Replicated Server 2 to Server 1");
       	  }
//        	 System.out.println("We didnt update");
         }
     } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      }
      

   } 
}