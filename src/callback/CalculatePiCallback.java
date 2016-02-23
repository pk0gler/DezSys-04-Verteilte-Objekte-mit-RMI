package callback;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * CalculatePiCallback is the implementation of the CalculateCallback interface.
 *
 * @author Alexandru Jaravete
 */
public class CalculatePiCallback implements CalculateCallback {
    private boolean isStub;

    /**
     * The Constructor automatically sets isStub to false because
     * normally an instance of this class is not a stub.
     */
    public CalculatePiCallback() {
        isStub = false;
    }

    @Override
    public void receiveResult(BigDecimal result) throws RemoteException {
        System.out.println("Result: " + result);
        if (isStub) {
            UnicastRemoteObject.unexportObject(this, true);
        }
    }

    @Override
    public void setIsStub(boolean isStub) throws RemoteException {
        this.isStub = isStub;
    }
}
