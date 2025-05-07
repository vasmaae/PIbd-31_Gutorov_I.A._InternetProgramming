document.getElementById("newsForm").addEventListener("submit", (e) => {
    e.preventDefault();
    const title = document.getElementById("newsTitle").value.trim();
    const content = document.getElementById("newsContent").value.trim();
    if (title && content) {
        const newsContainer = document.getElementById("newsContainer");
        const newArticle = document.createElement("div");
        newArticle.className = "card mb-3";
        newArticle.innerHTML = `
            <div class="card-body">
                <h3 class="card-title">${title}</h3>
                <p class="card-text">${content}</p>
            </div>
        `;
        newsContainer.prepend(newArticle);
        document.getElementById("newsTitle").value = "";
        document.getElementById("newsContent").value = "";
    } else {
        alert("Пожалуйста, заполните все поля.");
    }
});
