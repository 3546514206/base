let fs = require("fs");

fs.readFile("./input.txt",function (err,data) {
    if(err){
        console.error(err);
    }

    console.log(data.toString());

});