package receiver;

import command.Command;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Receiver extends Remote {

    void executeCommand(Command cmd) throws RemoteException;
}
