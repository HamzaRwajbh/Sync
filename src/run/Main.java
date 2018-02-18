package run;

import src.syncfolder.FolderSync;

import java.io.File;
import java.io.IOException;



public class Main extends Thread {


    public static void main(String[] args) throws IOException {


        File source = new File("C:/A");
        File target = new File("C:/B");
        FolderSync sync = new FolderSync();
        sync.syncFolder(source,target);


	}
}

