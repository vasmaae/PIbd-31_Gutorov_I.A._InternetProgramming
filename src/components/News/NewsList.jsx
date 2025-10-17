import NewsItem from "./NewsItem";

export const NewsList = ({ news, onEdit, onDelete }) => {
    return (
        <div>
            {news.map((newsItem) => (
                <NewsItem key={newsItem.id} news={newsItem} onEdit={onEdit} onDelete={onDelete} />
            ))}
        </div>
    );
};

export default NewsList;
