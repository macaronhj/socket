package com.heejin.socketClient.core;


import com.heejin.socketClient.view.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{

    private static final Logger logger = LogManager.getLogger(Client.class);

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private ClientReceiver receiver;

    public Client(String ip, int port) {
        try{
            this.socket = new Socket(ip, port);
            this.oos = new ObjectOutputStream(socket.getOutputStream());	//입력 스트림 얻기
            this.ois = new ObjectInputStream(socket.getInputStream());		//출력 스트림 얻기
            receiver = new ClientReceiver(this);
            receiver.start();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void initialize() {

    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void sendMessage(String protocol, String... msg){
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append(Protocol.seperator);
        for (int i = 0; i < msg.length; i++) {
            if(i < msg.length-1){
                sb.append(msg[i]).append(Protocol.seperator);
            }else{
                sb.append(msg[i]);
            }
        }

        logger.info("서버로 전송되는 메세지 : {}", sb.toString());

        try {
            oos.writeObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void shutdown(){

        try {
            oos.close();
            ois.close();
            socket.close();
            receiver = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isAlive(){
        return !socket.isInputShutdown();
    }
}

/*
 * 데이터를 보내려면 보낼 데이터를 byte[]배열로 생성하고 이것을 매개값으로 하여 OutputStream의 write()메서드를 호출하면 됨.
 * 상대방이 보낸 데이터를 받으려면 받은 데이터를 저장할 byte[]배열을 하나 생성하고, 이것을 매개값으로 InputStream의 read()메서드 호출
 * */
