import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImplExample implements Hello{
	static int status;
	
	int[] dbstatus = new int[]{ 0, 0, 0, 0};
	String msg ="";
	static boolean isWrite = false; // will be used to know whether the status increment is write or read;
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
	public void write(int id)throws Exception, ClassNotFoundException {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      int t = id % 7;
	      
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
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
	      
	      
	      String name = "Mani";
	      String branch = "cse";
	      int percent = 01;
	      String email = "mani.gmail";
	      
	      //check for id in table 
	      boolean idExists = false;
	      stmt = conn.createStatement();
	      String sql = "SELECT * FROM samplermi"; 
	      ResultSet rs = stmt.executeQuery(sql);  
	      
	      //Extract data from result set 
	      while(rs.next()) { 
	         // Retrieve by column name 
	         if( t == rs.getInt("id")) {
	        	 idExists = true;
	        	 percent += rs.getInt("percentage");
	        	 break;
	         }
	      }
	      

	      if (!idExists) {
	      //ResultSet rs = stmt.executeQuery(sql);  
	      String insert = "INSERT INTO samplermi(id, name, branch, percentage, email) values('"+id+"','"+name+"','"+branch+"','"+percent+"','"+email+"')";
	      int count=stmt.executeUpdate(insert);
	      msg = insert;
	      }
	      else {
	    	  
	    	  String update = "UPDATE samplermi SET percentage = "+percent+" where id = "+t;
		      int count=stmt.executeUpdate(update);
		      msg = update;
	      }
	      setDbStatus();
//	      dbstatus[2] =0;
	      status = 0;


	 		 try {
	 		      FileWriter logwtr = new FileWriter("Server1.log",true);
	 		      BufferedWriter bw = new BufferedWriter(logwtr);
	 		      PrintWriter pw = new PrintWriter(bw);
	 		      System.out.println("LOGGIGN");

	 		      pw.println("P1:  Write id: "+t	 +"  Percent: "+ percent);

//	 		      logwtr.append();
	 	          pw.flush();
	 		      logwtr.close();
	 		      
//	 		      System.out.println("Successfully wrote to the file.");
	 		    } catch (IOException e) {
	 		      System.out.println("An error occurred.");
	 		      e.printStackTrace();
	 		    }
	      System.out.println("wrote");    
	      

	}
    public void setWrite() throws RemoteException {
        isWrite = true;
    }

    public void setRead() throws RemoteException {
    	isWrite = false;
    }
    
    public boolean isWrite() throws RemoteException {
    	return isWrite;
    }
    
    public String getStmt() throws RemoteException {
    	return msg;
    }
    
    public void setDbStatus() throws RemoteException {
	      for (int i =0; i<4; i++) {
	    	  dbstatus[i]++;
	      }
    }
    
    @Override
    public void notify(int i) throws RemoteException{
    	dbstatus[i]--;
    }
    
    public int dbstatus(int i) throws RemoteException{
    	return dbstatus[i];
    }
    public int dbstatus() throws RemoteException{
    	int status = 0;
    	for (int i=0; i<4; i++) {
    		status = status + dbstatus[i];
    	}
    	status = status - dbstatus[2];

    	return status;
    }
    
    public int getStatus() throws RemoteException{
    	return status;
    }
    public void setStatus() throws RemoteException{
    	status = 1;
    }
    
}

