import { useState } from "react";
import ApplicationForm from "../components/Application/ApplicationForm";
import ApplicationList from "../components/Application/ApplicationList";
import useApplications from "../hooks/applications/useApplications";

export const ApplicationsPage = () => {
    const [editingApplication, setEditingApplication] = useState(null);
    const { applications, programs, saveApplication, deleteApplication } = useApplications();

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
            <ApplicationList applications={applications} onEdit={setEditingApplication} onDelete={deleteApplication} />
        </div>
    );
};

export default ApplicationsPage;
