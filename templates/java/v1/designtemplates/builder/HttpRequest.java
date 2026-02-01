package designtemplates.builder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest {
    private static final String DEFAULT_METHOD = "GET";
    private static final String DEFAULT_BODY = "";
    private static final int DEFAULT_TIMEOUT_MS = 5000;
    private final String method;
    private final String url;
    private final Map<String, String> headers;
    private final String body;
    private final int timeoutMs;

    private HttpRequest(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = Collections.unmodifiableMap(new LinkedHashMap<>(builder.headers));
        this.body = builder.body;
        this.timeoutMs = builder.timeoutMs;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public int getTimeoutMs() {
        return timeoutMs;
    }

    public String summary() {
        return method + " " + url + " (headers=" + headers.size() + ", timeoutMs=" + timeoutMs + ")";
    }

    public static class Builder {
        private String method = DEFAULT_METHOD;
        private String url;
        private final Map<String, String> headers = new LinkedHashMap<>();
        private String body = DEFAULT_BODY;
        private int timeoutMs = DEFAULT_TIMEOUT_MS;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeoutMs(int timeoutMs) {
            this.timeoutMs = timeoutMs;
            return this;
        }

        public HttpRequest build() {
            if (url == null || url.isBlank()) {
                throw new IllegalStateException("url is required");
            }
            return new HttpRequest(this);
        }
    }
}
