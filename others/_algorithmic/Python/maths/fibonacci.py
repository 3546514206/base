"""
Calculates the Fibonacci sequence using iteration, recursion, memoization,
and a simplified form of Binet's formula

NOTE 1: the iterative, recursive, memoization functions are more accurate than
the Binet's formula function because the Binet formula function  uses floats

NOTE 2: the Binet's formula function is much more limited in the size of inputs
that it can handle due to the size limitations of Python floats

See benchmark numbers in __main__ for performance comparisons/
https://en.wikipedia.org/wiki/Fibonacci_number for more information
"""

import functools
from collections.abc import Iterator
from math import sqrt
from time import time


def time_func(func, *args, **kwargs):
    """
    Times the execution of a function with parameters
    """
    start = time()
    output = func(*args, **kwargs)
    end = time()
    if int(end - start) > 0:
        print(f"{func.__name__} runtime: {(end - start):0.4f} s")
    else:
        print(f"{func.__name__} runtime: {(end - start) * 1000:0.4f} ms")
    return output


def fib_iterative_yield(n: int) -> Iterator[int]:
    """
    Calculates the first n (1-indexed) Fibonacci numbers using iteration with yield
    >>> list(fib_iterative_yield(0))
    [0]
    >>> tuple(fib_iterative_yield(1))
    (0, 1)
    >>> tuple(fib_iterative_yield(5))
    (0, 1, 1, 2, 3, 5)
    >>> tuple(fib_iterative_yield(10))
    (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
    >>> tuple(fib_iterative_yield(-1))
    Traceback (most recent call last):
        ...
    ValueError: n is negative
    """
    if n < 0:
        raise ValueError("n is negative")
    a, b = 0, 1
    yield a
    for _ in range(n):
        yield b
        a, b = b, a + b


def fib_iterative(n: int) -> list[int]:
    """
    Calculates the first n (0-indexed) Fibonacci numbers using iteration
    >>> fib_iterative(0)
    [0]
    >>> fib_iterative(1)
    [0, 1]
    >>> fib_iterative(5)
    [0, 1, 1, 2, 3, 5]
    >>> fib_iterative(10)
    [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    >>> fib_iterative(-1)
    Traceback (most recent call last):
        ...
    ValueError: n is negative
    """
    if n < 0:
        raise ValueError("n is negative")
    if n == 0:
        return [0]
    fib = [0, 1]
    for _ in range(n - 1):
        fib.append(fib[-1] + fib[-2])
    return fib


def fib_recursive(n: int) -> list[int]:
    """
    Calculates the first n (0-indexed) Fibonacci numbers using recursion
    >>> fib_iterative(0)
    [0]
    >>> fib_iterative(1)
    [0, 1]
    >>> fib_iterative(5)
    [0, 1, 1, 2, 3, 5]
    >>> fib_iterative(10)
    [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    >>> fib_iterative(-1)
    Traceback (most recent call last):
        ...
    ValueError: n is negative
    """

    def fib_recursive_term(i: int) -> int:
        """
        Calculates the i-th (0-indexed) Fibonacci number using recursion
        >>> fib_recursive_term(0)
        0
        >>> fib_recursive_term(1)
        1
        >>> fib_recursive_term(5)
        5
        >>> fib_recursive_term(10)
        55
        >>> fib_recursive_term(-1)
        Traceback (most recent call last):
            ...
        Exception: n is negative
        """
        if i < 0:
            raise ValueError("n is negative")
        if i < 2:
            return i
        return fib_recursive_term(i - 1) + fib_recursive_term(i - 2)

    if n < 0:
        raise ValueError("n is negative")
    return [fib_recursive_term(i) for i in range(n + 1)]


def fib_recursive_cached(n: int) -> list[int]:
    """
    Calculates the first n (0-indexed) Fibonacci numbers using recursion
    >>> fib_iterative(0)
    [0]
    >>> fib_iterative(1)
    [0, 1]
    >>> fib_iterative(5)
    [0, 1, 1, 2, 3, 5]
    >>> fib_iterative(10)
    [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    >>> fib_iterative(-1)
    Traceback (most recent call last):
        ...
    ValueError: n is negative
    """

    @functools.cache
    def fib_recursive_term(i: int) -> int:
        """
        Calculates the i-th (0-indexed) Fibonacci number using recursion
        """
        if i < 0:
            raise ValueError("n is negative")
        if i < 2:
            return i
        return fib_recursive_term(i - 1) + fib_recursive_term(i - 2)

    if n < 0:
        raise ValueError("n is negative")
    return [fib_recursive_term(i) for i in range(n + 1)]


def fib_memoization(n: int) -> list[int]:
    """
    Calculates the first n (0-indexed) Fibonacci numbers using memoization
    >>> fib_memoization(0)
    [0]
    >>> fib_memoization(1)
    [0, 1]
    >>> fib_memoization(5)
    [0, 1, 1, 2, 3, 5]
    >>> fib_memoization(10)
    [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    >>> fib_iterative(-1)
    Traceback (most recent call last):
        ...
    ValueError: n is negative
    """
    if n < 0:
        raise ValueError("n is negative")
    # Cache must be outside recursuive function
    # other it will reset every time it calls itself.
    cache: dict[int, int] = {0: 0, 1: 1, 2: 1}  # Prefilled cache

    def rec_fn_memoized(num: int) -> int:
        if num in cache:
            return cache[num]

        value = rec_fn_memoized(num - 1) + rec_fn_memoized(num - 2)
        cache[num] = value
        return value

    return [rec_fn_memoized(i) for i in range(n + 1)]


def fib_binet(n: int) -> list[int]:
    """
    Calculates the first n (0-indexed) Fibonacci numbers using a simplified form
    of Binet's formula:
    https://en.m.wikipedia.org/wiki/Fibonacci_number#Computation_by_rounding

    NOTE 1: this function diverges from fib_iterative at around n = 71, likely
    due to compounding floating-point arithmetic errors

    NOTE 2: this function doesn't accept n >= 1475 because it overflows
    thereafter due to the size limitations of Python floats
    >>> fib_binet(0)
    [0]
    >>> fib_binet(1)
    [0, 1]
    >>> fib_binet(5)
    [0, 1, 1, 2, 3, 5]
    >>> fib_binet(10)
    [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    >>> fib_binet(-1)
    Traceback (most recent call last):
        ...
    ValueError: n is negative
    >>> fib_binet(1475)
    Traceback (most recent call last):
        ...
    ValueError: n is too large
    """
    if n < 0:
        raise ValueError("n is negative")
    if n >= 1475:
        raise ValueError("n is too large")
    sqrt_5 = sqrt(5)
    phi = (1 + sqrt_5) / 2
    return [round(phi**i / sqrt_5) for i in range(n + 1)]


if __name__ == "__main__":
    from doctest import testmod

    testmod()
    # Time on an M1 MacBook Pro -- Fastest to slowest
    num = 30
    time_func(fib_iterative_yield, num)  # 0.0012 ms
    time_func(fib_iterative, num)  # 0.0031 ms
    time_func(fib_binet, num)  # 0.0062 ms
    time_func(fib_memoization, num)  # 0.0100 ms
    time_func(fib_recursive_cached, num)  # 0.0153 ms
    time_func(fib_recursive, num)  # 257.0910 ms
