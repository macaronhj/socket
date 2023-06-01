package com.heejin.socketClient.view;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;


public class JoinView extends JDialog {
	
	private static final Logger logger = LogManager.getLogger(JoinView.class);

    private JLabel jlb_id = new JLabel("아이디");
    private JLabel jlb_pw = new JLabel("비밀번호");
    private JLabel jlb_name = new JLabel("이름");
    private JTextField jtf_id = new JTextField("");
    private JTextField jtf_pw = new JTextField("");
    private JTextField jtf_name = new JTextField("");
    private JButton jbtn_join = new JButton("가입신청");

    public JoinView() {
        initializeDisplay();
    }

    private void initializeDisplay() {
        //라벨 추가.
        this.add(jlb_id);
        this.add(jlb_pw);
        this.add(jlb_name);

        //라벨 위치 세팅.
        jlb_id.setBounds(55, 50, 80, 40);
        jlb_pw.setBounds(55, 100, 80, 40);
        jlb_name.setBounds(55, 150, 80, 40);

        //텍스트 필드 추가.
        this.add(jtf_id);
//		jtf_id.addActionListener(addHandler);
        this.add(jtf_pw);
//		jtf_pw.addActionListener(addHandler);
        this.add(jtf_name);
//		jtf_name.addActionListener(addHandler);

        //텍스트 필드 위치 세팅.

        jtf_id.setBounds(120, 50, 180, 40);
        jtf_pw.setBounds(120, 100, 180, 40);
        jtf_name.setBounds(120, 150, 180, 40);

        //버튼추가.
        this.add(jbtn_join);
//		jbtn_join.addActionListener(addHandler);
        logger.info("실행됨");

        //버튼 위치 세팅.
        jbtn_join.setBounds(160, 270, 100, 40);

        //dialog 세팅.
        //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(1100, 200, 400, 400);
        this.setTitle("회원가입");
        this.setLayout(null);
        this.setVisible(true);
    }


}
