import java.util.*;

public interface Node extends java.rmi.Remote 
{ 
    public List<String> getConnections();

    public HashMap<String, List<String>> getGroup(String IP);

    public void sairGrupo(String ip);

    public void eraseCon(String ip);

    public void enviarMSG(String word);
}