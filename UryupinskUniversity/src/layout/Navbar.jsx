import "bootstrap/dist/css/bootstrap.min.css";
import { Link, NavLink } from "react-router-dom";
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
                        <img src={logo} alt="Логотип УрГТУ" className="me-2" style={{ height: "50px" }} />
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
                                <a className="nav-link">
                                    <i className="bi bi-house"></i> Главная
                                </a>
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/faculties">
                                <a className="nav-link">
                                    <i className="bi bi-book"></i> Факультеты
                                </a>
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/applicants">
                                <a className="nav-link">
                                    <i className="bi bi-person"></i> Абитуриентам
                                </a>
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/research">
                                <a className="nav-link">
                                    <i className="bi bi-gear"></i> Наука
                                </a>
                            </NavLink>
                            <NavLink className="nav-link nav-item" to="/contacts">
                                <a className="nav-link">
                                    <i className="bi bi-envelope"></i> Контакты
                                </a>
                            </NavLink>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Navbar;
