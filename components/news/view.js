export default class NewsView {
    constructor() {
        this.container = document.getElementById("newsContainer");
        this.modalElement = document.getElementById("newsModal");

        if (!this.modalElement) {
            console.error("Модальное окно не найдено");
            return;
        }

        this.modal = new bootstrap.Modal(this.modalElement);
    }

    renderNews(newsList) {
        if (!this.container) {
            console.error("Контейнер новостей не найден");
            return;
        }

        this.container.innerHTML = "";
        const sortedNews = [...newsList].sort((a, b) => new Date(b.postDate) - new Date(a.postDate));

        sortedNews.forEach((news) => {
            const card = document.createElement("div");
            card.className = "card mb-3";
            card.innerHTML = `
                <div class="card-body">
                    <h3 class="card-title">${news.title}</h3>
                    <small class="text-muted">${news.postDate}</small>
                    <p class="card-text">${news.content}</p>
                    <button class="btn btn-warning edit-btn" data-id="${news.id}">Редактировать</button>
                    <button class="btn btn-danger delete-btn" data-id="${news.id}">Удалить</button>
                </div>
            `;
            this.container.appendChild(card);
        });
    }

    async initDropdowns(model) {
        try {
            const [categories, authors] = await Promise.all([model.getCategories(), model.getAuthors()]);

            const categorySelect = document.getElementById("newsCategory");
            if (categorySelect) {
                categorySelect.innerHTML = categories
                    .map((cat) => `<option value="${cat.id}">${cat.name}</option>`)
                    .join("");
            }

            const authorSelect = document.getElementById("newsAuthor");
            if (authorSelect) {
                authorSelect.innerHTML = authors
                    .map((author) => `<option value="${author.id}">${author.name}</option>`)
                    .join("");
            }
        } catch (error) {
            console.error("Ошибка загрузки dropdowns:", error);
        }
    }

    openModal(news = {}) {
        const form = document.getElementById("newsForm");
        form.reset();

        document.getElementById("newsId").value = news.id || "";
        document.getElementById("newsTitle").value = news.title || "";
        document.getElementById("newsContent").value = news.content || "";

        if (news.categoryId) {
            document.getElementById("newsCategory").value = news.categoryId;
        }
        if (news.authorId) {
            document.getElementById("newsAuthor").value = news.authorId;
        }

        this.modal.show();
    }
}
