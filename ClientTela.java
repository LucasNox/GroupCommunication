import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.util.*;

public class ClientTela { 

    JTextArea tx;
    JTextField tf,ip, name;
    JButton connect, bt;
    JList lst;
    JFrame frame;

    public void doConnect(){
        //connect to grouṕ
        bt.setEnabled(true);
    }

    /*public void getTextUsers(){
        get msgs in a thread
    }*/

    public void sendText(){
          String st=tf.getText();
          st="["+name.getText()+"] "+st+"\n";
          tf.setText("");
          tx.append(st);
          //send server
      }
     
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
	    lst=new JList();        
	    main.setLayout(new BorderLayout(5,5));         
	    top.setLayout(new GridLayout(1,0,5,5));   
	    cn.setLayout(new BorderLayout(5,5));
	    bottom.setLayout(new BorderLayout(5,5));
	    top.add(new JLabel("Your name: "));top.add(name);    
	    top.add(new JLabel("Server Address: "));top.add(ip);
	    top.add(connect);
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
	    //Events
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

    public static void main(String[] args) {
		ClientTela cliente = new ClientTela();
    }
}