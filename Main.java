package com.company;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

public class Main {

    public static Vector createHeaders(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector columnNames = new Vector();

        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        return columnNames;
    }
    public static Vector createRow(ResultSet rs) throws SQLException {
        Vector data = new Vector();
        ResultSetMetaData metaData = rs.getMetaData();
        while (rs.next()) {
            Vector row = new Vector();
            int columnCount = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(rs.getObject(columnIndex));
            }
            data.add(row);
        }
        return data;
    }
    /*************************************/
    public static Vector createHeadersTeachers(ResultSet rs4) throws SQLException {
        ResultSetMetaData metaData2 = rs4.getMetaData();
        Vector columnNamesTeachers = new Vector();

        int columnCount = metaData2.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNamesTeachers.add(metaData2.getColumnName(column));
        }
        return columnNamesTeachers;
    }
    public static Vector createRowTeacher(ResultSet rs4) throws SQLException {
        Vector data = new Vector();
        ResultSetMetaData metaData2 = rs4.getMetaData();
        while (rs4.next()) {
            Vector rowDataTeacher = new Vector();
            int columnCount = metaData2.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowDataTeacher.add(rs4.getObject(columnIndex));
            }
            data.add(rowDataTeacher);
        }
        return data;
    }
    /*********************************************************/
    public static Vector createHeaderLessons(ResultSet rs2) throws  SQLException {
        ResultSetMetaData metaData3 = rs2.getMetaData();
        Vector columnLessons = new Vector();

        int columnCount = metaData3.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnLessons.add(metaData3.getColumnName(column));
        }
        return columnLessons;
    }
    public static Vector createRowLessons (ResultSet rs2) throws SQLException {
        Vector dataLessons = new Vector();
        ResultSetMetaData metaData3 = rs2.getMetaData();
        while (rs2.next()) {
            Vector rowDataLessons = new Vector();
            int columnCount = metaData3.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowDataLessons.add(rs2.getObject(columnIndex));
            }
            dataLessons.add(rowDataLessons);
        }
        return dataLessons;
    }

    public static void main(String[] args)  {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);

                try {
                    UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel");
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                JFrame w = new JFrame("Skolas datu bāze");
                //Container pane = w.getContentPane();
                //Container pane2 = w.getContentPane();
                JPanel pane = new JPanel(null);
                JPanel pane2 = new JPanel(null);
                JPanel pane3 = new JPanel(null);
                JPanel pane4 = new JPanel(null);
                pane.setLayout(null);
                pane2.setLayout(null);
                pane3.setLayout(null);
                pane4.setLayout(null);


                JMenuBar menuBar = new JMenuBar();
                JMenu fileMenu = new JMenu("Fails");
                JMenu settingMenu = new JMenu("Līdzekļi");
                final JMenu helpMenu = new JMenu("Palidzība");

                URL fileIconUrl = Main.class.getResource("/resources/file.png");
                ImageIcon fileIcon = new ImageIcon(fileIconUrl);
                fileMenu.setIcon(fileIcon);
                URL settingsIconURL = Main.class.getResource("/resources/settings.png");
                ImageIcon settingsIcon = new ImageIcon(settingsIconURL);
                settingMenu.setIcon(settingsIcon);
                URL infoIconURL = Main.class.getResource("/resources/info.png");
                ImageIcon infoIcon = new ImageIcon(infoIconURL);
                helpMenu.setIcon(infoIcon);

                JMenuItem saveMenuItem = new JMenuItem("Saglabāt kā fails");
                fileMenu.add(saveMenuItem);
                JMenuItem loadDbMenuItem = new JMenuItem("Atvert datu bāze TSV");
                fileMenu.add(loadDbMenuItem);
                fileMenu.add(settingMenu);

                fileMenu.addSeparator();

                JMenuItem exit = new JMenuItem("Iziet");
                fileMenu.add(exit);

                JMenuItem aboutProgram = new JMenuItem("Par programmu");
                helpMenu.add(aboutProgram);
                JMenuItem programContactAuthor = new JMenuItem("Iegūst palidzību");
                helpMenu.add(programContactAuthor);

                JMenuItem newUserMenuItem = new JMenuItem("Pievienot studentu");
                settingMenu.add(newUserMenuItem);
                final JMenuItem editUserMenuItem = new JMenuItem("Rediģēt studentu");
                settingMenu.add(editUserMenuItem);
                settingMenu.addSeparator();
                JMenuItem newTeacherMenuItem = new JMenuItem("Pievienot skolotāju");
                settingMenu.add(newTeacherMenuItem);
                final JMenuItem editTeacherMenuItem = new JMenuItem("Rediģēt skolotāju");
                settingMenu.add(editTeacherMenuItem);
                settingMenu.addSeparator();
                JMenuItem newUserLessonMenuItem = new JMenuItem("Pievienot priekšmetu");
                settingMenu.add(newUserLessonMenuItem);
                settingMenu.addSeparator();
                JMenuItem newUserScoreMenuItem = new JMenuItem("Pievienot atzīmi");
                settingMenu.add(newUserScoreMenuItem);

                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                menuBar.add(fileMenu);
                menuBar.add(settingMenu);
                menuBar.add(helpMenu);

                Connection conn = null;
                Statement stmt = null;
                Statement stmt2 = null;
                Statement stmt3 = null;
                Statement stmt4 = null;
                Statement stmt8 = null;

                //SQL komanda tabula izveidošanai
                String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Students(id INT(3) NOT NULL  AUTO_INCREMENT PRIMARY KEY,"
                        + "First_Name varchar(20) NOT NULL,"
                        + "Last_Name varchar(20) NOT NULL,"
                        + "Sex varchar(20) NOT NULL,"
                        + "Age int(3) NOT NULL,"
                        + "Person_Code varchar(20) NOT NULL,"
                        + "Student_Group varchar(40) NOT NULL,"
                        + "Phone VARCHAR (40) NOT NULL,"
                        + "Email VARCHAR (40) NOT NULL)";

                String sqlDataToTable = "SELECT * FROM Students";

                String sqlCreateTable2 = "CREATE TABLE IF NOT EXISTS Lessons(id INT(3) NOT NULL  AUTO_INCREMENT PRIMARY KEY,"
                        + "Lesson_Name VARCHAR (20) NOT NULL,"
                        + "Course VARCHAR (3) NOT NULL,"
                        + "Teacher VARCHAR (20) NOT NULL)";

                String sqlDataToTable2 = "SELECT * FROM Lessons";

                String sqlCreateTable3 = "CREATE TABLE IF NOT EXISTS Evaluations(id INT(3) NOT NULL  AUTO_INCREMENT PRIMARY KEY,"
                        + "Student varchar(20) NOT NULL,"
                        + "Lesson varchar(20) NOT NULL,"
                        + "Evaluation INT(3) NOT NULL)";

                String sqlDataToTable3 = "SELECT * FROM Evaluations";

                String sqlCreateTable4 = "CREATE TABLE IF NOT EXISTS Teachers(id INT(3) NOT NULL  AUTO_INCREMENT PRIMARY KEY,"
                        + "First_Name varchar(20) NOT NULL,"
                        + "Last_Name varchar(20) NOT NULL,"
                        + "Sex varchar(20) NOT NULL,"
                        + "Age int(3) NOT NULL,"
                        + "Person_Code varchar(20) NOT NULL,"
                        + "Phone VARCHAR (40) NOT NULL,"
                        + "Email VARCHAR (40) NOT NULL)";

                String sqlDataToTable4 = "SELECT * FROM Teachers";

                //Studenti
                String sex[] = {"Vīrietis", "Sieviete"};
                String group[] = {"P1", "P2", "P3", "P4", "A1", "A2", "A3", "A4"};
                JTextField nameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JComboBox sexComboBox = new JComboBox(sex);
                NumberFormat numberFormat = NumberFormat.getIntegerInstance();
                numberFormat.setGroupingUsed(false);
                JFormattedTextField ageField = new JFormattedTextField(numberFormat);
                JTextField personCodeField = new JTextField();
                JComboBox groupComboBox = new JComboBox(group);
                JTextField emailField = new JTextField();
                JFormattedTextField phoneField = null;

                try {
                    MaskFormatter mfPhone = new MaskFormatter("+371 ###-###-##");
                    mfPhone.setPlaceholderCharacter('_');
                    phoneField = new JFormattedTextField(mfPhone);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(w, "Vecums ir ievadīts nepareizi!", "Kļūda", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }

                JPanel addRecordFormPanel = new JPanel(new GridLayout(0, 1));
                addRecordFormPanel.setPreferredSize(new Dimension(280, 400));
                addRecordFormPanel.add(new JLabel("Vārds: "));
                addRecordFormPanel.add(nameField);
                addRecordFormPanel.add(new JLabel("Uzvārds: "));
                addRecordFormPanel.add(lastNameField);
                addRecordFormPanel.add(new JLabel("Dzimums: "));
                addRecordFormPanel.add(sexComboBox);
                addRecordFormPanel.add(new JLabel("Vecums: "));
                addRecordFormPanel.add(ageField);
                addRecordFormPanel.add(new JLabel("Personas kods"));
                addRecordFormPanel.add(personCodeField);
                addRecordFormPanel.add(new JLabel("Kurss: "));
                addRecordFormPanel.add(groupComboBox);
                addRecordFormPanel.add(new JLabel("Mob. tālrunis: "));
                addRecordFormPanel.add(phoneField);
                addRecordFormPanel.add(new JLabel("E-pasts: "));
                addRecordFormPanel.add(emailField);

                String sqlInsertIntoTable = "INSERT INTO Students (ID, First_Name, Last_Name, Sex, Age, Person_Code, Student_Group, Phone, Email)"
                        + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";

                String sqlInsertIntoTableTeacher = "INSERT INTO Teachers (ID, First_Name, Last_Name, Sex, Age, Person_Code, Phone, Email)"
                        + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";

                String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                String DB_URL = "jdbc:mysql://173.212.192.223:3306/studentdb?useUnicode=true&characterEncoding=utf-8";
                String USER = "studentdb";
                String PASS = "J8a2B2a2";

                try {
                    Class.forName(JDBC_DRIVER);
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    stmt = conn.createStatement();
                    stmt2 = conn.createStatement();
                    stmt4 = conn.createStatement();
                    stmt8 = conn.createStatement();
                    stmt.execute(sqlCreateTable);
                    stmt2.execute(sqlCreateTable2);
                    stmt4.execute(sqlCreateTable4);
                    stmt8.execute(sqlCreateTable3);

                    ResultSet rs = stmt.executeQuery(sqlDataToTable);
                    ResultSet rs2 = stmt2.executeQuery(sqlDataToTable2);
                    //ResultSet rs3 = stmt8.executeQuery(sqlCreateTable3);
                    ResultSet rs4 = stmt4.executeQuery(sqlDataToTable4);

                    Vector columnNames = createHeaders(rs);
                    Vector rowData = createRow(rs);
                    JTable table = new JTable(rowData, columnNames);
                    DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    table.setModel(tableModel);

                    Vector columnNamesTeacher = createHeadersTeachers(rs4);
                    Vector rowDataTeacher = createRowTeacher(rs4);
                    JTable tableTeachers = new JTable(rowDataTeacher, columnNamesTeacher);
                    DefaultTableModel tableTeacherModel = new DefaultTableModel(rowDataTeacher, columnNamesTeacher) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    tableTeachers.setModel(tableTeacherModel);

                    Vector columnLessons = createHeaderLessons(rs2);
                    Vector rowDataLessons = createRowLessons(rs2);
                    JTable lessonsTable = new JTable(rowDataLessons, columnLessons);
                    DefaultTableModel lessonsTableModel = new DefaultTableModel(rowDataLessons, columnLessons) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    lessonsTable.setModel(lessonsTableModel);

                    JScrollPane tableScrollPane = new JScrollPane(table);
                    pane.add(tableScrollPane);
                    tableScrollPane.setBounds(10, 20, 900, 400);
                    tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    JScrollPane tableTeachersScrollPane = new JScrollPane(tableTeachers);
                    pane2.add(tableTeachersScrollPane);
                    tableTeachersScrollPane.setBounds(10, 20, 900, 400);
                    tableTeachersScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    JScrollPane lessonsTableScrollPane = new JScrollPane(lessonsTable);
                    pane3.add(lessonsTableScrollPane);
                    lessonsTableScrollPane.setBounds(10, 20, 900, 400);
                    lessonsTableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    JTableHeader tableHeader = table.getTableHeader();
                    TableColumnModel tcm = tableHeader.getColumnModel();
                    TableColumn idColumn = tcm.getColumn(0);
                    TableColumn nameColumn = tcm.getColumn(1);
                    TableColumn lastNameColumn = tcm.getColumn(2);
                    TableColumn sexColumn = tcm.getColumn(3);
                    TableColumn ageColumn = tcm.getColumn(4);
                    TableColumn personCodeColumn = tcm.getColumn(5);
                    TableColumn groupColumn = tcm.getColumn(6);
                    TableColumn emailColumn = tcm.getColumn(8);
                    TableColumn phoneColumn = tcm.getColumn(7);
                    idColumn.setHeaderValue("Nr.");
                    nameColumn.setHeaderValue("Vārds");
                    lastNameColumn.setHeaderValue("Uzvārds");
                    sexColumn.setHeaderValue("Dzimums");
                    ageColumn.setHeaderValue("Vecums");
                    personCodeColumn.setHeaderValue("Personas kods");
                    groupColumn.setHeaderValue("Kurss");
                    emailColumn.setHeaderValue("E-pasts");
                    phoneColumn.setHeaderValue("Mob. tālrunis");
                    tcm.removeColumn(idColumn);
                    tableHeader.repaint();

                    ageColumn.setPreferredWidth(25);
                    sexColumn.setPreferredWidth(25);
                    personCodeColumn.setPreferredWidth(85);
                    groupColumn.setPreferredWidth(15);

                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                    idColumn.setCellRenderer(centerRenderer);
                    nameColumn.setCellRenderer(centerRenderer);
                    lastNameColumn.setCellRenderer(centerRenderer);
                    sexColumn.setCellRenderer(centerRenderer);
                    ageColumn.setCellRenderer(centerRenderer);
                    personCodeColumn.setCellRenderer(centerRenderer);
                    groupColumn.setCellRenderer(centerRenderer);
                    phoneColumn.setCellRenderer(centerRenderer);
                    emailColumn.setCellRenderer(centerRenderer);

                    ListSelectionModel selectionModel = table.getSelectionModel();
                    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    Font font = tableHeader.getFont();
                    table.setRowHeight(30);
                    tableHeader.setFont(new Font(String.valueOf(font), Font.BOLD, 12));

                    TableRowSorter sorter = new TableRowSorter(tableModel);
                    table.setRowSorter(sorter);

                    /********************************************/
                    JTableHeader tableHeaderTeachers = tableTeachers.getTableHeader();
                    TableColumnModel tcmT = tableHeaderTeachers.getColumnModel();
                    TableColumn idColumnTeachers = tcmT.getColumn(0);
                    TableColumn nameColumnTeachers = tcmT.getColumn(1);
                    TableColumn lastNameColumnTeachers = tcmT.getColumn(2);
                    TableColumn sexColumnTeachers = tcmT.getColumn(3);
                    TableColumn ageColumnTeachers = tcmT.getColumn(4);
                    TableColumn personCodeColumnTeachers = tcmT.getColumn(5);
                    TableColumn phoneColumnTeachers = tcmT.getColumn(6);
                    TableColumn emailColumnTeachers = tcmT.getColumn(7);

                    idColumnTeachers.setHeaderValue("Nr.");
                    nameColumnTeachers.setHeaderValue("Vārds");
                    lastNameColumnTeachers.setHeaderValue("Uzvārds");
                    sexColumnTeachers.setHeaderValue("Dzimums");
                    ageColumnTeachers.setHeaderValue("Vecums");
                    personCodeColumnTeachers.setHeaderValue("Personas kods");
                    emailColumnTeachers.setHeaderValue("E-pasts");
                    phoneColumnTeachers.setHeaderValue("Mob. tālrunis");
                    tcmT.removeColumn(idColumnTeachers);
                    tableHeaderTeachers.repaint();

                    ageColumnTeachers.setPreferredWidth(25);
                    sexColumnTeachers.setPreferredWidth(25);
                    personCodeColumnTeachers.setPreferredWidth(85);

                    DefaultTableCellRenderer centerRenderer4 = new DefaultTableCellRenderer();
                    centerRenderer4.setHorizontalAlignment(SwingConstants.CENTER);
                    idColumnTeachers.setCellRenderer(centerRenderer4);
                    nameColumnTeachers.setCellRenderer(centerRenderer4);
                    lastNameColumnTeachers.setCellRenderer(centerRenderer4);
                    sexColumnTeachers.setCellRenderer(centerRenderer4);
                    ageColumnTeachers.setCellRenderer(centerRenderer4);
                    personCodeColumnTeachers.setCellRenderer(centerRenderer4);
                    phoneColumnTeachers.setCellRenderer(centerRenderer4);
                    emailColumnTeachers.setCellRenderer(centerRenderer4);

                    ListSelectionModel selectionModel4 = tableTeachers.getSelectionModel();
                    selectionModel4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    Font font4 = tableHeaderTeachers.getFont();
                    tableTeachers.setRowHeight(30);
                    tableHeaderTeachers.setFont(new Font(String.valueOf(font4), Font.BOLD, 12));

                    TableRowSorter sorterTeacher = new TableRowSorter(tableTeacherModel);
                    tableTeachers.setRowSorter(sorterTeacher);

                    /*********************************************/
                    JTableHeader lessonsHeader = lessonsTable.getTableHeader();
                    TableColumnModel tcmL = lessonsHeader.getColumnModel();
                    TableColumn idColumnLesson = tcmL.getColumn(0);
                    TableColumn lessonNameColumn = tcmL.getColumn(1);
                    TableColumn lessonGroupColumn = tcmL.getColumn(2);
                    TableColumn teacherNameColumn = tcmL.getColumn(3);

                    idColumnLesson.setHeaderValue("Nr.");
                    lessonNameColumn.setHeaderValue("Priekšmēta nosaukums");
                    lessonGroupColumn.setHeaderValue("Kurss");
                    teacherNameColumn.setHeaderValue("Skolotājs/Skolotāja");
                    tcmL.removeColumn(idColumnLesson);
                    lessonsHeader.repaint();

                    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                    cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                    lessonNameColumn.setCellRenderer(cellRenderer);
                    lessonGroupColumn.setCellRenderer(cellRenderer);
                    teacherNameColumn.setCellRenderer(cellRenderer);

                    ListSelectionModel selectionModel1 = lessonsTable.getSelectionModel();
                    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    Font font1 = lessonsHeader.getFont();
                    lessonsTable.setRowHeight(30);
                    lessonsHeader.setFont(new Font(String.valueOf(font1), Font.BOLD, 12));
                    /********************************************/

                    JTabbedPane stPane = new JTabbedPane();
                    w.add(stPane);
                    stPane.setBounds(10, 20, 920, 420);

                    stPane.add("Studenti", pane);
                    stPane.add("Skolotāji", pane2);
                    stPane.add("Priekšmēti", pane3);

                    JButton addStudentButton = new JButton("Jauns students");
                    pane.add(addStudentButton);
                    addStudentButton.setBounds(230, 450, 130, 25);
                    URL newStudentIconURL = Main.class.getResource("/resources/new-student.png");
                    ImageIcon newStudentIconButton = new ImageIcon(newStudentIconURL);
                    addStudentButton.setIcon(newStudentIconButton);

                    JButton showSqlDataButton = new JButton("Rediģēt");
                    pane.add(showSqlDataButton);
                    showSqlDataButton.setBounds(370, 450, 130, 25);
                    URL editStudentIconURL = Main.class.getResource("/resources/edit-student.png");
                    ImageIcon editStudentIcon = new ImageIcon(editStudentIconURL);
                    showSqlDataButton.setIcon(editStudentIcon);

                    JButton deleteStudentButton = new JButton("Dzēst studentu");
                    pane.add(deleteStudentButton);
                    deleteStudentButton.setBounds(510, 450, 130, 25);
                    URL removeStudentIconURL = Main.class.getResource("/resources/remove.png");
                    ImageIcon removeStudentIcon = new ImageIcon(removeStudentIconURL);
                    deleteStudentButton.setIcon(removeStudentIcon);

                    JButton studentsContactsShowButton = new JButton("Kontakti");
                    pane.add(studentsContactsShowButton);
                    studentsContactsShowButton.setBounds(650, 450, 130, 25);
                    URL showStudentsContactIconURL = Main.class.getResource("/resources/student-contacts.png");
                    ImageIcon showStudentContactsIcon = new ImageIcon(showStudentsContactIconURL);
                    studentsContactsShowButton.setIcon(showStudentContactsIcon);

                    /***************************************/
                    JButton addTeacherButton = new JButton("Jauns skolotājs");
                    pane2.add(addTeacherButton);
                    addTeacherButton.setBounds(230, 450, 130, 25);
                    URL newTeacherIconURL = Main.class.getResource("/resources/new-student.png");
                    ImageIcon newTeacherIconButton = new ImageIcon(newTeacherIconURL);
                    addTeacherButton.setIcon(newTeacherIconButton);

                    JButton editTeacherButton = new JButton("Rediģēt skolotāja datus");
                    pane2.add(editTeacherButton);
                    editTeacherButton.setBounds(370, 450, 160, 25);
                    URL editTeacherIconURL = Main.class.getResource("/resources/edit-student.png");
                    ImageIcon editTeacherIcon = new ImageIcon(editTeacherIconURL);
                    editTeacherButton.setIcon(editTeacherIcon);

                    JButton deleteTeacherButton = new JButton("Dzēst skolotāju");
                    pane2.add(deleteTeacherButton);
                    deleteTeacherButton.setBounds(540, 450, 130, 25);
                    URL removeTeacherIconURL = Main.class.getResource("/resources/remove.png");
                    ImageIcon removeTeacherIcon = new ImageIcon(removeTeacherIconURL);
                    deleteTeacherButton.setIcon(removeTeacherIcon);

                    JButton teacherContactsShowButton = new JButton("Kontakti");
                    pane2.add(teacherContactsShowButton);
                    teacherContactsShowButton.setBounds(680, 450, 130, 25);
                    URL showTeacherContactIconURL = Main.class.getResource("/resources/student-contacts.png");
                    ImageIcon showTeacherContactsIcon = new ImageIcon(showTeacherContactIconURL);
                    teacherContactsShowButton.setIcon(showTeacherContactsIcon);

                    /**************************************/
                    JButton addLessonButton = new JButton("Jauns priekšmēts");
                    pane3.add(addLessonButton);
                    addLessonButton.setBounds(230, 450, 130, 25);
                    URL newLessonIconURL = Main.class.getResource("/resources/new-student.png");
                    ImageIcon newLessonIconButton = new ImageIcon(newLessonIconURL);
                    addLessonButton.setIcon(newLessonIconButton);
                    /**************************************/

                    Placeholder searchField = new Placeholder();
                    pane.add(searchField);
                    searchField.setBounds(10, 450, 200, 30);

                    Placeholder searchFieldTeacher = new Placeholder();
                    pane2.add(searchFieldTeacher);
                    searchFieldTeacher.setBounds(10, 450, 200, 30);

                    Placeholder searchFieldLesson = new Placeholder();
                    pane3.add(searchFieldLesson);
                    searchFieldLesson.setBounds(10, 450, 200, 30);

                    Connection finalConn = conn;
                    JFormattedTextField finalPhoneField = phoneField;
                    ActionListener newRecord = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final String[] option = {"Pievienot", "Atcelt"};
                            URL studentIconURL = Main.class.getResource("/resources/student.png");
                            ImageIcon studentIcon = new ImageIcon(studentIconURL);
                            int n = JOptionPane.showOptionDialog(w, addRecordFormPanel, "Studenta pievienošana", JOptionPane.PLAIN_MESSAGE, 0, studentIcon, option, option[1]);
                            int h = table.getRowCount() + 1;
                            if (n == 0 && nameField.getText().isEmpty() | lastNameField.getText().isEmpty() | ageField.getText().isEmpty() | personCodeField.getText().isEmpty() | emailField.getText().isEmpty()) {
                                while (true) {
                                    JOptionPane.showMessageDialog(w, "Lūdzu, ievadiet datus!", "Kļūda", JOptionPane.WARNING_MESSAGE);
                                    JOptionPane newOP = new JOptionPane();
                                    int o = newOP.showOptionDialog(w, addRecordFormPanel, "Studenta pievienošana", JOptionPane.PLAIN_MESSAGE, 0, studentIcon, option, option[1]);
                                    if (o == 1) {
                                        break;
                                    }
                                    if (!nameField.getText().isEmpty() | !lastNameField.getText().isEmpty() | !ageField.getText().isEmpty() | !personCodeField.getText().isEmpty() | !emailField.getText().isEmpty()) {
                                        try {
                                            PreparedStatement preparedStmt = finalConn.prepareStatement(sqlInsertIntoTable);
                                            //preparedStmt.setInt(1, 8);
                                            preparedStmt.setString(1, nameField.getText());
                                            preparedStmt.setString(2, lastNameField.getText());
                                            preparedStmt.setString(3, (String) sexComboBox.getSelectedItem());
                                            preparedStmt.setInt(4, Integer.parseInt(ageField.getText()));
                                            preparedStmt.setString(5, personCodeField.getText());
                                            preparedStmt.setString(6, (String) groupComboBox.getSelectedItem());
                                            preparedStmt.setString(7, emailField.getText());
                                            preparedStmt.setString(8, finalPhoneField.getText());
                                            preparedStmt.execute();
                                            //finalConn.close();
                                        } catch (SQLException e1) {
                                            e1.printStackTrace();
                                        }
                                        tableModel.addRow(new Object[]{h,
                                                nameField.getText(),
                                                lastNameField.getText(),
                                                sexComboBox.getSelectedItem(),
                                                ageField.getText(),
                                                personCodeField.getText(),
                                                groupComboBox.getSelectedItem(),
                                                finalPhoneField.getText(),
                                                emailField.getText()
                                        });
                                        tableModel.fireTableDataChanged();
                                        break;
                                    }
                                }
                                nameField.setText(null);
                                lastNameField.setText(null);
                                ageField.setText(null);
                                sexComboBox.setSelectedItem(null);
                                personCodeField.setText(null);
                                groupComboBox.setSelectedItem(null);
                                finalPhoneField.setText(null);
                                emailField.setText(null);
                            }
                            if (n == 0 && !nameField.getText().isEmpty() | !lastNameField.getText().isEmpty() | !ageField.getText().isEmpty() | !personCodeField.getText().isEmpty() | !emailField.getText().isEmpty()) {
                                try {
                                    PreparedStatement preparedStmt = finalConn.prepareStatement(sqlInsertIntoTable);
                                    //preparedStmt.setInt(1, 8);
                                    preparedStmt.setString(1, nameField.getText());
                                    preparedStmt.setString(2, lastNameField.getText());
                                    preparedStmt.setString(3, (String) sexComboBox.getSelectedItem());
                                    preparedStmt.setInt(4, Integer.parseInt(ageField.getText()));
                                    preparedStmt.setString(5, personCodeField.getText());
                                    preparedStmt.setString(6, (String) groupComboBox.getSelectedItem());
                                    preparedStmt.setString(7, finalPhoneField.getText());
                                    preparedStmt.setString(8, emailField.getText());
                                    preparedStmt.execute();
                                    //finalConn.close();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                                tableModel.addRow(new Object[]{h,
                                        nameField.getText(),
                                        lastNameField.getText(),
                                        sexComboBox.getSelectedItem(),
                                        ageField.getText(),
                                        personCodeField.getText(),
                                        groupComboBox.getSelectedItem(),
                                        finalPhoneField.getText(),
                                        emailField.getText()
                                });
                                tableModel.fireTableDataChanged();
                                nameField.setText(null);
                                lastNameField.setText(null);
                                ageField.setText(null);
                                sexComboBox.setSelectedItem(null);
                                personCodeField.setText(null);
                                groupComboBox.setSelectedItem(null);
                                finalPhoneField.setText(null);
                                emailField.setText(null);

                            } else {
                                nameField.setText(null);
                                lastNameField.setText(null);
                                ageField.setText(null);
                                sexComboBox.setSelectedItem(null);
                                personCodeField.setText(null);
                                groupComboBox.setSelectedItem(null);
                                finalPhoneField.setText(null);
                                emailField.setText(null);
                            }

                            nameField.setText(null);
                            lastNameField.setText(null);
                            ageField.setText(null);
                            sexComboBox.setSelectedItem(null);
                            personCodeField.setText(null);
                            groupComboBox.setSelectedItem(null);
                            finalPhoneField.setText(null);
                            emailField.setText(null);

                        }
                    };
                    /*********************************/
                    JTextField lessonField = new JTextField();

                    DefaultListModel coursesListModel = new DefaultListModel();
                    coursesListModel.addElement("P1");
                    coursesListModel.addElement("P2");
                    coursesListModel.addElement("P3");
                    coursesListModel.addElement("P4");
                    coursesListModel.addElement("A1");
                    coursesListModel.addElement("A2");
                    coursesListModel.addElement("A3");
                    coursesListModel.addElement("A4");
                    JList coursesList = new JList(coursesListModel);
                    coursesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    coursesList.setVisibleRowCount(-1);
                    coursesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                    JScrollPane coursesScrollPane = new JScrollPane(coursesList);
                    coursesScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    coursesScrollPane.setBackground(Color.WHITE);

                    DefaultListModel teachersListModel = new DefaultListModel();

                   String sqlSelectTeacherName = "SELECT First_Name, Last_Name FROM Teachers";
                   Statement stmt5 = conn.createStatement();
                   ResultSet rs5 = stmt5.executeQuery(sqlSelectTeacherName);
                   String sqlCreateLesson = "INSERT INTO Lessons (ID, Lesson_Name, Course, Teacher)"
                           + "VALUES (DEFAULT, ?, ?, ?)";


                    JPanel addLessonPanel = new JPanel(new GridLayout(0, 1));
                    addLessonPanel.setPreferredSize(new Dimension(300, 160));
                    addLessonPanel.add(new JLabel("Priekšmēta nosaukums: "));
                    addLessonPanel.add(lessonField);
                    addLessonPanel.add(new JLabel("Grupas: "));
                    addLessonPanel.add(coursesScrollPane);
                    addLessonPanel.add(new JLabel("Skolotājs(-āja)"));
                    JList teachersList = null;
                    while (rs5.next()) {
                        String name = rs5.getString("First_Name");
                        String lastName = rs5.getString("Last_Name");
                        teachersListModel.addElement(name + " " + lastName);
                        teachersList = new JList(teachersListModel);
                    }

                    addLessonPanel.add(teachersList);

                    JList finalTeachersList = teachersList;
                    Connection finalConn1 = conn;
                    ActionListener newLesson = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final String[] option = {"Pievienot", "Atcelt"};
                            URL LessonIconURL = Main.class.getResource("/resources/lesson.png");
                            ImageIcon lessonIcon = new ImageIcon(LessonIconURL);
                            int nl = JOptionPane.showOptionDialog(w, addLessonPanel, "Pievienot priekšmētu", JOptionPane.PLAIN_MESSAGE, 0, lessonIcon, option, option[1]);
                            int h = lessonsTable.getRowCount() + 1;
                            try {
                                PreparedStatement preparedStmtL = finalConn1.prepareStatement(sqlCreateLesson);
                                preparedStmtL.setString(1, lessonField.getText());
                                preparedStmtL.setString(2, String.valueOf(coursesList.getSelectedValue()));
                                preparedStmtL.setString(3, String.valueOf(finalTeachersList.getSelectedValue()));
                                preparedStmtL.execute();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            int ho = lessonsTable.getRowCount() + 1;
                            lessonsTableModel.addRow(new Object[] {ho, lessonField.getText(), coursesList.getSelectedValue(), finalTeachersList.getSelectedValue()});
                        }
                    };

                    /*******************************/

                    String sexTeacher[] = {"Vīrietis", "Sieviete"};
                    JTextField nameFieldTeacher = new JTextField();
                    JTextField lastNameFieldTeacher = new JTextField();
                    JComboBox sexComboBoxTeacher = new JComboBox(sexTeacher);
                    NumberFormat numberFormatTeacher = NumberFormat.getIntegerInstance();
                    numberFormatTeacher.setGroupingUsed(false);
                    JFormattedTextField ageFieldTeacher = new JFormattedTextField(numberFormatTeacher);
                    JTextField personCodeFieldTeacher = new JTextField();

                    JTextField emailFieldTeacher = new JTextField();
                    JFormattedTextField phoneFieldTeacher = null;

                    try {
                        MaskFormatter mfPhoneTeacher = new MaskFormatter("+371 ###-###-##");
                        mfPhoneTeacher.setPlaceholderCharacter('_');
                        phoneFieldTeacher = new JFormattedTextField(mfPhoneTeacher);
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(w, "Vecums ir ievadīts nepareizi!", "Kļūda", JOptionPane.WARNING_MESSAGE);
                    }

                    JPanel addRecordFormPanelTeacher = new JPanel(new GridLayout(0, 1));
                    addRecordFormPanelTeacher.setPreferredSize(new Dimension(280, 400));
                    addRecordFormPanelTeacher.add(new JLabel("Vārds: "));
                    addRecordFormPanelTeacher.add(nameFieldTeacher);
                    addRecordFormPanelTeacher.add(new JLabel("Uzvārds: "));
                    addRecordFormPanelTeacher.add(lastNameFieldTeacher);
                    addRecordFormPanelTeacher.add(new JLabel("Dzimums: "));
                    addRecordFormPanelTeacher.add(sexComboBoxTeacher);
                    addRecordFormPanelTeacher.add(new JLabel("Vecums: "));
                    addRecordFormPanelTeacher.add(ageFieldTeacher);
                    addRecordFormPanelTeacher.add(new JLabel("Personas kods"));
                    addRecordFormPanelTeacher.add(personCodeFieldTeacher);
                    //addRecordFormPanelTeacher.add(new JLabel("Priekšmēts: "));
                    //addRecordFormPanelTeacher.add(lessonsComboBox);
                    addRecordFormPanelTeacher.add(new JLabel("Mob. tālrunis: "));
                    addRecordFormPanelTeacher.add(phoneFieldTeacher);
                    addRecordFormPanelTeacher.add(new JLabel("E-pasts: "));
                    addRecordFormPanelTeacher.add(emailFieldTeacher);

                    JFormattedTextField finalPhoneFieldTeacher = phoneFieldTeacher;
                    //JComboBox finalLessonsComboBox = lessonsComboBox;
                    ActionListener newRecordTeacher = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final String[] option = {"Pievienot", "Atcelt"};
                            URL teacherIconURL = Main.class.getResource("/resources/teacher.png");
                            ImageIcon teacherIcon = new ImageIcon(teacherIconURL);
                            int n = JOptionPane.showOptionDialog(w, addRecordFormPanelTeacher, "Skolotāja pievienošana", JOptionPane.PLAIN_MESSAGE, 0, teacherIcon, option, option[1]);
                            int h = tableTeachers.getRowCount() + 1;
                            if (n == 0 && nameFieldTeacher.getText().isEmpty() | lastNameFieldTeacher.getText().isEmpty() | ageFieldTeacher.getText().isEmpty() | personCodeFieldTeacher.getText().isEmpty() | emailFieldTeacher.getText().isEmpty()) {
                                while (true) {
                                    JOptionPane.showMessageDialog(w, "Lūdzu, ievadiet datus!", "Kļūda", JOptionPane.WARNING_MESSAGE);
                                    JOptionPane newOP = new JOptionPane();
                                    int o = newOP.showOptionDialog(w, addRecordFormPanelTeacher, "Studenta pievienošana", JOptionPane.PLAIN_MESSAGE, 0, teacherIcon, option, option[1]);
                                    if (o == 1) {
                                        break;
                                    }
                                    if (!nameFieldTeacher.getText().isEmpty() | !lastNameFieldTeacher.getText().isEmpty() | !ageFieldTeacher.getText().isEmpty() | !personCodeFieldTeacher.getText().isEmpty() | !emailFieldTeacher.getText().isEmpty()) {
                                        try {
                                            PreparedStatement preparedStmtNewT = finalConn.prepareStatement(sqlInsertIntoTableTeacher);
                                            //preparedStmt.setInt(1, 8);
                                            preparedStmtNewT.setString(1, nameFieldTeacher.getText());
                                            preparedStmtNewT.setString(2, lastNameFieldTeacher.getText());
                                            preparedStmtNewT.setString(3, (String) sexComboBoxTeacher.getSelectedItem());
                                            preparedStmtNewT.setInt(4, Integer.parseInt(ageFieldTeacher.getText()));
                                            preparedStmtNewT.setString(5, personCodeFieldTeacher.getText());
                                            preparedStmtNewT.setString(6, finalPhoneFieldTeacher.getText());
                                            preparedStmtNewT.setString(7, emailFieldTeacher.getText());
                                            preparedStmtNewT.execute();
                                            //finalConn.close();
                                        } catch (SQLException e1) {
                                            e1.printStackTrace();
                                        }
                                        tableTeacherModel.addRow(new Object[]{h,
                                                nameFieldTeacher.getText(),
                                                lastNameFieldTeacher.getText(),
                                                sexComboBoxTeacher.getSelectedItem(),
                                                ageFieldTeacher.getText(),
                                                personCodeFieldTeacher.getText(),
                                                null,
                                                finalPhoneFieldTeacher.getText(),
                                                emailFieldTeacher.getText()
                                        });
                                        tableTeacherModel.fireTableDataChanged();
                                        break;
                                    }
                                }

                                nameFieldTeacher.setText(null);
                                lastNameFieldTeacher.setText(null);
                                ageFieldTeacher.setText(null);
                                sexComboBoxTeacher.setSelectedItem(null);
                                personCodeFieldTeacher.setText(null);
                                //finalLessonsComboBox.setSelectedItem(null);
                                finalPhoneFieldTeacher.setText(null);
                                emailFieldTeacher.setText(null);
                            }
                            if (n == 0 && !nameFieldTeacher.getText().isEmpty() | !lastNameFieldTeacher.getText().isEmpty() | !ageFieldTeacher.getText().isEmpty() | !personCodeFieldTeacher.getText().isEmpty() | !emailFieldTeacher.getText().isEmpty()) {
                                try {
                                    PreparedStatement preparedStmtNewT = finalConn.prepareStatement(sqlInsertIntoTableTeacher);
                                    //preparedStmt.setInt(1, 8);
                                    preparedStmtNewT.setString(1, nameFieldTeacher.getText());
                                    preparedStmtNewT.setString(2, lastNameFieldTeacher.getText());
                                    preparedStmtNewT.setString(3, (String) sexComboBoxTeacher.getSelectedItem());
                                    preparedStmtNewT.setInt(4, Integer.parseInt(ageFieldTeacher.getText()));
                                    preparedStmtNewT.setString(5, personCodeFieldTeacher.getText());
                                    preparedStmtNewT.setString(6, finalPhoneFieldTeacher.getText());
                                    preparedStmtNewT.setString(7, emailFieldTeacher.getText());
                                    preparedStmtNewT.execute();
                                    //finalConn.close();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                                tableTeacherModel.addRow(new Object[]{h,
                                        nameFieldTeacher.getText(),
                                        lastNameFieldTeacher.getText(),
                                        sexComboBoxTeacher.getSelectedItem(),
                                        ageFieldTeacher.getText(),
                                        personCodeFieldTeacher.getText(),
                                        null,
                                        finalPhoneFieldTeacher.getText(),
                                        emailFieldTeacher.getText()
                                });
                                tableTeacherModel.fireTableDataChanged();
                                nameFieldTeacher.setText(null);
                                lastNameFieldTeacher.setText(null);
                                ageFieldTeacher.setText(null);
                                sexComboBoxTeacher.setSelectedItem(null);
                                personCodeFieldTeacher.setText(null);
                                //finalLessonsComboBox.setSelectedItem(null);
                                finalPhoneFieldTeacher.setText(null);
                                emailFieldTeacher.setText(null);
                            } else {
                                nameFieldTeacher.setText(null);
                                lastNameFieldTeacher.setText(null);
                                ageFieldTeacher.setText(null);
                                sexComboBoxTeacher.setSelectedItem(null);
                                personCodeFieldTeacher.setText(null);
                                //finalLessonsComboBox.setSelectedItem(null);
                                finalPhoneFieldTeacher.setText(null);
                                emailFieldTeacher.setText(null);
                            }

                            nameFieldTeacher.setText(null);
                            lastNameFieldTeacher.setText(null);
                            ageFieldTeacher.setText(null);
                            sexComboBoxTeacher.setSelectedItem(null);
                            personCodeFieldTeacher.setText(null);
                            //finalLessonsComboBox.setSelectedItem(null);
                            finalPhoneFieldTeacher.setText(null);
                            emailFieldTeacher.setText(null);

                        }
                    };
                    /**************************************/


                    /**************************************/
                    tableTeacherModel.addTableModelListener(new TableModelListener() {
                        @Override
                        public void tableChanged(TableModelEvent e) {
                            System.out.print("All Right!");
                        }
                    });
                    ActionListener editSqlData = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int nameColumn = 1;
                            int lastNameColumn = 2;
                            int sexColumn = 3;
                            int ageColumn = 4;
                            int personCodeColumn = 5;
                            int studentGroupColumn = 6;
                            int phoneColumn = 7;
                            int emailColumn = 8;
                            int selectedRow = table.getSelectedRow();
                            int row1 = table.convertRowIndexToModel(selectedRow);
                            nameField.setText(String.valueOf(table.getModel().getValueAt(row1, nameColumn)));
                            lastNameField.setText(String.valueOf(table.getModel().getValueAt(row1, lastNameColumn)));
                            sexComboBox.setSelectedItem(String.valueOf(table.getModel().getValueAt(row1, sexColumn)));
                            ageField.setText(String.valueOf(table.getModel().getValueAt(row1, ageColumn)));
                            personCodeField.setText(String.valueOf(table.getModel().getValueAt(row1, personCodeColumn)));
                            groupComboBox.setSelectedItem(String.valueOf(table.getModel().getValueAt(row1, studentGroupColumn)));
                            finalPhoneField.setText(String.valueOf(table.getModel().getValueAt(row1, phoneColumn)));
                            emailField.setText(String.valueOf(table.getModel().getValueAt(row1, emailColumn)));
                            JOptionPane.showMessageDialog(w, addRecordFormPanel, "Datu rediģēšana", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.setValueAt(nameField.getText(), row1, 1);
                            tableModel.setValueAt(lastNameField.getText(), row1, 2);
                            tableModel.setValueAt(sexComboBox.getSelectedItem(), row1, 3);
                            tableModel.setValueAt(ageField.getText(), row1, 4);
                            tableModel.setValueAt(personCodeField.getText(), row1, 5);
                            tableModel.setValueAt(groupComboBox.getSelectedItem(), row1, 6);
                            tableModel.setValueAt(finalPhoneField.getText(), row1, 7);
                            tableModel.setValueAt(emailField.getText(), row1, 8);
                            int col = table.getSelectedColumn();
                            tableModel.fireTableCellUpdated(row1, col);

                            Object o = table.getModel().getValueAt(row1, 0);

                            String sqlUpdateData = "UPDATE Students SET First_Name = ?, Last_Name = ?, Sex = ?, Age = ?, Person_Code = ?, Student_Group = ?, Phone = ?, Email = ?"
                                    + "WHERE ID =" + o;

                            try {
                                PreparedStatement preparedStmtUpd;
                                preparedStmtUpd = finalConn.prepareStatement(sqlUpdateData);
                                preparedStmtUpd.setString(1, nameField.getText());
                                preparedStmtUpd.setString(2, lastNameField.getText());
                                preparedStmtUpd.setString(3, (String) sexComboBox.getSelectedItem());
                                preparedStmtUpd.setInt(4, Integer.parseInt(ageField.getText()));
                                preparedStmtUpd.setString(5, personCodeField.getText());
                                preparedStmtUpd.setString(6, (String) groupComboBox.getSelectedItem());
                                preparedStmtUpd.setString(7, finalPhoneField.getText());
                                preparedStmtUpd.setString(8, emailField.getText());
                                preparedStmtUpd.execute();
                                //finalConn.close();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            tableModel.fireTableDataChanged();
                            nameField.setText(null);
                            lastNameField.setText(null);
                            ageField.setText(null);
                            sexComboBox.setSelectedItem(null);
                            personCodeField.setText(null);
                            groupComboBox.setSelectedItem(null);
                            finalPhoneField.setText(null);
                            emailField.setText(null);
                        }
                    };

                    /************************************************************/
                   ActionListener editSqlDataTeachers = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int nameColumn = 1;
                            int lastNameColumn = 2;
                            int sexColumn = 3;
                            int ageColumn = 4;
                            int personCodeColumn = 5;
                            int phoneColumn = 6;
                            int emailColumn = 7;
                            int selectedRow = tableTeachers.getSelectedRow();
                            int row1 = tableTeachers.convertRowIndexToModel(selectedRow);
                            nameFieldTeacher.setText(String.valueOf(tableTeachers.getModel().getValueAt(row1, nameColumn)));
                            lastNameFieldTeacher.setText(String.valueOf(tableTeachers.getModel().getValueAt(row1, lastNameColumn)));
                            sexComboBoxTeacher.setSelectedItem(String.valueOf(tableTeachers.getModel().getValueAt(row1, sexColumn)));
                            ageFieldTeacher.setText(String.valueOf(tableTeachers.getModel().getValueAt(row1, ageColumn)));
                            personCodeFieldTeacher.setText(String.valueOf(tableTeachers.getModel().getValueAt(row1, personCodeColumn)));
                            finalPhoneFieldTeacher.setText(String.valueOf(tableTeachers.getModel().getValueAt(row1, phoneColumn)));
                            emailFieldTeacher.setText(String.valueOf(tableTeachers.getModel().getValueAt(row1, emailColumn)));
                            JOptionPane.showMessageDialog(w, addRecordFormPanelTeacher, "Datu rediģēšana", JOptionPane.INFORMATION_MESSAGE);
                            tableTeacherModel.setValueAt(nameFieldTeacher.getText(), row1, 1);
                            tableTeacherModel.setValueAt(lastNameFieldTeacher.getText(), row1, 2);
                            tableTeacherModel.setValueAt(sexComboBoxTeacher.getSelectedItem(), row1, 3);
                            tableTeacherModel.setValueAt(ageFieldTeacher.getText(), row1, 4);
                            tableTeacherModel.setValueAt(personCodeFieldTeacher.getText(), row1, 5);
                            tableTeacherModel.setValueAt(finalPhoneFieldTeacher.getText(), row1, 6);
                            tableTeacherModel.setValueAt(emailFieldTeacher.getText(), row1, 7);
                            int col = tableTeachers.getSelectedColumn();
                            tableTeacherModel.fireTableCellUpdated(row1, col);

                            Object o = tableTeachers.getModel().getValueAt(row1, 0);

                            String sqlUpdateDataTeacher = "UPDATE Teachers SET First_Name = ?, Last_Name = ?, Sex = ?, Age = ?, Person_Code = ?, Phone = ?, Email = ?"
                                    + "WHERE ID =" + o;

                            try {
                                PreparedStatement preparedStmtUpdT;
                                preparedStmtUpdT = finalConn.prepareStatement(sqlUpdateDataTeacher);
                                preparedStmtUpdT.setString(1, nameFieldTeacher.getText());
                                preparedStmtUpdT.setString(2, lastNameFieldTeacher.getText());
                                preparedStmtUpdT.setString(3, (String) sexComboBoxTeacher.getSelectedItem());
                                preparedStmtUpdT.setInt(4, Integer.parseInt(ageFieldTeacher.getText()));
                                preparedStmtUpdT.setString(5, personCodeFieldTeacher.getText());
                                preparedStmtUpdT.setString(6, finalPhoneFieldTeacher.getText());
                                preparedStmtUpdT.setString(7, emailFieldTeacher.getText());
                                preparedStmtUpdT.execute();
                                //finalConn.close();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            tableTeacherModel.fireTableDataChanged();
                            nameFieldTeacher.setText(null);
                            lastNameFieldTeacher.setText(null);
                            ageFieldTeacher.setText(null);
                            sexComboBoxTeacher.setSelectedItem(null);
                            personCodeFieldTeacher.setText(null);
                            finalPhoneFieldTeacher.setText(null);
                            emailFieldTeacher.setText(null);
                        }
                    };
                    /***********************************************************/

                    Connection finalConn2 = conn;
                    ActionListener deleteData = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int oo = JOptionPane.showConfirmDialog(w, "Vai jūs tiešām vēlaties dzēst šo ierakstu?", "Datu dzēšana", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                            if (oo == JOptionPane.YES_OPTION) {
                                int selectedRow = table.getSelectedRow();
                                int row = table.convertRowIndexToModel(selectedRow);
                                Object o = table.getModel().getValueAt(row, 0);

                                String deleteDataSql = "DELETE FROM Students " + "WHERE ID = " + o;
                                try {
                                    PreparedStatement preparedStmt = finalConn2.prepareStatement(deleteDataSql);
                                    preparedStmt.execute();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                                int[] tableSelectedRow = table.getSelectedRows();
                                for (int i = 0; i < tableSelectedRow.length; i++) {
                                    tableSelectedRow[i] = table.convertRowIndexToModel(tableSelectedRow[i]);
                                    tableModel.removeRow(tableSelectedRow[i] - i);
                                }
                                tableModel.fireTableDataChanged();
                            } else {

                            }
                        }
                    };
                    /***********************************************************/
                    Connection finalConnT2 = conn;
                    ActionListener deleteDataTeacher = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int oo = JOptionPane.showConfirmDialog(w, "Vai jūs tiešām vēlaties dzēst šo ierakstu?", "Datu dzēšana", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                            if (oo == JOptionPane.YES_OPTION) {
                                int selectedRow = tableTeachers.getSelectedRow();
                                int row = tableTeachers.convertRowIndexToModel(selectedRow);
                                Object o = tableTeachers.getModel().getValueAt(row, 0);

                                String deleteDataSqlTeacher = "DELETE FROM Teachers " + "WHERE ID = " + o;
                                try {
                                    PreparedStatement preparedStmt = finalConnT2.prepareStatement(deleteDataSqlTeacher);
                                    preparedStmt.execute();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                                int[] tableSelectedRow = tableTeachers.getSelectedRows();
                                for (int i = 0; i < tableSelectedRow.length; i++) {
                                    tableSelectedRow[i] = tableTeachers.convertRowIndexToModel(tableSelectedRow[i]);
                                    tableTeacherModel.removeRow(tableSelectedRow[i] - i);
                                }
                                tableTeacherModel.fireTableDataChanged();
                            } else {

                            }
                        }
                    };
                    /***********************************************************/
                    ActionListener contactsShowing = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            URL contactIconURL = Main.class.getResource("/resources/contacts.png");
                            ImageIcon contactIcon = new ImageIcon(contactIconURL);
                            JPanel contacts = new JPanel(new GridLayout(0, 1));
                            int selectedRow = table.getSelectedRow();
                            int row = table.convertRowIndexToModel(selectedRow);
                            JLabel phoneLabel = new JLabel();
                            JLabel emailLabel = new JLabel();

                            contacts.add(phoneLabel);
                            contacts.add(emailLabel);

                            int phoneC = 7;
                            int emailC = 8;

                            Object phoneObj = table.getModel().getValueAt(row, phoneC);
                            Object emailObj = table.getModel().getValueAt(row, emailC);

                            phoneLabel.setText(String.valueOf("Mob. tālrunis: " + phoneObj));
                            emailLabel.setText(String.valueOf("E-pasts: " + emailObj));
                            JOptionPane.showMessageDialog(w, contacts, "Studenta kontakti", JOptionPane.INFORMATION_MESSAGE, contactIcon);
                        }
                    };
                    /*************************************/
                    ActionListener contactsShowingTeacher = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            URL contactIconURLTeacher = Main.class.getResource("/resources/contacts.png");
                            ImageIcon contactIconTeacher = new ImageIcon(contactIconURLTeacher);
                            JPanel contactsTeachers = new JPanel(new GridLayout(0, 1));
                            int selectedRow = tableTeachers.getSelectedRow();
                            int row = tableTeachers.convertRowIndexToModel(selectedRow);
                            JLabel phoneLabelTeacher = new JLabel();
                            JLabel emailLabelTeacher = new JLabel();

                            contactsTeachers.add(phoneLabelTeacher);
                            contactsTeachers.add(emailLabelTeacher);

                            int phoneC = 7;
                            int emailC = 8;

                            Object phoneObj = tableTeachers.getModel().getValueAt(row, phoneC);
                            Object emailObj = tableTeachers.getModel().getValueAt(row, emailC);

                            phoneLabelTeacher.setText(String.valueOf("Mob. tālrunis: " + phoneObj));
                            emailLabelTeacher.setText(String.valueOf("E-pasts: " + emailObj));
                            JOptionPane.showMessageDialog(w, contactsTeachers, "Skolotāja kontakti", JOptionPane.INFORMATION_MESSAGE, contactIconTeacher);
                        }
                    };
                    /*************************************/
                    final JFileChooser fc = new JFileChooser("C:");
                    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    fc.setFileFilter(new OpenFileFilter("tsv"));

                    ActionListener saveDB = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fc.showSaveDialog(w);
                            try {
                                File file = new File(fc.getSelectedFile() + ".tsv");
                                FileWriter excel = new FileWriter(file);
                                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                                    excel.write(tableModel.getColumnName(i) + "\t");
                                }
                                excel.write("\n");
                                for (int i = 0; i < tableModel.getRowCount(); i++) {
                                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                                        excel.write(tableModel.getValueAt(i, j).toString() + "\t");
                                    }
                                    excel.write("\n");
                                }
                                excel.close();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedEncodingException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    };
                    searchField.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            String text = searchField.getText();
                            if (text.length() == 0) {
                                sorter.setRowFilter(null);
                            } else {
                                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            String text = searchField.getText();
                            if (text.length() == 0) {
                                sorter.setRowFilter(null);
                            } else {
                                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                            }
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            throw new UnsupportedOperationException("Nav atbalstīts vēl.");
                        }
                    });

                    searchFieldTeacher.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            String textTeacher = searchFieldTeacher.getText();
                            if (textTeacher.length() == 0) {
                                sorterTeacher.setRowFilter(null);
                            } else {
                                sorterTeacher.setRowFilter(RowFilter.regexFilter("(?i)" + textTeacher));
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            String textTeacher = searchFieldTeacher.getText();
                            if (textTeacher.length() == 0) {
                                sorterTeacher.setRowFilter(null);
                            } else {
                                sorterTeacher.setRowFilter(RowFilter.regexFilter("(?i)" + textTeacher));
                            }
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            throw new UnsupportedOperationException("Nav atbalstīts vēl.");
                        }
                    });

                    addStudentButton.addActionListener(newRecord);
                    newUserMenuItem.addActionListener(newRecord);
                    showSqlDataButton.addActionListener(editSqlData);
                    editUserMenuItem.addActionListener(editSqlData);
                    deleteStudentButton.addActionListener(deleteData);
                    studentsContactsShowButton.addActionListener(contactsShowing);
                    saveMenuItem.addActionListener(saveDB);
                    newTeacherMenuItem.addActionListener(newRecordTeacher);
                    //loadDbMenuItem.addActionListener(loadDB);
                    addTeacherButton.addActionListener(newRecordTeacher);
                    editTeacherButton.addActionListener(editSqlDataTeachers);
                    teacherContactsShowButton.addActionListener(contactsShowingTeacher);
                    deleteTeacherButton.addActionListener(deleteDataTeacher);
                    addLessonButton.addActionListener(newLesson);

                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Datu bāze nestrāda", "Kļūda", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                    e.printStackTrace();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Datu bāze nestrāda", "Kļūda", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                    System.exit(0);
                }

                aboutProgram.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String msg = "Lorem Ipsum\n" +
                                "\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\n" +
                                "\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"\n" +
                                "\n" +
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce hendrerit lacinia metus, id maximus magna euismod ac. Quisque non nisi dictum, consequat nibh sit amet, sollicitudin tortor. Suspendisse mollis, enim eget mollis facilisis, sem nulla tempus lacus, in pretium orci nunc condimentum sapien. Sed vel est neque. Donec enim dolor, dictum non malesuada ut, viverra non eros. Praesent tincidunt ultrices sem, sed tincidunt nisi viverra malesuada. Sed eget lacus in sem dictum sagittis vulputate eget purus. Phasellus lectus augue, tincidunt vel dictum ac, maximus sit amet lacus. Fusce eget erat et sem dictum sagittis et ut quam. Donec sollicitudin, dolor nec porta tempor, velit ligula scelerisque elit, non sodales augue enim nec ante. Maecenas maximus tincidunt leo, quis aliquet purus accumsan laoreet. Nam ullamcorper ultrices leo, vitae placerat nibh faucibus eget. Donec malesuada commodo purus sed accumsan. Donec dignissim elit at fermentum suscipit. Morbi porttitor accumsan lacus vel condimentum.";
                        JOptionPane optionPane = new NarrowOptionPane();
                        optionPane.setMessage(msg);
                        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                        JDialog dialog = optionPane.createDialog(null, "Par programmu");
                        dialog.setVisible(true);
                    }
                });
                programContactAuthor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String msg = "Lorem Ipsum\n" +
                                "\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\n" +
                                "\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"\n";
                        JOptionPane optionPane = new NarrowOptionPane();
                        optionPane.setMessage(msg);
                        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                        JDialog dialog = optionPane.createDialog(null, "Par programmu");
                        dialog.setVisible(true);
                    }
                });
                w.addWindowFocusListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {

                    }
                });

                w.setJMenuBar(menuBar);
                w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                w.setPreferredSize(new Dimension(950, 700));
                w.pack();
                w.setLocationRelativeTo(null);
                w.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}