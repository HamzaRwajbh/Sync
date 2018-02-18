package run;

import src.syncfolder.FolderSync;

import java.io.File;
import java.io.IOException;

public class Cheker implements Runnable {
    @Override
    public void run() {
        try {
            while (true){
                File source = new File("C:/A");
                File target = new File("C:/B");
                FolderSync sync = new FolderSync();
                sync.syncFolder(source,target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
