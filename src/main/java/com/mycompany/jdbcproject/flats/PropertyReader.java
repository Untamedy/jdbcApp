/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcproject.flats;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;







/**
 *
 * @author Lenovo
 */
public class PropertyReader {
    
    private final Properties property = new Properties();
    private String path;
    
    public PropertyReader(String path){
        this.path=path;
        
    }
    
    public Properties getProperties(){
        try {
            FileReader reader = null;
            
            try {
                reader = new FileReader(path);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            property.load(reader);
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return property;
    }
    
    
    
}
