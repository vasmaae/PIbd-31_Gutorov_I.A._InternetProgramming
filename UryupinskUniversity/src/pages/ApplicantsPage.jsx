export const ApplicantsPage = () => {
    return (
        <div className="container my-5">
            <section className="my-5">
                <h2>Как к нам попасть</h2>

                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Направление</h3>
                        <p className="card-text">Выбор программы бакалавриата или магистратуры.</p>
                    </div>
                </div>

                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">ЕГЭ</h3>
                        <p className="card-text">
                            Нужно сдать экзамен ровно на минимальный порог - 84. Не больше и не меньше.
                        </p>
                    </div>
                </div>

                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Приёмная комиссия</h3>
                        <p className="card-text">
                            Телефон: <a href="tel:+14881337228">+1 488 133 72 28</a>
                        </p>
                        <p className="card-text">
                            Email: <a href="mailto:carpe.diem@memento.mori">carpe.diem@memento.mori</a>
                        </p>
                    </div>
                </div>
            </section>
        </div>
    );
};
