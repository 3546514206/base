const { app, BrowserWindow, ipcMain } = require('electron/main');

const path = require('node:path');

const createWindow = () => {
    const window = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js')
        }
    });
    window.loadFile('index.html');
    window.webContents.openDevTools();
    window.console.log(window.webContents);
};

app.whenReady().then(() => {
    ipcMain.handle('ping', () => 'pong');
    createWindow();
});