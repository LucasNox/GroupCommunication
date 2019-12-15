import GroupCommunication.Node;
import java.util.*;

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
        
        try{
            look_up = (Node) Naming.lookup("rmi://"+IP+"/groupCom");

            HashMap<String, List<String>> resp = look_up.getGroup(IP);   
            System.out.println("Quantidade nós = " + resp.get("total"));
            System.out.println("Grafo de conecxões do grupo:");
            for (Map.Entry<String, List<String>> entry : map.entrySet()){
                System.out.println(entry.getKey() + " -> ");
                for (int i = 0; i < entry.getValue().size(); i++){
                    System.out.println(entry.getValue().get(i) + ", ");
                }
            }
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
    /**
    * @fn public static void main(String[] args)
    * @brief se conecta com um nó e exibe as infos de admin dele
    * @param null
    * @return null
    */
}