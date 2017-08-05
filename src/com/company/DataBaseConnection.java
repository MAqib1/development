package com.company;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Khubaib ch on 7/18/2017.
 */
public class DataBaseConnection {
    public static Connection ConnectDB(){
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:E:\\pharmacy retail project\\database\\pharmacy retail management system.sqlite");

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    }

