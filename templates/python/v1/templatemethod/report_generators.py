from __future__ import annotations

from dataclasses import dataclass


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
        return f"Summary report: {normalized}"


@dataclass(slots=True)
class DetailedReportGenerator(BaseReportGenerator):
    def build_body(self, normalized: str) -> str:
        return "Detailed report:\n- " + normalized.replace("\n", "\n- ")

    def format(self, body: str) -> str:
        return "[DETAIL]\n" + body
