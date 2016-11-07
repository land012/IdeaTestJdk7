package cf.umbrella.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by xudazhou on 2016/11/4.
 */
public class SolrTest {
    public static final String SOLR_URL = "http://192.168.186.3:8080/solr";

    SolrServer server;

    @Before
    public void bofore() {
        server = new HttpSolrServer(SOLR_URL);
    }

    /**
     * 查询
     * @throws Exception
     */
    @Test
    public void test1_query() throws Exception {
        SolrParams params = new SolrQuery("ipod");
        SolrResponse response = server.query(params);
        NamedList<Object> namedList = response.getResponse();
        SolrDocumentList documentList = (SolrDocumentList)namedList.get("response");
        System.out.println(documentList);
        System.out.println(documentList.getNumFound());
        for(SolrDocument document : documentList) {
            System.out.println(document.get("id"));
        }
    }

    /**
     * 查询2
     * @throws Exception
     */
    @Test
    public void test1_2_Query() throws Exception {
        SolrQuery params = new SolrQuery();
        params.setQuery("id:1");
        SolrResponse response = server.query(params);
        NamedList<Object> namedList = response.getResponse();
        SolrDocumentList documentList = (SolrDocumentList)namedList.get("response");
        System.out.println("documentList=" + documentList);
        System.out.println("documentList.getNumFound()=" + documentList.getNumFound());
        for(SolrDocument document : documentList) {
            System.out.println(document.get("id"));
        }
    }

    /**
     * add
     */
    @Test
    public void test2_addDocu() throws Exception {
        SolrInputDocument docu = new SolrInputDocument();
        docu.addField("id", "1");
        docu.addField("name", "Nobunaga");
        docu.addField("lastModified", new Date());
        UpdateResponse resp = server.add(docu);
        server.commit();
        System.out.println(resp);
    }

    /**
     * add Bean
     * @throws Exception
     */
    @Test
    public void test3_addBean() throws Exception {
        Collection1 collection1 = new Collection1();
        collection1.setId("1");
        collection1.setSku("sku1");
        collection1.setName("Mikasa");
        collection1.setCat(new String[]{"c1", "c2"});
        collection1.setLastModified(new Date());
        collection1.setLinks("www.baidu.com");
        UpdateResponse resp = server.addBean(collection1);
        System.out.println(resp);
        System.out.println(server.commit());
    }

    /**
     * delete
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        System.out.println(server.deleteByQuery("id:1"));
        System.out.println(server.commit());
    }

    @Test
    public void test5() throws Exception {
        System.out.println(server.ping());
    }
}
