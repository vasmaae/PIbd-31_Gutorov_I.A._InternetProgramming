import { useState } from "react";
import ApplicationForm from "../components/Application/ApplicationForm";
import ApplicationList from "../components/Application/ApplicationList";
import { useApplications } from "../hooks/applications/useApplications";

export const ApplicationsPage = () => {
    const [editingApplication, setEditingApplication] = useState(null);
    const { applications, programs, saveApplication, deleteApplication } = useApplications();
    const [filterProgramId, setFilterProgramId] = useState("");
    const [filterAdmitted, setFilterAdmitted] = useState(false);

    const handleApprove = async (application) => {
        const updatedApplication = {
            id: application.id,
            fullName: application.fullName,
            email: application.email,
            programId: application.program?.id,
            isAdmitted: true,
            submissionDate: application.submissionDate
        };

        try {
            await saveApplication(updatedApplication);
        } catch (error) {
            console.error("Ошибка при одобрении заявки", error);
        }
    };

    const filteredApplications = applications?.filter((item) => {
        const matchesProgram = filterProgramId ? item.program?.id === filterProgramId : true;
        const matchesAdmitted = filterAdmitted ? item.isAdmitted === true : true;
        return matchesProgram && matchesAdmitted;
    });

    return (
        <div className="container my-5">
            <h2>Список заявок</h2>
            <button className="btn btn-success mb-3" onClick={() => setEditingApplication({})}>
                Добавить заявку
            </button>
            <div className="d-flex gap-2 mb-3">
                <select
                    className="form-select"
                    id="applicationsProgram"
                    name="programId"
                    required
                    value={filterProgramId}
                    onChange={(e) => setFilterProgramId(e.target.value)}
                >
                    <option value="">Без фильтра</option>
                    {programs.map((category) => (
                        <option key={category.id} value={category.id}>
                            {category.name}
                        </option>
                    ))}
                </select>
            </div>
            <div className="d-flex flex-wrap gap-3 mb-3">
                <div className="form-check mt-4">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="filterAdmittedCheckbox"
                        checked={filterAdmitted}
                        onChange={(e) => setFilterAdmitted(e.target.checked)}
                    />
                    <label className="form-check-label" htmlFor="filterAdmittedCheckbox">
                        Только одобренные
                    </label>
                </div>
            </div>
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
            <ApplicationList
                applications={filteredApplications}
                programs={programs}
                onEdit={setEditingApplication}
                onDelete={deleteApplication}
                onApprove={handleApprove}
            />
        </div>
    );
};

export default ApplicationsPage;
