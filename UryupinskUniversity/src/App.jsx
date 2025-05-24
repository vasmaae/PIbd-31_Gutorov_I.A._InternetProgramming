import { BrowserRouter, Route, Routes } from "react-router-dom";
import { MainLayout } from "./layout/MainLayout";
import { ApplicantsPage } from "./pages/ApplicantsPage";
import { ApplicationsPage } from "./pages/ApplicationsPage";
import { ContactsPage } from "./pages/ContactsPage";
import { FacultiesPage } from "./pages/FacultiesPage";
import { NewsPage } from "./pages/NewsPage";
import { NotFoundPage } from "./pages/NotFoundPage";
import { ResearchPage } from "./pages/ResearchPage";

export const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route element={<MainLayout />}>
                    <Route index element={<NewsPage />} />
                    <Route path="/contacts" element={<ContactsPage />} />
                    <Route path="/applicants" element={<ApplicantsPage />} />
                    <Route path="/applications" element={<ApplicationsPage />} />
                    <Route path="/faculties" element={<FacultiesPage />} />
                    <Route path="/research" element={<ResearchPage />} />
                    <Route path="*" element={<NotFoundPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
};
