package com.heejin.socketClient;


import com.heejin.socketClient.core.Client;
import com.heejin.socketClient.view.LoginView;
import com.heejin.socketClient.view.MainView;

public class SocketClientApplication {

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 9100);
	}

}
