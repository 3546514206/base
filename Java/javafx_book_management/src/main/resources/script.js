function addBook() {
    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;

    fetch('http://localhost:8080/addBook', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({title, author})
    })
        .then(response => {
            if (response.ok) {
                document.getElementById("title").value = '';
                document.getElementById("author").value = '';
                loadBooks();
            } else {
                alert("添加书籍失败");
            }
        });
}

function loadBooks() {
    fetch('http://localhost:8080/getAllBooks')
        .then(response => response.json())
        .then(books => {
            const bookList = document.getElementById("bookList");
            bookList.innerHTML = '';
            books.forEach(book => {
                const li = document.createElement("li");
                li.textContent = `${book.title} - ${book.author}`;
                bookList.appendChild(li);
            });
        });
}

window.onload = loadBooks;
