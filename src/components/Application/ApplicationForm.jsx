import { useState } from "react";

export const ApplicationForm = ({ application = {}, programs, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        id: application.id || "",
        fullName: application.fullName || "",
        email: application.email || "",
        programId: application.program?.id || "",
        submissionDate: application.submissionDate || new Date().toISOString(),
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <div
            className="modal fade show d-block"
            tabIndex="-1"
            style={{ backgroundColor: "rgba(0,0,0,0.5)" }}
        >
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">
                            {
                                application.id ? "Редактировать заявку" : "Подать заявку"
                            }
                        </h5>
                        <button type="button" className="btn-close" onClick={onCancel}></button>
                    </div>
                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="fullName" className="form-label">
                                    ФИО
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="fullName"
                                    name="fullName"
                                    value={formData.fullName}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="email" className="form-label">
                                    Email
                                </label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="program" className="form-label">
                                    Программа обучения
                                </label>
                                <select
                                    className="form-select"
                                    id="program"
                                    name="programId"
                                    value={formData.programId}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="">Выберите программу</option>
                                    {programs.map((cat) => (
                                        <option key={cat.id} value={cat.id}>
                                            {cat.name}
                                        </option>
                                    ))}
                                </select>
                            </div>
                            <button type="submit" className="btn btn-primary">
                                Сохранить
                            </button>
                            <button type="button" className="btn btn-secondary ms-2" onClick={onCancel}>
                                Отмена
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ApplicationForm;
