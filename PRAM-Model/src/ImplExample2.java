import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImplExample2 implements Hello{
	static int status;
	int[] dbstatus = new int[]{ 0, 0, 0, 0};  // 0- c1, 1-c2, 3-s1, 4-s2
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
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi4";  
	      
	      // Database credentials 
	      String USER = "root"; 
	      String PASS = "bhanuprakash";  
	      
	      Connection conn = null; 
	      Statement stmt = null;  

	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("SERVER 2 Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	      System.out.println("Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("SERVER 2 Creating statement..."); 
	      	
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
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi4";  
	      
	      // Database credentials 
	      String USER = "root"; 
	      String PASS = "bhanuprakash";  
	      
	      Connection conn = null; 
	      Statement stmt = null;  

	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("SERVER 2 Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	      System.out.println("SERVER 2 Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("SERVER 2 Creating statement...");
	      
	      
	      String name = "Bhanu";
	      String branch = "cse";
	      int percent = 96;
	      String email = "bhanu.gmail";
	      
	      stmt = conn.createStatement();
	      //ResultSet rs = stmt.executeQuery(sql);  
	      String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values('"+id+"','"+name+"','"+branch+"','"+percent+"','"+email+"')";
	      int count=stmt.executeUpdate(insert);
	      for (int i =0; i<4; i++) {
	    	  dbstatus[i]++;
	      }
//	      dbstatus[3] = 0;
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
    	status = status - dbstatus[3];
    	return status;
    }
	@Override
	public int getStatus() throws RemoteException {
		// TODO Auto-generated method stub
		return status;
	}
	@Override
	public void setStatus() throws RemoteException {
		// TODO Auto-generated method stub
		status = 1;
	}
    
}

