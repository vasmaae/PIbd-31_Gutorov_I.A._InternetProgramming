import { useState } from "react";
import { NewsForm } from "../components/News/NewsForm";
import { NewsList } from "../components/News/NewsList";
import { useNewsData } from "../hooks/news/useNewsData";

export const NewsPage = () => {
    const { news, categories, authors, pagination, fetchNews, loading, saveNews, deleteNews, pageSize, setPageSize } = useNewsData();
    const [editingNews, setEditingNews] = useState(null);

    const handleSave = (newsData) => {
        saveNews(newsData);
        setEditingNews(null);
    };

    const handleDelete = (id) => {
        if (confirm("Вы уверены, что хотите удалить эту новость?")) {
            deleteNews(id);
        }
    };

    const handlePageChange = (newPage) => {
        fetchNews(newPage);
    };

    const handlePageSizeChange = (e) => {
        setPageSize(parseInt(e.target.value, 10));
    };


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
                                onSave={handleSave}
                                onCancel={() => setEditingNews(null)}
                            />
                        )}
                        {loading ? (
                            <p>Загрузка...</p>
                        ) : (
                            <NewsList
                                news={news}
                                onEdit={setEditingNews}
                                onDelete={handleDelete}
                            />
                        )}
                    </section>

                    <div className="d-flex justify-content-center align-items-center mt-4">
                        <button
                            className="btn btn-secondary"
                            onClick={() => handlePageChange(pagination.currentPage - 1)}
                            disabled={!pagination.hasPreviousPage}
                        >
                            Предыдущая
                        </button>
                        <span className="mx-3">
                            Страница {pagination.currentPage} из {pagination.totalPages}
                        </span>
                        <button
                            className="btn btn-secondary"
                            onClick={() => handlePageChange(pagination.currentPage + 1)}
                            disabled={!pagination.hasNextPage}
                        >
                            Следующая
                        </button>
                        <div className="ms-4">
                            <label htmlFor="pageSizeSelect" className="form-label me-2">
                                Новостей на странице:
                            </label>
                            <select
                                id="pageSizeSelect"
                                className="form-select w-auto d-inline-block"
                                value={pageSize}
                                onChange={handlePageSizeChange}
                            >
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                            </select>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
};
