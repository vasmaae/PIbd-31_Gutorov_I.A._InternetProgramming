import { NewsForm } from "../components/News/NewsForm";
import { NewsList } from "../components/News/NewsList";
import { useNewsActions } from "../hooks/news/useNewsActions";
import { useNewsData } from "../hooks/news/useNewsData";

export const NewsPage = () => {
    const { news, categories, authors, setNews } = useNewsData();
    const { editingNews, setEditingNews, handleSaveNews, handleDeleteNews, handleEditNews } = useNewsActions(null);

    return (
        <div className="d-flex flex-column min-vh-100">
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
                                onSave={(newsData) => handleSaveNews(newsData, news, setNews)}
                                onCancel={() => setEditingNews(null)}
                            />
                        )}
                        <NewsList
                            news={news}
                            onEdit={handleEditNews}
                            onDelete={(id) => handleDeleteNews(id, news, setNews)}
                        />
                    </section>
                </div>
            </main>
        </div>
    );
};
