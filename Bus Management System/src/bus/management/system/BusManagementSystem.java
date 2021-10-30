/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus.management.system;

import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author pasin
 */
public class BusManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainScreen login = new MainScreen(); //Creating an object of a MainScreen class
        login.setLocationRelativeTo(null); //to set window location at the center of the screen
        login.setVisible(true); //when the programme run the MainScreen will be executed
         
        

        

    }
    
}
