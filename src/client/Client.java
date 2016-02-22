package client;

import command.CalculatePi;
import receiver.Receiver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String serviceName = "Service";
            Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);

            CalculateCallback callback = new CalculatePiCallback();
            CalculateCallback callbackStub = (CalculateCallback) UnicastRemoteObject.exportObject(callback, 0);
            callbackStub.setIsStub(true);

            Receiver receiver = (Receiver) registry.lookup(serviceName);
            System.out.println("Service found");

            CalculatePi command = new CalculatePi(10, callbackStub);

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
