/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqtt_sub;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;
import javax.swing.SwingUtilities;
import org.eclipse.paho.client.mqttv3.*;
import org.json.*;
/**
 *
 * @author toni
 */
public class Mqtt_sub implements MqttCallback{
    
    private String clientID=null;
    private String broker=null;
    private MqttClient client=null;
    private Display d;
    private JSONObject obj;
    public Mqtt_sub(String conf){
        try{
            obj=new JSONObject(new JSONTokener(new FileReader(conf)));
        }
        catch(FileNotFoundException e){}
        
        this.clientID=obj.getString("clientID");
        this.broker=obj.getString("broker");
        try{
            this.client=new MqttClient(broker, clientID);
            System.out.println("Connecting...");
            this.client.connect();
            System.out.println("Connected!");
            this.client.setCallback(this);
            this.client.subscribe(obj.getString("topic"));
            System.out.println("Subscribed!");
            
            
        }
        catch(MqttException e){
            e.getMessage();
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        System.out.println("aaa");
        this.d.setV(Integer.parseInt(mm.toString()),string);
        
        
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRef(Display d){
        this.d=d;
    }

    
}
