import { useEffect, useState } from "react";
import Footer from "./components/Footer";
import Navbar from "./components/Navbar";
import NewsForm from "./components/NewsForm";
import NewsList from "./components/NewsList";

// Bootstrap
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/styles.css";

const App = () => {
    const [news, setNews] = useState([]);
    const [categories, setCategories] = useState([]);
    const [authors, setAuthors] = useState([]);
    const [editingNews, setEditingNews] = useState(null);

    const newsUrl = "http://localhost:3000/news";
    const categoryUrl = "http://localhost:3000/categories";
    const authorUrl = "http://localhost:3000/authors";

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [newsRes, categoriesRes, authorsRes] = await Promise.all([
                    fetch(newsUrl),
                    fetch(categoryUrl),
                    fetch(authorUrl),
                ]);
                const newsData = await newsRes.json();
                const categoriesData = await categoriesRes.json();
                const authorsData = await authorsRes.json();

                setNews(newsData.sort((a, b) => new Date(b.postDate) - new Date(a.postDate)));
                setCategories(categoriesData);
                setAuthors(authorsData);
            } catch (error) {
                console.error("Ошибка загрузки данных:", error);
            }
        };
        fetchData();
    }, []);

    const handleSaveNews = async (newsData) => {
        try {
            if (newsData.id) {
                await fetch(`${newsUrl}/${newsData.id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(newsData),
                });
                setNews(news.map((item) => (item.id === newsData.id ? newsData : item)));
            } else {
                newsData.id = crypto.randomUUID();
                newsData.postDate = new Date().toISOString();
                await fetch(newsUrl, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(newsData),
                });
                setNews([newsData, ...news]);
            }
            setEditingNews(null);
        } catch (error) {
            console.error("Ошибка сохранения новости:", error);
        }
    };

    const handleDeleteNews = async (id) => {
        if (confirm("Вы уверены, что хотите удалить эту новость?")) {
            try {
                await fetch(`${newsUrl}/${id}`, {
                    method: "DELETE",
                });
                setNews(news.filter((item) => item.id !== id));
            } catch (error) {
                console.error("Ошибка удаления новости:", error);
            }
        }
    };

    const handleEditNews = (newsItem) => {
        setEditingNews(newsItem);
    };

    return (
        <div className="d-flex flex-column min-vh-100">
            <Navbar />
            <main>
                <div className="container">
                    <section className="my-5">
                        <h2>О Нас</h2>
                        <div className="card">
                            <div className="card-body">
                                <p>
                                    Наш образовательный процесс, основанный на экспериментальных методологиях квантового
                                    либерализма, позволяет студентам углубленно исследовать феномены неопределенности,
                                    устойчиво отрицая каноны общепринятой логики.
                                </p>
                            </div>
                        </div>
                    </section>
                    <h2>Новость</h2>
                    <button className="btn btn-success mb-3" onClick={() => setEditingNews({})}>
                        Добавить новость
                    </button>
                    <section className="my-5">
                        <h2>Новости</h2>
                        {editingNews && (
                            <NewsForm
                                news={editingNews}
                                categories={categories}
                                authors={authors}
                                onSave={handleSaveNews}
                                onCancel={() => setEditingNews(null)}
                            />
                        )}
                        <NewsList news={news} onEdit={handleEditNews} onDelete={handleDeleteNews} />
                    </section>
                </div>
            </main>
            <Footer />
        </div>
    );
};

export default App;
