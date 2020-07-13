import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.*;

public interface Hello extends Remote{
	 public List<Student> getStudents() throws Exception;
	 public void addStudent(int id) throws Exception;
	void sendMessage(String s) throws RemoteException;
	public int dbstatus(int i) throws RemoteException;
	public int dbstatus() throws RemoteException;
	String getMessage() throws RemoteException;
	public void notify(int i) throws RemoteException ;
	public int getStatus() throws RemoteException;
	public void setStatus() throws RemoteException;
}
	