export default class NewsController {
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.init();
    }

    async init() {
        try {
            await this.view.initDropdowns(this.model);
            await this.loadAndRenderNews();
            this.setupEventListeners();
        } catch (error) {
            console.error("Ошибка инициализации:", error);
        }
    }

    async loadAndRenderNews() {
        try {
            const newsList = await this.model.getAllNews();
            this.view.renderNews(newsList);
        } catch (error) {
            console.error("Ошибка загрузки новостей:", error);
        }
    }

    setupEventListeners() {
        const addBtn = document.getElementById("addNewsBtn");
        if (addBtn) {
            addBtn.addEventListener("click", () => {
                this.view.openModal();
            });
        } else {
            console.error('Кнопка "Добавить новость" не найдена');
        }

        const newsForm = document.getElementById("newsForm");
        if (newsForm) {
            newsForm.addEventListener("submit", async (e) => {
                e.preventDefault();
                try {
                    const news = {
                        id: document.getElementById("newsId").value,
                        title: document.getElementById("newsTitle").value,
                        content: document.getElementById("newsContent").value,
                        categoryId: document.getElementById("newsCategory").value,
                        authorId: document.getElementById("newsAuthor").value,
                        postDate: new Date().toISOString(),
                    };

                    if (news.id) {
                        await this.model.updateNews(news.id, news);
                    } else {
                        await this.model.createNews(news);
                    }

                    this.view.modal.hide();
                    await this.loadAndRenderNews();
                } catch (error) {
                    console.error("Ошибка сохранения новости:", error);
                }
            });
        } else {
            console.error("Форма новости не найдена");
        }

        if (this.view.container) {
            this.view.container.addEventListener("click", async (e) => {
                try {
                    if (e.target.classList.contains("edit-btn")) {
                        const { id } = e.target.dataset;
                        const news = await this.model.getNewsById(id);
                        this.view.openModal(news);
                    }

                    if (e.target.classList.contains("delete-btn")) {
                        const { id } = e.target.dataset;
                        console.log(`Нажата кнопка удаления для ID: ${id}`); // Отладочное сообщение

                        if (confirm("Вы уверены, что хотите удалить эту новость?")) {
                            const success = await this.model.deleteNews(id);

                            if (success) {
                                console.log(`Новость с ID ${id} удалена, обновляем список`);
                                await this.loadAndRenderNews();
                            } else {
                                alert("Не удалось удалить новость");
                            }
                        }
                    }
                } catch (error) {
                    console.error("Ошибка обработки действия:", error);
                }
            });
        } else {
            console.error("Контейнер новостей не найден");
        }
    }
}
