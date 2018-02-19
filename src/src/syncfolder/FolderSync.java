package src.syncfolder;

import checkprocess.CheckPoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FolderSync {
    CheckPoint checkPoint ;
    public FolderSync(File sourse , File target) throws IOException {
        checkPoint = new CheckPoint() ;
        syncFolder(sourse,target,checkPoint.isFirstSync());
        System.out.println(checkPoint.getCheckPoint());
        checkPoint.setCheckPoint(new Date().getTime());
    }

    public void syncFolder(File source , File target , boolean firstSync) throws IOException {
        if (firstSync){
            System.out.println("______________________________The first Time_____________________________");
            syncFolder(source,target);
            syncFolder(target,source);
        }else{
            System.out.println("______________________________Again_____________________________");
            deleteUnSyncFiles(source,target);
            deleteUnSyncFiles(target,source);
            syncFolder(source,target);
            syncFolder(target,source);



        }
    }


    public void deleteUnSyncFiles(File source , File target ) throws IOException {
       File []subSource = source.listFiles() ;
       Set<File> subSrc = new HashSet<File>(Arrays.asList(subSource));
       String []subTarget = target.list();
       Set<String> subTag = new HashSet<String>(Arrays.asList(subTarget));
       for(File i : subSrc){
           if(!subTag.contains(i)){
               if(!isNewFile(i) ){
                   if( i.lastModified()<=checkPoint.getCheckPoint())
                   System.out.println(i + "  is not in target");
                   delete(i);
               }
           }else{
               if(i.isDirectory()){
                   deleteUnSyncFiles(new File(source.getAbsolutePath(),i.getName()) , new File(target.getAbsolutePath(),i.getName()));
               }
           }
       }


    }

    public String isRenamed(File file , String []subTarget){
        for(int i = 0 ; i<subTarget.length ; i++){
            if(true);
        }
        return null;
    }
    public void syncFolder(File source, File target) throws IOException {

        //check files exist in sources and not found in target & check files are updated in source
        for (File i : source.listFiles()) {
            //if it is the directory
            if (i.isDirectory()) {
                //if the file is directory ? true : recall the syncFolder again until find file with extension
                if (isExist(i.getName(), target.getAbsolutePath())) {
                    //System.out.println(i.getAbsolutePath() + " is a Directory and Exist");
                    syncFolder(i, new File(target.getAbsolutePath(), i.getName()));
                } else {
                    //System.out.println(i.getAbsolutePath() + "is a Directory and not Exist");
                    System.out.println(new File(target.getAbsolutePath(), i.getName()).mkdir() + " " + i.getAbsolutePath() + " is created");
                    //syncFolder(i, new File(target.getAbsolutePath(), i.getName()));
                }// if the file with extension
            } else {
                if (isExist(i.getName(), target.getAbsolutePath())) {
                    if (!isSyncFile(i, new File(target, i.getName()))) { //check if the file is NOT sync ? true : copy to target
                        //System.out.println(i.getAbsolutePath() + " is not sync , the copy is done");
                        copy(i, target);
                    } else { // if the file is NOT sync ? false : skip this file and continue
                        continue;
                    }
                } else {
                    copy(i, target);
                }

            }
        }
    }

    public boolean isExist(String name, String path) {
        File temp = new File(path + File.separator + name);
        return temp.exists();
    }

    public boolean isSyncFile(File src, File trg) {
        long targetModifiedTime = trg.lastModified();
        long sourceModifiedTime = src.lastModified();
        if (sourceModifiedTime <= targetModifiedTime) {
            return true;
        }
        return false;
    }

    public boolean isNewFile(File file) throws IOException {
        BasicFileAttributes attribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

        long fileCreationTime = attribute.creationTime().toMillis();
        if (fileCreationTime >= checkPoint.getCheckPoint()) {
            return true;
        }
        return false;
    }

    public void copy(File source, File target) throws IOException {
        Files.copy(source.toPath(), new File(target.getAbsolutePath(), source.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }


    public void delete(File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                delete(subFile);
            }
        }
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("Could not delete {}" + file);
            }
        }
    }
}
