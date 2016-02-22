package client;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculateCallback extends Remote {

    void receiveResult(BigDecimal result) throws RemoteException;

    void setIsStub(boolean isStub) throws RemoteException;
}
