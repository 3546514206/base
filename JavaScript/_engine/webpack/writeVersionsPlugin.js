let fs = require('fs-extra');
let path = require('path');

function WriteVersionsPlugin(file) {
    this.file = file;
}

WriteVersionsPlugin.prototype.writeStats = function (compiler, stats) {
    stats = stats.toJson();
    let assetsByChunkName = stats.assetsByChunkName;

    for (let name in assetsByChunkName) {
        if (assetsByChunkName[name] instanceof Array) {
            assetsByChunkName[name] = assetsByChunkName[name].map(function (path) {
                return compiler.options.output.publicPath + path;
            });
        } else {
            assetsByChunkName[name] = compiler.options.output.publicPath + assetsByChunkName[name];
        }
    }

    // console.log("WRITING VERSIONS", this.file, assetsByChunkName);

    fs.ensureDirSync(path.dirname(this.file));

    fs.writeFileSync(this.file, JSON.stringify(assetsByChunkName));
};

WriteVersionsPlugin.prototype.apply = function (compiler) {
    compiler.plugin("done", this.writeStats.bind(this, compiler));
};

module.exports = WriteVersionsPlugin;