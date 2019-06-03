package com.sm.frame;

import com.sm.entity.RewardStudent;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class AddRewardFrame extends JFrame {
    private ImgPanel rootPanel;
    private JTextField nameField;
    private JTextField rewardField;
    private JButton 新增Button;
    private JLabel closeLabel;
    private JTextField departmentField;
    private JTextField classField;
    private JLabel avatarLabel;
    private JTextField idField;
    private AdminMainFrame adminMainFrame;
    private File file;
    private int departmentId;
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
    //选择头像
    avatarLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("E:/"));
            int result = fileChooser.showOpenDialog(rootPanel);
            if (result == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                icon.setImage(icon.getImage().getScaledInstance(165,165, Image.SCALE_DEFAULT));
                avatarLabel.setText("");
                avatarLabel.setIcon(icon);
            }
        }
    });
    新增Button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            RewardStudent rewardStudent = new RewardStudent();
            rewardStudent.setDepartmentName(departmentField.getText());
            rewardStudent.setClassId(classField.getText());
            rewardStudent.setId(idField.getText());
            rewardStudent.setStudentName(nameField.getText());
            rewardStudent.setReward(rewardField.getText());
            //file文件上传阿里云
            rewardStudent.setLogo(AliOSSUtil.ossUpload(file));
            int n = ServiceFactory.getRewardStudentServiceInstance().insertRewardStudent(rewardStudent);
            if (n == 1){
                JOptionPane.showMessageDialog(rootPanel,"新增成功");
                AddRewardFrame.this.dispose();
            }
        }
    });
}
}
