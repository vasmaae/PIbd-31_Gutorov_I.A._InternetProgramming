import ApplicationItem from "./ApplicationItem";

const ApplicationList = ({ applications, programs, onEdit, onDelete, onApprove }) => {
    return (
        <div>
            {applications.map((application) => (
                <ApplicationItem
                    key={application.id}
                    application={application}
                    onEdit={onEdit}
                    onDelete={onDelete}
                    onApprove={onApprove}
                />
            ))}
        </div>
    );
};

export default ApplicationList;
