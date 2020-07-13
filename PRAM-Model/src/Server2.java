import java.rmi.registry.*; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.rmi.*;

public class Server2 extends ImplExample2 { 
   public Server2() {} 
   public static void main(String args[]) { 
	   List<Student> list = null;

      try {
     	 Class.forName("com.mysql.jdbc.Driver");
         // Instantiating the implementation class 
         ImplExample2 obj = new ImplExample2(); 
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi4", "root", "bhanuprakash");

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

//         System.out.println(stub2.notify());
//         while(true) {
//             if(stub2.getstatus() ==1)
//        	 System.out.println(stub2.getMessage());
//
//         }
         int t =0;

         while(true) {
             Thread.sleep(2000);

             stub_self.setStatus();
    		 stub_self.addStudent(t);
    		 stub_self.notify(3);
    		 t++;
          	  System.out.println("%%Server  "+stub_s1.dbstatus(3));
        	 if(stub_s1.dbstatus(3) == 1 ) {

         		  list = (List<Student>)stub_s1.getStudents();
         	      String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values("+list.get(list.size()-1).getId()+",'"+list.get(list.size()-1).getName()+"','"+ list.get(list.size()-1).getBranch()+"',"+list.get(list.size()-1).getPercent()+",'"+ list.get(list.size()-1).getEmail()+ "')";
         	      int count=stmt.executeUpdate(insert);
         	      stub_s1.notify(3);
             	  System.out.println("%%HELL  "+stub_s1.dbstatus(3));

         	      System.out.println("Replicated Server 1 to Server 2");
         	  }

//        	 System.out.println("Server 2 We didnt update");
         }
     } catch (Exception e) { 
         System.err.println("Server 2 exception: " + e.toString()); 
         e.printStackTrace(); 
      }
      

   } 
}