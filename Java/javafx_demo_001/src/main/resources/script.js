document.getElementById("clickMe").addEventListener("click", function () {
    document.getElementById("message").textContent = "Hello, JavaFX!";
});

document.getElementById("changeColor").addEventListener("click", function () {
    document.body.style.backgroundColor = getRandomColor();
});

document.getElementById("submitInput").addEventListener("click", function () {
    const userInput = document.getElementById("userInput").value;
    if (userInput) {
        const li = document.createElement("li");
        li.textContent = userInput;
        document.getElementById("inputList").appendChild(li);
        document.getElementById("userInput").value = ""; // 清空输入框
    }
});

function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
