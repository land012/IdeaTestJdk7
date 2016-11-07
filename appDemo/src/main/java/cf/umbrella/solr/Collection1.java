package cf.umbrella.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * Created by xudazhou on 2016/11/5.
 */
public class Collection1 {
    @Field
    private String id;
    @Field
    private String sku;
    @Field
    private String name;
    @Field
    private String[] cat;
    @Field
    private Date lastModified;

    @Field
    private String links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCat() {
        return cat;
    }

    public void setCat(String[] cat) {
        this.cat = cat;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
