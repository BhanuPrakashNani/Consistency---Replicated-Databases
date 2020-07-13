import java.rmi.registry.*; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;
import java.util.Random;

public class process4 extends container4 { 
   public process4() {} 
   public static void main(String args[]) { 
	   
	   List<Student> list = null;
	   String insert="";
	   int x=0;
	   int y=0;
	   
      try { 
	    	 Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi4", "newuser", "password");
	
	         Statement stmt = conn.createStatement();
	         
	         //creating an object of ImplExample type
	         container4 obj = new container4(); 
	         // Exporting the object of implementation class (
	         // here we are exporting the remote object to the stub)
	         hello stub4 = (hello) UnicastRemoteObject.exportObject(obj, 0); 
	//       Hello stub = new ImplExample();
	//       
	//       // Binding the remote object (stub) in the registry 
	         Registry registry = LocateRegistry.getRegistry(); 
	//         Naming.bind("rmi://localhost:5000/sonoo",stub);  
	         registry.bind("Hello4", stub4);  
	         System.out.println("Server ready");
	         
	         //creating skeletons for itself, others
	         hello stub_self4 = (hello) registry.lookup("Hello4"); //skeleton
	         Thread.sleep(3000);
	         hello stub_s1 = (hello) registry.lookup("Hello"); //skeleton
	         hello stub_s3 = (hello) registry.lookup("Hello3");
	         hello stub_s2 = (hello) registry.lookup("Hello2");
	
	         System.out.println("lookup server");

//         System.out.println(stub2.notify());
//         while(true) {
//             if(stub2.getstatus() ==1)
//        	 System.out.println(stub2.getMessage());
//
//         }

//---------------------------------------------------CONNECTION AND BASIC SETUP DONE-----------------------------------------------------         
         int t =0;
         int t1=0;
//         stub_self.addStudent(t);
         Thread.sleep(200);
//-------------------------------------------------------------------------------------------------------------
         while(true) {
        	 if(x<10)
        	 {	
        		 x++;
//-----------------------------------------------------------WRITE--------------------------------------------------------------------------        	 
        		 while(t<10 || (stub_self4.dbstatus(0)!=0 || stub_self4.dbstatus(1)!=0 || stub_self4.dbstatus(2)!=0 || stub_self4.dbstatus(3)!=0)) {
        			 if(t<10) {
        				 if(stub_self4.dbstatus(3) == 0 && stub_s2.dbstatus(3) == 0 && stub_s3.dbstatus(3) == 0 && stub_s1.dbstatus(3) == 0 ) {
        		 
        					 t++;
        					 stub_self4.request_write(t,4);
        		 
        					 stub_s2.set_dbstatus(3,1);
        					 stub_s3.set_dbstatus(3,1);
        					 stub_s1.set_dbstatus(3,1);
        					 

        		 
        				 }
        	 
        			 }	
        			 	if(stub_self4.dbstatus(2) == 1) {
        			 		String insert1 = stub_s3.insert_container();
        			 		stub_self4.request_write_others(insert1);	
        			 		stub_self4.notify(2);
        			 		System.out.println("Requested Replication of Server 3 to Server 4");
          		 
        			 	}	 
        
        			 	if(stub_self4.dbstatus(1) == 1) {
        			 		String insert1 = stub_s2.insert_container();
        			 		stub_self4.request_write_others(insert1);
        			 		stub_self4.notify(1);
        			 		System.out.println("Requested replication of Server 2 to Server 4");
          		 
        			 	}
       	 

       	 
        			 	if(stub_self4.dbstatus(0) == 1) {
        			 		String insert1 = stub_s1.insert_container();
        			 		stub_self4.request_write_others(insert1);
        			 		stub_self4.notify(0);
        			 		System.out.println("requested Replication of Server 1 to Server 4");
          		 
        			 	}
        			 	
       	 
       	 
        		 }
             

           
             
//---------------------------------------------UPDATE--------------------------------------------------------------            
        		 Random rand = new Random();
        		 int upper = 10;
        		 x = rand.nextInt(upper);
        		 int max = 4;
                 int nam = rand.nextInt(max);
                 String name;
                 switch (nam) {
     			case 0:
     				name = "mani";
     				break;
     			case 1:
     				name = "bhanu";
     				break;
     			case 2:
     				name="abhinav";
     				break;
     			case 3:
     				name="shrestha";
     				break;
     			default:
     				name = 	"Error";
     			
     			}
                 
                 


        		 if(stub_self4.dbstatus1(2) == 1) {
        			 String insert1 = stub_s3.insert_container1();
        			 stub_self4.request_update_others(insert1);
	
        			 stub_self4.notify1(2);
        			 System.out.println("requested replication of update of Server 3 to Server 4");

        		 }	 

        		 
        		 if(stub_self4.dbstatus1(1) == 1) {
        			 String insert1 = stub_s2.insert_container1();
        			 stub_self4.request_update_others(insert1);

        			 stub_self4.notify1(1);
        			 System.out.println("requested Replication of update of Server 2 to Server 4");
		
        		 }



        		 if(stub_self4.dbstatus1(0) == 1) {
        			 	String insert1 = stub_s1.insert_container1();
        			 	stub_self4.request_update_others(insert1);

        			 	stub_self4.notify1(0);
        			 	System.out.println("requested dReplication of update of Server 1 to Server 4");
	      
        		 }
        		 
                 
            		 if(y<50) {
            			 y++;

            			 if(stub_self4.dbstatus1(3) == 0 && stub_s2.dbstatus1(3) == 0 && stub_s3.dbstatus1(3) == 0 && stub_s1.dbstatus1(3) == 0 ) {
            				 while((x==stub_s1.getRandupd() && name==stub_s1.getRandname()) || (x==stub_s2.getRandupd() && name == stub_s2.getRandname()) || (x==stub_s3.getRandupd() && name == stub_s3.getRandname())) {
                        		 stub_self4.setRandupd(x);
                        		 stub_self4.setRandname(name);
                        		 System.out.println("stopped "+  stub_s1.getRandupd()+  stub_s2.getRandupd() + stub_s3.getRandupd()+x);
                             }
            	       	 

            				 stub_self4.request_update(y,x,name);

            				 stub_s2.set_dbstatus1(3,1);
            				 stub_s3.set_dbstatus1(3,1);
            				 stub_s1.set_dbstatus1(3,1);
            				 t1++;


            			 }
            		 }




        	 }
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
 //------------------------------------------------------------synch-------------------------------------------------            
             
        	 
        	 Queue<String> q = stub_self4.queue();
  
		
		
        	 if(x>=9||y>42) {
    			 try {
   			      FileWriter logwtr = new FileWriter("Server1.log",true);
   			      BufferedWriter bw = new BufferedWriter(logwtr);
   			      PrintWriter pw = new PrintWriter(bw);
   			      System.out.println("LOGGING");

   			      pw.println("P4: sync starts");

//   			      logwtr.append();
   			         pw.flush();
   			      logwtr.close();
   			      
   			      System.out.println("Successfully wrote to the file.");
   			    } 
   			 catch (IOException e) {
   			      System.out.println("An error occurred.");
   			      e.printStackTrace();
   			    }
    			 
    			 
    			 
    			 
        		 while(q.peek()!=null) {
        			 
        			 String insert1 = q.peek();
        			 q.remove();
        			 
        			 
  //LOGGING      			 
        			 try {
        			      FileWriter logwtr = new FileWriter("Server1.log",true);
        			      BufferedWriter bw = new BufferedWriter(logwtr);
        			      PrintWriter pw = new PrintWriter(bw);
        			      System.out.println("LOGGING");

        			      pw.println("P4: Entry write");

//        			      logwtr.append();
        			         pw.flush();
        			      logwtr.close();
        			      
        			      System.out.println("Successfully started writing to the file");
        			    } 
        			 catch (IOException e) {
        			      System.out.println("An error occurred.");
        			      e.printStackTrace();
        			    }
        			 
			
			
        			 String DB_URL = "jdbc:mysql://localhost:3306/rmi4";  
		      
			 
		      
        			 Connection conn1 = null; 
        			 Statement stmt1 = null;  

        			 //Register JDBC driver 
		        
        			 //Open a connection
        			 System.out.println("Connecting to a selected database..."); 
        			 conn1 = DriverManager.getConnection(DB_URL, "newuser", "password"); 
        			 System.out.println("Connected database successfully...");  
        			 stmt1 = conn1.createStatement();
        			 //ResultSet rs = stmt.executeQuery(sql);  
        			 stmt1.executeUpdate(insert1);
        			 try {
       			      FileWriter logwtr = new FileWriter("Server1.log",true);
       			      BufferedWriter bw = new BufferedWriter(logwtr);
       			      PrintWriter pw = new PrintWriter(bw);
       			      System.out.println("LOGGING");

       			      pw.println("P4: "+insert1);

//       			      logwtr.append();
       			         pw.flush();
       			      logwtr.close();
       			      
       			      System.out.println("insert printed in log.");
       			    } 
       			 catch (IOException e) {
       			      System.out.println("An error occurred.");
       			      e.printStackTrace();
       			    }
       			 
        			 conn1.close();
        			 try {
       			      FileWriter logwtr = new FileWriter("Server1.log",true);
       			      BufferedWriter bw = new BufferedWriter(logwtr);
       			      PrintWriter pw = new PrintWriter(bw);
       			      System.out.println("LOGGING");

       			      pw.println("P4: Exit write");

//       			      logwtr.append();
       			         pw.flush();
       			      logwtr.close();
       			      
       			      System.out.println("Successfully finished writing to the file.");
       			    } 
       			 catch (IOException e) {
       			      System.out.println("An error occurred.");
       			      e.printStackTrace();
       			    }
       			 

		      
        		 }
        		 
		
        		 x=0;
			stub_self4.clearqueue();
        	 }
			 try {
			      FileWriter logwtr = new FileWriter("Server1.log",true);
			      BufferedWriter bw = new BufferedWriter(logwtr);
			      PrintWriter pw = new PrintWriter(bw);
			      System.out.println("LOGGING");

			      pw.println("P4: sync ends");

//			      logwtr.append();
			         pw.flush();
			      logwtr.close();
			      
			      System.out.println("Successfully finished sync.");
			    } 
			 catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		
			 
			 
			 
			//------------------------------------------------------------READ--------------------------------------------------------------------

    		 
    		 if(stub_self4.dbstatus1(0) == 0 && stub_self4.dbstatus1(1) == 0 && stub_self4.dbstatus1(2) == 0 && stub_self4.dbstatus1(3) == 0) {
 
 
    			 System.out.println("Reading from process 4");
    		    	Random rand1 = new Random();
    		    	Random rand2 = new Random();
    		    	int upperno = 11;
    		    	int upperno1 = 4;
    		    	int d = rand1.nextInt(upperno);
    		    	int d1 = rand2.nextInt(upperno1);
             	   try {
      			      FileWriter logwtr = new FileWriter("Server1.log",true);
      			      BufferedWriter bw = new BufferedWriter(logwtr);
      			      PrintWriter pw = new PrintWriter(bw);
      			      System.out.println("LOGGING");

      			      pw.println("P4: Entry read id: "+Integer.toString(d));

//      			      logwtr.append();
      			         pw.flush();
      			      logwtr.close();
      			      
      			      System.out.println("Successfully wrote to the file.");
      			    } 
      			 catch (IOException e) {
      			      System.out.println("An error occurred.");
      			      e.printStackTrace();
      			    }
    			 
             	   
             	 
             	 Student student = new Student();
    			 list = new ArrayList<Student>(); 
    			 student = stub_self4.getStudents(d,d1);
             	   
    			 
    			 
    			 try {
      			      FileWriter logwtr = new FileWriter("Server1.log",true);
      			      BufferedWriter bw = new BufferedWriter(logwtr);
      			      PrintWriter pw = new PrintWriter(bw);
      			      System.out.println("LOGGING");
      			      if(student.getId()!=-1)
      			      pw.println("P4: Exit read id: "+student.getId() +" percent: "+student.getPercent()+" name: "+student.getName());
      			      else
      			      pw.println("P4: Exit read id: "+Integer.toString(d)+" nothing to read");  
//      			      logwtr.append();
      			         pw.flush();
      			      logwtr.close();
      			      
      			      System.out.println("Successfully wrote to the file.");
      			    } 
      			 catch (IOException e) {
      			      System.out.println("An error occurred.");
      			      e.printStackTrace();
      			    }
      



	 
 
//	 System.out.println("We didnt update");
} 
         	}
      }
       catch (Exception e) { 
         System.err.println("Server exception: " + e.toString());
		e.printStackTrace(); 
       }
      
   }
}
   


//class syncDB4 implements Runnable
//{
//
//	Queue<String> q = new LinkedList<>();
//	int tempstatus = 0;
//	
//	
//
//	
//	syncDB4(Queue<String> queue){
//		this.q = queue;
//	}
//	
//	
//	
//	
//	public void run() {
//		System.out.println("Thread for sync start");
//		try {
//			
//
//			
//			
//			
//			
//			
//			
//			
//		
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//}}

//syncDB4 synch = new syncDB4(q);
//Thread thrd_sync = new Thread(synch);
//Thread.sleep(2000);
//thrd_sync.start();
//try {
//      FileWriter logwtr = new FileWriter("Server1.log",true);
//      BufferedWriter bw = new BufferedWriter(logwtr);
//      PrintWriter pw = new PrintWriter(bw);
//      System.out.println("LOGGING");
//
//      pw.println("p4 : " + thrd_sync.getId());
//
////      logwtr.append();
//         pw.flush();
//      logwtr.close();
//      
////      System.out.println("Successfully wrote to the file.");
//    } catch (IOException e) {
//      System.out.println("An error occurred.");
//      e.printStackTrace();
//    }
//
//
//
//try {
//      FileWriter logwtr = new FileWriter("Server1.log",true);
//      BufferedWriter bw = new BufferedWriter(logwtr);
//      PrintWriter pw = new PrintWriter(bw);
//      System.out.println("LOGGING");
//
//      pw.println("p4 : " + thrd_sync.getId());
//
////      logwtr.append();
//         pw.flush();
//      logwtr.close();
//      
////      System.out.println("Successfully wrote to the file.");
//    } catch (IOException e) {
//      System.out.println("An error occurred.");
//      e.printStackTrace();
//    }
//stub_self4.clearqueue();
//     
//