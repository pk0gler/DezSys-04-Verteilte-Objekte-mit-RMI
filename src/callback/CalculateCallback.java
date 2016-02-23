package callback;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the callback interface for calculations.
 *
 * @author Alexandru Jaravete
 */
public interface CalculateCallback extends Remote {

    /**
     * Receives the result from a command.
     *
     * @param result received from a command
     * @throws RemoteException
     */
    void receiveResult(BigDecimal result) throws RemoteException;

    /**
     * If the object is a stub then it unexports itself as soon
     * as it receives the result from a command.
     *
     * @param isStub true if the object is a stub
     * @throws RemoteException
     */
    void setIsStub(boolean isStub) throws RemoteException;
}
