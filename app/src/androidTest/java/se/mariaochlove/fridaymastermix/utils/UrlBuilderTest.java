package se.mariaochlove.fridaymastermix.utils;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

/**
 * Created by love on 2015-07-09.
 */
public class UrlBuilderTest extends AndroidTestCase {

    public UrlBuilder uut;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        uut = new UrlBuilder();
    }

    @SmallTest
    public void testProtocol() {
        uut = new UrlBuilder("http://example.com");
        Assert.assertEquals("http", uut.getProtocol());

        uut = new UrlBuilder("https://example.com");
        Assert.assertEquals("https", uut.getProtocol());

        uut = new UrlBuilder("host.com:80");
        Assert.assertEquals("http", uut.getProtocol());
    }

    @SmallTest
    public void testHost() {
        uut = new UrlBuilder("http://example.com:8080/path");
        Assert.assertEquals("example.com", uut.getHost());

        uut = new UrlBuilder("http://example.com/path");
        Assert.assertEquals("example.com", uut.getHost());
    }

    @SmallTest
    public void testPort() {
        uut = new UrlBuilder("http://example.com:8080/path");
        Assert.assertEquals(8080, uut.getPort());

        uut = new UrlBuilder("http://examplle.com/path");
        Assert.assertEquals(80, uut.getPort());
    }

    @SmallTest
    public void testPath() {
        uut = new UrlBuilder("http://example.com:8080/path1/path2?query1=query2");
        Assert.assertEquals(2, uut.getPath().size());
        Assert.assertEquals("path1", uut.getPath().get(0));
        Assert.assertEquals("path2", uut.getPath().get(1));


        uut = new UrlBuilder("http://examaple.com/path1/path2/path3");
        Assert.assertEquals(3, uut.getPath().size());

        uut = new UrlBuilder("example.com");
        Assert.assertEquals(0, uut.getPath().size());
    }

    @SmallTest
    public void testQueryParameters() {
        uut = new UrlBuilder("http://example.com/path1/?query1=value1&query2=value2");
        Assert.assertEquals("value1", uut.getQueryParam("query1"));
        Assert.assertEquals("value2", uut.getQueryParam("query2"));

        uut = new UrlBuilder("example.com");
        Assert.assertEquals(0, uut.getQueryMap().size());
    }

    @SmallTest
    public void testToString() {
        String url = "https://subdomain.example.com/path1/path2?query1=value1&query2=value2";

        Assert.assertEquals(url, new UrlBuilder(url).toString());
    }
}
