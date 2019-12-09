import java.util.*;

/**
 * @file  NodeImpl.java
 * @brief Implementação das funções compartilhadas dos nós
*/

public class NodeImpl extends java.rmi.server.UnicastRemoteObject implements Node 
{
    /*******************************************************************
	*   GLOBAL VARIABLES
	*******************************************************************/
    private static final long serialVersionUID = -525732855037272824L;

    private HashMap<String, List<String>> connections;
    private List<String> nodes;

    /*******************************************************************
	*   IMPLEMENTATION
	*******************************************************************/
    @Override
    public HashMap<String, List<String>> getAdminInfo(){
        HashMap<String, List<String>> group;

        return group;
    }
    /**
    * @fn public HashMap<String, List<String>> getAdminInfo()
    * @brief percorre a arvore e retorna as infos do grupo
    * @param HashMap<String, List<String>>
    * @return null
    */
}