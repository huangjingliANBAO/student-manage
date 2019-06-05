package com.sm.frame;

import com.eltima.components.ui.DatePicker;
import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddRewardFrame extends JFrame {
    private ImgPanel rootPanel;

    private JTextField rewardField;
    private JButton 新增Button;
    private JLabel closeLabel;
    private JTextField idField;
    private JPanel timePanel;
    private AdminMainFrame adminMainFrame;
    private File file;

public AddRewardFrame(AdminMainFrame adminMainFrame){
    this.adminMainFrame = adminMainFrame;
    setUndecorated(true);
    setSize(700,700);
    setContentPane(rootPanel);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    rootPanel.setFileName("login.jpg");
    rootPanel.repaint();

//关闭标签
    closeLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            AddRewardFrame.this.dispose();
        }
    });
       DatePicker datepick = getDatePicker();
       timePanel.add(datepick);
       timePanel.revalidate();


    新增Button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            RewardPunishment rewardPunishment = new RewardPunishment();
            rewardPunishment.setStudentId(idField.getText());
            rewardPunishment.setDetails(rewardField.getText());
            rewardPunishment.setDate1((Date) getDatePicker().getValue());
            int n = ServiceFactory.getRewardPunishmentServiceInstance().insertRewardPunishment(rewardPunishment);
            if (n == 1){
                JOptionPane.showMessageDialog(rootPanel,"新增成功");
                AddRewardFrame.this.dispose();
                //用学号得到他对应的院系 班级 头像
                List<RewardPunishmentVO> rewardPunishmentList = ServiceFactory.getRewardPunishmentServiceInstance().getAll();
                adminMainFrame.showReward(rewardPunishmentList);
            }
        }
    });
}
    private static DatePicker getDatePicker(){
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        Dimension dimension = new Dimension(200, 30);
        int[] hilightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
//        datepick.setLocation(137, 83);//设置起始位置
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
        */
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        // 设置时钟面板可见
//        datepick.setTimePanleVisible(true);
        return datepick;
    }
}


