package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observable extends Remote {

    void updateGraphicalInterface() throws BookTerraException, RemoteException;
}
