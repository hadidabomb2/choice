const NOT_IMPLEMENTED_ERROR = "Not implemented";
const TOKEN_PREFIX = "token:";
const UNHANDLED_PREFIX = "Unhandled request: ";
const VALIDATION_FAILED_PREFIX = "Validation failed for request: ";
const AUTH_FAILED_PREFIX = "Auth failed for request: ";
const PROCESSED_PREFIX = "Processed request ";
const PAYLOAD_LENGTH_LABEL = " with payload length ";

function isDefined(value) {
  return value !== null && value !== undefined;
}

function formatUnhandledRequest(requestId) {
  return `${UNHANDLED_PREFIX}${requestId}`;
}

function formatValidationFailure(requestId) {
  return `${VALIDATION_FAILED_PREFIX}${requestId}`;
}

function formatAuthFailure(requestId) {
  return `${AUTH_FAILED_PREFIX}${requestId}`;
}

function formatProcessed(requestId, payloadLength) {
  return `${PROCESSED_PREFIX}${requestId}${PAYLOAD_LENGTH_LABEL}${payloadLength}`;
}

export class Handler {
  constructor() {
    this.next = null;
  }

  linkWith(next) {
    this.next = next;
    return next;
  }

  handle(request) {
    const result = this.process(request);
    if (isDefined(result)) {
      return result;
    }
    if (!this.next) {
      return formatUnhandledRequest(request.id);
    }
    return this.next.handle(request);
  }

  process(_request) {
    throw new Error(NOT_IMPLEMENTED_ERROR);
  }
}

export class ValidationHandler extends Handler {
  process(request) {
    if (!request.payload) {
      return formatValidationFailure(request.id);
    }
    return null;
  }
}

export class AuthHandler extends Handler {
  process(request) {
    if (request.payload.includes(TOKEN_PREFIX)) {
      return null;
    }
    return formatAuthFailure(request.id);
  }
}

export class BusinessHandler extends Handler {
  process(request) {
    return formatProcessed(request.id, request.payload.length);
  }
}
