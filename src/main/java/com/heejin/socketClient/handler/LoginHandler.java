package com.heejin.socketClient.handler;

import com.heejin.socketClient.core.Client;
import com.heejin.socketClient.view.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHandler implements ActionListener{

	private static final Logger logger = LogManager.getLogger(LoginHandler.class);

	private LoginView loginView = null;
	private Client client;

//    private JTextField field_id = new JTextField();
//    private JPasswordField field_pw = new JPasswordField();
	
	public LoginHandler(LoginView loginView, Client client){
		this.loginView = loginView;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();


	}

}
