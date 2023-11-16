"""
An AND Gate is a logic gate in boolean algebra which results to 1 (True) if both the
inputs are 1, and 0 (False) otherwise.

Following is the truth table of an AND Gate:
    ------------------------------
    | Input 1 | Input 2 | Output |
    ------------------------------
    |    0    |    0    |    0   |
    |    0    |    1    |    0   |
    |    1    |    0    |    0   |
    |    1    |    1    |    1   |
    ------------------------------

Refer - https://www.geeksforgeeks.org/logic-gates-in-python/
"""


def and_gate(input_1: int, input_2: int) -> int:
    """
    Calculate AND of the input values

    >>> and_gate(0, 0)
    0
    >>> and_gate(0, 1)
    0
    >>> and_gate(1, 0)
    0
    >>> and_gate(1, 1)
    1
    """
    return int(input_1 and input_2)


if __name__ == "__main__":
    import doctest

    doctest.testmod()
