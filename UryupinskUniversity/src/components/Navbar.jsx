const Navbar = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <div className="container">
                    <a className="navbar-brand" href="#">
                        <img src="/img/logo.png" alt="Логотип УрГТУ" height="50" />
                    </a>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNav"
                        aria-controls="navbarNav"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav ms-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="index">
                                    <i className="bi bi-house"></i> Главная
                                </a>
                            </li>
                            <li className="nav-item dropdown">
                                <a
                                    className="nav-link dropdown-toggle"
                                    href="#"
                                    id="facultiesDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                >
                                    <i className="bi bi-book"></i> Факультеты
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="facultiesDropdown">
                                    <li>
                                        <a className="dropdown-item" href="faculties">
                                            Факультет Параллельных Парадоксов
                                        </a>
                                    </li>
                                    <li>
                                        <a className="dropdown-item" href="faculties">
                                            Факультет Трансцендентных Технологий
                                        </a>
                                    </li>
                                    <li>
                                        <a className="dropdown-item" href="faculties">
                                            Факультет Анти-Эффективной Инженерии
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="applicants">
                                    <i className="bi bi-person"></i> Абитуриентам
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="research">
                                    <i className="bi bi-gear"></i> Наука
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="contacts">
                                    <i className="bi bi-envelope"></i> Контакты
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Navbar;
