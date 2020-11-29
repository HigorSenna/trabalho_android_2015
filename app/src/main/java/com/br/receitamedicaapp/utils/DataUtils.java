package com.br.receitamedicaapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public abstract class DataUtils {

    private static SimpleDateFormat formato;

    public static Date convertStringDateSqlToDateJava(String stringData) throws Exception{
        formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.parse(stringData);
    }

    public static Date convertStringToDate(String stringData) throws Exception{
        formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(stringData);
    }

    public static String convertDateToStringFormat(Date date){
        formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(date);
    }
}
