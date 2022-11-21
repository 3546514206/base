const fs = require("fs");

fs.writeFile("./2.txt", "lalalalaalal", function (err) {
    console.log(err);
});