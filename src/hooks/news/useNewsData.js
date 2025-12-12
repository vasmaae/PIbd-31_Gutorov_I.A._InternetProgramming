import {useCallback, useEffect, useState} from "react";

const newsUrl = "http://localhost:8080/api/v1.0/news";
const categoryUrl = "http://localhost:8080/api/v1.0/categories";
const authorUrl = "http://localhost:8080/api/v1.0/authors";

export const useNewsData = () => {
    const [news, setNews] = useState([]);
    const [categories, setCategories] = useState([]);
    const [authors, setAuthors] = useState([]);
    const [pagination, setPagination] = useState({
        currentPage: 1,
        totalPages: 1,
        totalItems: 0,
        hasNextPage: false,
        hasPreviousPage: false,
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchNews = useCallback(async (page = 1, size = 5) => {
        setLoading(true);
        try {
            const response = await fetch(`${newsUrl}?page=${page}&size=${size}`);
            const data = await response.json();
            setNews(data.items);
            setPagination({
                currentPage: data.currentPage,
                totalPages: data.totalPages,
                totalItems: data.totalItems,
                hasNextPage: data.hasNextPage,
                hasPreviousPage: data.hasPreviousPage,
            });
        } catch (err) {
            setError(err);
            console.error("Ошибка загрузки новостей:", err);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchNews();

        const fetchMeta = async () => {
            try {
                const [categoriesRes, authorsRes] = await Promise.all([
                    fetch(categoryUrl),
                    fetch(authorUrl),
                ]);
                const categoriesData = await categoriesRes.json();
                const authorsData = await authorsRes.json();

                setCategories(categoriesData);
                setAuthors(authorsData);
            } catch (err) {
                setError(err);
                console.error("Ошибка загрузки мета-данных для новостей:", err);
            }
        };

        fetchMeta();
    }, [fetchNews]);


    const saveNews = async (newsData) => {
        try {
            const method = newsData.id ? "PUT" : "POST";
            const url = newsData.id ? `${newsUrl}/${newsData.id}` : newsUrl;

            await fetch(url, {
                method: method,
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(newsData),
            });
            await fetchNews(pagination.currentPage);
        } catch (error) {
            console.error("Ошибка сохранения новости:", error);
        }
    };

    const deleteNews = async (id) => {
        try {
            await fetch(`${newsUrl}/${id}`, {
                method: "DELETE",
            });
            await fetchNews(pagination.currentPage);
        } catch (error) {
            console.error("Ошибка удаления новости:", error);
        }
    };


    return {news, categories, authors, loading, error, pagination, fetchNews, saveNews, deleteNews};
};
