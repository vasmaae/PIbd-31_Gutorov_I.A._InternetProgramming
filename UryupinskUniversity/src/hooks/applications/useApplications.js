import { useEffect, useState } from "react";

const applicationsUrl = "http://localhost:3000/applications";
const programsUrl = "http://localhost:3000/programs";

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
                    // @ts-ignore
                    applicationsData.sort((a, b) => new Date(b.submissionDate) - new Date(a.submissionDate))
                );
                setPrograms(programsData);
            } catch (error) {
                console.error("Ошибка загрузки заявок:", error);
            }
        };
        fetchApplications();
    }, []);

    const saveApplication = async (applicationData) => {
        try {
            if (applicationData.id) {
                await fetch(`${applicationsUrl}/${applicationData.id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(applicationData),
                });
                setApplications(
                    applications
                        .map((item) => (item.id === applicationData.id ? applicationData : item))
                        // @ts-ignore
                        .sort((a, b) => new Date(b.submissionDate) - new Date(a.submissionDate))
                );
            } else {
                applicationData.id = crypto.randomUUID();
                applicationData.submissionDate = new Date().toISOString();
                await fetch(applicationsUrl, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(applicationData),
                });
                setApplications(
                    [applicationData, ...applications].sort(
                        // @ts-ignore
                        (a, b) => new Date(b.submissionDate) - new Date(a.submissionDate)
                    )
                );
            }
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
