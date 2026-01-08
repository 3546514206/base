/* ================= DOM ================= */

const editor = document.getElementById('editor');
const status = document.getElementById('status');
const openBtn = document.getElementById('openBtn');
const saveBtn = document.getElementById('saveBtn');

/* ================= 状态 ================= */

let savedContent = '';
let autoSaveTimer = null;

/* ================= 工具 ================= */

function setStatus(text) {
    status.textContent = text;
}

/* ================= 草稿 ================= */

function autoSaveDraft() {
    const content = editor.value;
    localStorage.setItem('notepad-content', content);
    savedContent = content;
    setStatus('已自动保存（草稿）');
}

function loadDraft() {
    const content = localStorage.getItem('notepad-content');
    if (content !== null) {
        editor.value = content;
        savedContent = content;
        setStatus('草稿已恢复');
    } else {
        setStatus('未保存');
    }
}

/* ================= 保存 ================= */

async function saveToFile() {
    const content = editor.value;

    try {
        const result = await window.api.saveFile(content);

        if (result && result.success) {
            savedContent = content;
            setStatus('已保存到文件');
        } else {
            setStatus('已取消保存');
        }
    } catch (err) {
        console.error(err);
        setStatus('保存失败');
    }
}

/* ================= 打开文件（新增） ================= */

async function openFile() {
    try {
        const result = await window.api.openFile();

        if (!result || !result.success) {
            setStatus('已取消打开');
            return;
        }

        editor.value = result.content;
        savedContent = result.content;

        // 打开文件后，清空草稿
        localStorage.removeItem('notepad-content');

        setStatus('已打开文件');
    } catch (err) {
        console.error(err);
        setStatus('打开失败');
    }
}

/* ================= 输入监听 ================= */

editor.addEventListener('input', () => {
    setStatus('未保存');

    if (autoSaveTimer) {
        clearTimeout(autoSaveTimer);
    }

    autoSaveTimer = setTimeout(() => {
        autoSaveDraft();
    }, 800);
});

/* ================= 快捷键 ================= */

document.addEventListener('keydown', (e) => {
    const isSave =
        (e.ctrlKey || e.metaKey) &&
        e.key.toLowerCase() === 's';

    const isOpen =
        (e.ctrlKey || e.metaKey) &&
        e.key.toLowerCase() === 'o';

    if (isSave) {
        e.preventDefault();
        saveToFile();
    }

    if (isOpen) {
        e.preventDefault();
        openFile();
    }
});

// 按钮事件
openBtn.addEventListener('click', () => {
    openFile();
});

saveBtn.addEventListener('click', () => {
    saveToFile();
});

/* ================= 关闭前兜底 ================= */

window.addEventListener('beforeunload', () => {
    if (editor.value !== savedContent) {
        autoSaveDraft();
    }
});

/* ================= 启动 ================= */

loadDraft();
