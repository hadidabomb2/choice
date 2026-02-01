from __future__ import annotations

from dataclasses import dataclass, field
from typing import Dict

DEFAULT_METHOD = "GET"
DEFAULT_BODY = ""
DEFAULT_TIMEOUT_MS = 5000


@dataclass(frozen=True, slots=True)
class HttpRequest:
    method: str
    url: str
    headers: Dict[str, str]
    body: str
    timeout_ms: int

    def summary(self) -> str:
        return f"{self.method} {self.url} (headers={len(self.headers)}, timeoutMs={self.timeout_ms})"


@dataclass(slots=True)
class HttpRequestBuilder:
    method: str = DEFAULT_METHOD
    url: str | None = None
    headers: Dict[str, str] = field(default_factory=dict)
    body: str = DEFAULT_BODY
    timeout_ms: int = DEFAULT_TIMEOUT_MS

    def set_url(self, url: str) -> "HttpRequestBuilder":
        self.url = url
        return self

    def set_method(self, method: str) -> "HttpRequestBuilder":
        self.method = method
        return self

    def add_header(self, key: str, value: str) -> "HttpRequestBuilder":
        self.headers[key] = value
        return self

    def set_body(self, body: str) -> "HttpRequestBuilder":
        self.body = body
        return self

    def set_timeout_ms(self, timeout_ms: int) -> "HttpRequestBuilder":
        self.timeout_ms = timeout_ms
        return self

    def build(self) -> HttpRequest:
        if not self.url:
            raise ValueError("url is required")
        return HttpRequest(
            method=self.method,
            url=self.url,
            headers=dict(self.headers),
            body=self.body,
            timeout_ms=self.timeout_ms,
        )
