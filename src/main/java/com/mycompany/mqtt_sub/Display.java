/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqtt_sub;
import java.awt.FlowLayout;
import javax.swing.*;
import eu.hansolo.steelseries.gauges.*;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.*;
/**
 *
 * @author toni
 */
public class Display extends JFrame{
    
    private Map<String,Radial> map;
    private Radial r;
    private String conf="conf.json";
    private JSONObject obj=null;
    private JSONArray arr=null;
    public Display(){
        super("Mqtt");
        map=new HashMap<String,Radial>();
        try{
            obj=new JSONObject(new JSONTokener(new FileReader(conf)));
            arr=obj.getJSONArray("gauges");
        }
        catch(FileNotFoundException e){}
        Radial rad;
        for(int i=0;i<arr.length();i++){
            rad=new Radial();
            rad.setMinValue(arr.getJSONObject(i).getInt("min"));
            rad.setMaxValue(arr.getJSONObject(i).getInt("max"));
            rad.setTitle(arr.getJSONObject(i).getString("title"));
            rad.setUnitString(arr.getJSONObject(i).getString("unit"));
            rad.setValue(rad.getMinValue());
            rad.setLedVisible(false);
            rad.setMinimumSize(new Dimension(350, 350));
            add(rad);
            //gauges.add(rad);
            map.put(arr.getJSONObject(i).getString("topic"), rad);
        }
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
       /* r=new Radial();
        r.setMinimumSize(new Dimension(300, 300));
        r.setMaxValue(200);
        r.setMinValue(-10);
        add(r);*/
        
    }
    public void setV(int v,String topic){
        System.out.println(v+topic);
        if(map.containsKey(topic)){
        map.get(topic).setValueAnimated(v);
        }
    }

}
