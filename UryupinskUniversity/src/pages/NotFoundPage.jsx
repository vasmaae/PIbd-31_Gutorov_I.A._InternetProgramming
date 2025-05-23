import { Link } from "react-router-dom";

export const NotFoundPage = () => {
    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-6 text-center">
                    <Link className="nav-link" to="/">
                        <div className="mt-auto py-4">
                            <h1 className="mb-4">Страница не найдена</h1>
                            <p className="mb-0">Вернуться на главную</p>
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    );
};
