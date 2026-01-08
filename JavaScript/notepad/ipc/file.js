const {ipcMain, dialog} = require("electron");
const fs = require("fs");

module.exports = function registerFileIpc() {

    /* ========== 打开文件 ========== */
    ipcMain.handle("file:open", async () => {
        const result = await dialog.showOpenDialog({
            title: "打开文件",
            properties: ["openFile"],
            filters: [
                {name: "文本文件", extensions: ["txt", "md", "json", "log"]},
                {name: "所有文件", extensions: ["*"]}
            ]
        });

        if (result.canceled || result.filePaths.length === 0) {
            return {success: false};
        }

        const filePath = result.filePaths[0];

        try {
            const content = fs.readFileSync(filePath, "utf-8");
            return {success: true, filePath, content};
        } catch (err) {
            return {success: false, error: err.message};
        }
    });

    /* ========== 保存文件 ========== */
    ipcMain.handle("file:save", async (event, content) => {
        const {canceled, filePath} = await dialog.showSaveDialog({
            title: "保存文件"
        });

        if (canceled || !filePath) {
            return {success: false};
        }

        try {
            fs.writeFileSync(filePath, content, "utf-8");
            return {success: true, filePath};
        } catch (err) {
            return {success: false, error: err.message};
        }
    });

};
