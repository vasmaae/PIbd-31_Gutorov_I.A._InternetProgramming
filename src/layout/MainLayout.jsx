import { Outlet } from "react-router-dom";
import { Footer } from "./Footer";
import { Navbar } from "./Navbar";

export const MainLayout = () => {
    return (
        <div className="d-flex flex-column min-vh-100">
            <Navbar />
            <main>
                <div className="container">
                    <Outlet />
                </div>
            </main>
            <Footer />
        </div>
    );
};
