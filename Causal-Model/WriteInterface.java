import java.rmi.*;

public interface WriteInterface extends Remote{
    public void messageFromServer(String message) throws RemoteException;
    public void studentFromServer(Student student) throws RemoteException;
}