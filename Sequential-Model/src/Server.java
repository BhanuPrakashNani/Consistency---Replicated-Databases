//we implemented local-read algo 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.*; 
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Server extends DB1STUB { 
   public Server() {} 
   public static void main(String args[]) { 
	   //List<Student> list = null;

      try { 
         // Instantiating the implementation class 
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi", "root", "asdf;lkj");
          Statement stmt = conn.createStatement();

         DB1STUB obj = new DB1STUB(); 
         // Exporting the object of implementation class (
         // here we are exporting the remote object to the stub) 
         DBRemote stub = (DBRemote) UnicastRemoteObject.exportObject(obj, 0); 

         Registry registry = LocateRegistry.getRegistry(); 
         registry.bind("Hello1", stub);  
         System.out.println("Server ready");
         DBRemote stub_self = (DBRemote) registry.lookup("Hello1");
         Thread.sleep(3000);
         DBRemote stub_s2 = (DBRemote) registry.lookup("Hello2"); // client 1
         DBRemote stub_s3 = (DBRemote) registry.lookup("Hello3");
         DBRemote stub_s4 = (DBRemote) registry.lookup("Hello4");
         DBRemote stub_s5 = (DBRemote) registry.lookup("Hello5");

         System.out.println("lookup server");
         DBRemote stub_arr[] = new DBRemote[5]; 
         stub_arr[0] = stub_self;
         stub_arr[1] = stub_s2;
         stub_arr[2] = stub_s3;
         stub_arr[3] = stub_s4;
         stub_arr[4] = stub_s5;
         Random  rand = new Random();
         int t =0, x = 0, c = 0;
         boolean idExists = false;
         Thread.sleep(2000);
         String name = "Process 1";
         String branch = "cse";
	     int percent = 01;
	     String email = "mani.gmail";
         
	     while(stub_self.isSafe()) {
             Thread.sleep(2000);
             Student s = new Student();
	   	     t  = t% 7; 
             String query = "SELECT * FROM SAMPLERMI";
             ResultSet rs = stmt.executeQuery(query);
             while(rs.next()) {
		    	  if(t == rs.getInt("id")) {
		    		  idExists = true;
		    		  percent = rs.getInt("percentage")+5;
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
	            	  stub_s4.addQobj(s);
	            	  stub_s2.addQobj(s);
	            	  stub_s3.addQobj(s);
	            	  stub_s5.addQobj(s);
	            	  stub_self.request(s); // request to write
	            	
	       	       	  break;
				case 0:
					if(!Config.synchStart[0]) {
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
	         
	         
	         
	         //sync with other writer processes
	         int tempStatus = stub_self.dbstatus(0)+stub_s4.dbstatus(0)+stub_s5.dbstatus(0);
	         
	         if(tempStatus > 0) {
	        	 System.out.println("Writer 1 inside loop1 ");
	   	      

	        	 
	        	 Queue<Student> q = stub_self.getQobj();
	        	 syncDB synch = new syncDB(q, tempStatus, stub_arr,0);
	        	 Thread thrd_sync = new Thread(synch);
	        	 thrd_sync.start();
	        	 
	        	 
	         }
	         
	         System.out.println("WRITER 1 "+t); 
	         t++;
	         c++;
        	 
         }
     } 
      catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      }
      

   } 
}

class syncDB implements Runnable
{

	Queue<Student> q = new LinkedList<>();
	int tempstatus = 0;
	DBRemote stub_arr[] = null;

	Student s;
	int status_bit;
	

	
	syncDB(Queue<Student> que, int tempstat, DBRemote stub_ar[], int stat_bit){
		this.q = que;
		this.tempstatus = tempstat;
		this.stub_arr = stub_ar;
		this.status_bit = stat_bit;
	}
	
	public void run() {
        Random  rand = new Random();

		System.out.println("Thread for sync start");
		int t = tempstatus;
		try {
			Thread.sleep(rand.nextInt(500));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		      FileWriter logwtr = new FileWriter("Server1.log",true);
		      BufferedWriter bw = new BufferedWriter(logwtr);
		      PrintWriter pw = new PrintWriter(bw);
		      System.out.println("LOGGIGN");

		      pw.println("P"+(this.status_bit+1)+": Synch start "+t);

//		      logwtr.append();
	          pw.flush();
		      logwtr.close();
		      
//		      System.out.println("Successfully wrote to the file.");
		 } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
			
			q = stub_arr[this.status_bit].getQobj();

	   	 while(this.tempstatus > 0 && q.size() >0) {
	   		 Config.synchStart[this.status_bit] = true;
	   		 s = q.peek();
	   		 q.remove();
	   		 stub_arr[this.status_bit].addStudent(s);
	   		try {
				
			      FileWriter logwtr = new FileWriter("Server1.log",true);
			      BufferedWriter bw = new BufferedWriter(logwtr);
			      PrintWriter pw = new PrintWriter(bw);
			      System.out.println("LOGGIGN");

			      pw.println("UPdate from "+s.getName()+" for id: "+s.getId()+" percent: "+s.getPercent());

//			      logwtr.append();
		          pw.flush();
			      logwtr.close();
//			      System.out.println("Successfully wrote to the file.");
			    } catch (Exception e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
	   		 if(s.getName() == "Process 1")
	   			 stub_arr[2].notify(this.status_bit);
	   		 else if(s.getName() == "Process 4")
	   			 stub_arr[3].notify(this.status_bit);
	   		 else if(s.getName() == "Process 5")
	   			 stub_arr[4].notify(this.status_bit);
	   		 stub_arr[this.status_bit].removeQ();
//	   		 stub_self.notify(this.status_bit);
	   		 this.tempstatus--;
	   	 }
   		 Config.synchStart[this.status_bit] = false;

		 } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
			
		      FileWriter logwtr = new FileWriter("Server1.log",true);
		      BufferedWriter bw = new BufferedWriter(logwtr);
		      PrintWriter pw = new PrintWriter(bw);
		      System.out.println("LOGGIGN");

		      pw.println("P"+(this.status_bit+1)+": Synch end "+t);

//		      logwtr.append();
	          pw.flush();
		      logwtr.close();
//		      System.out.println("Successfully wrote to the file.");
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }


	
	}
	
}
