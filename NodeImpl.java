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

    private List<String> connections;
    private List<String> nodes;
    private int aux;

    private static Node look_up;

    /*******************************************************************
	*   IMPLEMENTATION
    *******************************************************************/
    public HashMap<String, List<String>> percorreTree(String code, HashMap<String, List<String>> group){
        aux++;
        for (int i = 0; i < com.size(); i++){
            try{
                look_up = (Node) Naming.lookup("rmi://"+com.get(i)+"/groupCom");
                List<String> next = look_up.getConnections();
                group.put(com.get(i), next);
                for (int j = 0; j < com.size(); j++){
                    group = percorreTree(next.get(i), group);
                }
                return group;
            }
            catch (MalformedURLException murle) { 
                System.out.println("\nMalformedURLException: "
                                   + murle); 
            }
            catch (RemoteException re) { 
                System.out.println("\nRemoteException: "
                                   + re); 
            } 
      
            catch (NotBoundException nbe) { 
                System.out.println("\nNotBoundException: "
                                   + nbe); 
            } 
      
            catch (java.lang.ArithmeticException ae) { 
                System.out.println("\nArithmeticException: " + ae); 
            } 
        }
    }
    /**
    * @fn HashMap<String, List<String>> percorreTree(String code, HashMap<String, List<String>> group)
    * @brief percorre o grafo do grupo e guarda numa estrutura
    * @param String code - ip de um nó
      @param HashMap<String, List<String>> group - grafo do grupo
    * @return HashMap<String, List<String>> group - grafo do grupo
    */

    @Override
    public List<String> getConnections(){
        return connections;
    }
    /**
    * @fn public List<String> getConnections()
    * @brief retorna a lista de conexões do nó
    * @param null
    * @return List<String> connections - lista de conexões do nó
    */

    @Override
    public HashMap<String, List<String>> getGroup(String IP){
        HashMap<String, List<String>> group;

        aux = 1;
        group.put(IP, connections);
        for(int i = 0; i < com.size(); i++){
            group = percorreTree(connections.get(i), group);
        }
        group.put("total", to_String(aux));

        return group;
    }
    /**
    * @fn public HashMap<String, List<String>> getGroup(String IP)
    * @brief controla a montagem do grafo de grupo
    * @param String IP - ip de um nó do grupo
    * @return HashMap<String, List<String>> group - grafo conexões do grupo
    */
}