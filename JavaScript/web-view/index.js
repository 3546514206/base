const editor = document.getElementById('editor');
const status = document.getElementById('status');

let savedContent = '';
let timer = null;

/* ================= 状态更新 ================= */

function setStatus(text) {
    status.textContent = text;
}

/* ================= 自动保存（localStorage） ================= */

function save() {
    const content = editor.value;
    localStorage.setItem('notepad-content', content);
    savedContent = content;
    setStatus('已保存');
}

function load() {
    const content = localStorage.getItem('notepad-content');
    if (content !== null) {
        editor.value = content;
        savedContent = content;
        setStatus('已加载');
    }
}

/* ================= 输入监听 ================= */

editor.addEventListener('input', () => {
    setStatus('未保存');

    if (timer) {
        clearTimeout(timer);
    }

    timer = setTimeout(() => {
        save();
    }, 800);
});

/* ================= 初始化 ================= */

load();

window.addEventListener('beforeunload', () => {
    if (editor.value !== savedContent) {
        save();
    }
});
