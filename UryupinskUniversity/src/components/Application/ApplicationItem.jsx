const ApplicationItem = ({ application, onEdit, onDelete, onApprove }) => {
    return (
        <div className="card mb-3">
            <div className="card-body">
                <h5 className="card-title">{application.fullName}</h5>
                <p className="card-text">Email: {application.email}</p>
                <p className="card-text">Программа: {application.program?.name}</p>
                <p className="card-text">
                    Дата подачи:{" "}
                    {new Date(application.submissionDate).toLocaleDateString("ru-RU", {
                        day: "numeric",
                        month: "long",
                        year: "numeric",
                        hour: "numeric",
                        minute: "numeric",
                        second: "numeric",
                    })}
                </p>
                <div className="gap-2 card-text">
                    {!application.isAdmitted && (
                        <button
                            className="btn btn-success me-2"
                            onClick={() => {
                                if (confirm("Одобрить эту заявку?")) {
                                    onApprove(application);
                                }
                            }}
                        >
                            Одобрить
                        </button>
                    )}
                    {application.isAdmitted && <span className="text-success fw-bold me-2">✅ Заявка одобрена</span>}
                    <button className="btn btn-primary me-2" onClick={() => onEdit(application)}>
                        Редактировать
                    </button>
                    <button
                        className="btn btn-danger"
                        onClick={() => {
                            if (confirm("Вы уверены, что хотите удалить эту заявку?")) {
                                onDelete(application.id);
                            }
                        }}
                    >
                        Удалить
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ApplicationItem;
