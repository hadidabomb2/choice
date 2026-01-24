export class ReportGenerator {
  generate(input) {
    const normalized = this.normalize(input);
    const body = this.buildBody(normalized);
    return this.format(body);
  }

  normalize(input) {
    return (input ?? "").trim();
  }

  buildBody(_normalized) {
    throw new Error("Not implemented");
  }

  format(body) {
    return body;
  }
}

export class SummaryReportGenerator extends ReportGenerator {
  buildBody(normalized) {
    return `Summary report: ${normalized}`;
  }
}

export class DetailedReportGenerator extends ReportGenerator {
  buildBody(normalized) {
    return `Detailed report:\n- ${normalized.split("\n").join("\n- ")}`;
  }

  format(body) {
    return `[DETAIL]\n${body}`;
  }
}
