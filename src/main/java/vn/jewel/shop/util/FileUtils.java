package vn.jewel.shop.util;

import java.io.File;

public class FileUtils {
    public void createDir(String dirFolder) {
        if(Utils.isEmptyText(dirFolder)) {
            return;
        }
        File dir = new File(dirFolder);
        if (!dir.exists()) {
            try{
                dir.mkdirs();
            } catch(SecurityException se){
                se.printStackTrace();
            }        
        }
    }
}
