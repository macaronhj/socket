package com.heejin.socketClient;

import com.heejin.socketClient.view.MainView;

import java.util.Arrays;
import java.util.List;

public class ViewLauncher {

    public static void main(String[] args) {
        MainView mainView = new MainView();
        List<String> online = Arrays.asList("A", "B", "C");
        List<String> offline = Arrays.asList("D", "E", "F");
		mainView.changeUserList(online, offline);
    }

}
