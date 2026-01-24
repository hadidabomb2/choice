import time
import tracemalloc
from functools import wraps
from typing import Any, Callable, Dict, Tuple

BYTES_IN_KB = 1024
MS_IN_SECOND = 1000
ROUND_PRECISION = 3


def profile(name: str | None = None) -> Callable[[Callable[..., Any]], Callable[..., Any]]:
    """
    Decorator that measures:
    - execution time (ms)
    - memory usage delta (KB)
    - number of local variables created
    """

    def decorator(func: Callable[..., Any]) -> Callable[..., Any]:
        label = name or func.__name__

        @wraps(func)
        def wrapper(*args: Any, **kwargs: Any) -> Any:
            tracemalloc.start()
            start_time = time.perf_counter()

            result = func(*args, **kwargs)

            end_time = time.perf_counter()
            memory_current, memory_peak = _get_memory_snapshot()

            report: Dict[str, Any] = {
                "name": label,
                "time_ms": _to_milliseconds(end_time - start_time),
                "memory_kb_current": _to_kilobytes(memory_current),
                "memory_kb_peak": _to_kilobytes(memory_peak),
                "locals_count": _count_locals(func)
            }

            print("PERF:", report)
            return result

        return wrapper

    return decorator


def _get_memory_snapshot() -> Tuple[int, int]:
    current, peak = tracemalloc.get_traced_memory()
    tracemalloc.stop()
    return current, peak


def _to_kilobytes(value_bytes: int) -> float:
    return round(value_bytes / BYTES_IN_KB, ROUND_PRECISION)


def _to_milliseconds(seconds: float) -> float:
    return round(seconds * MS_IN_SECOND, ROUND_PRECISION)


def _count_locals(func: Callable[..., Any]) -> int:
    return len(func.__code__.co_varnames)