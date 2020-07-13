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
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;

public class process1 extends container1 { 
   public process1() { }
   public static void main(String args[]) { 
	   
	   List<Student> list = null;
	   String insert="";
	   int x=0;
	   int y=0;
	   int z=0;
	  
      try { 
    	  Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi", "newuser", "password");
          Statement stmt = conn.createStatement();
          container1 obj = new container1(); 
         
          hello stub1 = (hello) UnicastRemoteObject.exportObject(obj, 0); 
         
          Registry registry = LocateRegistry.getRegistry(); 
         
          registry.bind("Hello", stub1);  
          System.out.println("Server ready");
         
       
          hello stub_self1 = (hello) registry.lookup("Hello"); //skeleton
          Thread.sleep(3000);
          hello stub_s2 = (hello) registry.lookup("Hello2"); //skeleton
          hello stub_s3 = (hello) registry.lookup("Hello3");
          hello stub_s4 = (hello) registry.lookup("Hello4");

         System.out.println("lookup server");
//----------------------------------------------------------CONNECTION AND BASIC SETUP DONE--------------------------------------------------------

         
         
         
         
         
         int t =0;
         int t1=0;
         Thread.sleep(200);

         
         while(true) {
        	 if(x<10) {
        		 
        		 x++;
  //-------------------------------------------------------------------WRITE------------------------------------------------------------------------      	 
             while (t<10 || (stub_self1.dbstatus(0)!=0 || stub_self1.dbstatus(1)!=0 || stub_self1.dbstatus(2)!=0 || stub_self1.dbstatus(3)!=0)){
            	 if(t<10) {
        	 if(stub_self1.dbstatus(0) == 0 && stub_s2.dbstatus(0) == 0 && stub_s3.dbstatus(0) == 0 && stub_s4.dbstatus(0) == 0 ) {
        		 
        		    t++;
        		 	stub_self1.request_write(t,1);
       	 
        		    stub_s2.set_dbstatus(0,1);
        		 	stub_s3.set_dbstatus(0,1);
        		 	stub_s4.set_dbstatus(0,1);
        		    
        		 
        	 		}
        	 
        	 
            	} 
        	 
            	 
            	 
            	 
            	 
            	 
            	 
            	 
            	 
            if(stub_self1.dbstatus(1) == 1) {
        		 String insert1 = stub_s2.insert_container();
        		 stub_self1.request_write_others(insert1);
          	     
        		 stub_self1.notify(1);
          	      System.out.println("requested replcation of Server 2 to Server 1");
          	   
          	  }
        	 
        	 
            
            if(stub_self1.dbstatus(2) == 1) {
        		 String insert1 = stub_s3.insert_container();
        		 stub_self1.request_write_others(insert1);
        		
           	      stub_self1.notify(2);
           	      System.out.println("requested replication of Server 3 to Server 1");

           	  }
        	 
        	 
            
            
            if(stub_self1.dbstatus(3) == 1) {
           		 String insert1 = stub_s4.insert_container();
           		 stub_self1.request_write_others(insert1);
           	      stub_self1.notify(3);
           	      System.out.println("requested replication of Server 4 to Server 1");
          		 
           	  }

         
             
          }
           	 
             
             
             
             
 //------------------------------------------------------------------UPDATE--------------------------------------------------------------------------------           
             
             
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
             
             
             
             if(y<50) {
            	 y++;

             
            	 if(stub_self1.dbstatus1(0) == 0 && stub_s2.dbstatus1(0) == 0 && stub_s3.dbstatus1(0) == 0 && stub_s4.dbstatus1(0) == 0 ) {
            		 while((x==stub_s2.getRandupd() && name==stub_s2.getRandname()) || (x==stub_s3.getRandupd() && name == stub_s3.getRandname()) || (x==stub_s4.getRandupd() && name == stub_s4.getRandname())) {
                		 stub_self1.setRandupd(x);
                		 stub_self1.setRandname(name);
                		 System.out.println("stopped "+ x + stub_s2.getRandupd()+  stub_s3.getRandupd()+ stub_s4.getRandupd());
                     }
        		 stub_self1.request_update(y,x,name);
        		 

        		 
        		 stub_s2.set_dbstatus1(0,1);
        		 stub_s3.set_dbstatus1(0,1);
        		 stub_s4.set_dbstatus1(0,1);
        		 t1++;
        		 
        		 
            	 }
             }
        	 
             
             
        	 if(stub_self1.dbstatus1(1) == 1) {
        		 String insert1 = stub_s2.insert_container1();
        		 stub_self1.request_update_others(insert1);    
          	      stub_self1.notify1(1);
          	      System.out.println("Replicated update of Server 2 to Server 1");
          	     
          	  }
        	 
        	 
        	 
        	 if(stub_self1.dbstatus1(2) == 1) {
        		 String insert1 = stub_s3.insert_container1();
        		 stub_self1.request_update_others(insert1);   
        		
           	      stub_self1.notify1(2);
           	      System.out.println("Replicated update of Server 3 to Server 1");
          		 
           	  }
        	 
        	 
        	 
        	 if(stub_self1.dbstatus1(3) == 1) {
           		 String insert1 = stub_s4.insert_container1();
           		stub_self1.request_update_others(insert1);   
           	      stub_self1.notify1(3);
           	      System.out.println("Replicated update of Server 4 to Server 1");

           	  }
           	  
           	  
        	 
        	 
        	 
        	 
	 
       }
        	 
        	 
//-----------------------------------------------------------------sync part---------------------------------------------------------------------------------
        	 
        	 
        	   	Queue<String> q = stub_self1.queue();

        	 if(x>=9||y>42) {
        		 
    			 try {
   			      FileWriter logwtr = new FileWriter("Server1.log",true);
   			      BufferedWriter bw = new BufferedWriter(logwtr);
   			      PrintWriter pw = new PrintWriter(bw);
   			      System.out.println("LOGGING");

   			      pw.println("P1: sync starts");

//   			      logwtr.append();
   			         pw.flush();
   			      logwtr.close();
   			      
   			      System.out.println("Successfully wrote to the file.");
   			    } 
   			 catch (IOException e) {
   			      System.out.println("An error occurred.");
   			      e.printStackTrace();
   			    }
        	 
        		 while(!q.isEmpty()) {
        			 String insert1 = q.peek();
        			 q.remove();
 				
        			 try {
       			      FileWriter logwtr = new FileWriter("Server1.log",true);
       			      BufferedWriter bw = new BufferedWriter(logwtr);
       			      PrintWriter pw = new PrintWriter(bw);
       			      System.out.println("LOGGING");

       			      pw.println("P1: Entry write");

//       			      logwtr.append();
       			         pw.flush();
       			      logwtr.close();
       			      
       			      System.out.println("Successfully started writing to the file");
        			 }
       			 catch (IOException e) {
       			      System.out.println("An error occurred.");
       			      e.printStackTrace();
       			    }
        			 String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
        			 Connection conn1 = null; 
        			 Statement stmt1 = null;  
        			 
        			 //Register JDBC driver 
 			        
        			 //Open a connection
        			 System.out.println("p1: Connecting to a selected database..."); 
        			 conn1 = DriverManager.getConnection(DB_URL, "newuser", "password"); 
        			 System.out.println("p1: Connected database successfully...");  
        			 stmt1 = conn1.createStatement();
        			 //ResultSet rs = stmt.executeQuery(sql);  
        			 stmt1.executeUpdate(insert1);
        			 conn1.close();
        			 try {
       			      FileWriter logwtr = new FileWriter("Server1.log",true);
       			      BufferedWriter bw = new BufferedWriter(logwtr);
       			      PrintWriter pw = new PrintWriter(bw);
       			      System.out.println("LOGGING");

       			      pw.println("P1: "+insert1);

//       			      logwtr.append();
       			         pw.flush();
       			      logwtr.close();
       			      
       			      System.out.println("insert printed in log");
       			    } 
       			 catch (IOException e) {
       			      System.out.println("An error occurred.");
       			      e.printStackTrace();
       			    }
        		 }
        			 try {
       			      FileWriter logwtr = new FileWriter("Server1.log",true);
       			      BufferedWriter bw = new BufferedWriter(logwtr);
       			      PrintWriter pw = new PrintWriter(bw);
       			      System.out.println("LOGGING");

       			      pw.println("P1: Exit write");

//       			      logwtr.append();
       			         pw.flush();
       			      logwtr.close();
       			      
       			      System.out.println("Successfully finished writing to the file.");
       			    } 
       			 catch (IOException e) {
       			      System.out.println("An error occurred.");
       			      e.printStackTrace();
       			    }
 			      
 			
 			x=0;
 			stub_self1.clearqueue();
 			
			 try {
			      FileWriter logwtr = new FileWriter("Server1.log",true);
			      BufferedWriter bw = new BufferedWriter(logwtr);
			      PrintWriter pw = new PrintWriter(bw);
			      System.out.println("LOGGING");

			      pw.println("P1: sync ends");

//			      logwtr.append();
			         pw.flush();
			      logwtr.close();
			      
			      System.out.println("Successfully finished sync.");
			    } 
			 catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			 
			 
        	 }  
        	 
        	 
//------------------------------------------------------------------READ-----------------------------------------------------------------------------        	 
        	 
        	 
        	 if(stub_self1.dbstatus1(0) == 0 && stub_self1.dbstatus1(1) == 0 && stub_self1.dbstatus1(2) == 0 && stub_self1.dbstatus1(3) == 0) {

           	      System.out.println("Reading from process 1");
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

 			      pw.println("P1: Entry read id: "+Integer.toString(d));

// 			      logwtr.append();
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
           	      student = stub_self1.getStudents(d,d1);  
           	       
           	      
     			 try {
     			      FileWriter logwtr = new FileWriter("Server1.log",true);
     			      BufferedWriter bw = new BufferedWriter(logwtr);
     			      PrintWriter pw = new PrintWriter(bw);
     			      System.out.println("LOGGING");
     			      if(student.getId()!=-1)
     			    	 pw.println("P1: Exit read id: "+student.getId() +" percent: "+student.getPercent()+" name: "+student.getName());
     			      else
     			      pw.println("P1: Exit read id: "+Integer.toString(d)+" nothing to read");  
//     			      logwtr.append();
     			         pw.flush();
     			      logwtr.close();
     			      
     			      System.out.println("Successfully wrote to the file.");
     			    } 
     			 catch (IOException e) {
     			      System.out.println("An error occurred.");
     			      e.printStackTrace();
     			    }
           	} 
        	 
         }
     } 
      
     catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
     }
      

   } 
}


////
////class syncDB implements Runnable
////{
////
////	Queue<String> q = new LinkedList<>();
////	int tempstatus = 0;
////	
////	
////
////	
////	syncDB(Queue<String> queue){
////		this.q = queue;
////	}
////	
////	
////	
////	
////	public void run() {
////		System.out.println("Thread for sync start");
////		try {
////			
////			w
////
////			
////			
////			
////			
////			
////		
////			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
////	
//}



//syncDB synch = new syncDB(q);
//Thread thrd_sync = new Thread(synch);
//Thread.sleep(2000);
//thrd_sync.start();
//try {
//       FileWriter logwtr = new FileWriter("Server1.log",true);
//       BufferedWriter bw = new BufferedWriter(logwtr);
//       PrintWriter pw = new PrintWriter(bw);
//       System.out.println("LOGGING");
//
//       pw.println("p1: " + thrd_sync.getId());
//
////       logwtr.append();
//          pw.flush();
//       logwtr.close();
//       
////       System.out.println("Successfully wrote to the file.");
//     } catch (IOException e) {
//       System.out.println("An error occurred.");
//       e.printStackTrace();
//     }
//
//try {
//    FileWriter logwtr = new FileWriter("Server1.log",true);
//    BufferedWriter bw = new BufferedWriter(logwtr);
//    PrintWriter pw = new PrintWriter(bw);
//    System.out.println("LOGGING");
//
//    pw.println("p1 : " + thrd_sync.getId());
//
////    logwtr.append();
//       pw.flush();
//    logwtr.close();
//    
////    System.out.println("Successfully wrote to the file.");
//  } catch (IOException e) {
//    System.out.println("An error occurred.");
//    e.printStackTrace();
//  }
//stub_self1.clearqueue(); 