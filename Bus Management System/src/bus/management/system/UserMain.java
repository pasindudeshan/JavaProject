/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bus.management.system;


import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.swing.Timer;

/**
 *
 * @author pasin
 */
public class UserMain extends javax.swing.JFrame {

  

    /**
     * Creates new form UserMain
     */
    public UserMain() {
        initComponents();
        InvoiceNo() ;
        InvoiceNoPassenger();
        Booking();
        Busnumber();
        InitialUpdatePassengerId();
        InitialUpdateBookingId();
        ShowDate();
        ShowDateLabel();
        ShowTimeLabel();
  
        
    } 
    
    
    
    
//This method is for generating random invoice number for the booking;
    public void InvoiceNo() 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");
            PreparedStatement pstmt ;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Max(Booking_invoiceid) from Booking");
            rs.next();
            
            rs.getString("Max(Booking_invoiceid)");
            
            if(rs.getString("Max(Booking_invoiceid)") == null)
            {
                invoicelabel.setText("B-0000001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("Max(Booking_invoiceid)").substring(2,rs.getString("Max(Booking_invoiceid)").length()));
                id++;
                invoicelabel.setText("B-" + String.format("%07d",id));
            }
            
            con.close();
            
        }
        catch(SQLException e)
        {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE,null,e);
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE,null,e);
        }
        
        
    }
    
    //This method is for generating random invoice number for the Passenger;
    public void InvoiceNoPassenger() 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");
            PreparedStatement pstmt ;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Max(Passenger_id) from Passenger");
            rs.next();
            
            rs.getString("Max(Passenger_id)");
            
            if(rs.getString("Max(Passenger_id)") == null)
            {
                id_label.setText("P-0000001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("Max(Passenger_id)").substring(2,rs.getString("Max(Passenger_id)").length()));
                id++;
                id_label.setText("P-" + String.format("%07d",id));
            }
            
        }
        catch(SQLException e)
        {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE,null,e);
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE,null,e);
        }
        
        
    }
    
    
    //to display the booking details in the booking panel this method is going to call when the UserMain is invoked
    public void Booking()
    {
           try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
          
            //2nd combo box
           String sql = "select Route_city1 from Route";
            PreparedStatement pst = con.prepareStatement(sql);
           String combo_box;

           ResultSet rs2 = pst.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
            while(rs2.next())
           {
              combo_box = rs2.getString("Route_city1");//here Bus_Busno is actual column name of the table
              booking_to.addItem(combo_box);//add into jcomboBox
           } 
        
           sql = "select Route_city2 from Route";
           PreparedStatement pst11 = con.prepareStatement(sql);
           rs2 = pst11.executeQuery();
           while(rs2.next())
           {
              combo_box = rs2.getString("Route_city2");//here Bus_Busno is actual column name of the table
              booking_to.addItem(combo_box);//add into jcomboBox
           }
           
           sql = "select Route_city3 from Route";   
          PreparedStatement pst12 = con.prepareStatement(sql);
           rs2 = pst12.executeQuery();
           while(rs2.next())
           {
              combo_box = rs2.getString("Route_city3");//here Bus_Busno is actual column name of the table
              booking_to.addItem(combo_box);//add into jcomboBox
           }
                    
            sql = "select Route_To from Route";            
           PreparedStatement pst13 = con.prepareStatement(sql);
           rs2 = pst13.executeQuery();
           while(rs2.next())
           {
              combo_box = rs2.getString("Route_To");//here Bus_Busno is actual column name of the table
              booking_to.addItem(combo_box);//add into jcomboBox
           }
           
          
           //3rd combo box
            sql = "select Bus_BusNo from Bus";
           PreparedStatement pst3 = con.prepareStatement(sql);
           ResultSet rs3 = pst3.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs3.next())
           {
              combo_box = rs3.getString("Bus_BusNo");//here Bus_Busno is actual column name of the table
              booking_busno.addItem(combo_box);//add into jcomboBox
           }
           
           
           //4rd combo box
           sql = "select Bus_TimeOfArrive from Bus";
           PreparedStatement pst4 = con.prepareStatement(sql);
           ResultSet rs4 = pst4.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           
           while(rs4.next())
           {
              combo_box = rs4.getString("Bus_TimeOfArrive");//here Bus_Busno is actual column name of the table
              booking_time.addItem(combo_box);//add into jcomboBox
           }
           
           sql = "select Passenger_id from Passenger";
           PreparedStatement pst5 = con.prepareStatement(sql);
           ResultSet rs5 = pst5.executeQuery();//ResultSet is the return typed
           //above statement we will get all Passenger_id from table class
           while(rs5.next())
           {
              combo_box = rs5.getString("Passenger_id");//here Passenger_id is actual column name of the table
              booking_passenger.addItem(combo_box);//add into jcomboBox
           }   
           
           con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
    public void BookingUpdate()
    {
           try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
          
            //2nd combo box
           String sql = "select Route_city1 from Route";
            PreparedStatement pst = con.prepareStatement(sql);
           String combo_box;

           ResultSet rs2 = pst.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
            while(rs2.next())
           {
              combo_box = rs2.getString("Route_city1");//here Bus_Busno is actual column name of the table
              B_to_update.addItem(combo_box);//add into jcomboBox
           } 
        
           sql = "select Route_city2 from Route";
           PreparedStatement pst11 = con.prepareStatement(sql);
           rs2 = pst11.executeQuery();
           while(rs2.next())
           {
              combo_box = rs2.getString("Route_city2");//here Bus_Busno is actual column name of the table
              B_to_update.addItem(combo_box);//add into jcomboBox
           }
           
           sql = "select Route_city3 from Route";   
          PreparedStatement pst12 = con.prepareStatement(sql);
           rs2 = pst12.executeQuery();
           while(rs2.next())
           {
              combo_box = rs2.getString("Route_city3");//here Bus_Busno is actual column name of the table
              B_to_update.addItem(combo_box);//add into jcomboBox
           }
                    
            sql = "select Route_To from Route";            
           PreparedStatement pst13 = con.prepareStatement(sql);
           rs2 = pst13.executeQuery();
           while(rs2.next())
           {
              combo_box = rs2.getString("Route_To");//here Bus_Busno is actual column name of the table
              B_to_update.addItem(combo_box);//add into jcomboBox
           }
           
          
           //3rd combo box
            sql = "select Bus_BusNo from Bus";
           PreparedStatement pst3 = con.prepareStatement(sql);
           ResultSet rs3 = pst3.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs3.next())
           {
              combo_box = rs3.getString("Bus_BusNo");//here Bus_Busno is actual column name of the table
              B_no_update.addItem(combo_box);//add into jcomboBox
           }
           
           
           //4rd combo box
           sql = "select Bus_TimeOfArrive from Bus";
           PreparedStatement pst4 = con.prepareStatement(sql);
           ResultSet rs4 = pst4.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           
           while(rs4.next())
           {
              combo_box = rs4.getString("Bus_TimeOfArrive");//here Bus_Busno is actual column name of the table
              B_time_update.addItem(combo_box);//add into jcomboBox
           }
           
           //5th combo box
           sql = "select Passenger_id from Passenger";
           PreparedStatement pst5 = con.prepareStatement(sql);
           ResultSet rs5 = pst5.executeQuery();//ResultSet is the return typed
           //above statement we will get all Passenger_id from table class
           while(rs5.next())
           {
              combo_box = rs5.getString("Passenger_id");//here Passenger_id is actual column name of the table
              B_name_update.addItem(combo_box);//add into jcomboBox
           }    
           
           con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
    

        public void Busnumber()
        {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
                   
            String sql = "select Bus_BusNo from Bus";
            PreparedStatement pst = con.prepareStatement(sql);
           ResultSet rs = pst.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs.next())
           {
               String Sbusno = rs.getString("Bus_BusNo");//here Bus_Busno is actual column name of the table
               busno.addItem(Sbusno);//add into jcomboBox
           }
           
           con.close();
        }
        
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        }  
        
        
       
        public void InitialUpdatePassengerId()
       {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
                   
             //5th combo box
         String  sql = "select Passenger_id from Passenger";
           PreparedStatement pst5 = con.prepareStatement(sql);
           ResultSet rs5 = pst5.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs5.next())
           {
            String  combo_box = rs5.getString("Passenger_id");//here Bus_Busno is actual column name of the table
        
            passenger_combobox.addItem(combo_box);//add into jcomboBox
           } 
           
           con.close();
        }
        
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        } 
        
        public void InitialUpdateBookingId()
       {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
                   
             //5th combo box
         String  sql = "select Booking_invoiceid from Booking";
           PreparedStatement pst5 = con.prepareStatement(sql);
           ResultSet rs5 = pst5.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs5.next())
           {
            String  combo_box = rs5.getString("Booking_invoiceid");//here Bus_Busno is actual column name of the table
        
            booking_combobox.addItem(combo_box);//add into jcomboBox
           } 
           
           con.close();
        }
        
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        } 
        
        
        public void UpdateBookingPassengerID(){
            String id = id_label.getText();
            booking_passenger.addItem(id);
        }
        
        public void UpdatePassengerID()
        {
            String id = id_label.getText();
            passenger_combobox.addItem(id);
        }
                    
        public void UpdateBookingid()
        {
            String id = invoicelabel.getText();
            booking_combobox.addItem(id);
        }
        public void ShowDate()
        {
            jDateChooser1.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));         
       
        }
        
        public void ShowDateLabel()
        {
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            datelabel.setText(s.format(d));
        }
        
        public void ShowTimeLabel()
        {
       new Timer(0,new ActionListener(){    
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                     Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                timelabel.setText(s.format(d));
            }
        }
        ).start();
                
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        datelabel = new javax.swing.JLabel();
        timelabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        mainPanel = new javax.swing.JTabbedPane();
        statusPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        busno = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner(sm);
        Date date1 = new Date();
        SpinnerDateModel sm1 =
        new SpinnerDateModel(date1,null,null,Calendar.HOUR_OF_DAY);
        jSpinner2 = new javax.swing.JSpinner(sm1);
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        bookingMainPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        bookingPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        booking_from = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        booking_to = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        booking_busno = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        booking_time = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        booking_passenger = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        invoicelabel = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        passengerPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        passenger_name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        passenger_dob = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        passenger_male = new javax.swing.JRadioButton();
        passenger_female = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        passenger_phoneno = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        id_label = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        passenger_combobox = new javax.swing.JComboBox<>();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        P_name = new javax.swing.JTextField();
        P_dob = new javax.swing.JTextField();
        P_gender = new javax.swing.JTextField();
        P_number = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        P_dob_update = new com.toedter.calendar.JDateChooser();
        P_male_update = new javax.swing.JRadioButton();
        P_female_update = new javax.swing.JRadioButton();
        P_name_update = new javax.swing.JTextField();
        P_number_update = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        booking_combobox = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        B_name = new javax.swing.JTextField();
        B_time = new javax.swing.JTextField();
        B_no = new javax.swing.JTextField();
        B_date = new javax.swing.JTextField();
        B_to = new javax.swing.JTextField();
        B_from = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        B_date_update = new javax.swing.JPanel();
        B_from_update = new javax.swing.JComboBox<>();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        B_to_update = new javax.swing.JComboBox<>();
        B_no_update = new javax.swing.JComboBox<>();
        B_time_update = new javax.swing.JComboBox<>();
        B_name_update = new javax.swing.JComboBox<>();
        jButton28 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 610));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(3306, 600));
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBackground(new java.awt.Color(255, 255, 0));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage (1).png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome!!!");

        datelabel.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        datelabel.setForeground(new java.awt.Color(255, 255, 51));
        datelabel.setText("jLabel42");
        datelabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        timelabel.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        timelabel.setForeground(new java.awt.Color(255, 255, 51));
        timelabel.setText("jLabel42");
        timelabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(datelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timelabel, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 2, 810, 160));

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));
        jPanel5.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home (1).png"))); // NOI18N
        jLabel6.setToolTipText("");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        jButton12.setBackground(new java.awt.Color(255, 153, 204));
        jButton12.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 51, 204));
        jButton12.setText("Home");
        jButton12.setToolTipText("");
        jButton12.setBorderPainted(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5, 120, 30));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, -1));

        jPanel11.setBackground(new java.awt.Color(204, 153, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/booking (1).png"))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel11.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 38));

        jButton13.setBackground(new java.awt.Color(255, 153, 204));
        jButton13.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 204));
        jButton13.setText("Booking");
        jButton13.setBorderPainted(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5, 120, 30));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, 40));

        jPanel12.setBackground(new java.awt.Color(204, 153, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-file.png"))); // NOI18N
        jLabel8.setToolTipText("");
        jPanel12.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        jButton14.setBackground(new java.awt.Color(255, 153, 204));
        jButton14.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 0, 204));
        jButton14.setText("Search");
        jButton14.setBorderPainted(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5, 120, 30));

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, -1));

        jPanel9.setBackground(new java.awt.Color(204, 153, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync.png"))); // NOI18N
        jLabel12.setToolTipText("");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        jButton16.setBackground(new java.awt.Color(255, 153, 204));
        jButton16.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(0, 0, 204));
        jButton16.setText("Update");
        jButton16.setBorderPainted(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5, 120, 30));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, -1));

        jPanel16.setBackground(new java.awt.Color(204, 153, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel26.setToolTipText("");
        jPanel16.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        jButton19.setBackground(new java.awt.Color(255, 153, 204));
        jButton19.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 0, 204));
        jButton19.setText("Exit");
        jButton19.setBorderPainted(false);
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5, 120, 30));

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        mainPanel.setBackground(new java.awt.Color(255, 255, 204));

        statusPanel.setBackground(new java.awt.Color(255, 255, 204));
        statusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Date: ");
        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 30));

        jDateChooser1.setBackground(new java.awt.Color(51, 51, 255));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        statusPanel.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 120, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Bus No :");
        jLabel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 140, 30));

        busno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busnoActionPerformed(evt);
            }
        });
        statusPanel.add(busno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 120, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Arrive At :");
        jLabel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 30));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("Left At :");
        jLabel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 140, 30));

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1,"HH:mm:ss");
        jSpinner1.setEditor(de);
        statusPanel.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 120, 30));

        JSpinner.DateEditor de1 = new JSpinner.DateEditor(jSpinner2,"HH:mm:ss");
        jSpinner2.setEditor(de1);
        statusPanel.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 120, 30));

        jButton17.setText("Submit");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        statusPanel.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 90, 40));

        jButton18.setText("Clear");
        jButton18.setActionCommand("");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statusPanel.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 90, 40));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        statusPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 390));

        mainPanel.addTab("tab1", statusPanel);

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 255, 204), null, null));
        jPanel4.setLayout(null);

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-check.png"))); // NOI18N
        jButton2.setText("Passenger");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 153), null, null));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);
        jButton2.setBounds(12, 74, 140, 39);

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(102, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reservation.png"))); // NOI18N
        jButton3.setText("Booking");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 51), null, null));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);
        jButton3.setBounds(12, 135, 140, 37);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel4.add(jLabel19);
        jLabel19.setBounds(0, 0, 800, 390);

        javax.swing.GroupLayout bookingMainPanelLayout = new javax.swing.GroupLayout(bookingMainPanel);
        bookingMainPanel.setLayout(bookingMainPanelLayout);
        bookingMainPanelLayout.setHorizontalGroup(
            bookingMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookingMainPanelLayout.setVerticalGroup(
            bookingMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.addTab("tab2", bookingMainPanel);

        bookingPanel.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Booking from       :");
        jLabel20.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel20);
        jLabel20.setBounds(10, 70, 150, 30);

        booking_from.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kegalle" }));
        bookingPanel.add(booking_from);
        booking_from.setBounds(200, 70, 160, 30);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText("Booking to           :");
        jLabel21.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel21);
        jLabel21.setBounds(10, 110, 150, 30);

        booking_to.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booking_toActionPerformed(evt);
            }
        });
        bookingPanel.add(booking_to);
        booking_to.setBounds(200, 110, 160, 30);

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Date                    :");
        jLabel22.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel22);
        jLabel22.setBounds(10, 150, 150, 30);

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        bookingPanel.add(jDateChooser2);
        jDateChooser2.setBounds(200, 150, 160, 30);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Bus No                :");
        jLabel23.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel23);
        jLabel23.setBounds(10, 190, 150, 30);

        bookingPanel.add(booking_busno);
        booking_busno.setBounds(200, 190, 160, 30);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel24.setText("Time                   : ");
        jLabel24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel24);
        jLabel24.setBounds(10, 230, 150, 30);

        bookingPanel.add(booking_time);
        booking_time.setBounds(200, 230, 160, 30);

        jLabel25.setBackground(new java.awt.Color(255, 204, 204));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel25.setText("Passenger ID   :");
        jLabel25.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel25);
        jLabel25.setBounds(10, 270, 150, 30);

        booking_passenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booking_passengerActionPerformed(evt);
            }
        });
        bookingPanel.add(booking_passenger);
        booking_passenger.setBounds(200, 270, 160, 30);

        jButton9.setText("Submit");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        bookingPanel.add(jButton9);
        jButton9.setBounds(410, 110, 110, 40);

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel37.setText("Invoice Number");
        jLabel37.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(jLabel37);
        jLabel37.setBounds(10, 20, 150, 40);

        invoicelabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        invoicelabel.setForeground(new java.awt.Color(0, 0, 255));
        invoicelabel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bookingPanel.add(invoicelabel);
        invoicelabel.setBounds(200, 24, 160, 30);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        bookingPanel.add(jLabel40);
        jLabel40.setBounds(0, 0, 770, 390);

        mainPanel.addTab("tab8", bookingPanel);

        passengerPanel.setBackground(new java.awt.Color(153, 255, 153));
        passengerPanel.setLayout(null);

        jLabel5.setBackground(new java.awt.Color(51, 0, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Name                  : ");
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        passengerPanel.add(jLabel5);
        jLabel5.setBounds(10, 60, 150, 30);

        passenger_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_nameActionPerformed(evt);
            }
        });
        passengerPanel.add(passenger_name);
        passenger_name.setBounds(190, 60, 270, 30);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Date of Birth        :");
        jLabel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        passengerPanel.add(jLabel9);
        jLabel9.setBounds(10, 100, 150, 30);

        passenger_dob.setDateFormatString("yyyy-MM-dd");
        passengerPanel.add(passenger_dob);
        passenger_dob.setBounds(190, 100, 190, 30);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Gender                :");
        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        passengerPanel.add(jLabel11);
        jLabel11.setBounds(10, 140, 150, 30);

        buttonGroup1.add(passenger_male);
        passenger_male.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        passenger_male.setText("Male");
        passenger_male.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        passengerPanel.add(passenger_male);
        passenger_male.setBounds(190, 140, 80, 30);

        buttonGroup1.add(passenger_female);
        passenger_female.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        passenger_female.setText("Female");
        passengerPanel.add(passenger_female);
        passenger_female.setBounds(300, 140, 80, 30);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Telephone No      :");
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        passengerPanel.add(jLabel10);
        jLabel10.setBounds(10, 180, 150, 30);

        passenger_phoneno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_phonenoActionPerformed(evt);
            }
        });
        passengerPanel.add(passenger_phoneno);
        passenger_phoneno.setBounds(190, 180, 270, 30);

        jButton6.setText("Submit");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        passengerPanel.add(jButton6);
        jButton6.setBounds(10, 240, 90, 40);

        jButton15.setText("Clear");
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        passengerPanel.add(jButton15);
        jButton15.setBounds(140, 240, 100, 40);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Passenger ID :");
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        passengerPanel.add(jLabel4);
        jLabel4.setBounds(10, 20, 150, 29);

        id_label.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        id_label.setForeground(new java.awt.Color(51, 51, 255));
        id_label.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        passengerPanel.add(id_label);
        id_label.setBounds(190, 20, 171, 34);

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        passengerPanel.add(jLabel39);
        jLabel39.setBounds(0, 0, 780, 390);

        mainPanel.addTab("tab8", passengerPanel);

        searchPanel.setBackground(new java.awt.Color(255, 255, 204));
        searchPanel.setLayout(null);

        jButton8.setBackground(new java.awt.Color(255, 204, 204));
        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(51, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bus.png"))); // NOI18N
        jButton8.setText("Bus");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton8);
        jButton8.setBounds(10, 21, 163, 35);

        jButton7.setBackground(new java.awt.Color(255, 204, 153));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/route.png"))); // NOI18N
        jButton7.setText("Route");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton7);
        jButton7.setBounds(10, 74, 163, 41);

        jButton4.setBackground(new java.awt.Color(255, 204, 102));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(102, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/passenger (1).png"))); // NOI18N
        jButton4.setText("Passenger");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton4);
        jButton4.setBounds(10, 139, 163, 39);

        jButton5.setBackground(new java.awt.Color(255, 153, 51));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(102, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/online-booking.png"))); // NOI18N
        jButton5.setText("Booking");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton5);
        jButton5.setBounds(10, 196, 163, 37);

        jButton11.setBackground(new java.awt.Color(255, 102, 51));
        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(102, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/contact-list.png"))); // NOI18N
        jButton11.setText("User");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton11);
        jButton11.setBounds(10, 251, 163, 37);

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus No", "No of Seats", "Time of Leave", "Time of Arrive", "Bus Route No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 591, 270));

        jTabbedPane10.addTab("tab6", jPanel6);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Route No", "Start from", "City 1", "City 2", "City 3", "Destination"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );

        jTabbedPane10.addTab("tab7", jPanel7);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Date of Birth", "Gender", "Phone Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );

        jTabbedPane10.addTab("tab8", jPanel8);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Start from", "Destination", "Date", "Bus No", "Time", "Buyer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );

        jTabbedPane10.addTab("tab9", jPanel10);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Date of Birth", "Gender", "Phone Number", "Position", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );

        jScrollPane3.getAccessibleContext().setAccessibleName("tab1");

        jTabbedPane10.addTab("tab10", jPanel13);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Bus No", "Arrived At", "Left At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );

        jTabbedPane10.addTab("tab6", jPanel15);

        jPanel14.add(jTabbedPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 0, 590, 290));
        jTabbedPane10.getAccessibleContext().setAccessibleName("tab3");
        jTabbedPane10.getAccessibleContext().setAccessibleDescription("");

        searchPanel.add(jPanel14);
        jPanel14.setBounds(180, 40, 601, 290);

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/status.png"))); // NOI18N
        jButton1.setText("Status");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 51), null, null));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton1);
        jButton1.setBounds(10, 300, 160, 36);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        searchPanel.add(jLabel17);
        jLabel17.setBounds(0, -40, 800, 470);

        mainPanel.addTab("tab3", searchPanel);

        jPanel17.setLayout(null);

        jButton10.setBackground(new java.awt.Color(255, 153, 153));
        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(102, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reservation.png"))); // NOI18N
        jButton10.setText("Booking");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 255), null, null));
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton10);
        jButton10.setBounds(19, 90, 140, 37);

        jButton20.setBackground(new java.awt.Color(255, 153, 153));
        jButton20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton20.setForeground(new java.awt.Color(102, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-check.png"))); // NOI18N
        jButton20.setText("Passenger");
        jButton20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 153, 153), null, null));
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton20);
        jButton20.setBounds(19, 33, 140, 39);

        jLabel27.setText("Name                 :");

        jLabel28.setText("Date of Birth      :");

        jLabel29.setText("Telephone No    :");

        jLabel30.setText("Gender              :  ");

        jButton21.setText("Search");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel1.setText("Passenger ID     :");

        passenger_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_comboboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("tab1", jPanel22);

        jButton23.setText("Delete");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton22.setText("Update");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(P_number, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(P_gender, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(P_name)
                    .addComponent(P_dob)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jButton22)))
                .addGap(0, 41, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(P_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(P_dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(P_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(P_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23)
                    .addComponent(jButton22))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("tab3", jPanel24);

        P_dob_update.setDateFormatString("yyyy-MM-dd");

        buttonGroup1.add(P_male_update);
        P_male_update.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        P_male_update.setText("Male");
        P_male_update.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonGroup1.add(P_female_update);
        P_female_update.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        P_female_update.setText("Female");

        P_name_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_name_updateActionPerformed(evt);
            }
        });

        P_number_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_number_updateActionPerformed(evt);
            }
        });

        jButton24.setText("Save");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(P_number_update)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(P_male_update, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(P_female_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(P_dob_update, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(P_name_update)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton24)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(P_name_update, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(P_dob_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P_female_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P_male_update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(P_number_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton24)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("tab2", jPanel23);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(passenger_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jTabbedPane3)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton21)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(passenger_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29))
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton21)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel18);

        jLabel31.setText("Invoice Number");

        jLabel32.setText("Booking from       :");

        jLabel33.setText("Booking to           :");

        jLabel34.setText("Date                    :");

        jLabel35.setText("Bus No                :");

        jLabel36.setText("Time                   : ");

        jButton25.setText("Search");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab3", jPanel20);

        B_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_nameActionPerformed(evt);
            }
        });

        jButton27.setText("Delete");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton26.setText("Update");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B_from, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(B_to)
                    .addComponent(B_date)
                    .addComponent(B_no)
                    .addComponent(B_time)
                    .addComponent(B_name))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton27)
                    .addComponent(jButton26))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(B_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(B_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(B_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(B_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(B_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jButton27)
                        .addGap(18, 18, 18)
                        .addComponent(jButton26)
                        .addGap(62, 62, 62))))
        );

        jTabbedPane2.addTab("tab2", jPanel21);

        B_from_update.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kegalle" }));

        jDateChooser4.setDateFormatString("yyyy-MM-dd");

        jButton28.setText("Save");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout B_date_updateLayout = new javax.swing.GroupLayout(B_date_update);
        B_date_update.setLayout(B_date_updateLayout);
        B_date_updateLayout.setHorizontalGroup(
            B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B_date_updateLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(B_date_updateLayout.createSequentialGroup()
                        .addGroup(B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(B_name_update, javax.swing.GroupLayout.Alignment.LEADING, 0, 82, Short.MAX_VALUE)
                            .addComponent(B_time_update, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B_no_update, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(B_date_updateLayout.createSequentialGroup()
                        .addGroup(B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(B_to_update, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B_from_update, javax.swing.GroupLayout.Alignment.LEADING, 0, 82, Short.MAX_VALUE))
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        B_date_updateLayout.setVerticalGroup(
            B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B_date_updateLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(B_from_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_to_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(B_date_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B_no_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_time_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(B_name_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab1", B_date_update);

        jLabel38.setText("Passenger name   :");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jButton25)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33))
                                    .addGap(113, 113, 113))
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(booking_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel38))
                        .addGap(31, 31, 31)
                        .addComponent(jTabbedPane2)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(booking_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel32)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton25))
        );

        jTabbedPane1.addTab("tab2", jPanel19);

        jPanel17.add(jTabbedPane1);
        jTabbedPane1.setBounds(204, 11, 498, 340);

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel17.add(jLabel41);
        jLabel41.setBounds(0, 0, 700, 390);

        mainPanel.addTab("tab6", jPanel17);

        jPanel1.add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    mainPanel.setSelectedIndex(0);
    
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
          mainPanel.setSelectedIndex(1);//to navigate between the tab in tabbed pane
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
         mainPanel.setSelectedIndex(4); 
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
           mainPanel.setSelectedIndex(5);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void passenger_phonenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_phonenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passenger_phonenoActionPerformed

    private void passenger_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passenger_nameActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(4);
        try
        {
            //open conncection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

            String sql = "Select User_name,User_dob,User_gender,User_phoneNo,User_position,User_address from User";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(3);
        try
        {
            //open conncection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

            String sql = "Select * from Booking";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(2);

        try
        {
            //open conncection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");
            //need to use user name and passwod at the end

            //    Statement st = con.createStatement();
            //mysql query
            String sql = "Select * from Passenger";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));

            //                while(rs.next())
            //                {
                //                    //data will be added until finish
                //                    String name = rs.getString("Passenger_name");//here  Passenger_name is coulumn name
                //                    String dob = rs.getString("Passenger_dob");
                //                    String  gender= rs.getString("Passenger_gender");
                //                    String phoneno = String.valueOf(rs.getInt("Passenger_phoneNo"));//convension due to int value
                //
                //                    //string array for stor data into jtable;
                //
                //                    String tbData[] = {name,dob,gender,phoneno};
                //                        DefaultTableMode1  tb1Mode1 = (DefaultTableMode1)jTable2.getModel();
                //
                //                        //add string array data into jTable
                //                        tb1Mode1.addRow(tbData);
                //                }

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(1);
        try
        {
            //open conncection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

            String sql = "Select * from Route";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(0);

        try
        {
            //open conncection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

            String sql = "Select * from Bus";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        mainPanel.setSelectedIndex(2);
      
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mainPanel.setSelectedIndex(3);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(5);        
                try
        {
            //open conncection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

            String sql = "select * from Status";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "insert into status values(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            Object time11=  jSpinner1.getValue();
            Object time22=  jSpinner2.getValue();
             
            String sql1 = "select * from Bus";
            PreparedStatement pst = con.prepareStatement(sql1);
           ResultSet rs = pst.executeQuery();//ResultSet is the return typed
           
           while(rs.next())
           {
               String Sbusno = rs.getString("Bus_BusNo");
               busno.addItem(Sbusno);
           }
            
            
           String selectedbusno = null;
           selectedbusno = busno.getSelectedItem().toString();
            
            pstmt.setString(2,selectedbusno);  
            
                    if (time11 instanceof Date) {
                        Date date = (Date)time11;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        String time1 = format.format(date);
                        pstmt.setString(3,time1);
                    }

                    if (time22 instanceof Date) {
                        Date date = (Date)time22;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        String time2 = format.format(date);
                        pstmt.setString(4,time2);
                    }
            
 
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "isertion succesful");
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
                try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "insert into passenger values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
           
             String id = id_label.getText();
             pstmt.setString(1, id);
            pstmt.setString(2,passenger_name.getText());
            pstmt.setString(3,((JTextField)passenger_dob.getDateEditor().getUiComponent()).getText());
            String dept = null;
            if(passenger_male.isSelected())
            {
                dept = passenger_male.getText();
            }
            if(passenger_female.isSelected())
            {
                dept = passenger_female.getText();
            }
            
            pstmt.setString(4,dept);
            pstmt.setInt(5,Integer.parseInt(passenger_phoneno.getText()));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insertion succesful");
            UpdateBookingPassengerID();//to add the new insert data into the booking panel passenger name combo box .this method wiil call and execute that operation
            UpdatePassengerID();//add the new insert data into the update panel passenger id combo box       
            InvoiceNoPassenger();//After succesfull insertion we need to increment the invoice  
                      
                         con.close();
                 
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
          try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "insert into booking values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
           
            
            
            
            
            
            
            String no = invoicelabel.getText();
            
           String selectedbooking = null;
           selectedbooking = booking_from.getSelectedItem().toString();
        
           String selectedbookedto = booking_to.getSelectedItem().toString();
           
           String selecteddate = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
           
           String selectedbusno = booking_busno.getSelectedItem().toString();
           
           String selectedtime = booking_time.getSelectedItem().toString();
           
           String selectedpassenger = booking_passenger.getSelectedItem().toString();
           
           pstmt.setString(1, no);
            pstmt.setString(2,selectedbooking); 
            pstmt.setString(3,selectedbookedto); 
            pstmt.setString(4,selecteddate); 
            pstmt.setString(5,selectedbusno); 
            pstmt.setString(6,selectedtime); 
            pstmt.setString(7,selectedpassenger);
            
            pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "isertion succesful");  
           UpdateBookingid();//to add the booking details to the updata panel booking id combo box
           InvoiceNo();//to increment the invoice number of the booking panel booking
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
    }//GEN-LAST:event_jButton9ActionPerformed
 
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        passenger_name.setText("");
        passenger_phoneno.setText("");//need to set up the clear for date and male female button

    }//GEN-LAST:event_jButton15ActionPerformed

    private void busnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busnoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_busnoActionPerformed

    private void booking_toActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booking_toActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_booking_toActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        dispose();
        MainScreen main = new  MainScreen();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        //when calling this every time multiplying the same element in combo box need to find a way to solve this
        jTabbedPane1.setSelectedIndex(1);

        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

        // TODO add your handling code here:
        jTabbedPane3.setSelectedIndex(0);
        jTabbedPane1.setSelectedIndex(0);

        
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        jTabbedPane3.setSelectedIndex(1);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

           
           
              String sql = "select * from Passenger where Passenger_id = ?";
           PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,passenger_combobox.getSelectedItem().toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                P_name.setText(rs.getString("Passenger_name"));
                
                P_dob.setText(rs.getString("Passenger_dob"));

                P_gender.setText(rs.getString("Passenger_gender"));

                //convert int to string to show 
                String phoneno = String.valueOf(rs.getInt("Passenger_phoneNo"));
                P_number.setText(phoneno);
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Record not found");
            }
            
                     con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
             jTabbedPane2.setSelectedIndex(1);
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

           
           
              String sql = "select * from Booking where Booking_invoiceid = ?";
           PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,booking_combobox.getSelectedItem().toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                B_from.setText(rs.getString("Booking_from"));
                
                B_to.setText(rs.getString("Booking_to"));

                B_date.setText(rs.getString("Booking_date"));
                B_no.setText(rs.getString("Booking_BusNo"));
                B_time.setText(rs.getString("Booking_time"));
                B_name.setText(rs.getString("Booking_buyer"));
                                                
                                               
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Record not found");
            }
            
                     con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
      BookingUpdate();

   
    }//GEN-LAST:event_jButton26ActionPerformed

    private void B_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_nameActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
                   try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "update booking set Booking_from = ?,Booking_to = ?, Booking_date = ?, Booking_BusNo = ?, Booking_time = ?, Booking_buyer = ? where Booking_invoiceid = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedbookingno = booking_combobox.getSelectedItem().toString();
      
            
           String selectedbooking = null;
           selectedbooking = B_from_update.getSelectedItem().toString();
        
           String selectedbookedto = B_to_update.getSelectedItem().toString();
           
           String selecteddate = ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText();
           
           String selectedbusno = B_no_update.getSelectedItem().toString();
           
           String selectedtime = B_time_update.getSelectedItem().toString();
           
           String selectedpassenger = B_name_update.getSelectedItem().toString();
           
           
            pstmt.setString(1,selectedbooking); 
            pstmt.setString(2,selectedbookedto); 
            pstmt.setString(3,selecteddate); 
            pstmt.setString(4,selectedbusno); 
            pstmt.setString(5,selectedtime); 
            pstmt.setString(6,selectedpassenger);
            pstmt.setString(7, selectedbookingno);
            pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record updated Successfully");  
 
           jTabbedPane2.setSelectedIndex(0);
           con.close();
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void P_name_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_name_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P_name_updateActionPerformed

    private void P_number_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_number_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P_number_updateActionPerformed

    private void passenger_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_comboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passenger_comboboxActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        jTabbedPane3.setSelectedIndex(2);
        
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
            try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "update Passenger set Passenger_name = ?, Passenger_dob = ?, Passenger_gender = ?, Passenger_phoneNo = ? where Passenger_id = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedpassengerno = passenger_combobox.getSelectedItem().toString();
   
           String selecteddate = ((JTextField)P_dob_update.getDateEditor().getUiComponent()).getText();
            String dept = null;
            if(P_male_update.isSelected())
            {
                dept = P_male_update.getText();
            }
            if(P_female_update.isSelected())
            {
                dept = P_female_update.getText();
            }
           
           
            pstmt.setString(1,P_name_update.getText()); 
            pstmt.setString(2,selecteddate); 
            pstmt.setString(3,dept); 
            pstmt.setInt(4,Integer.parseInt(P_number_update.getText()));
            pstmt.setString(5,selectedpassengerno); 
            pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record updated Successfully");  
 
           
           con.close();
           
            jTabbedPane3.setSelectedIndex(0);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
            try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "delete from Passenger where Passenger_id = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedpassengerno = passenger_combobox.getSelectedItem().toString(); 
           
          pstmt.setString(1,selectedpassengerno); 
          pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record Deleted Successfully");  
            int index = passenger_combobox.getSelectedIndex();//declare int for catch index
           passenger_combobox.removeItemAt(index);//remove item by select item
           
           P_name.setText("");
           P_dob.setText("");
           P_gender.setText("");
           P_number.setText("");
           con.close();
           
            jTabbedPane3.setSelectedIndex(0);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
      
                    try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "delete from Booking where Booking_invoiceid = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedpassengerno = booking_combobox.getSelectedItem().toString(); 
           
          pstmt.setString(1,selectedpassengerno); 
          pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record Deleted Successfully");  
           
           int index = booking_combobox.getSelectedIndex();//declare int for catch index
           booking_combobox.removeItemAt(index);//remove item by select item
           
           B_from.setText("");
           B_to.setText("");
           B_date.setText("");
           B_no.setText("");
           B_time.setText("");
           B_name.setText("");
           
            
           con.close();
            jTabbedPane2.setSelectedIndex(0);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void booking_passengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booking_passengerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_booking_passengerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField B_date;
    private javax.swing.JPanel B_date_update;
    private javax.swing.JTextField B_from;
    private javax.swing.JComboBox<String> B_from_update;
    private javax.swing.JTextField B_name;
    private javax.swing.JComboBox<String> B_name_update;
    private javax.swing.JTextField B_no;
    private javax.swing.JComboBox<String> B_no_update;
    private javax.swing.JTextField B_time;
    private javax.swing.JComboBox<String> B_time_update;
    private javax.swing.JTextField B_to;
    private javax.swing.JComboBox<String> B_to_update;
    private javax.swing.JTextField P_dob;
    private com.toedter.calendar.JDateChooser P_dob_update;
    private javax.swing.JRadioButton P_female_update;
    private javax.swing.JTextField P_gender;
    private javax.swing.JRadioButton P_male_update;
    private javax.swing.JTextField P_name;
    private javax.swing.JTextField P_name_update;
    private javax.swing.JTextField P_number;
    private javax.swing.JTextField P_number_update;
    private javax.swing.JPanel bookingMainPanel;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JComboBox<String> booking_busno;
    private javax.swing.JComboBox<String> booking_combobox;
    private javax.swing.JComboBox<String> booking_from;
    private javax.swing.JComboBox<String> booking_passenger;
    private javax.swing.JComboBox<String> booking_time;
    private javax.swing.JComboBox<String> booking_to;
    private javax.swing.JComboBox<String> busno;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel datelabel;
    private javax.swing.JLabel id_label;
    private javax.swing.JLabel invoicelabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTabbedPane mainPanel;
    private javax.swing.JPanel passengerPanel;
    private javax.swing.JComboBox<String> passenger_combobox;
    private com.toedter.calendar.JDateChooser passenger_dob;
    private javax.swing.JRadioButton passenger_female;
    private javax.swing.JRadioButton passenger_male;
    private javax.swing.JTextField passenger_name;
    private javax.swing.JTextField passenger_phoneno;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel timelabel;
    // End of variables declaration//GEN-END:variables
}
