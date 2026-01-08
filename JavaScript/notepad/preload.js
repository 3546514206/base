const {contextBridge, ipcRenderer} = require('electron');

contextBridge.exposeInMainWorld('api', {
    saveFile: (content) => {
        return ipcRenderer.invoke('file:save', content);
    },
    openFile: () => ipcRenderer.invoke('file:open')
});



