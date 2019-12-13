import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.util.*;

/**
 * @file  ClientTela.java
 * @brief Arquivo com as funções de construção e interação com a interface do usuario
*/

public class ClientTela { 

		/*******************************************************************
		*   GLOBAL VARIABLES
		*******************************************************************/
    JTextArea tx;
    JTextField tf,ip, name;
    JButton connect, bt, btc;
    JList lst;
    JFrame frame;

		/*******************************************************************
		*   IMPLEMENTATION
		*******************************************************************/
    public void doConnect(){
				//connection process
				bt.setEnabled(true);
				btc.setEnabled(true);
		}
		/**
    * @fn public void doConnect()
    * @brief chama a conexão do usuario do usuario ao grupo
    * @param null
    * @return null
    */

    public void getTextUsers(){
        //get msgs in a thread
		}
		/**
    * @fn public void getTextUsers()
    * @brief escuta/recebe as mensagens de outros usuarios
    * @param null
    * @return null
    */

    public void sendText(){
          String st=tf.getText();
          st="["+name.getText()+"] "+st+"\n";
          tf.setText("");
          tx.append(st);
          //send server
		}
		/**
    * @fn public void sendText()
    * @brief envia mensagens
    * @param null
    * @return null
		*/
		
		public void closeConnection(){
			//call client function to close connection
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
	    top.add(new JLabel("Server Address: "));top.add(ip);
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
	    frame.setSize(600,600);
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