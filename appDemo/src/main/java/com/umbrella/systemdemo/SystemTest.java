package com.umbrella.systemdemo;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

//import com.my.service.CommonService;
//import com.my.util.MyComparator;
import com.umbrella.vo.User;

public class SystemTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		/**
//		 * 数组拷贝
//		 */
//		String[] arr1 = { "a", "b", "c" };
//		
//		String[] arr2 = new String[4];
//		System.arraycopy(arr1, 0, arr2, 0, 3);
//		System.out.println("arr2.length:" + arr2.length);
//		System.out.println("arr2:" + Arrays.toString(arr2));
//		if("null".equals(arr2[3])) {
//			System.out.println("arr2[3] is 'null':" + arr2[3]);
//		} else if(null == arr2[3]) {
//			System.out.println("arr2[3] is null:" + arr2[3]);
//		} else {
//			System.out.println("arr2[3] is else:" + arr2[3]);
//		}
//		
//		
//		String[] arr3 = new String[2];
//		System.arraycopy(arr1, 0, arr3, 0, 2);
//		System.out.println("arr3:" + Arrays.toString(arr3));
//		
//		String[] arr4 = new String[2];
//		System.arraycopy(arr1, 1, arr4, 0, 2);
//		System.out.println("arr4:" + Arrays.toString(arr4));
//		
//		String[] arr5 = new String[2];
//		System.arraycopy(arr1, 0, arr5, 1, 1);
//		System.out.println("arr5:" + Arrays.toString(arr5));
		
		/**
		 * 排序
		 */
//		// 一维数组排序
//		String[] arr1 = { "b", "a", "c" };
//		Arrays.sort(arr1, new MyComparator<String>());
//		System.out.println(Arrays.toString(arr1));
		
//		// 二维数组排序
//		String[][] arr2 = {
//				{ "b", "1"},
//				{ "a", "2"},
//				{ "c", "3"}
//		};
//		Arrays.sort(arr2, new MyComparator<String[]>());
//		for(String[] ar : arr2) {
//			System.out.println(Arrays.toString(ar));
//		}
		
//		/**
//		 * 毫秒
//		 */
//		System.out.println(new Date().getTime());       // 1404024445904
//		System.out.println(System.currentTimeMillis()); // 1404024445904
//		System.out.println(System.nanoTime());          // 9071127851683 从JVM启动到当前的时长
		
//		/**
//		 * %s 占位符
//		 * %n 换行符
//		 */
//		System.out.format("name:%s,age:%s", "name", 19);
//		System.out.format("name:%s,age:%s%n", "name", 19);
//		System.out.format("name:%s,age:%s%n", "name", 19);
		
		/**
		 * 环境属性
		 */
//		Map<String, String> map1 = System.getenv();
//		Set<String> set1 = map1.keySet();
//		for(String key : set1) {
//			System.out.format("%s 是 %s%n", key, map1.get(key));
//		}

	}

    /**
     * system.property
     * -Dk=v
     */
	@Test
	public void test20() {
		System.out.println(System.getProperty("user.name")); // xudazhou
        // 作为java参数，用 -Dfoo=bar 的方式传进来
        System.out.println(System.getProperty("foo")); // bar
        System.out.println(System.getProperty("idea.launcher.port")); // 7540
	}
	
	@Test
	public void test21() {
		/**
		 * 系统属性
         * java.class.path 是 D:\ProgramDev\IntelliJ IDEA 14.0.3\lib\idea_rt.jar;
         * D:\ProgramDev\IntelliJ IDEA 14.0.3\plugins\junit\lib\junit-rt.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\charsets.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\deploy.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\javaws.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\jce.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\jfr.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\jfxrt.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\jsse.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\management-agent.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\plugin.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\resources.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\rt.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\access-bridge.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\dnsns.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\jaccess.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\localedata.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\sunec.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\sunjce_provider.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\sunmscapi.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\sunpkcs11.jar;
         * D:\ProgramDev\Java\jdk1.7.0_60\jre\lib\ext\zipfs.jar;D:\_idea\TestApp\appDemo\target\classes;D:\_idea\TestApp\hello-idea-service\target\classes;D:\_mvn_repository\org\springframework\spring-core\4.1.0.RELEASE\spring-core-4.1.0.RELEASE.jar;D:\_mvn_repository\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;D:\_mvn_repository\org\springframework\spring-context\4.1.0.RELEASE\spring-context-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-aop\4.1.0.RELEASE\spring-aop-4.1.0.RELEASE.jar;D:\_mvn_repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;D:\_mvn_repository\org\springframework\spring-beans\4.1.0.RELEASE\spring-beans-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-expression\4.1.0.RELEASE\spring-expression-4.1.0.RELEASE.jar;D:\_mvn_repository\org\aspectj\aspectjweaver\1.8.4\aspectjweaver-1.8.4.jar;D:\_mvn_repository\org\springframework\spring-web\4.1.0.RELEASE\spring-web-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-webmvc\4.1.0.RELEASE\spring-webmvc-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-test\4.1.0.RELEASE\spring-test-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-jdbc\4.1.0.RELEASE\spring-jdbc-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-tx\4.1.0.RELEASE\spring-tx-4.1.0.RELEASE.jar;D:\_mvn_repository\org\springframework\spring-context-support\4.1.0.RELEASE\spring-context-support-4.1.0.RELEASE.jar;D:\_mvn_repository\org\apache\commons\commons-lang3\3.3.2\commons-lang3-3.3.2.jar;D:\_mvn_repository\commons-beanutils\commons-beanutils\1.8.3\commons-beanutils-1.8.3.jar;D:\_mvn_repository\org\apache\httpcomponents\httpclient\4.3.6\httpclient-4.3.6.jar;D:\_mvn_repository\org\apache\httpcomponents\httpcore\4.3.3\httpcore-4.3.3.jar;D:\_mvn_repository\org\slf4j\slf4j-api\1.6.6\slf4j-api-1.6.6.jar;D:\_mvn_repository\org\slf4j\slf4j-log4j12\1.6.6\slf4j-log4j12-1.6.6.jar;D:\_mvn_repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\_mvn_repository\org\apache\cxf\cxf-bundle\2.7.13\cxf-bundle-2.7.13.jar;D:\_mvn_repository\org\apache\velocity\velocity\1.7\velocity-1.7.jar;D:\_mvn_repository\commons-collections\commons-collections\3.2.1\commons-collections-3.2.1.jar;D:\_mvn_repository\commons-lang\commons-lang\2.5\commons-lang-2.5.jar;D:\_mvn_repository\wsdl4j\wsdl4j\1.6.3\wsdl4j-1.6.3.jar;D:\_mvn_repository\com\sun\xml\bind\jaxb-xjc\2.1.13\jaxb-xjc-2.1.13.jar;D:\_mvn_repository\com\sun\xml\bind\jaxb-impl\2.1.13\jaxb-impl-2.1.13.jar;D:\_mvn_repository\org\apache\ws\xmlschema\xmlschema-core\2.1.0\xmlschema-core-2.1.0.jar;D:\_mvn_repository\antlr\antlr\2.7.7\antlr-2.7.7.jar;D:\_mvn_repository\org\apache\xmlbeans\xmlbeans\2.6.0\xmlbeans-2.6.0.jar;D:\_mvn_repository\xml-resolver\xml-resolver\1.2\xml-resolver-1.2.jar;D:\_mvn_repository\org\apache\geronimo\specs\geronimo-jaxws_2.2_spec\1.1\geronimo-jaxws_2.2_spec-1.1.jar;D:\_mvn_repository\org\codehaus\woodstox\woodstox-core-asl\4.4.1\woodstox-core-asl-4.4.1.jar;D:\_mvn_repository\org\codehaus\woodstox\stax2-api\3.1.4\stax2-api-3.1.4.jar;D:\_mvn_repository\org\apache\geronimo\specs\geronimo-javamail_1.4_spec\1.7.1\geronimo-javamail_1.4_spec-1.7.1.jar;D:\_mvn_repository\org\eclipse\jetty\jetty-server\8.1.15.v20140411\jetty-server-8.1.15.v20140411.jar;D:\_mvn_repository\org\eclipse\jetty\jetty-continuation\8.1.15.v20140411\jetty-continuation-8.1.15.v20140411.jar;D:\_mvn_repository\org\eclipse\jetty\jetty-http\8.1.15.v20140411\jetty-http-8.1.15.v20140411.jar;D:\_mvn_repository\org\eclipse\jetty\jetty-io\8.1.15.v20140411\jetty-io-8.1.15.v20140411.jar;D:\_mvn_repository\org\eclipse\jetty\jetty-util\8.1.15.v20140411\jetty-util-8.1.15.v20140411.jar;D:\_mvn_repository\org\eclipse\jetty\jetty-security\8.1.15.v20140411\jetty-security-8.1.15.v20140411.jar;D:\_mvn_repository\org\apache\geronimo\specs\geronimo-servlet_3.0_spec\1.0\geronimo-servlet_3.0_spec-1.0.jar;D:\_mvn_repository\org\apache\geronimo\specs\geronimo-jms_1.1_spec\1.1.1\geronimo-jms_1.1_spec-1.1.1.jar;D:\_mvn_repository\org\springframework\spring-jms\3.0.7.RELEASE\spring-jms-3.0.7.RELEASE.jar;D:\_mvn_repository\org\apache\httpcomponents\httpcore-nio\4.2.4\httpcore-nio-4.2.4.jar;D:\_mvn_repository\org\apache\httpcomponents\httpasyncclient\4.0-beta3\httpasyncclient-4.0-beta3.jar;D:\_mvn_repository\org\apache\mina\mina-core\2.0.7\mina-core-2.0.7.jar;D:\_mvn_repository\rhino\js\1.7R2\js-1.7R2.jar;D:\_mvn_repository\javax\ws\rs\javax.ws.rs-api\2.0-m10\javax.ws.rs-api-2.0-m10.jar;D:\_mvn_repository\net\oauth\core\oauth-provider\20100527\oauth-provider-20100527.jar;D:\_mvn_repository\net\oauth\core\oauth\20100527\oauth-20100527.jar;D:\_mvn_repository\net\sf\ehcache\ehcache-core\2.5.1\ehcache-core-2.5.1.jar;D:\_mvn_repository\org\apache\ws\security\wss4j\1.6.17\wss4j-1.6.17.jar;D:\_mvn_repository\org\apache\santuario\xmlsec\1.5.7\xmlsec-1.5.7.jar;D:\_mvn_repository\org\opensaml\opensaml\2.6.1\opensaml-2.6.1.jar;D:\_mvn_repository\org\opensaml\openws\1.5.1\openws-1.5.1.jar;D:\_mvn_repository\org\opensaml\xmltooling\1.4.1\xmltooling-1.4.1.jar;D:\_mvn_repository\joda-time\joda-time\2.7\joda-time-2.7.jar;D:\_mvn_repository\commons-httpclient\commons-httpclient\3.1\commons-httpclient-3.1.jar;D:\_mvn_repository\junit\junit\4.12\junit-4.12.jar;D:\_mvn_repository\xalan\serializer\2.7.1\serializer-2.7.1.jar;D:\_mvn_repository\org\apache\neethi\neethi\3.0.3\neethi-3.0.3.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-core\2.7.13\cxf-rt-core-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-api\2.7.13\cxf-api-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-transports-http\2.7.13\cxf-rt-transports-http-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-frontend-jaxws\2.7.13\cxf-rt-frontend-jaxws-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-bindings-soap\2.7.13\cxf-rt-bindings-soap-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-databinding-jaxb\2.7.13\cxf-rt-databinding-jaxb-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-bindings-xml\2.7.13\cxf-rt-bindings-xml-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-frontend-simple\2.7.13\cxf-rt-frontend-simple-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-ws-addr\2.7.13\cxf-rt-ws-addr-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-ws-policy\2.7.13\cxf-rt-ws-policy-2.7.13.jar;D:\_mvn_repository\org\apache\cxf\cxf-rt-frontend-jaxrs\2.7.13\cxf-rt-frontend-jaxrs-2.7.13.jar;D:\_mvn_repository\com\fasterxml\jackson\jaxrs\jackson-jaxrs-json-provider\2.4.3\jackson-jaxrs-json-provider-2.4.3.jar;D:\_mvn_repository\com\fasterxml\jackson\jaxrs\jackson-jaxrs-base\2.4.3\jackson-jaxrs-base-2.4.3.jar;D:\_mvn_repository\com\fasterxml\jackson\core\jackson-core\2.4.3\jackson-core-2.4.3.jar;D:\_mvn_repository\com\fasterxml\jackson\core\jackson-databind\2.4.3\jackson-databind-2.4.3.jar;D:\_mvn_repository\com\fasterxml\jackson\core\jackson-annotations\2.4.3\jackson-annotations-2.4.3.jar;D:\_mvn_repository\com\fasterxml\jackson\module\jackson-module-jaxb-annotations\2.4.3\jackson-module-jaxb-annotations-2.4.3.jar;D:\_mvn_repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;D:\_mvn_repository\redis\clients\jedis\2.7.2\jedis-2.7.2.jar;D:\_mvn_repository\org\apache\commons\commons-pool2\2.3\commons-pool2-2.3.jar;D:\_mvn_repository\org\apache\zookeeper\zookeeper\3.4.6\zookeeper-3.4.6.jar;D:\_mvn_repository\jline\jline\0.9.94\jline-0.9.94.jar;D:\_mvn_repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;D:\_idea\TestApp\common-domain\target\classes;D:\_idea\TestApp\WebServiceInterface\target\classes;D:\_idea\TestApp\common-util\target\classes;D:\_mvn_repository\com\google\code\gson\gson\2.3.1\gson-2.3.1.jar;D:\_mvn_repository\commons-codec\commons-codec\1.8\commons-codec-1.8.jar;D:\_mvn_repository\org\apache\poi\poi\3.10.1\poi-3.10.1.jar;D:\_mvn_repository\org\apache\poi\poi-ooxml\3.10.1\poi-ooxml-3.10.1.jar;D:\_mvn_repository\org\apache\poi\poi-ooxml-schemas\3.10.1\poi-ooxml-schemas-3.10.1.jar;D:\_mvn_repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;D:\_mvn_repository\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;D:\_mvn_repository\net\sf\json-lib\json-lib\2.4\json-lib-2.4-jdk15.jar;D:\_mvn_repository\net\sf\ezmorph\ezmorph\1.0.6\ezmorph-1.0.6.jar;D:\_mvn_repository\org\codehaus\jackson\jackson-jaxrs\1.9.13\jackson-jaxrs-1.9.13.jar;D:\_mvn_repository\org\codehaus\jackson\jackson-core-asl\1.9.13\jackson-core-asl-1.9.13.jar;D:\_mvn_repository\org\codehaus\jackson\jackson-mapper-asl\1.9.13\jackson-mapper-asl-1.9.13.jar;D:\_mvn_repository\org\hamcrest\hamcrest-all\1.3\hamcrest-all-1.3.jar;D:\_mvn_repository\com\caucho\hessian\4.0.38\hessian-4.0.38.jar;D:\_mvn_repository\org\mockito\mockito-all\2.0.2-beta\mockito-all-2.0.2-beta.jar;D:\_mvn_repository\com\google\guava\guava\18.0\guava-18.0.jar;D:\_mvn_repository\org\assertj\assertj-core\1.7.1\assertj-core-1.7.1.jar;D:\_mvn_repository\com\typesafe\akka\akka-actor_2.11\2.3.8\akka-actor_2.11-2.3.8.jar;D:\_mvn_repository\org\scala-lang\scala-library\2.11.4\scala-library-2.11.4.jar;D:\_mvn_repository\com\typesafe\config\1.2.1\config-1.2.1.jar;D:\_mvn_repository\mysql\mysql-connector-java\5.1.34\mysql-connector-java-5.1.34.jar;D:\_mvn_repository\org\apache\commons\commons-dbcp2\2.1\commons-dbcp2-2.1.jar;D:\_mvn_repository\com\atomikos\transactions-jta\4.0.0M4\transactions-jta-4.0.0M4.jar;D:\_mvn_repository\com\atomikos\transactions\4.0.0M4\transactions-4.0.0M4.jar;D:\_mvn_repository\com\atomikos\transactions-api\4.0.0M4\transactions-api-4.0.0M4.jar;D:\_mvn_repository\com\atomikos\atomikos-util\4.0.0M4\atomikos-util-4.0.0M4.jar;D:\_mvn_repository\com\atomikos\transactions-jdbc\4.0.0M4\transactions-jdbc-4.0.0M4.jar;D:\_mvn_repository\javax\transaction\javax.transaction-api\1.2\javax.transaction-api-1.2.jar;D:\_mvn_repository\com\thoughtworks\xstream\xstream\1.4.8\xstream-1.4.8.jar;D:\_mvn_repository\xmlpull\xmlpull\1.1.3.1\xmlpull-1.1.3.1.jar;D:\_mvn_repository\xpp3\xpp3_min\1.1.4c\xpp3_min-1.1.4c.jar;D:\_mvn_repository\org\quartz-scheduler\quartz\2.2.1\quartz-2.2.1.jar;D:\_mvn_repository\c3p0\c3p0\0.9.1.1\c3p0-0.9.1.1.jar;D:\_mvn_repository\net\sourceforge\groboutils\groboutils-core\5\groboutils-core-5.jar;D:\_idea\TestApp\fastdfs-client-java\target\classes;D:\_mvn_repository\com\itextpdf\itextpdf\5.5.9\itextpdf-5.5.9.jar;D:\_mvn_repository\com\itextpdf\tool\xmlworker\5.5.9\xmlworker-5.5.9.jar;D:\_mvn_repository\org\xhtmlrenderer\flying-saucer-core\9.0.8\flying-saucer-core-9.0.8.jar;D:\_mvn_repository\org\xhtmlrenderer\flying-saucer-pdf\9.0.8\flying-saucer-pdf-9.0.8.jar;D:\_mvn_repository\com\lowagie\itext\2.1.7\itext-2.1.7.jar;D:\_mvn_repository\bouncycastle\bcmail-jdk14\138\bcmail-jdk14-138.jar;D:\_mvn_repository\bouncycastle\bcprov-jdk14\138\bcprov-jdk14-138.jar;D:\_mvn_repository\org\bouncycastle\bctsp-jdk14\1.38\bctsp-jdk14-1.38.jar;D:\_mvn_repository\org\bouncycastle\bcprov-jdk14\1.38\bcprov-jdk14-1.38.jar;D:\_mvn_repository\org\bouncycastle\bcmail-jdk14\1.38\bcmail-jdk14-1.38.jar;D:\_mvn_repository\net\sf\dozer\dozer\5.5.1\dozer-5.5.1.jar;D:\_mvn_repository\org\slf4j\jcl-over-slf4j\1.7.5\jcl-over-slf4j-1.7.5.jar;D:\_mvn_repository\org\apache\struts\xwork\xwork-core\2.2.1\xwork-core-2.2.1.jar;D:\_mvn_repository\ognl\ognl\3.0\ognl-3.0.jar
		 */
		Properties prop1 = System.getProperties();
		Set<Object> set2 = prop1.keySet();
		for(Object key : set2) {
			System.out.format("%s 是 %s%n", key, prop1.get(key));
		}
	}

	@Test
	public void testLibraryPath() {
		System.out.println(System.getProperty("java.library.path"));
	}

}
