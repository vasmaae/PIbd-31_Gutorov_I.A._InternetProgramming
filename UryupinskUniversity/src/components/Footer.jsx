import "bootstrap/dist/css/bootstrap.min.css";

const Footer = () => {
    return (
        <footer className="bg-primary text-white py-4">
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <h3>Контакты</h3>
                        <p>
                            <i className="bi bi-telephone"></i> Телефон:{" "}
                            <a href="tel:+14881337228" class="text-white">
                                +1 488 133 72 28
                            </a>
                        </p>
                        <p>
                            <i className="bi bi-envelope"></i> Email:{" "}
                            <a href="mailto:carpe.diem@memento.mori" className="text-white">
                                carpe.diem@memento.mori
                            </a>
                        </p>
                        <p>
                            <i className="bi bi-clock"></i> Время работы: Пн-Пт 9:00-18:00
                        </p>
                    </div>
                    <div className="col-md-4">
                        <h3>Адрес</h3>
                        <p>
                            <i className="bi bi-geo-alt"></i> Пушкина, 228а (Колотушкина), Урюпинск, Волгоградская обл.,
                            403115
                        </p>
                        <p>
                            <i className="bi bi-building"></i> Приемная комиссия: корпус 2, кабинет 101
                        </p>
                    </div>
                    <div className="col-md-4">
                        <h3>Социальные сети</h3>
                        <div className="social-icons">
                            <a href="#" className="text-white me-2">
                                <i className="bi bi-facebook"></i>
                            </a>
                            <a href="#" className="text-white me-2">
                                <i className="bi bi-twitter"></i>
                            </a>
                            <a href="#" className="text-white">
                                <i className="bi bi-instagram"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <div className="text-center mt-3">
                    <p>© 2025 Урюпинский Государственный Технологический Университет (УрГТУ)</p>
                </div>
            </div>
        </footer>
    );
};

export default Footer;
