import { useState } from "react";

const newsUrl = "http://localhost:8080/api/v1.0/news";

export const useNewsActions = (initialNews = null) => {
    const [editingNews, setEditingNews] = useState(initialNews);

    const handleSaveNews = async (newsData, currentNews, setNews) => {
        try {
            if (newsData.id) {
                await fetch(`${newsUrl}/${newsData.id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(newsData),
                });
                setNews(
                    currentNews
                        .map((item) => (item.id === newsData.id ? newsData : item))
                        // @ts-ignore
                        .sort((a, b) => new Date(b.postDate) - new Date(a.postDate))
                );
            } else {
                newsData.id = crypto.randomUUID();
                await fetch(newsUrl, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(newsData),
                });
                // @ts-ignore
                setNews([newsData, ...currentNews].sort((a, b) => new Date(b.postDate) - new Date(a.postDate)));
            }
            setEditingNews(null);
        } catch (error) {
            console.error("Ошибка сохранения новости:", error);
        }
    };

    const handleDeleteNews = async (id, currentNews, setNews) => {
        if (confirm("Вы уверены, что хотите удалить эту новость?")) {
            try {
                await fetch(`${newsUrl}/${id}`, {
                    method: "DELETE",
                });
                setNews(currentNews.filter((item) => item.id !== id));
            } catch (error) {
                console.error("Ошибка удаления новости:", error);
            }
        }
    };

    const handleEditNews = (newsItem) => {
        setEditingNews(newsItem);
    };

    return {
        editingNews,
        setEditingNews,
        handleSaveNews,
        handleDeleteNews,
        handleEditNews,
    };
};
