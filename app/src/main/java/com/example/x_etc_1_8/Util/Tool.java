package com.example.x_etc_1_8.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

    public static String getTime(String type, Date date){
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

}
