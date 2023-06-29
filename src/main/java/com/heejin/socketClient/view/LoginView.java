package com.heejin.socketClient.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.heejin.socketClient.core.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heejin.socketClient.core.Client;

public class LoginView extends JFrame {

    private static final Logger logger = LogManager.getLogger(LoginView.class);
    private final Client client;

    public JTextField field_id;
    public JPasswordField field_pw;


    public LoginView(Client client) {
        this.client = client;
        initializeDisplay();
        initialize();
    }

    private void initialize() {
        logger.info("로그인 뷰 생성 성공");
        logger.warn("로그인 뷰 생성 성공");
        logger.error("로그인 뷰 생성 성공");
    }

    private void initializeDisplay() {
        JLabel label_id = new JLabel("ID");
        JLabel label_pw = new JLabel("PW");

        Font font = new Font("고딕체", Font.BOLD, 17);
        label_id.setFont(font);
        label_pw.setFont(font);
        label_id.setBounds(55, 200, 80, 40);
        label_pw.setBounds(55, 250, 80, 40);

        this.setLayout(null);
        this.add(label_id);
        this.add(label_pw);

        field_id = new JTextField();
        field_pw = new JPasswordField();
        this.add(field_id);
        field_pw.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    client.sendMessage(Protocol.checkLogin, field_id.getText(), field_pw.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.add(field_pw);
        field_id.setBounds(120, 200, 185, 40);
        field_id.getText();
        field_pw.setBounds(120, 250, 185, 40);
        field_pw.getText();

        JButton button_login = new JButton("로그인");
        JButton button_join = new JButton("회원가입");

        button_login.addActionListener(e -> {
            Object source = e.getSource();
            logger.info("source: "+ source);
            try {
            	Map<String, String> map = new HashMap<String, String>();
            	map.put("id", field_id.getText());
            	map.put("pwd", field_pw.getText());
				client.getOos().writeObject(map);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        });
        
        this.add(button_login);
        button_login.setBounds(160, 300, 100, 40);
        button_join.addActionListener(e -> {
            Object source = e.getSource();


        });
        this.add(button_join);
        button_join.setBounds(160, 350, 100, 40);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(700, 200, 400, 600);
        this.setTitle("Login");
        this.setVisible(true);
    }

}
