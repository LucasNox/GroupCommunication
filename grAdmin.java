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
            String resp = look_up.getAdminInfo();   
            System.out.println(resp);         
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
    * @brief se conecta com um nó e recebe as infos de admin dele
    * @param null
    * @return null
    */
}