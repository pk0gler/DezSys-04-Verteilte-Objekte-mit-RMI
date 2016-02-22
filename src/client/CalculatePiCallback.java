package client;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatePiCallback implements CalculateCallback {
    boolean isStub;

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
