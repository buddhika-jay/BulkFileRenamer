package com.buddhikajay.javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Buddhika on 23/02/2015.
 */
public class  Logic {

    int prepareRename(List<File> files, int startingIndex, String commonName) throws IOException {
        String oldName;
        int index = startingIndex;
        for(File file : files){
            oldName = getOldName(file);
            this.rename(oldName, getNewName(oldName,commonName, index));
            index++;
        }
        return index-startingIndex;
    }

    public String getNewName(String oldName, String commonName, int startingIndex){

        String[] subStrings = oldName.split("\\.");
        String newName = subStrings[0].substring(0,subStrings[0].lastIndexOf("//")+2);
        newName = newName.concat(commonName+" "+startingIndex+"."+subStrings[1]);
        return newName;
    }

    public String getOldName(File file){
        return file.getAbsolutePath().replace("\\", "//");
    }

    Boolean rename(String oldName, String newName) throws FileNotFoundException {
        File oldFile = new File(oldName);
        File newFile = new File(newName);

        return oldFile.renameTo(newFile);
    }
}
