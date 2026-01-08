const editor = document.getElementById("editor");
const status = document.getElementById("status");

let savedContent = "";

// 更新状态
function setStatus(text) {
    status.textContent = text;
}

// 自动保存
function save() {
    const content = editor.value;
    localStorage.setItem('notepad-content', content);
    savedContent = content;
    setStatus("已保存");
}

function load() {
    const content = localStorage.getItem('notepad-content');
    if (content !== null) {
        editor.value = content;
        savedContent = content;
        setStatus('已加载');
    }
}




