namespace funcrightnowcall {
    let str = (function (a: number, b: number): string {
        console.log(a + b);
        return "ok";
    })(3, 4);

    console.log(str);
}