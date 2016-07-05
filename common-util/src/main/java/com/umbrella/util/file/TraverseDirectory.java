package com.umbrella.util.file;

import java.io.File;

/**
 * Created by xudazhou on 2016/7/4.
 */
public class TraverseDirectory {

    /**
     * 遍历文件夹及其子文件夹，并打印文件名
     * @param path
     * @throws Exception
     */
    public static void traverseDir(File path) throws Exception {
        if(path==null) return;
        if(path.isFile()) {
            System.out.println(path.getName());
        }
        File[] files = path.listFiles();
        if(files==null || files.length<1) return;
        for (File f : files) {
            traverseDir(f);
        }
    }
}
