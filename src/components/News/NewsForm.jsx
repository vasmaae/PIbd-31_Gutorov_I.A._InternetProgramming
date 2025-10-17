import { useState } from "react";

export const NewsForm = ({ news, categories, authors, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        id: news.id || "",
        title: news.title || "",
        content: news.content || "",
        categoryId: news.categoryId || news.category?.id || "",
        authorId: news.authorId || news.author?.id || "",
        postDate: new Date().toISOString(),
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <div className="modal fade show d-block" tabIndex="-1" style={{ backgroundColor: "rgba(0,0,0,0.5)" }}>
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">{news.id ? "Редактировать новость" : "Добавить новость"}</h5>
                        <button type="button" className="btn-close" onClick={onCancel}></button>
                    </div>
                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="newsTitle" className="form-label">
                                    Заголовок
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="newsTitle"
                                    name="title"
                                    value={formData.title}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="newsContent" className="form-label">
                                    Содержание
                                </label>
                                <textarea
                                    className="form-control"
                                    id="newsContent"
                                    name="content"
                                    rows="3"
                                    value={formData.content}
                                    onChange={handleChange}
                                    required
                                ></textarea>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="newsCategory" className="form-label">
                                    Категория
                                </label>
                                <select
                                    className="form-select"
                                    id="newsCategory"
                                    name="categoryId"
                                    value={formData.categoryId}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="">Выберите категорию</option>
                                    {categories.map((cat) => (
                                        <option key={cat.id} value={cat.id}>
                                            {cat.name}
                                        </option>
                                    ))}
                                </select>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="newsAuthor" className="form-label">
                                    Автор
                                </label>
                                <select
                                    className="form-select"
                                    id="newsAuthor"
                                    name="authorId"
                                    value={formData.authorId}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="">Выберите автора</option>
                                    {authors.map((author) => (
                                        <option key={author.id} value={author.id}>
                                            {author.name}
                                        </option>
                                    ))}
                                </select>
                            </div>
                            <button type="submit" className="btn btn-primary">
                                Сохранить
                            </button>
                            <button type="button" className="btn btn-secondary ms-2" onClick={onCancel}>
                                Отмена
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NewsForm;
