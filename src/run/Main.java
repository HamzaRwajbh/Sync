package run;

import checkprocess.CheckPoint;
import checkprocess.Cheker;
import src.syncfolder.FolderSync;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;


public class Main extends Thread {


    public static void main(String[] args) throws IOException {


        Cheker cheker = new Cheker();
        Thread thread = new Thread(cheker);
        thread.start();







	}
}

