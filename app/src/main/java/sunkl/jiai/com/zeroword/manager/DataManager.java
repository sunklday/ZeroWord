package sunkl.jiai.com.zeroword.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2016/3/10.
 */
public class DataManager {
    public static String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String date = formatter.format(curDate);
        return date;
    }
}
