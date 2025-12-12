import { useCallback, useEffect, useState } from "react";

const applicationsUrl = "http://localhost:8080/api/v1.0/applications";
const programsUrl = "http://localhost:8080/api/v1.0/programs";

export const useApplications = () => {
    const [applications, setApplications] = useState([]);
    const [programs, setPrograms] = useState([]);
    const [pagination, setPagination] = useState({
        currentPage: 1,
        totalPages: 1,
        totalItems: 0,
        hasNextPage: false,
        hasPreviousPage: false,
    });
    const [loading, setLoading] = useState(false);

    const fetchApplications = useCallback(async (page = 1, size = 5) => {
        setLoading(true);
        try {
            const response = await fetch(`${applicationsUrl}?page=${page}&size=${size}`);
            const data = await response.json();
            setApplications(data.items);
            setPagination({
                currentPage: data.currentPage,
                totalPages: data.totalPages,
                totalItems: data.totalItems,
                hasNextPage: data.hasNextPage,
                hasPreviousPage: data.hasPreviousPage,
            });
        } catch (error) {
            console.error("Ошибка загрузки заявок:", error);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchApplications();
        const fetchPrograms = async () => {
            try {
                const programsRes = await fetch(programsUrl);
                const programsData = await programsRes.json();
                setPrograms(programsData);
            } catch (error)
{
                console.error("Ошибка загрузки программ:", error);
            }
        };
        fetchPrograms();
    }, [fetchApplications]);

    const saveApplication = async (applicationData) => {
        try {
            const method = applicationData.id ? "PUT" : "POST";
            const url = applicationData.id ? `${applicationsUrl}/${applicationData.id}` : applicationsUrl;

            await fetch(url, {
                method: method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(applicationData),
            });
            await fetchApplications(pagination.currentPage);
        } catch (error) {
            console.error("Ошибка сохранения заявки:", error);
        }
    };

    const deleteApplication = async (id) => {
        try {
            await fetch(`${applicationsUrl}/${id}`, {
                method: "DELETE",
            });
            await fetchApplications(pagination.currentPage);
        } catch (error) {
            console.error("Ошибка удаления заявки:", error);
        }
    };

    return { applications, programs, saveApplication, deleteApplication, pagination, fetchApplications, loading };
};

export default useApplications;
