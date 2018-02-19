
package checkprocess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class CheckPoint {
    private Properties prop = new Properties();
    private long check_Point;
    private boolean FirstSync;

    public CheckPoint() {
        try {
            if (this.isCheckPointCreated()) {
                this.FirstSync = false;
            } else {
                this.FirstSync = true;
            }

        } catch (IOException var2) {
            System.out.println(var2.toString());
        }

    }



    private boolean isCheckPointCreated() throws IOException {
        try {
            FileInputStream in = new FileInputStream("config.properties");
            this.prop.load(in);
            in.close();
            if (this.prop.getProperty("CHECK_POINT").isEmpty() || this.prop.getProperty("CHECK_POINT").equalsIgnoreCase("null")) {
                return false;
            }else
                check_Point =Long.parseLong(prop.getProperty("CHECK_POINT"));
        } catch (IOException var3) {
            System.out.println(var3.toString());
        }

        return true;
    }

    public void setCheckPoint(long date) {
        this.check_Point = date;

        try {
            FileOutputStream out = new FileOutputStream("config.properties");
            this.prop.setProperty("CHECK_POINT", ""+date);
            this.prop.store(out, null);
            out.close();
        } catch (Exception var4) {
            System.out.println(var4.toString());
        }

    }

    public long getCheckPoint() {
        return this.check_Point;
    }

    public boolean isFirstSync() {
        return this.FirstSync;
    }
}
