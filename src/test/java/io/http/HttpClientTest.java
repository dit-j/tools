package io.http;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.jawb.tools.io.http.HttpClient;
import de.jawb.tools.io.http.HttpRequest;
import de.jawb.tools.io.http.HttpRequestMethod;
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
        HttpRequest r = new HttpRequest(HttpRequestMethod.GET, url);
        r.addPathParameter("asdad", "search");
    }

    @Test
    public void testRequest_addPathParam2() {
        HttpRequest r = new HttpRequest(HttpRequestMethod.GET, url);
        r.addPathParameter("path", "search");
    }

    @Test
    public void testRequest_IsCachable() {
        HttpRequest r = new HttpRequest(HttpRequestMethod.GET, url);
        Assert.assertTrue(r.isCachable());
    }

    @Test
    public void testRequest_IsCachable2() {
        HttpRequest r = new HttpRequest(HttpRequestMethod.POST, url);
        Assert.assertFalse(r.isCachable());
    }

    @Test
    public void testClient() throws SocketTimeoutException, UnknownHostException {
        HttpRequest r = new HttpRequest(HttpRequestMethod.GET, url);
        r.addPathParameter("path", "search").addParameter("q", "apple");

        HttpResponse response = new HttpClient().sendRequest(r);

        Assert.assertEquals(403, response.getCode());
    }

}
