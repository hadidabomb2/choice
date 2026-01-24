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
    if (result !== null && result !== undefined) {
      return result;
    }
    if (!this.next) {
      return `Unhandled request: ${request.id}`;
    }
    return this.next.handle(request);
  }

  process(_request) {
    throw new Error("Not implemented");
  }
}

export class ValidationHandler extends Handler {
  process(request) {
    if (!request.payload) {
      return `Validation failed for request: ${request.id}`;
    }
    return null;
  }
}

export class AuthHandler extends Handler {
  process(request) {
    if (request.payload.includes("token:")) {
      return null;
    }
    return `Auth failed for request: ${request.id}`;
  }
}

export class BusinessHandler extends Handler {
  process(request) {
    return `Processed request ${request.id} with payload length ${request.payload.length}`;
  }
}
