package com.heejin.socketClient;

import com.heejin.socketClient.view.ChatRoomView;
import com.heejin.socketClient.view.LoginView;
import com.heejin.socketClient.view.MainView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SocketClientApplicationTests {

	@Test
	void loginViewTest() {
		LoginView loginView = new LoginView(null);
	}

	@Test
	void mainViewTest() {
		MainView mainView = new MainView();

		List online = Arrays.asList("A", "B", "C");
		List offline = Arrays.asList("D", "E", "F");
		mainView.changeUserList(online, offline);
	}

	@Test
	void chatRoomViewTest() {
		ChatRoomView chatRoomView = new ChatRoomView();
	}


}
