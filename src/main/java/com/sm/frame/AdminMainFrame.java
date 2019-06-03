package com.sm.frame;

import com.sm.entity.*;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import net.coobird.thumbnailator.Thumbnails;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class AdminMainFrame extends JFrame {
    private ImgPanel rootPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JButton 新增院系Button;
    private JButton 刷新数据Button;
    private JTextField depNameField;
    private JButton 选择logo图Button;
    private JButton 新增Button;
    private JPanel leftPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel contentPanel;
    private JLabel logoLabel;
    private JLabel adminLabel;
    private JLabel timeLabel;
    //private JComboBox depcomboBox;
    private JTextField textField1;
    private JButton 新增班级Button;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private String uploadFileUrl;
    private File file;
    private File toPic;
    private Admin admin;
    private TimerTask clockTask;
    private Timer timer;
    private JComboBox<Department> depcomboBox;
    private JComboBox<Department> comboBox1;
    private JComboBox<CClass> comboBox2;
    private JTextField searchField;
    private JButton 搜索Button;
    private JButton 新增学生Button;
    private JButton 批量导入Button;
    private ImgPanel infoPanel;
    private JPanel tablePanel;
    private JLabel stuIdLabel;
    private JLabel stuDepLabel;
    private JLabel stuClassLabel;
    private JLabel stuNameLabel;
    private JLabel stuGenderLabel;
    private JTextField stuAddressField;
    private JTextField stuPhoneField;
    private JLabel stuBirthLabel;
    private JLabel stuAvatarLabel;
    private JPanel stuTopPanel;
    private JButton 刷新数据Button1;
    private JButton 初始化数据Button;
    private JButton 编辑Button;
    private JLabel personalInformationLabel;
    private JPanel rewardPunishPanel;
    private JPanel rpLeftPanel;
    private JLabel rewardLabel;
    private JPanel rewardPanel;
    private JLabel insertRewardStudent;

    private int departmentId = 0;
    private int row;

    public AdminMainFrame() {
        setContentPane(rootPanel);
        setTitle("学生管理系统");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showDepartments();
        //显示管理员
        adminLabel.setText("欢迎您！管理员");
        Font font = new Font("微软雅黑", Font.BOLD, 20);
        adminLabel.setFont(font);
        Font font1 = new Font("微软雅黑", Font.BOLD, 22);
        personalInformationLabel.setFont(font1);
        //显示本地时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        clockTask = new TimerTask() {
            @Override
            public void run() {
                Date currentTime = new Date();
                String a = format.format(currentTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLabel.setText("今天是：" + a);
                timeLabel.setFont(font);
            }
        };
        timer = new Timer();
        timer.schedule(clockTask, 0, 1000);
        //设置需要的背景图
        rootPanel.setFileName("bg4.jpg");
        //组件重绘
        rootPanel.repaint();
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        院系管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card1");
            }
        });
        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card2");
                showClassPanel();
            }
        });
        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card3");
                infoPanel.setFileName("card.jpg");
                infoPanel.repaint();
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(studentList);
                Department tip1 = new Department();
                tip1.setDepartmentName("请选择院系");
                comboBox1.addItem(tip1);
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox2.addItem(tip2);
                List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
                for (Department department : departmentList) {
                    comboBox1.addItem(department);
                }
                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClassList) {
                    comboBox2.addItem(cClass);
                }
                comboBox1.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox1.getSelectedIndex();
                            if (index != 0) {
                                departmentId = comboBox1.getItemAt(index).getId();
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartmentId(departmentId);
                                showStudentTable(studentList);
                                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(departmentId);
                                comboBox2.removeAllItems();
                                CClass tip = new CClass();
                                tip.setClassName("请选择班级");
                                comboBox2.addItem(tip);
                                for (CClass cClass : cClassList) {
                                    comboBox2.addItem(cClass);
                                }
                            }
                        }
                    }
                });
            }
        });
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index = comboBox2.getSelectedIndex();
                    if (index != 0) {
                        int classId = comboBox2.getItemAt(index).getId();
                        List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByClassId(classId);
                        showStudentTable(studentList);
                    }
                }
            }
        });
        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card4");
                showReward();
            }
        });
        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == true) {
                    leftPanel.setVisible(false);
                } else {
                    leftPanel.setVisible(true);
                }
            }
        });
        刷新数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepartments();

            }
        });
        depNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depNameField.setText("");
            }
        });
        选择logo图Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("E:/java-study/image/"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    //缩略图
                    ((ImageIcon) icon).setImage(((ImageIcon) icon).getImage().getScaledInstance(200, 220, Image.SCALE_DEFAULT));
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    选择logo图Button.setVisible(false);
                }
            }
        });
        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //创建Department对象，并设置相应属性
                Department department = new Department();
                department.setDepartmentName(depNameField.getText().trim());
                department.setLogo(uploadFileUrl);
                //department.setDescription(descriptionArea.getText().trim());
                //调用service实现新增功能
                int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //新增成功后，将侧边栏隐藏
                    leftPanel.setVisible(false);
                    //刷新界面数据
                    showDepartments();
                    //将图片预览标签隐藏
                    logoLabel.setVisible(false);
                    //将选择图片的按钮可见
                    选择logo图Button.setVisible(true);
                    //清空文本框
                    depNameField.setText("");
                    // descriptionArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }

            }
        });
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("E:/java-study-file/image/"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    logoLabel.removeAll();
                    //选中文件，原图
                    file = fileChooser.getSelectedFile();
                    //指定缩略图大小
                    toPic = fileChooser.getSelectedFile();
                    try {
                        Thumbnails.of(file).size(200, 220).toFile(toPic);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(toPic.getAbsolutePath());
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                }
            }
        });
        //下拉框
        depcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = depcomboBox.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其id备用
                Department department = (Department) depcomboBox.getItemAt(index);
                departmentId = department.getId();
            }
        });
        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CClass cClass = new CClass();
                cClass.setClassName(textField1.getText().trim());
                cClass.setDepartmentId(departmentId);
                int n = ServiceFactory.getCClassServiceInstance().addClass(cClass);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增班级成功");
                    //将选择图片的按钮可见
                    新增Button.setVisible(true);
                    //清空文本框
                    textField1.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增班级失败");
                }
            }
        });
        初始化数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(studentList);
                comboBox1.setSelectedIndex(0);
                comboBox2.removeAllItems();
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClassList) {
                    comboBox2.addItem(cClass);
                }
                stuAvatarLabel.setText("<html><img src='https://huangjingli.oss-cn-beijing.aliyuncs.com/logo/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F1.png'/></html>");
                stuIdLabel.setText("未选择");
                stuDepLabel.setText("未选择");
                stuClassLabel.setText("未选择");
                stuNameLabel.setText("未选择");
                stuGenderLabel.setText("未选择");
                stuBirthLabel.setText("未选择");
                stuAddressField.setText("未选择");
                stuPhoneField.setText("");
                stuAddressField.setText("");
                searchField.setText("");
                编辑Button.setVisible(false);
                编辑Button.setText("保存");
            }
        });
        搜索Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = searchField.getText().trim();
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByKeywords(keywords);
                if (studentList != null) {
                    showStudentTable(studentList);
                }
            }
        });
        新增学生Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开新增学生界面
                new AddStudentFrame(AdminMainFrame.this);
                // AdminMainFrame.this.setEnabled(false);
            }
        });
        insertRewardStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddRewardFrame(AdminMainFrame.this);
            }
        });
    }
    public void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Map> departmentList = ServiceFactory.getDepartmentServiceInstance().selectDepartmentInfo();
        //int len = departmentList.size();
        //int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        GridLayout gridLayout = new GridLayout(0, 4, 20, 20);
        contentPanel.setLayout(gridLayout);
        for (Map map : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            Department department = (Department) map.get("department");
            int classCount = (int) map.get("classCount");
            int studentCount = (int) map.get("studentCount");
            depPanel.setPreferredSize(new Dimension(300, 300));
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            JLabel logoLabel = new JLabel("<html><img src ='" + department.getLogo() + "'/></html>");
            logoLabel.setPreferredSize(new Dimension(280, 280));
            depPanel.add(logoLabel);
            JLabel infoLabel = new JLabel("班级" + classCount + "个,学生" + studentCount + "人");
            depPanel.add(infoLabel);
            JButton delBtn = new JButton("删除");
            delBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int n = JOptionPane.showConfirmDialog(null, "确定要删除这行数据吗？", "删除警告",
                            JOptionPane.YES_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        contentPanel.remove(depPanel);
                        contentPanel.repaint();
                        ServiceFactory.getDepartmentServiceInstance().deleteDepartment(department.getId());
                    }
                }
            });
            delBtn.setPreferredSize(new Dimension(100, 50));
            depPanel.add(delBtn);
            //院系面板加入主体内容面板
            contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();
        }
    }

    private void showComboBox(List<Department> departmentList) {
        for (Department department : departmentList) {
            depcomboBox.addItem(department);
        }
    }

    private void showClassPanel() {
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showComboBox(departmentList);
        showTree(departmentList);
        showClasses(departmentList);
    }

    private void showClasses(List<Department> departmentList) {
        classContentPanel.removeAll();
        //右侧流式布局显示
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 20);
        for (Department department : departmentList) {
            ImgPanel depPanel = new ImgPanel();
            depPanel.setFileName("list.jpg");
            depPanel.repaint();
            depPanel.setPreferredSize(new Dimension(350, 500));
            depPanel.setLayout(null);
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setBounds(130, 15, 200, 30);
            //获得这个院系的所有班级
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            //数据模型
            DefaultListModel listModel = new DefaultListModel();
            //遍历班级集合，依次加入数据模型
            for (CClass cClass : cClassList) {
                listModel.addElement(cClass);
            }
            JList<CClass> jList = new JList<>(listModel);
            //JList加入滚动面板
            JScrollPane listScrollPanel = new JScrollPane(jList);
            listScrollPanel.setBounds(90, 120, 200, 260);
            depPanel.add(depNameLabel);
            depPanel.add(listScrollPanel);
            classContentPanel.add(depPanel);

            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("修改");
            JMenuItem item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);
            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //选中项的下标
                    int index = jList.getSelectedIndex();
                    //点击鼠标右键
                    if (e.getButton() == 3) {
                        //在鼠标位置弹出菜单
                        jPopupMenu.show(jList, e.getX(), e.getY());
                        //取出选中数据
                        CClass cClass = jList.getModel().getElementAt(index);
                        //对删除菜单项添加监听
                        item2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel, "删除？");
                                if (choice == 0) {
                                    ServiceFactory.getCClassServiceInstance().deleteClassById(cClass.getId());
                                    listModel.remove(index);
                                    showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                                }
                            }
                        });
                    }
                }
            });

        }
    }

    private void showTree(List<Department> departmentList) {
        treePanel.removeAll();
        //左侧树形结构根节点
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department : departmentList) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass : cClassList) {
                int num = ServiceFactory.getStudentServiceInstance().countStudentByClassId(cClass.getId());
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName() + "（" + num + "人)");
                group.add(node);
            }
        }
        final JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        treePanel.add(tree);
        treePanel.revalidate();
    }

    public void showStudentTable(List<StudentVO> studentList) {
        tablePanel.removeAll();
        //获得所有学生视图数据
        //创建表格
        JTable table = new JTable();
        //表格数据模型
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //表头内容
        model.setColumnIdentifiers(new String[]{"学号", "院系", "班级", "姓名", "性别", "地址", "手机号", "出生日期", "头像"});
        //遍历List 转成Object数组
        for (StudentVO student : studentList) {
            Object[] object = new Object[]{student.getId(), student.getDepartmentName(), student.getClassName(),
                    student.getStudentName(), student.getGender(), student.getAddress(), student.getPhone(),
                    student.getBirthday(), student.getAvatar()};
            model.addRow(object);
        }
        //将最后一列隐藏像地址不显示在表格中
        TableColumn tc = table.getColumnModel().getColumn(8);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        //获得表头
        JTableHeader head = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        //设置表头大小
        head.setPreferredSize(new Dimension(head.getWidth(), 40));
        //设置表头字体
        head.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        //设置表格行高
        table.setRowHeight(35);
        //表格背景色
        table.setBackground(new Color(246, 154, 181));
        //表格内容居中
        DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //表格加入滚动面板，水平垂直方向带滚动条
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablePanel.add(scrollPane);
        tablePanel.revalidate();
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        jPopupMenu.add(item);
        table.add(jPopupMenu);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                stuIdLabel.setText(table.getValueAt(row, 0).toString());
                stuDepLabel.setText(table.getValueAt(row, 1).toString());
                stuClassLabel.setText(table.getValueAt(row, 2).toString());
                stuNameLabel.setText(table.getValueAt(row, 3).toString());
                stuGenderLabel.setText(table.getValueAt(row, 4).toString());
                stuAddressField.setText(table.getValueAt(row, 5).toString());
                stuPhoneField.setText(table.getValueAt(row, 6).toString());
                stuBirthLabel.setText(table.getValueAt(row, 7).toString());
                stuAvatarLabel.setText("<html><img src='" + table.getValueAt(row, 8).toString() + "'/></html>");
                //设置字体大小
                Font font = new Font("微软雅黑", Font.BOLD, 18);
                stuIdLabel.setFont(font);
                stuDepLabel.setFont(font);
                stuClassLabel.setFont(font);
                stuNameLabel.setFont(font);
                stuGenderLabel.setFont(font);
                stuAddressField.setFont(font);
                stuPhoneField.setFont(font);
                stuBirthLabel.setFont(font);
                编辑Button.setVisible(true);
                编辑Button.setText("编辑");
                编辑Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("编辑")) {
                            stuAddressField.setEditable(true);
                            stuAddressField.setEditable(true);
                            stuPhoneField.setEditable(true);
                            stuPhoneField.setEditable(true);
                            编辑Button.setText("保存");
                        }
                        if (e.getActionCommand().equals("保存")) {
                            Student student = new Student();
                            student.setId(stuIdLabel.getText());
                            student.setAddress(stuAddressField.getText());
                            student.setPhone(stuPhoneField.getText());
                            int n = ServiceFactory.getStudentServiceInstance().updateStudent(student);
                            if (n == 1) {
                                model.setValueAt(stuAddressField.getText(), row, 5);
                                model.setValueAt(stuPhoneField.getText(), row, 6);
                                stuAddressField.setEditable(false);
                                stuAddressField.setEditable(false);
                                stuPhoneField.setEditable(false);
                                stuPhoneField.setEditable(false);
                                编辑Button.setText("编辑");
                            }
                        }
                    }
                });
                if (e.getButton() == 3) {
                    jPopupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) table.getValueAt(row, 0);
                int choice = JOptionPane.showConfirmDialog(tablePanel, "确定删除吗？");
                if (choice == 0) {
                    model.removeRow(row);
                }
                ServiceFactory.getStudentServiceInstance().deleteById(id);
            }
        });
    }

    public void showReward() {
        rewardPanel.removeAll();
        List<RewardStudent> rewardStudentList = ServiceFactory.getRewardStudentServiceInstance().getAll();
        GridLayout gridLayout1 = new GridLayout(0, 3, 15, 15);
        rewardPanel.setLayout(gridLayout1);
        for (RewardStudent rewardStudent : rewardStudentList) {
            JPanel rsPanel = new JPanel();
            rsPanel.setPreferredSize(new Dimension(320, 360));
            JLabel dpLabel = new JLabel("院系：" + rewardStudent.getDepartmentName());
            JLabel classIdLabel = new JLabel("班级：" + rewardStudent.getClassId());
            JLabel idLabel = new JLabel("学号：" + rewardStudent.getId());
            JLabel snLabel = new JLabel("姓名：" + rewardStudent.getStudentName());
            JLabel rewardLabel = new JLabel("荣获2019年:" + rewardStudent.getReward());
            JLabel logoLabel = new JLabel("<html><img src = '" + rewardStudent.getLogo() + "' width=300 height=300 /></html>");
            rsPanel.add(dpLabel);
            rsPanel.add(classIdLabel);
            rsPanel.add(idLabel);
            rsPanel.add(snLabel);
            rsPanel.add(rewardLabel);
            rsPanel.add(logoLabel);
            rewardPanel.add(rsPanel);
            rewardPanel.revalidate();
        }
    }

    public static void main(String[] args) throws Exception {
        new AdminMainFrame();
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
    }
}