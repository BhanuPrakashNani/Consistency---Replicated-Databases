import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.*;
import java.util.concurrent.Semaphore;

public interface DBRemote extends Remote{
//	 Semaphore sem = null;
	public List<Student> getStudents() throws Exception;
	 public void addStudent(Student s) throws Exception;
	public boolean isSafe() throws RemoteException;
	public void notSafe() throws RemoteException;
	public int dbstatus(int i) throws RemoteException;
	public int dbstatus() throws RemoteException;
	public void request(Student s) throws RemoteException;
	public void removeQ() throws RemoteException;
	public Queue<Student> getQobj() throws RemoteException;
	public void notify(int i) throws RemoteException ;
	public int getStatus() throws RemoteException;
	public void setStatus() throws RemoteException;
	public void releaseSynch() throws RemoteException;
	public void setSynch() throws RemoteException;
	public boolean isWrite() throws RemoteException;
	public void setDBStatus() throws RemoteException;
	public Student read(int x) throws Exception, ClassNotFoundException;
	public void addQobj(Student s) throws RemoteException;
}
	