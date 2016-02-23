package receiver;

import command.Command;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface represents the receiver where the commands are executed.
 *
 * @author Alexandru Jaravete
 */
public interface Receiver extends Remote {

    /**
     * Executes a command.
     *
     * @param cmd to be executed
     * @throws RemoteException
     */
    void executeCommand(Command cmd) throws RemoteException;
}
