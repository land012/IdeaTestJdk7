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

    /**
     * ftp
     */
    @Test
    public void testftp1() throws URISyntaxException {
        URI uri1 = new URI("ftp://host1:/dir1/./dir2");
        System.out.println(uri1.getScheme()); // ftp
        System.out.println(uri1.getAuthority()); // host1:
        System.out.println(uri1.getHost()); // host1
        System.out.println(uri1.getPort()); // -1
        System.out.println(uri1.getPath()); // /dir1/./dir2
        System.out.println(uri1.getFragment()); // null

        System.out.println(uri1.getRawPath()); // /dir1/./dir2

        System.out.println(uri1.getSchemeSpecificPart()); // //host1:/dir1/./dir2
        System.out.println(uri1.getUserInfo()); // null
    }

    /**
     * ftp2
     */
    @Test
    public void testftp2() throws URISyntaxException {
        URI uri1 = new URI("ftp://user1@host1:22/dir1/dir2");
        System.out.println(uri1.getAuthority()); // user1@host1:22
        System.out.println(uri1.getFragment()); // null
        System.out.println(uri1.getHost()); // host1
        System.out.println(uri1.getPath()); // /dir1/dir2
        System.out.println(uri1.getPort()); // 22

        System.out.println(uri1.getRawPath()); // /dir1/dir2
        System.out.println(uri1.getScheme()); // ftp
        System.out.println(uri1.getSchemeSpecificPart()); // //user1@host1:22/dir1/dir2
        System.out.println(uri1.getUserInfo()); // user1
    }
}
