package server;

import receiver.Receiver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String serviceName = "Service";
            Service service = new Service();
            Receiver stub = (Receiver) UnicastRemoteObject.exportObject(service, 0);

            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.rebind(serviceName, stub);

            System.out.println("Service has been successfully bound. Enter \"exit\" to close server.");
        } catch (RemoteException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
