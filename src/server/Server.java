package server;

import receiver.Receiver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class represents the server that has a Receive stub which
 * waits for clients to transmit their commands and to execute
 * them.
 *
 * @author Alexandru Jaravete
 */
public class Server {

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
            //Creating a new service
            Service service = new Service();
            //Creating a stub from the service object
            Receiver stub = (Receiver) UnicastRemoteObject.exportObject(service, 0);

            //Creating a new registry on the standard port
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            //Binding the service to the registry
            registry.rebind(serviceName, stub);

            System.out.println("Service bound! Press Enter to terminate ...");

            //Closing the server and unexporting the service
            while (System.in.read() != '\n') ;
            UnicastRemoteObject.unexportObject(service, true);

            System.out.println("Service unbound, System goes down ...");

        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
