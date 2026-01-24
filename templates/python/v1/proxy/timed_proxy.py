from __future__ import annotations

import time
from typing import Any, Callable


class TimedProxy:
    def __init__(self, target: Any) -> None:
        self._target = target

    def __getattr__(self, name: str) -> Any:
        value = getattr(self._target, name)
        if not callable(value):
            return value

        def wrapper(*args: Any, **kwargs: Any) -> Any:
            start = time.perf_counter()
            result = value(*args, **kwargs)
            end = time.perf_counter()
            ms = (end - start) * 1000
            print(f"PROXY: {name} took {ms:.3f} ms")
            return result

        return wrapper
