package com.heejin.socketClient.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heejin.socketClient.view.LoginView;
import com.heejin.socketClient.view.MainView;

public class ClientReceiver extends Thread {

	private static final Logger logger = LogManager.getLogger(ClientReceiver.class);

    private final Client client;
    private LoginView loginView;
    private MainView mainView;

    public ClientReceiver(Client client){
        this.client = client;
        createLoginView(client);
     }


    @Override
    public void run() {

        boolean isStop = false;
        while (client.isAlive()) {
            try {
                String msg = client.getOis().readObject().toString();
                logger.info("수신 메세지 : {}", msg);
                String[] arr = msg.split(Protocol.seperator);
                switch (arr[0]) {
                    case Protocol.checkLogin:

                        if("Y".equals(arr[2])){ //arr[2] 가 Y일때 로그인 성공
                            String onlineUserNames = arr[3];
                            logger.info("온라인 유저 목록 배열 : {}", onlineUserNames);
                            String[] split = onlineUserNames.split(",");
                            createMainView(client);

                        }
                        break;
                }
                logger.info(msg);
                logger.info(client);
                logger.info(loginView);
                if("Y".equals(msg)){
                	logger.info("client.getOis().readObject().toString();: {}",client.getOis().readObject().toString());
                	Protocol.myID = client.getOis().readObject().toString();
                    createMainView(client);
					if(loginView != null) {
                        loginView.dispose();
					}
				} else if("N".equals(msg)) {
					JOptionPane.showMessageDialog(loginView, "로그인에 실패했습니다.");
				}

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        shutdown();

    }

    private synchronized void shutdown(){
        loginView = null;
        mainView = null;
        client.shutdown();
    }

    private synchronized void createLoginView(Client client){
        if(loginView == null){
            loginView = new LoginView(client);
        }
    }

    private synchronized void createMainView(Client client){
        if(mainView == null){
            mainView = new MainView(client);
        }
    }
}
