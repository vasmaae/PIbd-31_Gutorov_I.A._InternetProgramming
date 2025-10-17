export const ContactsPage = () => {
    return (
        <div className="container my-5">
            <section className="my-5">
                <h2 className="mb-4">Контактная информация</h2>
                {/* <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Адрес</h3>
                        <p>Пушкина, 228а (Колотушкина), Урюпинск, Волгоградская обл., 403115</p>
                        <div>
                            <script
                                type="text/javascript"
                                async
                                src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3A960fcd3a3fc74d7e0b9c86a92879e755a7eef14481ccedc066e0c92fbe696abb&amp;width=100%25&amp;height=500&amp;lang=ru_RU&amp;scroll=true"
                            ></script>
                        </div>
                    </div>
                </div> */}
                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Адрес</h3>
                        <i className="bi bi-telephone"></i>{" "}
                        <a href="tel:+14881337228">Пушкина, 228а (Колотушкина), Урюпинск, Волгоградская обл., 403115</a>
                    </div>
                </div>
                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Телефон</h3>
                        <p>
                            <i className="bi bi-telephone"></i> Телефон: <a href="tel:+14881337228">+1 488 133 72 28</a>
                        </p>
                    </div>
                </div>
                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Email</h3>
                        <p>
                            <i className="bi bi-envelope"></i> Email:{" "}
                            <a href="mailto:carpe.diem@memento.mori">carpe.diem@memento.mori</a>
                        </p>
                    </div>
                </div>
                <div className="card mb-4">
                    <div className="card-body">
                        <h3 className="card-title">Время Работы</h3>
                        <p>
                            <i className="bi bi-clock"></i> Время работы: Пн-Пт 9:00-18:00
                        </p>
                    </div>
                </div>
            </section>
        </div>
    );
};
