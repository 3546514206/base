import fs from "fs";

fs.readFile('package.json', 'UTF-8', (err, data) => {
    if (err) {
        console.log(err)
    } else {
        console.log(data)
    }
})