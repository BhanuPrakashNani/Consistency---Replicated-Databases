import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.*;

public interface Hello extends Remote{
	 public List<Student> getStudents() throws Exception;
	 public void write(int id) throws Exception;
	public void setWrite() throws RemoteException;
	public int dbstatus(int i) throws RemoteException;
	public int dbstatus() throws RemoteException;
	public void setRead() throws RemoteException;
	public String getStmt() throws RemoteException;
	public boolean isWrite() throws RemoteException;
	public void notify(int i) throws RemoteException ;
	public int getStatus() throws RemoteException;
	public void setStatus() throws RemoteException;
	public void setDbStatus() throws RemoteException;
}
	