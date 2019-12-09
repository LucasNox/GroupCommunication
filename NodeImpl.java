import java.util.*;

public class NodeImpl extends java.rmi.server.UnicastRemoteObject implements Node 
{
    private static final long serialVersionUID = -525732855037272824L;

    private HashMap<String, List<String>> connections;
    private List<String> nodes;

    @Override
    public HashMap<String, List<String>> getAdminInfo(){
        HashMap<String, List<String>> group;

        
    }
}