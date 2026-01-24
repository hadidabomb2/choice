import time
import tracemalloc
from functools import wraps
from typing import Callable, Any, Dict

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
            start = time.perf_counter()

            result = func(*args, **kwargs)

            end = time.perf_counter()
            current, peak = tracemalloc.get_traced_memory()
            tracemalloc.stop()

            locals_count = len(func.__code__.co_varnames)

            report: Dict[str, Any] = {
                "name": label,
                "time_ms": round((end - start) * 1000, 3),
                "memory_kb_current": round(current / 1024, 3),
                "memory_kb_peak": round(peak / 1024, 3),
                "locals_count": locals_count
            }

            print("PERF:", report)
            return result

        return wrapper
    return decorator