package com.heejin.socketClient.view;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heejin.socketClient.core.Client;

public class LoginView extends JFrame {

    private static final Logger logger = LogManager.getLogger(LoginView.class);
    private Client client;

    private final JLabel label_id = new JLabel("ID");
    private final JLabel label_pw = new JLabel("PW");
    public JTextField field_id = new JTextField();
    public JPasswordField field_pw = new JPasswordField();
    private final JButton button_login = new JButton("로그인");
    private final JButton button_join = new JButton("회원가입");
    Font font = new Font("고딕체", Font.BOLD, 17);

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

        this.setLayout(null);
        this.add(label_id);
        this.add(label_pw);
        label_id.setFont(font);
        label_pw.setFont(font);
        label_id.setBounds(55, 200, 80, 40);
        label_pw.setBounds(55, 250, 80, 40);

        this.add(field_id);
        field_pw.addActionListener(e -> {
            Object source = e.getSource();

        });


        this.add(field_pw);
        field_id.setBounds(120, 200, 185, 40);
        field_id.getText();
        field_pw.setBounds(120, 250, 185, 40);
        field_pw.getText();

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

    public JLabel getLabel_id() {
        return label_id;
    }

    public JLabel getLabel_pw() {
        return label_pw;
    }

    public JTextField getField_id() {
        return field_id;
    }

    public JPasswordField getField_pw() {
        return field_pw;
    }

    public JButton getButton_login() {
        return button_login;
    }

    public JButton getButton_join() {
        return button_join;
    }
}
