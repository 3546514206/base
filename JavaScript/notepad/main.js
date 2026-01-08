const { app, BrowserWindow, ipcMain } = require('electron/main');
const path = require('path');

let mainWindow = null;

/* ================= 创建窗口 ================= */

function createMainWindow() {
    mainWindow = new BrowserWindow({
        width: 900,
        height: 600,
        show: false, // 等页面 ready 再显示，避免白屏
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            contextIsolation: true,
            nodeIntegration: false,
            sandbox: false
        }
    });

    // 加载页面（开发 / 生产通用写法）
    mainWindow.loadFile('index.html');

    // 页面准备好再显示
    mainWindow.once('ready-to-show', () => {
        mainWindow.show();
    });

    // 窗口关闭
    mainWindow.on('closed', () => {
        mainWindow = null;
    });
}

/* ================= App 生命周期 ================= */

app.whenReady().then(() => {
    createMainWindow();

    // macOS：点击 dock 图标重新创建窗口
    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createMainWindow();
        }
    });
});

// 非 macOS：所有窗口关闭就退出
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

/* ================= IPC（示例，可按需删） ================= */

// 推荐使用 invoke / handle
ipcMain.handle('app:get-version', () => {
    return app.getVersion();
});