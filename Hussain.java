import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Hussain extends Frame implements Runnable, ActionListener {
    TextField textField;
    TextArea textArea;
    Button Send;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread chat;
    Hussain()
    {
        textField=new TextField();
        textArea=new TextArea();
        Send=new Button("Send");

        Send.addActionListener(this);
        try {

            socket=new Socket("LoclaHost",15550);

            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream((socket.getOutputStream()));

        }
        catch (Exception E){

        }
        add(textField);
        add(textArea);
        add(Send);

        chat=new Thread(this);
        chat.setDaemon(true);
        chat.start();

        setSize(500,100);
        setTitle("Hussain");
        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=textField.getText();
        textArea.append("Hussain :" + msg+"\n");
        textField.setText("");

        try {

            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        }
        catch (Exception E)
        {

        }
    }

    public static void main(String[] args) {
        new Hussain();

    }
    public void run(){
        while (true){
            try {
                String msg=dataInputStream.readUTF();
                textArea.append("Arivu :"+msg+"\n");


            }
            catch (Exception E)
            {

            }
        }
    }
}