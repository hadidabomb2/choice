from __future__ import annotations

from dataclasses import dataclass
from typing import Optional

TOKEN_PREFIX = "token:"
UNHANDLED_PREFIX = "Unhandled request: "
VALIDATION_FAILED_PREFIX = "Validation failed for request: "
AUTH_FAILED_PREFIX = "Auth failed for request: "
PROCESSED_PREFIX = "Processed request "
PAYLOAD_LENGTH_LABEL = " with payload length "


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
            return _format_unhandled(request.id)
        return self.next.handle(request)

    def process(self, request: Request) -> Optional[str]:
        raise NotImplementedError


class ValidationHandler(Handler):
    def process(self, request: Request) -> Optional[str]:
        if not request.payload:
            return _format_validation_failed(request.id)
        return None


class AuthHandler(Handler):
    def process(self, request: Request) -> Optional[str]:
        if TOKEN_PREFIX in request.payload:
            return None
        return _format_auth_failed(request.id)


class BusinessHandler(Handler):
    def process(self, request: Request) -> Optional[str]:
        return _format_processed(request.id, len(request.payload))


def _format_unhandled(request_id: str) -> str:
    return f"{UNHANDLED_PREFIX}{request_id}"


def _format_validation_failed(request_id: str) -> str:
    return f"{VALIDATION_FAILED_PREFIX}{request_id}"


def _format_auth_failed(request_id: str) -> str:
    return f"{AUTH_FAILED_PREFIX}{request_id}"


def _format_processed(request_id: str, payload_length: int) -> str:
    return f"{PROCESSED_PREFIX}{request_id}{PAYLOAD_LENGTH_LABEL}{payload_length}"
