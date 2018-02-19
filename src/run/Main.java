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


        //Cheker cheker = new Cheker();
        //Thread thread = new Thread(cheker);
        //thread.start();


        File A = new File("C:/A/d");
        File B = new File("C:/B/New folder");
        BasicFileAttributes a = Files.readAttributes(A.toPath(),BasicFileAttributes.class);
        BasicFileAttributes b = Files.readAttributes(B.toPath(),BasicFileAttributes.class);
        System.out.println(A.getUsableSpace());
        System.out.println(A.getTotalSpace());
        System.out.println(A.getFreeSpace());
        System.out.println(A.length());
        System.out.println();
        System.out.println(B.getUsableSpace());
        System.out.println(B.getTotalSpace());
        System.out.println(B.getFreeSpace());
        System.out.println(B.length());






	}
}

