export class HttpRequest {
  constructor({ method, url, headers, body, timeoutMs }) {
    this.method = method;
    this.url = url;
    this.headers = headers;
    this.body = body;
    this.timeoutMs = timeoutMs;
  }

  summary() {
    return `${this.method} ${this.url} (headers=${Object.keys(this.headers).length}, timeoutMs=${this.timeoutMs})`;
  }
}

export class HttpRequestBuilder {
  constructor() {
    this.method = "GET";
    this.url = null;
    this.headers = {};
    this.body = "";
    this.timeoutMs = 5000;
  }

  setUrl(url) {
    this.url = url;
    return this;
  }

  setMethod(method) {
    this.method = method;
    return this;
  }

  addHeader(key, value) {
    this.headers[key] = value;
    return this;
  }

  setBody(body) {
    this.body = body;
    return this;
  }

  setTimeoutMs(timeoutMs) {
    this.timeoutMs = timeoutMs;
    return this;
  }

  build() {
    if (!this.url) {
      throw new Error("url is required");
    }
    return new HttpRequest({
      method: this.method,
      url: this.url,
      headers: { ...this.headers },
      body: this.body,
      timeoutMs: this.timeoutMs
    });
  }
}
