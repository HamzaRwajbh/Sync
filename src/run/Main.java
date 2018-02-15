package run;

import SyncFile.Synchronize;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        File source = new File("J:/B");
        File target = new File("J:/A");
        Synchronize sync = new Synchronize();
       sync.syncFolderEdit(source,target);
	}
}

