package com.heejin.socketClient.view;


import com.heejin.socketClient.core.Client;
import com.heejin.socketClient.core.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;


public class MainView extends JFrame {
    private static final Logger logger = LogManager.getLogger(MainView.class);
    private Client client;

    private UserListPanel panel_userList;
    private ChatListPanel panel_chatList;

    private String userName;

    public MainView() {
        initializeDisplay();
    }

    public MainView(Client client) {
        this.client = client;
        initializeDisplay();
    }

    public MainView(Client client, String userName) {
        this.client = client;
        this.userName = userName;
        initializeDisplay();
    }

    private void initializeDisplay() {

        JTabbedPane tabbedPane = new JTabbedPane();

        panel_userList = new UserListPanel();
        panel_userList.add(new JLabel("유저목록", SwingConstants.CENTER));
        tabbedPane.addTab("유저목록", panel_userList);

        panel_chatList = new ChatListPanel();
        panel_chatList.add(new JLabel("방목록", SwingConstants.CENTER));
        tabbedPane.addTab("방목록", panel_chatList);

        this.setTitle(Protocol.myID);
        this.setBounds(650, 200, 500, 600);
        this.setVisible(true);
        this.add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void changeUserList(List<String> onlineUserNameList, List<String> offlineUserNameList) {
        DefaultTableModel dtm_online = panel_userList.getDtm_online();
        Vector onlineData = dtm_online.getDataVector();
        onlineData.clear(); //테이블 온라인 유저 데이터 초기화

        for(String s : onlineUserNameList){ //테이블에 온라인 유저 데이터 입력
            Object[] data = {s};
            dtm_online.addRow(data);
        }

        DefaultTableModel dtm_offline = panel_userList.getDtm_offline();
        Vector offlineData = dtm_offline.getDataVector();
        offlineData.clear();//테이블 오프라인 유저 데이터 초기화

        for(String s : offlineUserNameList){//테이블에 오프라인 유저 데이터 입력
            Object[] data = {s};
            dtm_offline.addRow(data);
        }
    }

    public void changeChatRoomList(List<String> chattingRoomList) {
        DefaultTableModel dtm_room = panel_chatList.getDtm_room();
        Vector roomData = dtm_room.getDataVector();
        roomData.clear();//테이블 오프라인 유저 데이터 초기화

        for(String s : chattingRoomList){//테이블에 오프라인 유저 데이터 입력
            Object[] data = {s};
            dtm_room.addRow(data);
        }
    }

}
