"use strict";
// 在元素类型后面加上[]
let arr01 = [1, 2];
// 或者使用数组泛型
let arr02 = [1, 2];
let sites = new Array("Google", "Runoob", "Taobao", "Facebook");

function disp(arr_sites) {
    for (let i = 0; i < arr_sites.length; i++) {
        console.log(arr_sites[i]);
    }
}

disp(sites);
let gogle = sites.pop();
console.log(gogle);
let tempArr001 = [10, 2, 4, 19, 35, 3, 6];

function isGeTenIndex(element, index, array) {
    return element > 10;
}

let filterList = tempArr001.filter(isGeTenIndex);
console.log(filterList);
//# sourceMappingURL=arr_test.js.map