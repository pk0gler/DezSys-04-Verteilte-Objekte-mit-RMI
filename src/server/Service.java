package server;

import command.Command;
import receiver.Receiver;

import java.rmi.RemoteException;

public class Service implements Receiver {

    @Override
    public void executeCommand(Command cmd) throws RemoteException {
        cmd.execute();
    }
}
