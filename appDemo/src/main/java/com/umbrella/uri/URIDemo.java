package com.umbrella.uri;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * create by xudazhou 2017/12/19
 */
public class URIDemo {

    @Test
    public void test1() {
        try {
            URI uri = new URI("afs", "tianqi.afs.baidu.com:9902", "/dir/dir2/dir3/file4", "k1=v1&k2=v2", "fragment");
            // afs://tianqi.afs.baidu.com:9902/dir/dir2/dir3/file4?k1=v1&k2=v2#fragment
            System.out.println(uri.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
