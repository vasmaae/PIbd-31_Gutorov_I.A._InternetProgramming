import ApplicationItem from "./ApplicationItem";

const ApplicationList = ({ applications, programs, onEdit, onDelete, onApprove }) => {
    return (
        <div>
            {applications.map((application) => (
                <ApplicationItem
                    key={application.id}
                    programName={programs.find((p) => p.id === application.programId)?.name}
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
