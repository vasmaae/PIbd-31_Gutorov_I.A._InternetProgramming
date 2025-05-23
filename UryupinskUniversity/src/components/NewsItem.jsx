const NewsItem = ({ news, onEdit, onDelete }) => {
    return (
        <div className="card mb-3 news-card">
            <div className="card-body">
                <h3 className="card-title news-title">{news.title}</h3>
                <small className="text-muted">{news.postDate}</small>
                <p className="card-text">{news.content}</p>
                <button className="btn btn-warning me-2" onClick={() => onEdit(news)}>
                    Редактировать
                </button>
                <button className="btn btn-danger" onClick={() => onDelete(news.id)}>
                    Удалить
                </button>
            </div>
        </div>
    );
};

export default NewsItem;
