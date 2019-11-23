package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Broadcast extends Remote {
    // Entra em um grupo pelo IP do nó integrante.
    // Caso não encontre nenhum nó com o IP do grupo ou IP do grupo é igual ao próprio IP ou IP do grupo seja null, cria um grupo consigo dentro.
    void enterGroup(String own_ip_address, String group_ip_address) throws RemoteException;
}