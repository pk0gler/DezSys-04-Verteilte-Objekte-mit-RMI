package server;

import command.Command;
import receiver.Receiver;

import java.rmi.RemoteException;

/**
 * This is an implementation of the interface Receiver.
 *
 * @author Alexandru Jaravete
 */
public class Service implements Receiver {

    @Override
    public void executeCommand(Command cmd) throws RemoteException {
        cmd.execute();
    }
}
