import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Arivu extends Frame implements Runnable, ActionListener {
    TextField textField;
    TextArea textArea;
    Button Send;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread chat;
    Arivu()
    {
        textField=new TextField();
        textArea=new TextArea();
        Send=new Button("Send");

        Send.addActionListener(this);
        try {

            serverSocket = new ServerSocket(15550);
            socket=serverSocket.accept();

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
        setTitle("Arivu");
        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=textField.getText();
        textArea.append("Arivu :"+msg+"\n");
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
        new Arivu();

    }
    public void run(){
        while (true){
            try {
                String msg=dataInputStream.readUTF();
                textArea.append("Hussain :"+msg+"\n");


            }
            catch (Exception E)
            {

            }
        }
    }
}