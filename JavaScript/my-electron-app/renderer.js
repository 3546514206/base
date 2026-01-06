const information = document.getElementById('info');

information.innerText = `本应用正在使用 Chrome (v${window.versions.chrome()}),Node.js (v${window.versions.node()}),和 Electron (v${window.versions.electron()})`;

const func = async () => {
    const response = await window.versions.ping()
    information.innerText = information.innerText + "，返回响应 " + response;
};

func();