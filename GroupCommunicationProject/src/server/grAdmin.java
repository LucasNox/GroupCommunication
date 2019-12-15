package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Iterator;

import org.w3c.dom.Node;

/**
 * @file  grAdmin.java
 * @brief Arquivo com as funções do admin do sistema: 
 * - quantidade de elementos do grupo
 * - o endereço IP de cada elemento
 * - a relação existente entre os elementos
*/

public class grAdmin
{
    /*******************************************************************
	*   GLOBAL VARIABLES
	*******************************************************************/
    private static Node look_up;

    /*******************************************************************
	*   IMPLEMENTATION
	*******************************************************************/
    public static void main(String[] args){
        String IP = args[0];
        
        try {
            Registry registry = LocateRegistry.getRegistry(IP);
			Broadcast stub = (Broadcast) registry.lookup("Broadcast");
						
            System.out.println(stub.grAdmin());
        } catch (Exception e) {
            System.err.println("grAdmin exception: " + e.toString());
            e.printStackTrace();
        }
    }
    /**
    * @fn public static void main(String[] args)
    * @brief se conecta com um nó e exibe as infos de admin dele
    * @param null
    * @return null
    */
}