package run;


import classes.CheckPoint;
import utils.FileAttributeUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    static final SimpleDateFormat PATTERN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    static  Date checkPoint ;
    public static void syncFolder(File source , File target) throws IOException {


        File []A = source.listFiles() ;
        File []B = target.listFiles() ;

        for (File i : A){
            if( i.isDirectory()){
                if(isExist(i.getName(),target.getAbsolutePath())){
                    System.out.println(i.getAbsolutePath()+"is a Directory and Exist");
                    syncFolder(i,new File(target.getAbsolutePath()+File.separator+i.getName()));
                }else  {
                    System.out.println(i.getAbsolutePath()+" is a Directory and not Exist ");
                    System.out.println(new File(target.getAbsolutePath()+File.separator+i.getName()).mkdir()+" the file "+i.getName()+" is created");
                    syncFolder(i,new File(target.getAbsolutePath()+File.separator+i.getName()));
                }
            }else if(isExist(i.getName(),target.getAbsolutePath())){
                if(!isSync(i)){
                    Files.copy(i.toPath() , target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    public static boolean isSync(File file){
        Date lastModifiedTime = FileAttributeUtil.getInstance().lastModifiedTime(file);
        if(lastModifiedTime.before(checkPoint)){
            return true ;
        }
        return false ;
    }
    public static boolean isExist(String name, String path){

        File temp = new File(path+File.separator+name);
        return temp.exists();
    }

    public void copyFile(File file ,Path from , Path to ){

    }

    public static void main(String[] args) {

        checkPoint = new CheckPoint().getCheckPoint();
        System.out.println(checkPoint);

        File source = new File("J:/B/Guts");
        File target = new File("J:/A/Guts");
        //syncFolder(source , target);
        Date a = null, b=null ;
        a = FileAttributeUtil.getInstance().lastAccessTime(source);
        b = FileAttributeUtil.getInstance().creationTime(source);
        System.out.println(source.getName()+"  "+a);
        System.out.println(target.getName()+"  "+b);


        System.out.println(a.compareTo(b));




        /*for(File i : scurse.listFiles()) {
        System.out.print(i.getAbsolutePath()+"    ");
        System.out.println(i.isDirectory());
            try {
                attr = Files.readAttributes(i.toPath(), BasicFileAttributes.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
        }*/
	}

    }

