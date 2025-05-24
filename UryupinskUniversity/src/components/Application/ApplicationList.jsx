import ApplicationItem from "./ApplicationItem";

const ApplicationList = ({ applications, onEdit, onDelete }) => {
    return (
        <div>
            {applications.map((application) => (
                <ApplicationItem key={application.id} application={application} onEdit={onEdit} onDelete={onDelete} />
            ))}
        </div>
    );
};

export default ApplicationList;
