const express = require("express");
const path = require("path");


const app = express();
const publicPath = path.join(__dirname, "public");


app.use(express.static(publicPath));

console.log(__dirname);

app.listen(5001);
