package io.http;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.jawb.tools.io.http.HttpClient;
import de.jawb.tools.io.http.HttpRequest;
import de.jawb.tools.io.http.HttpResponse;

@RunWith(Parameterized.class)
public class HttpClientTest {

    private String url;

    public HttpClientTest(String url) {
        this.url = url;
    }

    @Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[] { "http://www.google.com/{path}" });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequest_addPathParam() {
        HttpRequest r = new HttpRequest(url, "GET");
        r.addPathParameter("asdad", "search");
    }

    @Test
    public void testRequest_addPathParam2() {
        HttpRequest r = new HttpRequest(url, "GET");
        r.addPathParameter("path", "search");
    }

    @Test
    public void testClient() {
        HttpRequest r = new HttpRequest(url, "GET");
        r.addPathParameter("path", "search").addParameter("q", "apple");

        HttpResponse response = new HttpClient().sendRequest(r);
        
        Assert.assertEquals(403, response.getCode());
    }

}
