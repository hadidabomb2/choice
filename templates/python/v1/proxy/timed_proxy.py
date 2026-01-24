from __future__ import annotations

import time
from typing import Any, Callable

MS_IN_SECOND = 1000
DURATION_PRECISION = 3


class TimedProxy:
    def __init__(self, target: Any) -> None:
        self._target = target

    def __getattr__(self, name: str) -> Any:
        value = getattr(self._target, name)
        if not callable(value):
            return value

        def wrapper(*args: Any, **kwargs: Any) -> Any:
            start_time = time.perf_counter()
            result = value(*args, **kwargs)
            end_time = time.perf_counter()
            duration_ms = _to_milliseconds(end_time - start_time)
            print(f"PROXY: {name} took {duration_ms:.{DURATION_PRECISION}f} ms")
            return result

        return wrapper


def _to_milliseconds(seconds: float) -> float:
    return seconds * MS_IN_SECOND
