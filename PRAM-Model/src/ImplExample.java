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
	String msg =",";
	public List<Student> getStudents() throws Exception, ClassNotFoundException {
		
   	 		Class.forName("com.mysql.jdbc.Driver");
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
	         int id  = rs.getInt("sno"); 
	         
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
	public void addStudent(int id)throws Exception, ClassNotFoundException {
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
	      
	      
	      String name = "Mani";
	      String branch = "cse";
	      int percent = 95;
	      String email = "mani.gmail";
	      
	      stmt = conn.createStatement();
	      //ResultSet rs = stmt.executeQuery(sql);  
	      String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values('"+id+"','"+name+"','"+branch+"','"+percent+"','"+email+"')";
	      int count=stmt.executeUpdate(insert);
	      for (int i =0; i<4; i++) {
	    	  dbstatus[i]++;
	      }
//	      dbstatus[2] =0;
	      status = 0;
	      System.out.println(count);     

	}
    @Override
    public void sendMessage(String s) throws RemoteException {
        System.out.println(s);
        msg = s;
        status++;
    }

    @Override
    public String getMessage() throws RemoteException {
    	status--;
        return "Your message is: " + msg;
        
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

