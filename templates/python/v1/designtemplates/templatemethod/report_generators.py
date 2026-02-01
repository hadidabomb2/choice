from __future__ import annotations

from dataclasses import dataclass

SUMMARY_PREFIX = "Summary report: "
DETAIL_PREFIX = "Detailed report:"
DETAIL_HEADER = "[DETAIL]"
LINE_BREAK = "\n"
BULLET_PREFIX = "- "


@dataclass(slots=True)
class BaseReportGenerator:
    def generate(self, input_text: str | None) -> str:
        normalized = self.normalize(input_text)
        body = self.build_body(normalized)
        return self.format(body)

    def normalize(self, input_text: str | None) -> str:
        return (input_text or "").strip()

    def build_body(self, normalized: str) -> str:
        raise NotImplementedError

    def format(self, body: str) -> str:
        return body


@dataclass(slots=True)
class SummaryReportGenerator(BaseReportGenerator):
    def build_body(self, normalized: str) -> str:
        return f"{SUMMARY_PREFIX}{normalized}"


@dataclass(slots=True)
class DetailedReportGenerator(BaseReportGenerator):
    def build_body(self, normalized: str) -> str:
        return _format_bulleted_body(normalized)

    def format(self, body: str) -> str:
        return f"{DETAIL_HEADER}{LINE_BREAK}{body}"


def _format_bulleted_body(text: str) -> str:
    return f"{DETAIL_PREFIX}{LINE_BREAK}{BULLET_PREFIX}" + text.replace(
        LINE_BREAK, f"{LINE_BREAK}{BULLET_PREFIX}"
    )
