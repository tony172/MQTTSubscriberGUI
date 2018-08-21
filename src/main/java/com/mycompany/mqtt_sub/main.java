/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqtt_sub;

import javax.swing.SwingUtilities;

/**
 *
 * @author toni
 */
public class main{
    
    public static void main(String [] args){
        Mqtt_sub subscriber = new Mqtt_sub("conf.json");
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Display d=new Display();
                subscriber.setRef(d);
            }
            
        });
        
        
    }

    
}
