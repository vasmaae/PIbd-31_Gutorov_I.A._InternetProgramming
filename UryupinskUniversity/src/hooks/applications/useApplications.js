import { useEffect, useState } from "react";

const applicationsUrl = "http://localhost:8080/api/v1.0/applications";
const programsUrl = "http://localhost:8080/api/v1.0/programs";

export const useApplications = () => {
    const [applications, setApplications] = useState([]);
    const [programs, setPrograms] = useState([]);

    useEffect(() => {
        const fetchApplications = async () => {
            try {
                const [applicationsRes, programsRes] = await Promise.all([fetch(applicationsUrl), fetch(programsUrl)]);
                const applicationsData = await applicationsRes.json();
                const programsData = await programsRes.json();
                setApplications(
                    applicationsData.sort((a, b) => new Date(b.submissionDate) - new Date(a.submissionDate))
                );
                setPrograms(programsData);
            } catch (error) {
                console.error("Ошибка загрузки заявок:", error);
            }
        };
        fetchApplications();
    }, []);

    const fetchApplications = async () => {
        try {
            const [applicationsRes, programsRes] = await Promise.all([fetch(applicationsUrl), fetch(programsUrl)]);
            const applicationsData = await applicationsRes.json();
            const programsData = await programsRes.json();
            setApplications(
                applicationsData.sort((a, b) => new Date(b.submissionDate) - new Date(a.submissionDate))
            );
            setPrograms(programsData);
        } catch (error) {
            console.error("Ошибка загрузки заявок:", error);
        }
    };

    const saveApplication = async (applicationData) => {
        try {
            const method = applicationData.id ? "PUT" : "POST";
            const url = applicationData.id ? `${applicationsUrl}/${applicationData.id}` : applicationsUrl;

            await fetch(url, {
                method: method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(applicationData),
            });
            await fetchApplications();
        } catch (error) {
            console.error("Ошибка сохранения заявки:", error);
        }
    };

    const deleteApplication = async (id) => {
        try {
            await fetch(`${applicationsUrl}/${id}`, {
                method: "DELETE",
            });
            setApplications(applications.filter((item) => item.id !== id));
        } catch (error) {
            console.error("Ошибка удаления заявки:", error);
        }
    };

    return { applications, programs, saveApplication, deleteApplication };
};

export default useApplications;
