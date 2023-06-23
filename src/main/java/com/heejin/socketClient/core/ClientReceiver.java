package com.heejin.socketClient.core;

import com.heejin.socketClient.view.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

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
                logger.info(msg);
                logger.info(client);
                logger.info(loginView);
                if("로그인 성공".equals(msg)){
                	Protocol.myID = client.getOis().readObject().toString();
                    createMainView(client);
					if(loginView != null) {
                        loginView.dispose();
					}
				} else if("로그인 실패".equals(msg)) {
					JOptionPane.showMessageDialog(loginView, "로그인에 실패했습니다.");
				}

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
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
