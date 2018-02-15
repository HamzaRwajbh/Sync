package classes;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CheckPoint {

    private final SimpleDateFormat PATTERN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final String PROP_NAME = "config.properties";

    private Properties prop;
    private Date checkPoint;
    private boolean FirstSync;

    public CheckPoint(){

        prop = new Properties();
        try {

            if (this.isCheckPointCreated()) {    //check if the sync is the first time or  not

                String str = prop.getProperty("CHECK_POINT");
                checkPoint = PATTERN.parse(str);
                FirstSync = false ;

            } else {

                FirstSync = true ;
                this.setCheckPoint(new Date());

            }
        }catch (IOException e){
            System.out.println(e.toString());
        }catch (ParseException e){
            System.out.println(e.toString());
        }
    }

    private boolean isCheckPointCreated() throws IOException {

        FileInputStream in;
        try {
            in = new FileInputStream(PROP_NAME);
            prop.load(in);
            in.close();
            if (prop.getProperty("CHECK_POINT").isEmpty() || prop.getProperty("CHECK_POINT").equalsIgnoreCase("null"))
                return false;
        }catch (IOException e) {
            System.out.println(e.toString());
        }
        return true ;
    }

    public void setCheckPoint(Date date){

        FileOutputStream out ;
        checkPoint = date;

        try{

            out = new FileOutputStream(PROP_NAME);
            prop.setProperty("CHECK_POINT", PATTERN.format(date));
            prop.store(out,null);
            System.out.println("checkPoint is changed"+PATTERN.format(date));
            out.close();

        }catch (Exception e ){
            System.out.println(e.toString());
        }

    }

    public Date getCheckPoint(){
        return checkPoint;
    }

    public boolean isFirstSync(){
        return FirstSync ;
    }

}
