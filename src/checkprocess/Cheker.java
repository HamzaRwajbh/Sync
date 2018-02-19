package checkprocess;

import src.syncfolder.FolderSync;

import java.io.File;
import java.io.IOException;

public class Cheker implements Runnable {

    FolderSync sync ;
    @Override
    public void run(){
        try {
            while (true) {
                File A = new File("C:/A");
                File B = new File("C:/B");
                sync = new FolderSync(A,B);
                Thread.sleep(20000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
