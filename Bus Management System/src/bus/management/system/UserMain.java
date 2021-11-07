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
        ShowRouteNumber();
         UserAccountShow();
         InvoiceNoStatus(); 
        
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
    
    
        public void InvoiceNoStatus() 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");
            PreparedStatement pstmt ;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Max(Status_invoice) from Status");
            rs.next();
            
            rs.getString("Max(Status_invoice)");
            
            if(rs.getString("Max(Status_invoice)") == null)
            {
                statuslabel.setText("S-0000001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("Max(Status_invoice)").substring(2,rs.getString("Max(Status_invoice)").length()));
                id++;
                statuslabel.setText("S-" + String.format("%07d",id));
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
           sql = "select Bus_Time_Of_Arrive from Bus";
           PreparedStatement pst4 = con.prepareStatement(sql);
           ResultSet rs4 = pst4.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           
           while(rs4.next())
           {
              combo_box = rs4.getString("Bus_Time_Of_Arrive");//need to book the seat at the arrive time not the leave time
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
           sql = "select Bus_Time_Of_Arrive from Bus";
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
               busno_combobox.addItem(Sbusno);
               busno1.addItem(Sbusno);
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
        public void AdministrationPanelShow()
        {
        administration_panel.setSelectedIndex(1);
        }
           
        public void ShowRouteNumber()
        {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
                   
             //5th combo box
         String  sql = "select Route_RouteNO from Route";
           PreparedStatement pst5 = con.prepareStatement(sql);
           ResultSet rs5 = pst5.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs5.next())
           {
            String  combo_box = rs5.getString("Route_RouteNO");//here Bus_Busno is actual column name of the table
        
            route_number.addItem(combo_box);//add into jcomboBox
            B_route_no_update.addItem(combo_box);
            route_combobox.addItem(combo_box);
           } 
           
           con.close();
        }
        
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
         
        }
        
        public void UpdateStatusBookingid()
        {
            String id = bus_number.getText();
            busno.addItem(id);
            booking_busno.addItem(id);
            busno_combobox.addItem(id);
        }
        
        public void UpdateBusRouteNo()
        {
            String id = route_no.getText();
            route_number.addItem(id);
        }
        
        public void UpdateBookingTime(String time)
        {
           
           booking_time.addItem(time);
       }//This method is taking the time as string and add it to the booking_time combo box
        
        
        public void UserAccountShow()
        {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
                   
            String sql = "select User_username from User";
            PreparedStatement pst = con.prepareStatement(sql);
           ResultSet rs = pst.executeQuery();//ResultSet is the return typed
           //above statement we will get all Busno from table class
           while(rs.next())
           {
               String name = rs.getString("User_username");//here Bus_Busno is actual column name of the table
               user_name.addItem(name);//add into jcomboBox
     
           }
           
           con.close();
        }
        
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        }  
        
        
//        public void UpdateBusNo()
//        {
//            busno_combobox.
//        }
        
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
        jPanel35 = new javax.swing.JPanel();
        administration_panel = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        administration_panel_bg = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
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
        jLabel83 = new javax.swing.JLabel();
        statuslabel = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
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
        jPanel25 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        route_number = new javax.swing.JComboBox<>();
        Date date_arrive = new Date();
        SpinnerDateModel sm_arrive =
        new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        time_of_arrive = new javax.swing.JSpinner(sm_arrive);
        Date date_left = new Date();
        SpinnerDateModel sm_left =
        new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        time_of_leave = new javax.swing.JSpinner(sm_left);
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        bus_number = new javax.swing.JTextField();
        no_of_seats = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        route_no = new javax.swing.JTextField();
        city_1 = new javax.swing.JTextField();
        city_4 = new javax.swing.JTextField();
        city_2 = new javax.swing.JTextField();
        city_3 = new javax.swing.JTextField();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        start_from = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jButton40 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel44 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jButton42 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        busno_combobox = new javax.swing.JComboBox<>();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        B_seats = new javax.swing.JTextField();
        B_arrive = new javax.swing.JTextField();
        B_leave = new javax.swing.JTextField();
        B_routeno = new javax.swing.JTextField();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        B_seats_update = new javax.swing.JTextField();
        jButton45 = new javax.swing.JButton();
        B_route_no_update = new javax.swing.JComboBox<>();
        Date date_left_udpate = new Date();
        SpinnerDateModel sm_left_update =
        new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        B_left_time_update = new javax.swing.JSpinner(sm_left_update);
        Date date_arrive_update = new Date();
        SpinnerDateModel sm_arrive_update =
        new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        B_arrive_time_update = new javax.swing.JSpinner(sm_arrive_update);
        B_busno_update = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        route_combobox = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jButton46 = new javax.swing.JButton();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        R_to = new javax.swing.JTextField();
        R_city3 = new javax.swing.JTextField();
        R_city2 = new javax.swing.JTextField();
        R_city1 = new javax.swing.JTextField();
        R_from = new javax.swing.JTextField();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        B_date_update1 = new javax.swing.JPanel();
        jButton49 = new javax.swing.JButton();
        R_route_update = new javax.swing.JTextField();
        R_from_update = new javax.swing.JTextField();
        R_city1_update = new javax.swing.JTextField();
        R_city2_update = new javax.swing.JTextField();
        R_city3_update = new javax.swing.JTextField();
        R_to_update = new javax.swing.JTextField();
        jPanel45 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        user_name = new javax.swing.JComboBox<>();
        jButton52 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        busno1 = new javax.swing.JComboBox<>();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        statusid = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        arr_at = new javax.swing.JTextField();
        left_at = new javax.swing.JTextField();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        nameF = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        dobF = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        MaleB = new javax.swing.JRadioButton();
        FemaleB = new javax.swing.JRadioButton();
        jLabel53 = new javax.swing.JLabel();
        phoneNoF = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        positionF = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        addressF = new javax.swing.JTextArea();
        jLabel56 = new javax.swing.JLabel();
        usernameF = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        passwordF = new javax.swing.JPasswordField();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bus Management System - Home");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timelabel, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, -1));

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

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 170, 40));

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

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, -1));

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

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, -1));

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

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 170, -1));

        jPanel35.setBackground(new java.awt.Color(255, 102, 102));
        jPanel35.setToolTipText("");

        administration_panel.setBackground(new java.awt.Color(255, 102, 102));

        jPanel27.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        administration_panel.addTab("tab1", jPanel27);

        jPanel28.setBackground(new java.awt.Color(255, 102, 102));

        administration_panel_bg.setBackground(new java.awt.Color(255, 102, 102));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Administration Panel");
        jLabel46.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel26.setBackground(new java.awt.Color(204, 153, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel43.setToolTipText("");

        jButton30.setBackground(new java.awt.Color(255, 153, 204));
        jButton30.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton30.setForeground(new java.awt.Color(0, 0, 204));
        jButton30.setText("DataBase");
        jButton30.setBorderPainted(false);
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database (1).png"))); // NOI18N
        jLabel47.setToolTipText("");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(204, 153, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync.png"))); // NOI18N
        jLabel44.setToolTipText("");
        jPanel29.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        jButton31.setBackground(new java.awt.Color(255, 153, 204));
        jButton31.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(0, 0, 204));
        jButton31.setText("Update");
        jButton31.setBorderPainted(false);
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel29.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 120, 40));

        jPanel30.setBackground(new java.awt.Color(204, 153, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setToolTipText("");
        jPanel30.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        jButton32.setBackground(new java.awt.Color(255, 153, 204));
        jButton32.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton32.setForeground(new java.awt.Color(0, 0, 204));
        jButton32.setText("User Account");
        jButton32.setBorderPainted(false);
        jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 120, 40));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/management (1).png"))); // NOI18N
        jLabel48.setToolTipText("");
        jPanel30.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 38));

        javax.swing.GroupLayout administration_panel_bgLayout = new javax.swing.GroupLayout(administration_panel_bg);
        administration_panel_bg.setLayout(administration_panel_bgLayout);
        administration_panel_bgLayout.setHorizontalGroup(
            administration_panel_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(administration_panel_bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(administration_panel_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(administration_panel_bgLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(administration_panel_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        administration_panel_bgLayout.setVerticalGroup(
            administration_panel_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(administration_panel_bgLayout.createSequentialGroup()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
            .addGroup(administration_panel_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(administration_panel_bgLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(62, 62, 62)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(49, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(administration_panel_bg, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(administration_panel_bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        administration_panel.addTab("tab2", jPanel28);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(administration_panel)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(administration_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 180, 240));

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Dashboard");
        jLabel49.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        mainPanel.setBackground(new java.awt.Color(255, 255, 204));

        statusPanel.setBackground(new java.awt.Color(255, 255, 204));
        statusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Status ID         : ");
        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 30));

        jDateChooser1.setBackground(new java.awt.Color(51, 51, 255));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        statusPanel.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 120, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Bus No     :");
        jLabel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 140, 30));

        busno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busnoActionPerformed(evt);
            }
        });
        statusPanel.add(busno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 120, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Arrive At   :");
        jLabel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, 30));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("Left At :");
        jLabel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 140, 30));

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1,"HH:mm:ss");
        jSpinner1.setEditor(de);
        statusPanel.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 120, 30));

        JSpinner.DateEditor de1 = new JSpinner.DateEditor(jSpinner2,"HH:mm:ss");
        jSpinner2.setEditor(de1);
        statusPanel.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 120, 30));

        jButton17.setText("Submit");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        statusPanel.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, 40));

        jButton18.setText("Clear");
        jButton18.setActionCommand("");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statusPanel.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 90, 40));

        jLabel83.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel83.setText("Date         : ");
        jLabel83.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, 30));

        statuslabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        statuslabel.setForeground(new java.awt.Color(0, 0, 255));
        statuslabel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        statusPanel.add(statuslabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 150, 30));

        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        statusPanel.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 390));

        mainPanel.addTab("tab1", statusPanel);

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 255, 204), null, null));
        jPanel4.setLayout(null);

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton3.setForeground(new java.awt.Color(51, 102, 255));
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
        jLabel40.setBounds(0, 0, 860, 390);

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
        jButton8.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton7.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton4.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton5.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton11.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton1.setForeground(new java.awt.Color(51, 102, 255));
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
        jButton10.setForeground(new java.awt.Color(51, 102, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/online-booking.png"))); // NOI18N
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
        jButton20.setForeground(new java.awt.Color(51, 102, 255));
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
        jLabel41.setBounds(0, 0, 820, 390);

        mainPanel.addTab("tab6", jPanel17);

        jPanel25.setLayout(null);

        jButton29.setBackground(new java.awt.Color(255, 153, 153));
        jButton29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton29.setForeground(new java.awt.Color(51, 102, 255));
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bus (1).png"))); // NOI18N
        jButton29.setText("Bus");
        jButton29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 153), null, null));
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton29.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel25.add(jButton29);
        jButton29.setBounds(20, 60, 140, 39);

        jButton33.setBackground(new java.awt.Color(255, 153, 153));
        jButton33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton33.setForeground(new java.awt.Color(51, 102, 255));
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/route (1).png"))); // NOI18N
        jButton33.setText("Route");
        jButton33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 153), null, null));
        jButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton33.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel25.add(jButton33);
        jButton33.setBounds(20, 120, 140, 39);

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel25.add(jLabel42);
        jLabel42.setBounds(0, 0, 790, 390);

        mainPanel.addTab("tab7", jPanel25);

        jPanel31.setLayout(null);

        jLabel59.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel59.setText("Bus No :");
        jLabel59.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel31.add(jLabel59);
        jLabel59.setBounds(10, 48, 140, 30);

        jLabel60.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel60.setText("No of Seats  :");
        jLabel60.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel31.add(jLabel60);
        jLabel60.setBounds(10, 96, 140, 30);

        jLabel61.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel61.setText("Time of Arrive :");
        jLabel61.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel31.add(jLabel61);
        jLabel61.setBounds(10, 144, 140, 30);

        jLabel62.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel62.setText("Time of Leave :");
        jLabel62.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel31.add(jLabel62);
        jLabel62.setBounds(10, 182, 140, 30);

        jLabel63.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel63.setText("Route No :");
        jLabel63.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel31.add(jLabel63);
        jLabel63.setBounds(10, 223, 140, 30);

        jPanel31.add(route_number);
        route_number.setBounds(170, 220, 160, 30);

        JSpinner.DateEditor de_arrive = new JSpinner.DateEditor(time_of_arrive,"HH:mm:ss");
        time_of_arrive.setEditor(de_arrive);
        jPanel31.add(time_of_arrive);
        time_of_arrive.setBounds(168, 144, 112, 26);

        JSpinner.DateEditor de_leave = new JSpinner.DateEditor(time_of_leave,"HH:mm:ss");
        time_of_leave.setEditor(de_leave);
        jPanel31.add(time_of_leave);
        time_of_leave.setBounds(168, 181, 112, 27);

        jButton36.setText("Submit");
        jButton36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton36);
        jButton36.setBounds(10, 287, 90, 40);

        jButton37.setText("Clear");
        jButton37.setActionCommand("");
        jButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton37);
        jButton37.setBounds(168, 287, 90, 40);
        jPanel31.add(bus_number);
        bus_number.setBounds(170, 50, 170, 30);
        jPanel31.add(no_of_seats);
        no_of_seats.setBounds(170, 100, 170, 30);

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel31.add(jLabel70);
        jLabel70.setBounds(0, 0, 860, 390);

        mainPanel.addTab("tab8", jPanel31);

        jPanel32.setLayout(null);

        jLabel64.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel64.setText("Route No :");
        jLabel64.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.add(jLabel64);
        jLabel64.setBounds(10, 77, 140, 30);

        jLabel65.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel65.setText("Start from");
        jLabel65.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.add(jLabel65);
        jLabel65.setBounds(10, 117, 140, 30);

        jLabel66.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel66.setText("City 1 :");
        jLabel66.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.add(jLabel66);
        jLabel66.setBounds(10, 157, 140, 30);

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel67.setText("City 2 :");
        jLabel67.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.add(jLabel67);
        jLabel67.setBounds(10, 193, 140, 30);

        jLabel68.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel68.setText("City 3 :");
        jLabel68.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.add(jLabel68);
        jLabel68.setBounds(10, 229, 140, 30);

        jLabel69.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel69.setText("Destination :");
        jLabel69.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.add(jLabel69);
        jLabel69.setBounds(10, 265, 140, 30);
        jPanel32.add(route_no);
        route_no.setBounds(200, 70, 170, 30);
        jPanel32.add(city_1);
        city_1.setBounds(200, 150, 170, 30);
        jPanel32.add(city_4);
        city_4.setBounds(200, 270, 170, 30);
        jPanel32.add(city_2);
        city_2.setBounds(200, 190, 170, 30);
        jPanel32.add(city_3);
        city_3.setBounds(200, 230, 170, 30);

        jButton38.setText("Submit");
        jButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel32.add(jButton38);
        jButton38.setBounds(410, 160, 90, 40);

        jButton39.setText("Clear");
        jButton39.setActionCommand("");
        jButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel32.add(jButton39);
        jButton39.setBounds(530, 160, 90, 40);
        jPanel32.add(start_from);
        start_from.setBounds(200, 110, 170, 30);

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel32.add(jLabel71);
        jLabel71.setBounds(0, 0, 930, 390);

        mainPanel.addTab("tab9", jPanel32);

        jPanel36.setLayout(null);

        jButton40.setBackground(new java.awt.Color(255, 153, 153));
        jButton40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton40.setForeground(new java.awt.Color(51, 102, 255));
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/route (1).png"))); // NOI18N
        jButton40.setText("Route");
        jButton40.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 255), null, null));
        jButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton40);
        jButton40.setBounds(20, 90, 140, 40);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("tab3", jPanel44);

        jLabel72.setText("No of Seats        :");

        jLabel73.setText("Time of Arrive     :");

        jLabel74.setText("Route No            :");

        jLabel75.setText("Time of Leave     :  ");

        jButton42.setText("Search");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jLabel76.setText("Bus No               :");

        busno_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busno_comboboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("tab1", jPanel38);

        jButton43.setText("Delete");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setText("Update");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B_routeno, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B_leave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B_seats)
                    .addComponent(B_arrive)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jButton43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jButton44)))
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(B_seats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(B_arrive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(B_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(B_routeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton43)
                    .addComponent(jButton44))
                .addContainerGap())
        );

        jTabbedPane5.addTab("tab3", jPanel39);

        B_seats_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_seats_updateActionPerformed(evt);
            }
        });

        jButton45.setText("Save");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de_leave_update = new JSpinner.DateEditor(B_left_time_update,"HH:mm:ss");
        B_left_time_update.setEditor(de_leave_update);

        JSpinner.DateEditor de_arrive_update = new JSpinner.DateEditor(B_arrive_time_update,"HH:mm:ss");

        B_arrive_time_update.setEditor(de_arrive_update);

        B_busno_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_busno_updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B_route_no_update, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B_left_time_update, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B_seats_update))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jButton45))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B_busno_update, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B_arrive_time_update, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B_busno_update, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(B_seats_update, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(B_arrive_time_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(B_left_time_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(B_route_no_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton45)
                .addContainerGap())
        );

        jTabbedPane5.addTab("tab2", jPanel40);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(busno_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton42))
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane5)
                .addGap(19, 19, 19))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel76)
                            .addComponent(busno_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel72)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel73)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel75)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel74)
                        .addGap(18, 18, 18)
                        .addComponent(jButton42))
                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("tab1", jPanel37);

        jLabel77.setText("Route No        :");

        jLabel78.setText("Start from       :");

        jLabel79.setText("City 1              :");

        jLabel80.setText("City 2              :");

        jLabel81.setText("City 3              :");

        jLabel82.setText("Destination     : ");

        jButton46.setText("Search");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("tab3", jPanel42);

        jButton47.setText("Delete");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setText("Update");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(R_city1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(R_city2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(R_city3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton47, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton48, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(R_from, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(R_to, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(0, 55, Short.MAX_VALUE)
                .addComponent(R_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jButton47)
                        .addGap(18, 18, 18)
                        .addComponent(jButton48)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(R_city1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(R_city2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(R_city3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(R_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );

        jTabbedPane6.addTab("tab2", jPanel43);

        jButton49.setText("Save");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout B_date_update1Layout = new javax.swing.GroupLayout(B_date_update1);
        B_date_update1.setLayout(B_date_update1Layout);
        B_date_update1Layout.setHorizontalGroup(
            B_date_update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B_date_update1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(B_date_update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B_date_update1Layout.createSequentialGroup()
                        .addGroup(B_date_update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(R_to_update, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(R_city3_update, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(R_city1_update, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(R_city2_update, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(11, 11, 11)
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(B_date_update1Layout.createSequentialGroup()
                        .addGroup(B_date_update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(R_from_update, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(R_route_update, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        B_date_update1Layout.setVerticalGroup(
            B_date_update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B_date_update1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(R_route_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R_from_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(B_date_update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(B_date_update1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton49))
                    .addGroup(B_date_update1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(R_city1_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(R_city2_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(R_city3_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(R_to_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("tab1", B_date_update1);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jButton46)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel82)
                                    .addComponent(jLabel81)
                                    .addComponent(jLabel80)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(112, 112, 112))
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(route_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jTabbedPane6)))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(route_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel78)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel79)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel80)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel81)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel82)
                        .addGap(38, 38, 38)))
                .addComponent(jButton46))
        );

        jTabbedPane4.addTab("tab2", jPanel41);

        jButton50.setBackground(new java.awt.Color(255, 153, 153));
        jButton50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton50.setForeground(new java.awt.Color(51, 102, 255));
        jButton50.setText("User Account");
        jButton50.setToolTipText("");
        jButton50.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 153, 153), null, null));
        jButton50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton50.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton51.setBackground(new java.awt.Color(255, 153, 153));
        jButton51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton51.setForeground(new java.awt.Color(51, 102, 255));
        jButton51.setText("Status");
        jButton51.setToolTipText("");
        jButton51.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 153, 153), null, null));
        jButton51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton51.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("tab1", jPanel46);

        jTextField1.setText("User Name    :");

        jButton52.setText("Delete");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(user_name, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jTabbedPane7.addTab("tab2", jPanel47);

        jPanel48.setLayout(null);

        jLabel84.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel84.setText("Status ID   : ");
        jLabel84.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel48.add(jLabel84);
        jLabel84.setBounds(10, 91, 111, 19);

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel85.setText("Date         : ");
        jLabel85.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel48.add(jLabel85);
        jLabel85.setBounds(10, 11, 111, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Bus No     :");
        jLabel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel48.add(jLabel16);
        jLabel16.setBounds(10, 43, 111, 30);

        busno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busno1ActionPerformed(evt);
            }
        });
        jPanel48.add(busno1);
        busno1.setBounds(139, 48, 114, 20);

        jDateChooser3.setBackground(new java.awt.Color(51, 51, 255));
        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        jPanel48.add(jDateChooser3);
        jDateChooser3.setBounds(139, 11, 103, 26);

        statusid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusidActionPerformed(evt);
            }
        });
        jPanel48.add(statusid);
        statusid.setBounds(139, 91, 114, 23);

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel86.setText("Arrive At   :");
        jLabel86.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel48.add(jLabel86);
        jLabel86.setBounds(10, 127, 111, 22);

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel87.setText("Left At :");
        jLabel87.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel48.add(jLabel87);
        jLabel87.setBounds(10, 160, 111, 20);
        jPanel48.add(arr_at);
        arr_at.setBounds(139, 128, 114, 20);
        jPanel48.add(left_at);
        left_at.setBounds(139, 165, 114, 20);

        jButton54.setText("Search");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jPanel48.add(jButton54);
        jButton54.setBounds(30, 200, 88, 23);

        jButton55.setText("Delete");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });
        jPanel48.add(jButton55);
        jButton55.setBounds(190, 200, 88, 23);

        jButton56.setText("OK");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });
        jPanel48.add(jButton56);
        jButton56.setBounds(260, 90, 60, 23);

        jTabbedPane7.addTab("tab3", jPanel48);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane7)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane4.addTab("tab4", jPanel45);

        jPanel36.add(jTabbedPane4);
        jTabbedPane4.setBounds(204, 11, 498, 340);

        jButton41.setBackground(new java.awt.Color(255, 153, 153));
        jButton41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton41.setForeground(new java.awt.Color(51, 102, 255));
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bus (1).png"))); // NOI18N
        jButton41.setText("Bus");
        jButton41.setToolTipText("");
        jButton41.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 153, 153), null, null));
        jButton41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton41.setPreferredSize(new java.awt.Dimension(105, 35));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton41);
        jButton41.setBounds(19, 33, 140, 39);

        jButton53.setBackground(new java.awt.Color(255, 153, 153));
        jButton53.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton53.setForeground(new java.awt.Color(51, 102, 255));
        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-message.png"))); // NOI18N
        jButton53.setText("Delete");
        jButton53.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 255), null, null));
        jButton53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton53);
        jButton53.setBounds(20, 210, 140, 40);

        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel36.add(jLabel88);
        jLabel88.setBounds(0, 0, 860, 390);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        mainPanel.addTab("tab10", jPanel33);

        jPanel34.setLayout(null);

        jLabel50.setBackground(new java.awt.Color(51, 0, 255));
        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel50.setText("Name                     : ");
        jLabel50.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel50);
        jLabel50.setBounds(10, 20, 161, 27);

        nameF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFActionPerformed(evt);
            }
        });
        jPanel34.add(nameF);
        nameF.setBounds(210, 20, 162, 20);

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel51.setText("Date of Birth          :");
        jLabel51.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel51);
        jLabel51.setBounds(10, 50, 161, 27);

        dobF.setDateFormatString("yyyy-MM-dd");
        jPanel34.add(dobF);
        dobF.setBounds(210, 50, 162, 27);

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel52.setText("Gender                  :");
        jLabel52.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel52);
        jLabel52.setBounds(10, 91, 161, 27);

        MaleB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        MaleB.setText("Male");
        jPanel34.add(MaleB);
        MaleB.setBounds(208, 93, 53, 25);

        FemaleB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        FemaleB.setText("Female");
        jPanel34.add(FemaleB);
        FemaleB.setBounds(294, 92, 65, 27);

        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel53.setText("Telephone No        :");
        jLabel53.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel53);
        jLabel53.setBounds(10, 138, 161, 27);

        phoneNoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNoFActionPerformed(evt);
            }
        });
        jPanel34.add(phoneNoF);
        phoneNoF.setBounds(208, 140, 162, 26);

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel54.setText("Position                 :");
        jLabel54.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel54);
        jLabel54.setBounds(10, 184, 151, 27);
        jPanel34.add(positionF);
        positionF.setBounds(208, 186, 162, 27);

        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel55.setText("Address                 :");
        jLabel55.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel55);
        jLabel55.setBounds(10, 237, 161, 27);

        addressF.setColumns(20);
        addressF.setRows(5);
        jScrollPane7.setViewportView(addressF);

        jPanel34.add(jScrollPane7);
        jScrollPane7.setBounds(208, 237, 181, 96);

        jLabel56.setBackground(new java.awt.Color(51, 0, 255));
        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel56.setText("User Name         : ");
        jLabel56.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel56);
        jLabel56.setBounds(435, 11, 169, 32);

        usernameF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFActionPerformed(evt);
            }
        });
        jPanel34.add(usernameF);
        usernameF.setBounds(614, 11, 176, 26);

        jLabel57.setBackground(new java.awt.Color(51, 0, 255));
        jLabel57.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel57.setText("Password            : ");
        jLabel57.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.add(jLabel57);
        jLabel57.setBounds(435, 61, 169, 27);

        passwordF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel34.add(passwordF);
        passwordF.setBounds(614, 61, 176, 27);

        jButton34.setText("Submit");
        jButton34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton34);
        jButton34.setBounds(449, 159, 104, 40);

        jButton35.setText("Clear");
        jButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton35);
        jButton35.setBounds(610, 160, 100, 40);

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Webp.net-resizeimage.jpg"))); // NOI18N
        jPanel34.add(jLabel58);
        jLabel58.setBounds(0, 0, 810, 390);

        mainPanel.addTab("tab11", jPanel34);

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
         
            String sql = "insert into status values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,statuslabel.getText());
            pstmt.setString(2,((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            Object time11=  jSpinner1.getValue();
            Object time22=  jSpinner2.getValue();
             
//            String sql1 = "select * from Bus";
//            PreparedStatement pst = con.prepareStatement(sql1);
//           ResultSet rs = pst.executeQuery();//ResultSet is the return typed
//           
//           while(rs.next())
//           {
//               String Sbusno = rs.getString("Bus_BusNo");
//               busno.addItem(Sbusno);
//           }
            
            
           String selectedbusno = null;
           selectedbusno = busno.getSelectedItem().toString();
            
            pstmt.setString(3,selectedbusno);  
            
                    if (time11 instanceof Date) {
                        Date date = (Date)time11;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        String time1 = format.format(date);
                        pstmt.setString(4,time1);
                    }

                    if (time22 instanceof Date) {
                        Date date = (Date)time22;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        String time2 = format.format(date);
                        pstmt.setString(5,time2);
                    }
            
 
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insertion succesful");
             InvoiceNoStatus(); 
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
        int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to log out ?","Log out",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
        dispose();
        MainScreen main = new  MainScreen();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
        }
        else
        {
            
        }
        
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
             int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Save ?","Saved",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
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
         int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Save ?","Saved",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
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
        }   
        
        
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
          
         int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Delete ?","Deleted",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
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
        }
       
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
       int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Delete ?","Deleted",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
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
        }
                   
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void booking_passengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booking_passengerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_booking_passengerActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        mainPanel.setSelectedIndex(10);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        mainPanel.setSelectedIndex(9);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        mainPanel.setSelectedIndex(6);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
            mainPanel.setSelectedIndex(7);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
            mainPanel.setSelectedIndex(8);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void nameFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFActionPerformed

    private void phoneNoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNoFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNoFActionPerformed

    private void usernameFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        //add button action
        /*   String name = nameF.getText(); // get the text from the nam
        String dob = dobF.getText();
        String phoneNo = phoneNoF.getText();
        String position = positionF.getText();
        String address = addressF.getText();
        String username = usernameF.getText();
        char[] password = passwordF.getPassword();
        */
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //String databaseURL = "jdbc:mysql://localhost:3307/bus_management_system";
            //  Connection con = (Connection) DriverManager.getConnection(databaseURL,"root", "");
            //Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");

            //  String insertQuery = "insert into User Values('"+name+"',"+dob+"','"+null+"','"+phoneNo+"','"+position+"','"+address+"','"+username+"','"+Arrays.toString(password)+"','"+position+"')";
            // Statement stat = con.createStatement();
            //int x = stat.executeUpdate(insertQuery);
            //System.out.print(x);
            String sql = "insert into user values(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,nameF.getText());
            pstmt.setString(2,((JTextField)dobF.getDateEditor().getUiComponent()).getText());
            String dept = null;
            if(MaleB.isSelected())
            {
                dept = MaleB.getText();
            }
            if(FemaleB.isSelected())
            {
                dept = FemaleB.getText();
            }

            pstmt.setString(3,dept);
            pstmt.setInt(4,Integer.parseInt(phoneNoF.getText()));
            pstmt.setString(5,positionF.getText());
            pstmt.setString(6,addressF.getText());
            pstmt.setString(7,usernameF.getText());
            String pwd = new String(passwordF.getPassword());  // to get the input from  the password field
            pstmt.setString(8,pwd);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insertion succesful");
            
            user_name.addItem(usernameF.getText());
            
            nameF.setText("");
            phoneNoF.setText("");
            positionF.setText("");
            addressF.setText("");
            usernameF.setText("");
            passwordF.setText("");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        nameF.setText("");
        phoneNoF.setText("");
        positionF.setText("");
        addressF.setText("");
        usernameF.setText("");
        passwordF.setText("");
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
         try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "insert into Bus values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,bus_number.getText());
            pstmt.setInt(2,Integer.parseInt(no_of_seats.getText()));
            Object time11=  time_of_arrive.getValue();
            Object time22=  time_of_leave.getValue();
             
//            String sql1 = "select * from Bus";
//            PreparedStatement pst = con.prepareStatement(sql1);
//           ResultSet rs = pst.executeQuery();//ResultSet is the return typed
//           
//           while(rs.next())
//           {
//               String Sbusno = rs.getString("Bus_BusNo");
//               busno.addItem(Sbusno);
//           }
            
                String time1 = null;
                    if (time11 instanceof Date) {
                        Date date = (Date)time11;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        time1 = format.format(date);
                        pstmt.setString(3,time1);
                    }

                    if (time22 instanceof Date) {
                        Date date = (Date)time22;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        String time2 = format.format(date);
                        pstmt.setString(4,time2);
                    }
                    
                    
           String selectedrouteno = null;
           selectedrouteno = route_number.getSelectedItem().toString();
            
            pstmt.setString(5,selectedrouteno); 
 
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insertion succesful");
            UpdateStatusBookingid();
            UpdateBookingTime( time1);//to add the time to the booking panel
            busno1.addItem(bus_number.getText());
            con.close();
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        bus_number.setText("");
        no_of_seats.setText("");
        
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
         try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "insert into Route values(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,route_no.getText());
            pstmt.setString(2,start_from.getText());
            pstmt.setString(3,city_1.getText());
            pstmt.setString(4,city_2.getText());
            pstmt.setString(5,city_3.getText());
            pstmt.setString(6,city_4.getText());
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insertion succesful");
            UpdateBusRouteNo();
            
            con.close();
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        route_no.setText("");
        start_from.setText("");
        city_1.setText("");
        city_2.setText("");
        city_3.setText("");
        city_4.setText("");
        
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        jTabbedPane4.setSelectedIndex(2);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        jTabbedPane4.setSelectedIndex(1);
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
                     jTabbedPane5.setSelectedIndex(1);
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

           
           
              String sql = "select * from Bus where Bus_BusNo = ?";
           PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,busno_combobox.getSelectedItem().toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                B_seats.setText(rs.getString("Bus_No_Of_Seats"));
                
                B_arrive.setText(rs.getString("Bus_Time_Of_Arrive"));

                B_leave.setText(rs.getString("Bus_Time_Of_Leave"));
                B_routeno.setText(rs.getString("Bus_RouteNo"));
                                                
                                               
                
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
        
    }//GEN-LAST:event_jButton42ActionPerformed

    private void busno_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busno_comboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busno_comboboxActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Delete ?","Deleted",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
 try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "delete from Bus where Bus_BusNo = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedbusno = busno_combobox.getSelectedItem().toString(); 
           
          pstmt.setString(1,selectedbusno); 
          pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record Deleted Successfully");  
            int index = busno_combobox.getSelectedIndex();//declare int for catch index
           busno_combobox.removeItemAt(index);//remove item by select item
           
           B_seats.setText("");
           B_arrive.setText("");
           B_leave.setText("");
           B_routeno.setText("");
           con.close();
           
            jTabbedPane5.setSelectedIndex(0);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        }
 
        
              
        
       
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
        jTabbedPane5.setSelectedIndex(2);
    }//GEN-LAST:event_jButton44ActionPerformed

    private void B_seats_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_seats_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_seats_updateActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Save ?","Saved",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
 try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "update Bus set Bus_BusNo = ?, Bus_No_Of_Seats = ?, Bus_Time_Of_Arrive = ?, Bus_Time_Of_Arrive = ? ,Bus_RouteNo =? where Bus_BusNo = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
            pstmt.setString(1,B_busno_update.getText());
            pstmt.setInt(2,Integer.parseInt(B_seats_update.getText()));
            Object time11=  B_arrive_time_update.getValue();
            Object time22=  B_left_time_update.getValue();
             
//            String sql1 = "select * from Bus";
//            PreparedStatement pst = con.prepareStatement(sql1);
//           ResultSet rs = pst.executeQuery();//ResultSet is the return typed
//           
//           while(rs.next())
//           {
//               String Sbusno = rs.getString("Bus_BusNo");
//               busno.addItem(Sbusno);
//           }
            
                String time1 = null;
                    if (time11 instanceof Date) {
                        Date date = (Date)time11;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        time1 = format.format(date);
                        pstmt.setString(3,time1);
                    }

                    if (time22 instanceof Date) {
                        Date date = (Date)time22;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//need to import the SinmpleDateFormat
                        String time2 = format.format(date);
                        pstmt.setString(4,time2);
                    }
                    
                    
           String selectedrouteno = null;
           selectedrouteno = B_route_no_update.getSelectedItem().toString();
            
            pstmt.setString(5,selectedrouteno); 
            pstmt.setString(6,busno_combobox.getSelectedItem().toString());
            pstmt.executeUpdate();
           int index = busno_combobox.getSelectedIndex();//declare int for catch index
           busno_combobox.removeItemAt(index);
           
           busno_combobox.addItem(B_busno_update.getText());
           JOptionPane.showMessageDialog(null, "Record updated Successfully");  
            con.close();
           jTabbedPane5.setSelectedIndex(0);
         }
           catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
           
        }
        
        

    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
        jTabbedPane6.setSelectedIndex(1);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

           
           
              String sql = "select * from Route where Route_RouteNO = ?";
           PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,route_combobox.getSelectedItem().toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                R_from.setText(rs.getString("Route_from"));
                
                R_city1.setText(rs.getString("Route_city1"));

                R_city2.setText(rs.getString("Route_city2"));
                R_city3.setText(rs.getString("Route_city3"));
                R_to.setText(rs.getString("Route_To"));
                                                
                                               
                
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
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
         int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Delete ?","Deleted",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
        try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "delete from Route where Route_RouteNO = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedrouteno = route_combobox.getSelectedItem().toString(); 
           
          pstmt.setString(1,selectedrouteno); 
          pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record Deleted Successfully");  
            int index = route_combobox.getSelectedIndex();//declare int for catch index
           route_combobox.removeItemAt(index);//remove item by select item
           route_number.removeItem(selectedrouteno);
           R_from.setText("");
           R_city1.setText("");
           R_city2.setText("");
           R_city3.setText("");
           R_to.setText("");
           con.close();
           
            jTabbedPane5.setSelectedIndex(0);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        }
        
         
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
        jTabbedPane6.setSelectedIndex(2);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Save ?","Saved",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
            try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "update Route set Route_RouteNO = ?, Route_from = ?, Route_city1 = ?, Route_city2 = ?, Route_city3 = ?, Route_to = ? where Route_RouteNO = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,R_route_update.getText());
            pstmt.setString(2,R_from_update.getText());
            pstmt.setString(3,R_city1_update.getText());
            pstmt.setString(4,R_city2_update.getText());
            pstmt.setString(5,R_city3_update.getText());
            pstmt.setString(6,R_to_update.getText());
            pstmt.setString(7,route_combobox.getSelectedItem().toString());
            
            pstmt.executeUpdate();
           int index = route_combobox.getSelectedIndex();//declare int for catch index
           route_combobox.removeItemAt(index);
           
           route_combobox.addItem(R_route_update.getText());
           
    
           route_number.removeItem(route_combobox.getSelectedItem().toString());
           
           route_number.addItem(R_route_update.getText());
           
           JOptionPane.showMessageDialog(null, "Record updated Successfully");  
   
            
            con.close();
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
        }
        
                               
    }//GEN-LAST:event_jButton49ActionPerformed

    private void B_busno_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_busno_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_busno_updateActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
        jTabbedPane7.setSelectedIndex(1);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
        jTabbedPane7.setSelectedIndex(2);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
        int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Delete ?","Deleted",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
        try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "delete from User where User_username = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedname = user_name.getSelectedItem().toString(); 
           
          pstmt.setString(1,selectedname); 
          pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record Deleted Successfully");  
            int index = user_name.getSelectedIndex();//declare int for catch index
           user_name.removeItemAt(index);//remove item by select item
           
           con.close();
           
            jTabbedPane7.setSelectedIndex(0);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        
        }
        
        
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
         jTabbedPane4.setSelectedIndex(3);
        
    }//GEN-LAST:event_jButton53ActionPerformed

    private void busno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busno1ActionPerformed

    private void statusidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusidActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_statusidActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
         statusid.removeAllItems();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

           
          
              String sql = "select Status_invoice from Status where Status_date = ? and Status_BusNo = ? ";
           PreparedStatement pstmt = con.prepareStatement(sql);
          
           pstmt.setString(1,((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText());
            pstmt.setString(2,busno1.getSelectedItem().toString());
            ResultSet rs = pstmt.executeQuery();
          
            
            sql = "select Status_invoice from Status where Status_date = ? and Status_BusNo = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText());
            pstmt.setString(2,busno1.getSelectedItem().toString());
            ResultSet rs1 = pstmt.executeQuery();
          



                                               
            if(rs.next())
            {                                

                
                while(rs1.next())
                {
                    String combo_box = rs1.getString("Status_invoice");//here Bus_Busno is actual column name of the table
                    statusid.addItem(combo_box);//add into jcomboBox
                }
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
        
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
         int YesorNo = JOptionPane.showConfirmDialog(null,"Do you want to Delete ?","Deleted",JOptionPane.YES_NO_OPTION);
        
        if(YesorNo == 0)
        {
        try{       
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system","root","1406");
         
            String sql = "delete from Status where Status_invoice = ?"; 
            PreparedStatement pstmt = con.prepareStatement(sql);
     
          String  selectedbusno = statusid.getSelectedItem().toString(); 
           
          pstmt.setString(1,selectedbusno); 
          pstmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Record Deleted Successfully");  
            int index = statusid.getSelectedIndex();//declare int for catch index
           statusid.removeItemAt(index);//remove item by select item
           
           arr_at.setText("");
           left_at.setText("");
           con.close();
           
           
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e);
          }
        }
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
                arr_at.setText("");
        left_at.setText("");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_management_system", "root","1406");

           
           
             String sql = "select Status_ArriveAt,Status_LeftAt from Status where Status_invoice = ?";
           PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1,statusid.getSelectedItem().toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                arr_at.setText(rs.getString("Status_ArriveAt"));
                
                left_at.setText(rs.getString("Status_LeftAt"));

                
            }

                     con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton56ActionPerformed

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
    private javax.swing.JTextField B_arrive;
    private javax.swing.JSpinner B_arrive_time_update;
    private javax.swing.JTextField B_busno_update;
    private javax.swing.JTextField B_date;
    private javax.swing.JPanel B_date_update;
    private javax.swing.JPanel B_date_update1;
    private javax.swing.JTextField B_from;
    private javax.swing.JComboBox<String> B_from_update;
    private javax.swing.JTextField B_leave;
    private javax.swing.JSpinner B_left_time_update;
    private javax.swing.JTextField B_name;
    private javax.swing.JComboBox<String> B_name_update;
    private javax.swing.JTextField B_no;
    private javax.swing.JComboBox<String> B_no_update;
    private javax.swing.JComboBox<String> B_route_no_update;
    private javax.swing.JTextField B_routeno;
    private javax.swing.JTextField B_seats;
    private javax.swing.JTextField B_seats_update;
    private javax.swing.JTextField B_time;
    private javax.swing.JComboBox<String> B_time_update;
    private javax.swing.JTextField B_to;
    private javax.swing.JComboBox<String> B_to_update;
    private javax.swing.JRadioButton FemaleB;
    private javax.swing.JRadioButton MaleB;
    private javax.swing.JTextField P_dob;
    private com.toedter.calendar.JDateChooser P_dob_update;
    private javax.swing.JRadioButton P_female_update;
    private javax.swing.JTextField P_gender;
    private javax.swing.JRadioButton P_male_update;
    private javax.swing.JTextField P_name;
    private javax.swing.JTextField P_name_update;
    private javax.swing.JTextField P_number;
    private javax.swing.JTextField P_number_update;
    private javax.swing.JTextField R_city1;
    private javax.swing.JTextField R_city1_update;
    private javax.swing.JTextField R_city2;
    private javax.swing.JTextField R_city2_update;
    private javax.swing.JTextField R_city3;
    private javax.swing.JTextField R_city3_update;
    private javax.swing.JTextField R_from;
    private javax.swing.JTextField R_from_update;
    private javax.swing.JTextField R_route_update;
    private javax.swing.JTextField R_to;
    private javax.swing.JTextField R_to_update;
    private javax.swing.JTextArea addressF;
    private javax.swing.JTabbedPane administration_panel;
    private javax.swing.JPanel administration_panel_bg;
    private javax.swing.JTextField arr_at;
    private javax.swing.JPanel bookingMainPanel;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JComboBox<String> booking_busno;
    private javax.swing.JComboBox<String> booking_combobox;
    private javax.swing.JComboBox<String> booking_from;
    private javax.swing.JComboBox<String> booking_passenger;
    private javax.swing.JComboBox<String> booking_time;
    private javax.swing.JComboBox<String> booking_to;
    private javax.swing.JTextField bus_number;
    private javax.swing.JComboBox<String> busno;
    private javax.swing.JComboBox<String> busno1;
    private javax.swing.JComboBox<String> busno_combobox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField city_1;
    private javax.swing.JTextField city_2;
    private javax.swing.JTextField city_3;
    private javax.swing.JTextField city_4;
    private javax.swing.JLabel datelabel;
    private com.toedter.calendar.JDateChooser dobF;
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
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
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
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField left_at;
    private javax.swing.JTabbedPane mainPanel;
    private javax.swing.JTextField nameF;
    private javax.swing.JTextField no_of_seats;
    private javax.swing.JPanel passengerPanel;
    private javax.swing.JComboBox<String> passenger_combobox;
    private com.toedter.calendar.JDateChooser passenger_dob;
    private javax.swing.JRadioButton passenger_female;
    private javax.swing.JRadioButton passenger_male;
    private javax.swing.JTextField passenger_name;
    private javax.swing.JTextField passenger_phoneno;
    private javax.swing.JPasswordField passwordF;
    private javax.swing.JTextField phoneNoF;
    private javax.swing.JTextField positionF;
    private javax.swing.JComboBox<String> route_combobox;
    private javax.swing.JTextField route_no;
    private javax.swing.JComboBox<String> route_number;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField start_from;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JComboBox<String> statusid;
    private javax.swing.JLabel statuslabel;
    private javax.swing.JSpinner time_of_arrive;
    private javax.swing.JSpinner time_of_leave;
    private javax.swing.JLabel timelabel;
    private javax.swing.JComboBox<String> user_name;
    private javax.swing.JTextField usernameF;
    // End of variables declaration//GEN-END:variables
}
