package client;

import callback.CalculateCallback;
import callback.CalculatePiCallback;
import command.CalculatePi;
import receiver.Receiver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class represents the user that is connecting to the server
 * and executes the command on the Receiver class.
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //Creating a new SecurityManager if none exists
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String serviceName = "Service";
            //Getting the registry
            Registry registry = LocateRegistry.getRegistry(args[0], Registry.REGISTRY_PORT);

            //Creating the callback object
            CalculateCallback callback = new CalculatePiCallback();
            //Creating a stub from the callback object
            CalculateCallback callbackStub = (CalculateCallback) UnicastRemoteObject.exportObject(callback, 0);
            //Telling the
            callbackStub.setIsStub(true);

            //Getting the Receiver object from the registry
            Receiver receiver = (Receiver) registry.lookup(serviceName);
            System.out.println("Service found");

            //Creating a new CalculatePi command
            CalculatePi command = new CalculatePi(Integer.parseInt(args[1]), callbackStub);

            //Executing the command on the remote Receiver object
            receiver.executeCommand(command);

        } catch (RemoteException e) {
            System.out.println("Registry not found");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Service was not found");
            e.printStackTrace();
        }
    }
}
