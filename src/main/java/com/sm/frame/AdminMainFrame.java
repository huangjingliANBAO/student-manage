package com.sm.frame;

import com.sm.entity.Admin;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import net.coobird.thumbnailator.Thumbnails;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private JPanel rewardPunishPanel;
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
    private JTextField stuAddressFiele;
    private JTextField stuPhoneFiele;
    private JLabel stuBirthLabel;
    private JLabel stuAvatarLabel;
    private JPanel stuTopPanel;
    private JButton 刷新数据Button1;
    private JButton 初始化数据Button;
    private JButton 编辑Button;
    private int departmentId = 0;


    public AdminMainFrame(Admin admin) {
        setContentPane(rootPanel);
        setTitle("学生管理系统");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showDepartments();
        //显示管理员
        this.admin = admin;
        adminLabel.setText("当前管理员：" + admin.getAdminName());
        Font font = new Font("微软雅黑", 0, 20);
        adminLabel.setFont(font);
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
                timeLabel.setText(a);
                timeLabel.setFont(font);
            }
        };
        timer = new Timer();
        timer.schedule(clockTask, 0, 1000);
        //设置需要的背景图
        rootPanel.setFileName("bg.jpg");
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
                infoPanel.setFileName("login.jpg");
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
                for (Department department : departmentList){
                    comboBox1.addItem(department);
                }
                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClassList){
                    comboBox2.addItem(cClass);
                }
                comboBox1.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            int index = comboBox1.getSelectedIndex();
                            if (index != 0){
                                departmentId = comboBox1.getItemAt(index).getId();
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartmentId(departmentId);
                                showStudentTable(studentList);
                                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(departmentId);
                                comboBox2.removeAllItems();
                                CClass tip = new CClass();
                                tip.setClassName("请选择班级");
                                comboBox2.addItem(tip);
                                for (CClass cClass : cClassList){
                                    comboBox2.addItem(cClass);
                                }
                                comboBox2.addItemListener(new ItemListener() {
                                    @Override
                                    public void itemStateChanged(ItemEvent e) {
                                       if (e.getStateChange() == ItemEvent.SELECTED){
                                           int index = comboBox2.getSelectedIndex();
                                           if (index != 0){
                                               int classId = comboBox2.getItemAt(index).getId();
                                               List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByClassId(classId);
                                               showStudentTable(studentList);
                                           }
                                       }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card4");
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
                fileChooser.setCurrentDirectory(new File("D:/image/"));
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
                fileChooser.setCurrentDirectory(new File("D:/image"));
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
                for (CClass cClass : cClassList){
                    comboBox2.addItem(cClass);
                }
            stuAvatarLabel.setText("<html><img src='https://huangjingli.oss-cn-beijing.aliyuncs.com/logo/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F1.png'/></html>");
                stuIdLabel.setText("未选择");
                stuDepLabel.setText("未选择");
                stuClassLabel.setText("未选择");
                stuNameLabel.setText("未选择");
                stuGenderLabel.setText("未选择");
                stuBirthLabel.setText("未选择");
                stuAddressFiele.setText("未选择");
                stuPhoneFiele.setText("");
                stuAddressFiele.setText("");
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
                if (studentList != null){
                    showStudentTable(studentList);
                }
            }
        });
    }

    private void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        //int len = departmentList.size();
        //int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        GridLayout gridLayout = new GridLayout(0, 4, 20, 20);
        contentPanel.setLayout(gridLayout);
        for (Department department : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(400, 420));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
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
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "' width=300 height=280/></html>");
            //图标标签加入院系面板
            depPanel.add(logoLabel);
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
            depPanel.setFileName("login.jpg");
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
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department : departmentList) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass : cClassList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName());
                group.add(node);
            }
        }
        final JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        treePanel.add(tree);
        treePanel.revalidate();
    }

    private void showStudentTable(List<StudentVO> studentList) {
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
        table.setBackground(new Color(200, 232, 61));
        //表格内容居中
        DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //表格加入滚动面板，水平垂直方向带滚动条
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablePanel.add(scrollPane);
        tablePanel.revalidate();
        //表格内容选择监听，点击一行，在右边显示这个学生信息
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                stuIdLabel.setText(table.getValueAt(row, 0).toString());
                stuDepLabel.setText(table.getValueAt(row, 1).toString());
                stuClassLabel.setText(table.getValueAt(row, 2).toString());
                stuNameLabel.setText(table.getValueAt(row, 3).toString());
                stuGenderLabel.setText(table.getValueAt(row, 4).toString());
                stuAddressFiele.setText(table.getValueAt(row, 5).toString());
                stuPhoneFiele.setText(table.getValueAt(row, 6).toString());
                stuBirthLabel.setText(table.getValueAt(row, 7).toString());
                stuAvatarLabel.setText("<html><img src='" + table.getValueAt(row, 8).toString() + "'/></html>");
                编辑Button.setVisible(true);
                编辑Button.setText("编辑");
            }
        });
    }
    public static void main(String[] args) throws Exception {
        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("111"));
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
    }
}