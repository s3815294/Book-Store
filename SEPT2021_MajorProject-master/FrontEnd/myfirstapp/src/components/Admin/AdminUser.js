import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import AddUser from "./AddUser";
import ApprovalTable from "./ApprovalTable";
import UserTable from "./UserTable";

export const AdminUser = (props) => {
  const [addUser, setAddUser] = useState(false);
  const [approval, setApproval] = useState(false);

  useEffect(() => {}, [approval]);
  return (
    <div className="container ">
      <h1>User Dashboard</h1>
      <button className="btn btn-primary mt-3" onClick={() => setAddUser(!addUser)}>
        {addUser ? "Hide User" : "Add User"}
      </button>
      <button className="btn btn-primary mt-3" onClick={() => setApproval(!approval)}>
        {approval ? "Hide User Approvals" : "Show User Aprovals"}
      </button>
      <div className="mt-5">{addUser && <AddUser />}</div>
      {approval ? <ApprovalTable /> : <UserTable />}
    </div>
  );
};

const mapStateToProps = (state) => {
  return {};
};

export default connect(mapStateToProps)(AdminUser);
