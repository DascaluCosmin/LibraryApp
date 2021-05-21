package controller;

import service.BookTerraException;
import service.Observable;
import service.ServiceInterface;
import view.ManageBooksView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ManageBooksController extends UnicastRemoteObject implements Observable {

    private ServiceInterface server;
    private ManageBooksView manageBooksView;

    public ManageBooksController() throws RemoteException {
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setManageBooksView(ManageBooksView manageBooksView) {
        this.manageBooksView = manageBooksView;
    }

    public ManageBooksView getManageBooksView() {
        return manageBooksView;
    }

    @Override
    public void updateGraphicalInterface() throws BookTerraException, RemoteException {

    }
}
