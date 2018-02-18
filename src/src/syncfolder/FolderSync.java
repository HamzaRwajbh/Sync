package src.syncfolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FolderSync {

    public FolderSync() {
    }

    public void syncFolder(File source, File target) throws IOException {

        System.out.println("We are in " + source.getAbsolutePath());
        System.out.println("_____________________________________________________________________________________________");

        //delete all file not found in source
        for (File subFile : target.listFiles()) {
            if (!new File(source.getAbsolutePath(), subFile.getName()).exists()) {
                System.out.println(subFile.getName() + " doesn't exist in source");
                delete(new File(target, subFile.getName()));
                System.out.println(subFile.getAbsolutePath() + " is deleted");
            }
        }
        //check files exist in sources and not found in target & check files are updated in source
        for (File i : source.listFiles()) {
            //if it is the directory
            if (i.isDirectory()) {
                //if the file is directory ? true : recall the syncFolder again until find file with extension
                if (isExist(i.getName(), target.getAbsolutePath())) {
                    System.out.println(i.getAbsolutePath() + " is a Directory and Exist");
                    syncFolder(i, new File(target.getAbsolutePath(), i.getName()));
                } else {
                    System.out.println(i.getAbsolutePath() + "is a Directory and not Exist");
                    System.out.println(new File(target.getAbsolutePath(), i.getName()).mkdir() + " " + i.getAbsolutePath() + " is created");
                    syncFolder(i, new File(target.getAbsolutePath(), i.getName()));
                }// if the file with extension
            } else {
                if (isExist(i.getName(), target.getAbsolutePath())) {
                    if (!isSync(i, new File(target, i.getName()))) { //check if the file is NOT sync ? true : copy to target
                        System.out.println(i.getAbsolutePath() + " is not sync , the copy is done");
                        copy(i, target);
                    } else { // if the file is NOT sync ? false : skip this file and continue
                        continue;
                    }
                } else {
                    copy(i, target);
                }

            }
        }
        System.out.println();
    }

    public boolean isExist(String name, String path) {
        File temp = new File(path + File.separator + name);
        return temp.exists();
    }

    public boolean isSync(File src, File trg) {
        long targetModifiedTime = trg.lastModified();
        long sourceModifiedTime = src.lastModified();
        if (sourceModifiedTime <= targetModifiedTime) {
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
