package info.kaminosoft.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utilitarios {
    
    public static String ObtenerDatosPropertiesUserHome(String archivo, String dato) {
        String variable = "";
        try {
        	
        	 InputStream input = new FileInputStream(System.getProperty("user.home")+"/configurations-pide/componente-proxy/"+archivo+".properties");
        	 Properties prop = new Properties();
        	 prop.load(input);
             variable=prop.getProperty(dato);
             
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return variable.trim();
    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
}
