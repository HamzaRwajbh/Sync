package SyncFile;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Synchronize {

    public Synchronize() {}

    public void syncFolderEdit(File source , File target) throws IOException {

        File []src = source.listFiles();
        Set<File> srcName = new HashSet<>(Arrays.asList(source));
        //delete all file not found in source
        for(File subFile : target.listFiles()){
            System.out.println(subFile.getName());
            if(!srcName.contains(subFile)){
                delete(new File(target,subFile.getName()));
            }
        }
        //check files exist in sources and not found in target & check files are updated in source
        for (File i : source.listFiles()) {
            //if file exist in target
            if (isExist(i.getName(), target.getAbsolutePath())) {
                //if the file is directory ? true : recall the syncFolder again until find file with extension
                if (i.isDirectory()) {
                    System.out.println(i.getAbsolutePath() + " is a Directory and Exist");
                    syncFolderEdit(i, new File(target.getAbsolutePath(),i.getName()));
                } else {
                    // if the file with extension
                    if (!isSync(i , new File(target,i.getName()))) { //check if the file is NOT sync ? true : copy to target
                        //copy to target;
                    } else { // if the file is NOT sync ? false : skip this file and continue
                        continue;
                    }
                }
            } else {// if the file does NOT exist in target regardless if it was file or directory
                //copy to target
            }
        }
    }

    public boolean isExist(String name, String path){

        File temp = new File(path+File.separator+name);
        return temp.exists();
    }

    public boolean isSync(File src ,File trg){
        long targetModifiedTime = trg.lastModified();
        long sourceModifiedTime = src.lastModified();
        if(sourceModifiedTime < targetModifiedTime){
            return true ;
        }
        return false ;
    }

    /*this method take to parameter (the file show copy, and the file will copy to)
    //
    //will check if the source is a directory,
    //  if (true)
    //      the target should create new folder with the same name of source file
    //      and recall copyDirectory(
    //  else
    //
    */
    public void copy(File source, File target) throws IOException {

        if(source.isDirectory() && target.isDirectory()){
            System.out.println(new File(target.getAbsolutePath()+File.separator+source.getName()).mkdir());
            copyContent(source,target);
        }else if(source.isFile() && target.isDirectory()){
            System.out.println(source.toPath());
            System.out.println(target.toPath());
            Files.copy(source.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void copyContent(File source, File target) {

    }

    //check files exist in target and not found in source
    public void syncFolderDelete(File source , File target) throws IOException {
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
            }
        }
    }

    public void delete(File file){
        if ( file.isDirectory() ) {
            for ( File subFile : file.listFiles() ) {
                delete( subFile );
            }
        }
        if ( file.exists() ) {
            if ( !file.delete() ) {
                System.out.println( "Could not delete {}" + file);
            }
        }
    }
}
