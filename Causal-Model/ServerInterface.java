import java.rmi.*;

public interface ServerInterface extends Remote{
    public void connect(String nickname) throws RemoteException;
    public void sendMessage(String nickname, String message) throws RemoteException;
    public void sendStudent(Student student) throws RemoteException;
    public void sendToAll(String newMessage) throws RemoteException;
    public void sendStudentToAll(Student student) throws RemoteException;
    public void disconnect(String nickname) throws RemoteException;
}
