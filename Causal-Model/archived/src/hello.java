import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.*;

public interface hello extends Remote{
	 public Student getStudents(int k, int j) throws Exception;
	 public void addStudent(int id,int k) throws Exception;
	 public void updateStudent(int id,int k) throws Exception;
	void sendMessage(String s) throws RemoteException;
	public int dbstatus(int i) throws RemoteException;
	public int dbstatus() throws RemoteException;
	public int dbstatus1(int i) throws RemoteException;
	String getMessage() throws RemoteException;
	public void notify(int i) throws RemoteException ;
	public void notify1(int i) throws RemoteException ;
	public int getStatus() throws RemoteException;
	public void setStatus() throws RemoteException;
	public void set_dbstatus(int i, int val) throws RemoteException;
	public void set_dbstatus1(int i, int val) throws RemoteException;
	public String get_insert() throws RemoteException;
	public String insert_container() throws RemoteException;
	public String insert_container1() throws RemoteException;
	public void request_write(int id, int k) throws RemoteException;
	public void request_update(int id, int k,String name) throws RemoteException;
	public void request_write_others(String insert) throws RemoteException;
	public void request_update_others(String insert) throws RemoteException;
	public Queue<String> queue() throws RemoteException;
	public void clearqueue() throws RemoteException;
	public int getRandupd() throws RemoteException;
	public String getRandname() throws RemoteException;
	public void setRandupd(int kval) throws RemoteException;
	public void setRandname(String name) throws RemoteException;
}
	