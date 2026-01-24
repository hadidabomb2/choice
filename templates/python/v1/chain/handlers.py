from __future__ import annotations

from dataclasses import dataclass
from typing import Optional


@dataclass(slots=True)
class Request:
    id: str
    payload: str


@dataclass(slots=True)
class Handler:
    next: Optional["Handler"] = None

    def link_with(self, next_handler: "Handler") -> "Handler":
        self.next = next_handler
        return next_handler

    def handle(self, request: Request) -> str:
        result = self.process(request)
        if result is not None:
            return result
        if self.next is None:
            return f"Unhandled request: {request.id}"
        return self.next.handle(request)

    def process(self, request: Request) -> Optional[str]:
        raise NotImplementedError


class ValidationHandler(Handler):
    def process(self, request: Request) -> Optional[str]:
        if not request.payload:
            return f"Validation failed for request: {request.id}"
        return None


class AuthHandler(Handler):
    def process(self, request: Request) -> Optional[str]:
        if "token:" in request.payload:
            return None
        return f"Auth failed for request: {request.id}"


class BusinessHandler(Handler):
    def process(self, request: Request) -> Optional[str]:
        return f"Processed request {request.id} with payload length {len(request.payload)}"
