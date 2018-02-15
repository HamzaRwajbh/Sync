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
    public static void syncFolderEdit(File source , File target) throws IOException {

        //check files exist in sources and not found in target & check files are updated in source
        for (File i : source.listFiles()) {
            if (isExist(i.getName(), target.getAbsolutePath())) {//if file exist in target
                if (i.isDirectory()) {//if the file is directory ? true : recall the syncFolder again until find file with extension
                    System.out.println(i.getAbsolutePath() + " is a Directory and Exist");
                    syncFolderEdit(i, new File(target.getAbsolutePath(),i.getName()));
                } else { // if the file with extension
                    if (!isSync(i , new File(target,i.getName()))) { //check if the file is NOT sync ? true : copy to target
                        //copy to target;
                        Files.copy(i.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } else { // if the file is NOT sync ? false : skip this file and continue
                        continue;
                    }
                }
            } else {// if the file does NOT exist in target regardless if it was file or directory
                //copy to target
                System.out.println(i.getAbsolutePath() + " is NOT Exist");
                Files.copy(i.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
    public static void syncFolderDelete(File source , File target) throws IOException {
        //check files exist in target and not found in source
        for (File i : target.listFiles()){
            if (isExist(i.getName(), source.getAbsolutePath())) {
                if (i.isDirectory()) {
                    System.out.println(i.getAbsolutePath() + " is a Directory and Exist");
                    syncFolderDelete(i, new File(source.getAbsolutePath() + File.separator + i.getName()));
                }else{
                    continue;
                }
            } else {
                //delete from source
                Files.delete(i.toPath());
            }
        }

    }

    private static void copyDirectory(File source, File target) throws IOException {
        new File(target.getAbsolutePath()+File.separator+source.getName());
        for(File i : source.listFiles()){
            if(i.isDirectory()) {
                    if(new File(target.getAbsolutePath()+File.separator+i.getName()).mkdir()){
                        copyDirectory(i,new File(target.getAbsolutePath()+File.separator+i.getName()));
                        System.out.println(target.getAbsolutePath()+File.separator+i.getName()+" is Created");
                    }else{
                        System.out.println(i.getName()+" does NOT created");
                    }
            }else{
                System.out.println("The file is copying");
                System.out.println(source.toPath());
                System.out.println(target.toPath());
                Files.copy(source.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING);
            }
        }



    }

    public static boolean isSync(File src ,File trg){
        Date targetModifiedTime = FileAttributeUtil.getInstance().lastModifiedTime(trg);
        Date sourceModifiedTime = FileAttributeUtil.getInstance().lastModifiedTime(src);
        if(sourceModifiedTime.before(targetModifiedTime)){
            return true ;
        }
        return false ;
    }
    public static boolean isExist(String name, String path){

        File temp = new File(path+File.separator+name);
        return temp.exists();
    }

    public static void main(String[] args) throws IOException {

        //checkPoint = new CheckPoint().getCheckPoint();
        //System.out.println(checkPoint);

        File source = new File("J:/B/alrwajbh");
        File target = new File("J:/A");
        copyDirectory(source, target);
//        syncFolderEdit(source , target);



        /*a = FileAttributeUtil.getInstance().lastAccessTime(source);
        b = FileAttributeUtil.getInstance().creationTime(source);
        System.out.println(source.getName()+"  "+a);
        System.out.println(target.getName()+"  "+b);
        System.out.println(isSync(source,target));
        /*
        File new_file = new File(source.getAbsolutePath()+File.separator+"new_file");
        System.out.println(new_file.mkdir());
        File new_file1 = new File(new_file.getAbsolutePath()+File.separator+"new_file1");
        System.out.println(new_file1.mkdir());

        System.out.println(i.getAbsolutePath()+" is a Directory and not Exist ");
        System.out.println(new File(target.getAbsolutePath()+File.separator+i.getName()).mkdir()+" the file "+i.getName()+" is created");
        syncFolderEdit(i,new File(target.getAbsolutePath()+File.separator+i.getName()))




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

