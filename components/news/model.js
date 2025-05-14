export default class NewsModel {
    constructor() {
        this.apiUrl = "http://localhost:3000/news";
        this.categoryUrl = "http://localhost:3000/categories";
        this.authorUrl = "http://localhost:3000/authors";
        this.categories = null;
        this.authors = null;
    }

    async getAllNews() {
        const response = await fetch(`${this.apiUrl}`);
        const news = await response.json();
        const sortedNews = news.sort((a) => a.postDate);
        return sortedNews;
    }

    async getCategories() {
        if (!this.categories) {
            const response = await fetch(this.categoryUrl);
            this.categories = await response.json();
        }
        return this.categories;
    }

    async getAuthors() {
        if (!this.authors) {
            const response = await fetch(this.authorUrl);
            this.authors = await response.json();
        }
        return this.authors;
    }

    async createNews(news) {
        news.id = crypto.randomUUID();
        const response = await fetch(this.apiUrl, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(news),
        });
        return await response.json();
    }

    async updateNews(id, news) {
        const response = await fetch(`${this.apiUrl}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(news),
        });
        return await response.json();
    }

    async deleteNews(id) {
        console.log(`Пытаемся удалить новость с ID: ${id}`);
        try {
            const response = await fetch(`${this.apiUrl}/${id}`, {
                method: "DELETE",
            });

            if (!response.ok) {
                throw new Error(`Ошибка HTTP: ${response.status}`);
            }

            console.log(`Новость с ID ${id} успешно удалена`);
            return true;
        } catch (error) {
            console.error("Ошибка при удалении новости:", error);
            return false;
        }
    }

    async getNewsById(id) {
        const response = await fetch(`${this.apiUrl}/${id}`);
        return await response.json();
    }
}
