package run;

import SyncFile.Synchronize;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws IOException {

        File source = new File("J:/B");
        File target = new File("J:/A");
        Synchronize sync = new Synchronize();
        sync.syncFolder(source,target);


	}
}

