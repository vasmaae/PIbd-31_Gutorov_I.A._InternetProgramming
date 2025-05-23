import { useEffect, useState } from "react";

const newsUrl = "http://localhost:3000/news";
const categoryUrl = "http://localhost:3000/categories";
const authorUrl = "http://localhost:3000/authors";

export const useNewsData = () => {
    const [news, setNews] = useState([]);
    const [categories, setCategories] = useState([]);
    const [authors, setAuthors] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const [newsRes, categoriesRes, authorsRes] = await Promise.all([
                    fetch(newsUrl),
                    fetch(categoryUrl),
                    fetch(authorUrl),
                ]);
                const newsData = await newsRes.json();
                const categoriesData = await categoriesRes.json();
                const authorsData = await authorsRes.json();

                // @ts-ignore
                setNews(newsData.sort((a, b) => new Date(b.postDate) - new Date(a.postDate)));
                setCategories(categoriesData);
                setAuthors(authorsData);
            } catch (err) {
                setError(err);
                console.error("Ошибка загрузки данных:", err);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, []);

    return { news, categories, authors, loading, error, setNews };
};
