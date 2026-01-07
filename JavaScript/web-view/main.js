const { app, BrowserWindow, ipcMain } = require('electron/main');

const createWindow = () => {
    const window = new BrowserWindow({
        width: 800,
        height: 600
    });

    window.loadFile('index.html');
};

app.whenReady().then(() => {
    createWindow();
});