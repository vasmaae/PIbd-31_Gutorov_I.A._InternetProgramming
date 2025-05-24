import { useState } from "react";
import ApplicationForm from "../components/Application/ApplicationForm";
import useApplications from "../hooks/applications/useApplications";

export const ApplicantsPage = () => {
    const [editingApplication, setEditingApplication] = useState(null);
    const { saveApplication } = useApplications();

    return (
        <div className="container my-5">
            <h2>Абитуриентам</h2>
            <div className="card mb-4">
                <div className="card-body">
                    <h3 className="card-title">Направление</h3>
                    <p className="card-text">Выбор программы бакалавриата или магистратуры.</p>
                </div>
            </div>
            <div className="card mb-4">
                <div className="card-body">
                    <h3 className="card-title">ЕГЭ</h3>
                    <p className="card-text">
                        Нужно сдать экзамен ровно на минимальный порог - 84. Не больше и не меньше.
                    </p>
                </div>
            </div>
            <div className="card mb-4">
                <div className="card-body">
                    <h3 className="card-title">Приёмная комиссия</h3>
                    <p className="card-text">
                        Телефон: <a href="tel:+14881337228">+1 488 133 72 28</a>
                    </p>
                    <p className="card-text">
                        Email: <a href="mailto:carpe.diem@memento.mori">carpe.diem@memento.mori</a>
                    </p>
                </div>
            </div>
            <div className="card mb-3">
                <div className="card-body">
                    <p>
                        Присоединяйтесь к нашему университету! Подайте заявку на обучение, выбрав одну из наших
                        программ: Физика, Математика или Информатика.
                    </p>
                </div>
            </div>
            <button className="btn btn-success mb-3" onClick={() => setEditingApplication({})}>
                Подать заявку
            </button>
            {editingApplication && (
                <ApplicationForm
                    application={editingApplication}
                    onSave={(applicationData) => {
                        saveApplication(applicationData);
                        setEditingApplication(null);
                    }}
                    onCancel={() => setEditingApplication(null)}
                />
            )}
        </div>
    );
};
