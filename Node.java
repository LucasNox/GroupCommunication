import java.util.*;

public interface Node extends java.rmi.Remote 
{ 
    public HashMap<String, List<String>> getAdminInfo();
}