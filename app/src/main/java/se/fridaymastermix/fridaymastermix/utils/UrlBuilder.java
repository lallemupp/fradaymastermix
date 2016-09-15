package se.mariaochlove.fridaymastermix.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by love on 2015-07-09.
 */
public class UrlBuilder {

    private static final String PROTOCOL_SEPARATOR = "://";
    private static final String PATH_SEPARATOR = "/";
    private static final String PORT_SEPARATOR = ":";
    private static final String QUERY_STRING_START = "?";
    private static final String QUERY_PAIR_SEPARATOR = "&";
    private static final String QUERY_SEPARATOR = "=";

    private static final Integer DEFAULT_PORT = 80;

    private String protocol;
    private String host;
    private Integer port;
    private List<String> path;
    private Map<String, String> params;

    public UrlBuilder() {
        path = new ArrayList<>();
        params = new LinkedHashMap<>();
    }

    public UrlBuilder(String url) {
        this();
        if (StringUtils.contains(url, PROTOCOL_SEPARATOR)) {
            protocol = StringUtils.substringBefore(url, PROTOCOL_SEPARATOR);
        } else {
            protocol = "http";
        }

        String urlWithoutProtocol = StringUtils.substringAfter(url, PROTOCOL_SEPARATOR);
        String hostWithPort = StringUtils.substringBefore(urlWithoutProtocol, PATH_SEPARATOR);

        if (StringUtils.contains(hostWithPort, PORT_SEPARATOR)) {
            host = StringUtils.substringBefore(hostWithPort, PORT_SEPARATOR);
        } else {
            host = StringUtils.substringBefore(hostWithPort, PATH_SEPARATOR);
        }

        if (StringUtils.contains(hostWithPort, PORT_SEPARATOR)) {
            String portString = StringUtils.substringAfter(hostWithPort, PORT_SEPARATOR);
            port = Integer.valueOf(portString);
        }

        String pathSegment;

        if (StringUtils.contains(urlWithoutProtocol, PATH_SEPARATOR) && StringUtils.contains(urlWithoutProtocol, QUERY_STRING_START)) {
            pathSegment = StringUtils.substringBetween(urlWithoutProtocol, PATH_SEPARATOR, QUERY_STRING_START);
        } else {
            pathSegment = StringUtils.substringAfter(urlWithoutProtocol, PATH_SEPARATOR);
        }

        Collections.addAll(path, StringUtils.split(pathSegment, PATH_SEPARATOR));

        if (urlWithoutProtocol.contains(QUERY_STRING_START)) {
            String queryString = StringUtils.substringAfter(urlWithoutProtocol, QUERY_STRING_START);
            for (String queryPair : StringUtils.split(queryString, QUERY_PAIR_SEPARATOR)) {
                params.put(StringUtils.split(queryPair, "=")[0], StringUtils.split(queryPair, "=")[1]);
            }
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        if (port != null) {
            return port;
        } else {
            return DEFAULT_PORT;
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<String> getPath() {
        return path;
    }

    public String getQueryParam(String name) {
        return params.get(name);
    }

    public void addQueryParam(String name, String value) {
        this.params.put(name, value);
    }

    public Map<String, String> getQueryMap() {
        return params;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProtocol());
        sb.append(PROTOCOL_SEPARATOR);

        if (getHost() != null) {
            sb.append(getHost());
        }

        if (getPort() != DEFAULT_PORT) {
            sb.append(PORT_SEPARATOR);
            sb.append(getPort());
        }

        if (getPath().size() > 0) {
            sb.append(PATH_SEPARATOR);

            for (int i = 0; i < getPath().size(); i++) {
                sb.append(getPath().get(i));

                if (i < getPath().size() - 1) {
                    sb.append(PATH_SEPARATOR);
                }
            }
        }

        if (getQueryMap().size() > 0) {
            sb.append(QUERY_STRING_START);
            Iterator<Map.Entry<String, String>> queryIterator = getQueryMap().entrySet().iterator();

            while (queryIterator.hasNext()) {
                Map.Entry<String, String> queryEntry = queryIterator.next();

                sb.append(queryEntry.getKey());
                sb.append(QUERY_SEPARATOR);
                sb.append(queryEntry.getValue());

                if (queryIterator.hasNext()) {
                    sb.append(QUERY_PAIR_SEPARATOR);
                }
            }
        }

        return sb.toString();
    }
}
