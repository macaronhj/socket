package com.heejin.socketClient.view;


import java.awt.Font;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heejin.socketClient.core.Protocol;

public class UserListPanel extends JPanel {

    private static final Logger logger = LogManager.getLogger(UserListPanel.class);
    private final JLabel label_userName = new JLabel(); // 사용자이름

    private DefaultTableModel dtm_online;
    private DefaultTableModel dtm_offline;

    private String userName;

    public UserListPanel() {
        initializeDisplay();

    }

    public UserListPanel(String userName) {
        this.userName = userName;
        initializeDisplay();
        initialize();
    }

    public void initialize(){

    }

    public void initializeDisplay() {
        label_userName.setText(Protocol.myID + "님 환영합니다.");
        label_userName.setFont(new Font("맑은고딕", Font.BOLD, 15));

        JPanel panel_north = new JPanel();
        panel_north.setBounds(0, 20, 500, 40);
        panel_north.add(label_userName);

        JPanel panel_online = new JPanel();
        JTable jtb_online = new JTable(dtm_online);
        //jtb_online.addMouseListener(null);
        panel_online.setBounds(0, 60, 500, 200);
        panel_online.add(new JLabel("온라인"));
        panel_online.add(new JScrollPane(jtb_online));

        // 제목
        Vector<String> columnNames = new Vector<>(Arrays.asList("아이디"));
        // 데이터
    	dtm_online = new DefaultTableModel(){
            @Override //셀 더블클릭 후 수정 안되도록 조정.
            public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
                return false;
            }
    	};

        dtm_online.setDataVector(new Vector<String>(), columnNames);
    	jtb_online.setModel(dtm_online);

        JPanel panel_offline = new JPanel();
        JTable jtb_offline = new JTable(dtm_offline);
        //jtb_offline.addMouseListener(null);
        panel_offline.setBounds(0, 280, 500, 200);
        panel_offline.add(new JLabel("오프라인"));
        panel_offline.add(new JScrollPane(jtb_offline));

    	dtm_offline = new DefaultTableModel(){
            @Override //셀 더블클릭 후 수정 안되도록 조정.
            public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
                return false;
            }
    	};
        dtm_offline.setDataVector(new Vector<String>(), columnNames);
    	jtb_offline.setModel(dtm_offline);

        // 중단
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
        dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
        TableColumnModel tcm = jtb_online.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }
        tcm = jtb_offline.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

        JPanel panel_south = new JPanel();
        JButton button_chat = new JButton("방 만들기");
        // 하단
        button_chat.addActionListener(e -> {
            int row = jtb_online.getSelectedRow();
            int col = jtb_online.getSelectedColumn();
            String cellData = getCellData(row, col);
            logger.info("선택된 온라인 유저 셀 : {}", cellData);

            CreateChattingView view = new CreateChattingView();
        });
        panel_south.add(button_chat);

        JButton button_logout = new JButton("로그아웃");
        button_logout.addActionListener(e -> {

        });
        panel_south.add(button_logout);
        panel_south.setBounds(0, 500, 500, 40);

        JFrame.setDefaultLookAndFeelDecorated(true);
        setLayout(null);
        add(panel_north);
        add(panel_online); // 온라인 테이블 적용
        add(panel_offline); // 오프라인 테이블 적용
        add(panel_south);

    }

    public DefaultTableModel getDtm_online() {
        return dtm_online;
    }

    public DefaultTableModel getDtm_offline() {
        return dtm_offline;
    }

    private String getCellData(int row, int col){
        return (String) dtm_online.getValueAt(row, col);
    }

}
