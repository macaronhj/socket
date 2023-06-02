package com.heejin.socketClient;

import com.heejin.socketClient.view.ChatRoomView;
import com.heejin.socketClient.view.LoginView;
import com.heejin.socketClient.view.MainView;
import org.junit.jupiter.api.Test;

class SocketClientApplicationTests {

	@Test
	void loginViewTest() {
		LoginView loginView = new LoginView(null);
	}

	@Test
	void mainViewTest() {
		MainView mainView = new MainView();
	}

	@Test
	void chatRoomViewTest() {
		ChatRoomView chatRoomView = new ChatRoomView();
	}
}
