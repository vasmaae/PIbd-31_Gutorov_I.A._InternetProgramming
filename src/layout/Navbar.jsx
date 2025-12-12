import "bootstrap/dist/css/bootstrap.min.css";
import {Link, NavLink} from "react-router-dom";
// @ts-ignore
import logo from "../img/logo.png";

export const Navbar = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <div className="container">
                    <Link
                        to="/"
                        className="navbar-brand d-flex align-items-center text-decoration-none text-dark fs-4 fw-bold"
                    >
                        <img src={logo} alt="Логотип УрГТУ" className="me-2" style={{height: "50px"}}/>
                    </Link>
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
                            <NavLink className="nav-link nav-item" to="/">
                                <i className="bi bi-house"></i> Главная
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/faculties">
                                <i className="bi bi-book"></i> Факультеты
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/applicants">
                                <i className="bi bi-person"></i> Абитуриентам
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/applications">
                                <i className="bi bi-person-plus-fill"></i> Абитуриенты
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/research">
                                <i className="bi bi-gear"></i> Наука
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/contacts">
                                <i className="bi bi-envelope"></i> Контакты
                            </NavLink>
                            <li><a className="nav-link nav-item" href="/mvc/authors">
                                <i className="bi bi-person"></i> Авторы</a>
                            </li>
                            <li><a className="nav-link nav-item" href="/mvc/categories">
                                <i className="bi bi-tag"></i> Категории
                            </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    )
        ;
};

export default Navbar;
