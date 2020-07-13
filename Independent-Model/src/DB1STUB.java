import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.Semaphore;

public class DB1STUB implements DBRemote{
	static Queue<Student> q = new LinkedList<>();
	static int status;
	int[] dbstatus = new int[]{ 0, 0, 0, 0, 0}; // 0- s1, 1-c1, 2-c2, 3-s2, 4-s3
	//Student s;
	String msg =",";
	boolean isWrite;
	boolean SAFE = true;
	public List<Student> getStudents() throws Exception, ClassNotFoundException {  
			List<Student> list = new ArrayList<Student>();   
	      // JDBC driver name and database URL 
	      String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
	      // Database credentials 
	      String USER = "root"; 
	      String PASS = "bhanuprakash";  
	      
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
	      String sql = "SELECT * FROM samplermi"; 
	      ResultSet rs = stmt.executeQuery(sql);  
	      
	      //Extract data from result set 
	      while(rs.next()) { 
	         // Retrieve by column name 
	         int id  = rs.getInt("id"); 
	         
	         String name = rs.getString("name"); 
	         String branch = rs.getString("branch"); 
	         
	         int percent = rs.getInt("percentage"); 
	         String email = rs.getString("email");  
	         
	         // Setting the values 
	         Student student = new Student(); 
	         student.setID(id); 
	         student.setName(name); 
	         student.setBranch(branch); 
	         student.setPercent(percent); 
	         student.setEmail(email); 
	         list.add(student); 
	      } 
	      rs.close(); 
	      return list;     

	      }
	public void addStudent(Student s)throws Exception, ClassNotFoundException {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }

	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
	      // Database credentials 
	      String USER = "root"; 
	      String PASS = "bhanuprakash";  
	      
	      Connection conn = null; 
	      Statement stmt = null;  
	      
	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	      System.out.println("Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("Creating statement...");
	      
	      boolean idExists = false;
	      stmt = conn.createStatement();
	      String sql = "SELECT * FROM samplermi"; 
	      ResultSet rs = stmt.executeQuery(sql);  
	      
	      int id = s.getId();
	      String name = s.getName();
	      String branch = s.getBranch();
	      int percent = s.getPercent();
	      String email = s.getEmail();
	      int clock = s.getClock();

	      int t = id % 7;
	      try {
 		      FileWriter logwtr = new FileWriter("Server1.log",true);
 		      BufferedWriter bw = new BufferedWriter(logwtr);
 		      PrintWriter pw = new PrintWriter(bw);
 		      System.out.println("LOGGIGN");

 		      pw.println("P1: Entry Write id: "+t	 +" Percent: "+ percent);

// 		      logwtr.append();
 	          pw.flush();
 		      logwtr.close();
 		      
// 		      System.out.println("Successfully wrote to the file.");
 		    } catch (IOException e) {
 		      System.out.println("An error occurred.");
 		      e.printStackTrace();
 		    }
	      // search for id in the database 
	      while(rs.next()) {
	    	  if(t == rs.getInt("id")) {
	    		  idExists = true;
	    		  break;
	    	  }
	      }
	      
	      stmt = conn.createStatement();
	      //ResultSet rs = stmt.executeQuery(sql);  
	      if (!idExists) {
	      //ResultSet rs = stmt.executeQuery(sql);  
		      String insert = "INSERT INTO samplermi(id, name, branch, percentage, email,clock) values('"+t+"','"+name+"','"+branch+"','"+percent+"','"+email+"',"+clock+")";
		      int count=stmt.executeUpdate(insert);
		      msg = insert;
	      }
	      else {
	    	  
	    	  String update = "UPDATE samplermi SET percentage = "+percent+", name = '"+name+"', clock = "+clock+" where id = "+t;
		      int count=stmt.executeUpdate(update);
		      msg = update;
	      }
	      try {
 		      FileWriter logwtr = new FileWriter("Server1.log",true);
 		      BufferedWriter bw = new BufferedWriter(logwtr);
 		      PrintWriter pw = new PrintWriter(bw);
 		      System.out.println("LOGGIGN");

 		      pw.println("P1: Exit Write id: "+t	 +" Percent: "+ percent+" Clock: "+clock);

// 		      logwtr.append();
 	          pw.flush();
 		      logwtr.close();
 		      
// 		      System.out.println("Successfully wrote to the file.");
 		    } catch (IOException e) {
 		      System.out.println("An error occurred.");
 		      e.printStackTrace();
 		    }
	      conn.close();
      System.out.println("wrote in S1");    
	}
	public void request(Student s) throws RemoteException {
	      
			setDBStatus();
			q.add(s);
	}
	
	public void setDBStatus() throws RemoteException {
		for (int i1 =0; i1<5; i1++) {
	    	  dbstatus[i1]++;
	      }
	}
	public void addQobj(Student s) throws RemoteException {		
		q.add(s);
	}
	public Queue<Student> getQobj() throws RemoteException {		
		return q;
	}
	
    public boolean isSafe() throws RemoteException {
        return SAFE;
    }
    public void notSafe() throws RemoteException {
        SAFE = false;
    }

    public void removeQ() throws RemoteException {
    	q.remove();
    }
    @Override
    public void notify(int i) throws RemoteException{
    	dbstatus[i]--;
    	q.remove();
    }
    
    public int dbstatus(int i) throws RemoteException{
    	return dbstatus[i];
    	
    }
    public int dbstatus() throws RemoteException{
    	int status = 0;
    	for (int i=0; i<5; i++) {
    		status = status + dbstatus[i];
    	}
    	status = status - dbstatus[0];

    	return status;
    }
    
    public int getStatus() throws RemoteException{
    	return status;
    }
    public void setStatus() throws RemoteException{
    	status = 1;
    }
    
    public void releaseSynch() throws RemoteException {
//    	sem.release();
    }
    
    public void setSynch() throws RemoteException{
//    	try {
////			sem.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    public boolean isWrite() throws RemoteException {
    	return isWrite;
    }
    public Student read(int t)throws Exception, ClassNotFoundException {
		  t = t % 7;
	      try {
 		      FileWriter logwtr = new FileWriter("Server1.log",true);
 		      BufferedWriter bw = new BufferedWriter(logwtr);
 		      PrintWriter pw = new PrintWriter(bw);
 		      System.out.println("LOGGIGN");

 		      pw.println("P1: Entry Read id: "+t);

// 		      logwtr.append();
 	          pw.flush();
 		      logwtr.close();
 		      
// 		      System.out.println("Successfully wrote to the file.");
 		    } catch (IOException e) {
 		      System.out.println("An error occurred.");
 		      e.printStackTrace();
 		    }
	      // JDBC driver name and database URL 
	      String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
	      // Database credentials 
	      String USER = "root"; 
	      String PASS = "bhanuprakash";  
	      
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
	         int clock = rs.getInt("clock");
			  conn.close();

		      try {
	 		      FileWriter logwtr = new FileWriter("Server1.log",true);
	 		      BufferedWriter bw = new BufferedWriter(logwtr);
	 		      PrintWriter pw = new PrintWriter(bw);
	 		      System.out.println("LOGGIGN");

	 		      pw.println("P1: Exit Read id: "+t+ " Percent: "+percent+" Clock: "+clock);

//	 		      logwtr.append();
	 	          pw.flush();
	 		      logwtr.close();
	 		      
//	 		      System.out.println("Successfully wrote to the file.");
	 		    } catch (IOException e) {
	 		      System.out.println("An error occurred.");
	 		      e.printStackTrace();
	 		    }
	         // Setting the values 
	         Student st = new Student(); 
	         st.setID(id); 
	         st.setName(name); 
	         st.setBranch(branch); 
	         st.setPercent(percent); 
	         st.setEmail(email); 

	 		 return st;
	 		 
	      	}
		      try {
	 		      FileWriter logwtr = new FileWriter("Server1.log",true);
	 		      BufferedWriter bw = new BufferedWriter(logwtr);
	 		      PrintWriter pw = new PrintWriter(bw);
	 		      System.out.println("LOGGIGN");

	 		      pw.println("P1: Exit Read id: "+t+ " Percent: "+0+" Clock: "+0);

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


