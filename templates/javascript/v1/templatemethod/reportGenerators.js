const NOT_IMPLEMENTED_ERROR = "Not implemented";
const SUMMARY_PREFIX = "Summary report: ";
const DETAIL_PREFIX = "Detailed report:";
const DETAIL_HEADER = "[DETAIL]";
const BULLET_PREFIX = "- ";
const LINE_BREAK = "\n";

function normalizeInput(input) {
  return (input ?? "").trim();
}

function formatBulletedBody(text) {
  return `${DETAIL_PREFIX}${LINE_BREAK}${BULLET_PREFIX}${text.split(LINE_BREAK).join(`${LINE_BREAK}${BULLET_PREFIX}`)}`;
}

export class ReportGenerator {
  generate(input) {
    const normalized = this.normalize(input);
    const body = this.buildBody(normalized);
    return this.format(body);
  }

  normalize(input) {
    return normalizeInput(input);
  }

  buildBody(_normalized) {
    throw new Error(NOT_IMPLEMENTED_ERROR);
  }

  format(body) {
    return body;
  }
}

export class SummaryReportGenerator extends ReportGenerator {
  buildBody(normalized) {
    return `${SUMMARY_PREFIX}${normalized}`;
  }
}

export class DetailedReportGenerator extends ReportGenerator {
  buildBody(normalized) {
    return formatBulletedBody(normalized);
  }

  format(body) {
    return `${DETAIL_HEADER}${LINE_BREAK}${body}`;
  }
}
