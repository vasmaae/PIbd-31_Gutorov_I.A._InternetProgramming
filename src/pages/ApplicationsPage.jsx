import { useState } from "react";
import ApplicationForm from "../components/Application/ApplicationForm";
import ApplicationList from "../components/Application/ApplicationList";
import { useApplications } from "../hooks/applications/useApplications";

export const ApplicationsPage = () => {
    const [editingApplication, setEditingApplication] = useState(null);
    const { applications, programs, saveApplication, deleteApplication, pagination, fetchApplications, loading } = useApplications();

    const handleApprove = async (application) => {
        const updatedApplication = {
            id: application.id,
            fullName: application.fullName,
            email: application.email,
            programId: application.program?.id,
            admitted: true,
            submissionDate: application.submissionDate
        };

        try {
            await saveApplication(updatedApplication);
        } catch (error) {
            console.error("Ошибка при одобрении заявки", error);
        }
    };

    const handlePageChange = (newPage) => {
        fetchApplications(newPage);
    };

    return (
        <div className="container my-5">
            <h2>Список заявок</h2>
            <button className="btn btn-success mb-3" onClick={() => setEditingApplication({})}>
                Добавить заявку
            </button>

            {editingApplication && (
                <ApplicationForm
                    application={editingApplication}
                    programs={programs}
                    onSave={(applicationData) => {
                        saveApplication(applicationData);
                        setEditingApplication(null);
                    }}
                    onCancel={() => setEditingApplication(null)}
                />
            )}

            {loading ? (
                <p>Загрузка...</p>
            ) : (
                <ApplicationList
                    applications={applications}
                    programs={programs}
                    onEdit={setEditingApplication}
                    onDelete={deleteApplication}
                    onApprove={handleApprove}
                />
            )}

            <div className="d-flex justify-content-center align-items-center mt-4">
                <button
                    className="btn btn-secondary"
                    onClick={() => handlePageChange(pagination.currentPage - 1)}
                    disabled={!pagination.hasPreviousPage}
                >
                    Предыдущая
                </button>
                <span className="mx-3">
                    Страница {pagination.currentPage} из {pagination.totalPages}
                </span>
                <button
                    className="btn btn-secondary"
                    onClick={() => handlePageChange(pagination.currentPage + 1)}
                    disabled={!pagination.hasNextPage}
                >
                    Следующая
                </button>
            </div>
        </div>
    );
};

export default ApplicationsPage;
