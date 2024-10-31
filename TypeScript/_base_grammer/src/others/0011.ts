function identity<T>(a: T): T {
    return a;
}

console.log(identity("sss"));


function printArray<E>(arr: E[]): void {
    arr.forEach(x => console.log(x));
}

printArray([1, 2, 3, 4, 5]);