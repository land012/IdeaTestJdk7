package com.umbrella.util.file;

import org.junit.Test;

import java.io.File;

/**
 * Created by xudazhou on 2016/7/4.
 */
public class TraverseDirectoryTest {

    @Test
    public void testTraverseDir() throws Exception {
        TraverseDirectory.traverseDir(new File("F:\\Game\\Game Tools\\新建文件夹"));
    }
}
