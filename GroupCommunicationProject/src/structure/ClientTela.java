package structure;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.TimerTask;

import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import server.Broadcast;
import server.Server;

/**
 * @file  ClientTela.java
 * @brief Arquivo com as funções de construção e interação com a interface do usuario
*/

public class ClientTela { 

		/*******************************************************************
		 *   GLOBAL VARIABLES
		 *******************************************************************/
		static JTextArea tx;
		JTextField tf,ip, name, myIP;
		JButton connect, bt, btc;
		JList lst;
		JFrame frame;
		
		String IP;
		static Broadcast stub;
		
		/*******************************************************************
		 *   IMPLEMENTATION
		 *******************************************************************/
		public void doConnect(){
			IP = myIP.getText();
			String ipNode = ip.getText();
			bt.setEnabled(true);
			btc.setEnabled(true);

			
			Thread t = new Thread(new Server(IP));
			t.start();
			try {
				Thread.sleep(500);
				Registry registry = LocateRegistry.getRegistry(IP);
				stub = (Broadcast) registry.lookup("Broadcast");
				
				stub.createGroup(IP);
				if(!IP.equals(ipNode))
				{
					System.out.println(stub.getNodes());
					stub.enterGroup(ipNode);
					System.out.println(stub.getNodes());
				}
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}
			new Thread(listenMSG).start();
		}
		/**
    * @fn public void doConnect()
    * @brief chama a conexão do usuario do usuario ao grupo
    * @param null
    * @return null
		*/
		
		private static Runnable listenMSG = new Runnable() {
			public void run() {
					try{
							Timer timer = new Timer();
							timer.schedule(new TimerTask() {
								@Override 
									public void run(){
										getTextUsers();
									}
							}, 1, 1);
					} catch (Exception e){e.printStackTrace();}
			}
		};

    public static void getTextUsers(){
			try{
				LinkedList<Message> msgs = stub.getMSGS();
			
				//System.out.println("LISTEN MSGS");
		//System.out.println(msgs);

		if(msgs.size() > 0){
			System.out.println("MSGS EXIST");
			for (Message msg : msgs) {
				String format = "["+msg.getAuthor()+" "+msg.getTime()+"] "+msg.getMessage()+"\n";
				tx.append(format);
			}
		}
	}catch(Exception e){}
	}
		
		/**
    * @fn public void getTextUsers()
    * @brief escuta/recebe as mensagens de outros usuarios
    * @param null
    * @return null
    */

    public void sendText(){
					String st=tf.getText();
					String kmsg=tf.getText();
          st="["+name.getText()+ " " + java.time.LocalTime.now() +"] "+st+"\n";
          tf.setText("");
					tx.append(st);
					try{
						stub.sendString(kmsg, name.getText(), java.time.LocalTime.now(), "");
					}catch(Exception e){e.printStackTrace();};
		}
		/**
    * @fn public void sendText()
    * @brief envia mensagens
    * @param null
    * @return null
		*/
		
		public void closeConnection(){
			//call client function to close connection
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		/**
    * @fn public void closeConnection()
    * @brief chama processo de saida do grupo
    * @param null
    * @return null
		*/
     
    public ClientTela() {
      frame=new JFrame("Group Chat");
	    JPanel main =new JPanel();
	    JPanel top =new JPanel();
	    JPanel cn =new JPanel();
	    JPanel bottom =new JPanel();
	    ip=new JTextField();
			tf=new JTextField();
			myIP=new JTextField();
	    name=new JTextField();
	    tx=new JTextArea();
	    connect=new JButton("Connect");
			bt=new JButton("Send");
	    btc=new JButton("Sair");
	    lst=new JList();        
	    main.setLayout(new BorderLayout(5,5));         
	    top.setLayout(new GridLayout(1,0,5,5));   
	    cn.setLayout(new BorderLayout(5,5));
	    bottom.setLayout(new BorderLayout(5,5));
	    top.add(new JLabel("Your name: "));top.add(name);    
			top.add(new JLabel("Node IP: "));top.add(ip);
			top.add(new JLabel("My IP: "));top.add(myIP);
			top.add(connect);
			top.add(btc);
	    cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
	    cn.add(lst, BorderLayout.EAST);    
	    bottom.add(tf, BorderLayout.CENTER);    
	    bottom.add(bt, BorderLayout.EAST);
	    main.add(top, BorderLayout.NORTH);
	    main.add(cn, BorderLayout.CENTER);
	    main.add(bottom, BorderLayout.SOUTH);
      main.setBorder(new EmptyBorder(10, 10, 10, 10) );
      tx.setEditable(false);
			bt.setEnabled(false);
			btc.setEnabled(false);
			//Events
			btc.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ closeConnection();   }  });
	    connect.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ doConnect();   }  });
	    bt.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ sendText();   }  });
	    tf.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ sendText();   }  });
	    
	    frame.setContentPane(main);
	    frame.setSize(800,600);
	    frame.setVisible(true);  
		}
		/**
    * @fn public ClienteTela()
    * @brief cria a tela do app
    * @param null
    * @return null
    */

    public static void main(String[] args) {
			ClientTela cliente = new ClientTela();
		}
		/**
    * @fn public static void main()
    * @brief inicializa o app
    * @param null
    * @return null
    */
}