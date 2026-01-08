const {app, BrowserWindow, ipcMain, dialog} = require('electron/main');
const path = require('path');
const fs = require('fs');

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
            sandbox: true
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

/* ========== IPC：保存文件 ========== */
ipcMain.handle('file:save',
    async (event, content) => {
        // await 用来“暂停当前 async 函数的执行”，
        // 等一个 Promise 有结果（resolve / reject）之后，再继续往下执行。
        // 它只影响当前函数，不会阻塞整个线程或事件循环。
        // await 只能出现在 async 函数内部
        const {canceled, filePath} = await dialog.showSaveDialog({
            title: '保存文件',
            defaultPath: 'note.txt',
            filters: [{name: 'Text', extensions: ['txt']}]
        });

        if (canceled || !filePath) {
            return {success: false};
        }

        await fs.promises.writeFile(filePath, content, 'utf8');

        return {
            success: true,
            path: filePath
        };
    });

/* ========== IPC：打开文件 ========== */
ipcMain.handle('file:open', async () => {
    const result = await dialog.showOpenDialog({
        title: '打开文件',
        properties: ['openFile'],
        filters: [
            { name: '文本文件', extensions: ['txt', 'md', 'json', 'log'] },
            { name: '所有文件', extensions: ['*'] }
        ]
    });

    if (result.canceled || result.filePaths.length === 0) {
        return { success: false };
    }

    const filePath = result.filePaths[0];

    try {
        const content = fs.readFileSync(filePath, 'utf-8');

        return {
            success: true,
            filePath,
            content
        };
    } catch (err) {
        return {
            success: false,
            error: err.message
        };
    }
});
