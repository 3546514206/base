const {app, BrowserWindow} = require("electron");
const path = require("path");
const {registerIpc} = require("./ipc");

let mainWindow = null;

function createWindow() {
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, "preload.js")
        }
    });

    mainWindow.loadFile(path.join(__dirname, "ui", "index.html")).catch((err) => {
        console.error(err);
    });
}

app.whenReady().then(() => {
    createWindow();
    registerIpc();
});

app.on("window-all-closed", () => {
    if (process.platform !== "darwin") {
        app.quit();
    }
});
