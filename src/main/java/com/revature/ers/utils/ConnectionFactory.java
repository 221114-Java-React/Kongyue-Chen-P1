package com.revature.ers.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/* Bridges dao to db*/
public class ConnectionFactory {
    private static  ConnectionFactory connectionFactory;

    static {
        try {
           Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private final Properties props = new Properties();

    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/db.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
