import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import { getAllUsers, getAllApprovals } from "../../actions/personActions";
import { AdminUser } from "./AdminUser";
import ListSingleBook from "../Book/ListSingleBook";
import ListSingleUser from "./ListSingleUser";
import EditUser from "./EditUser";
import EditBook from "../Book/EditBook";

export const UserTable = (props) => {
  const [editUser, setEditUser] = useState("");
  const [enableEditUser, setEnableEditUser] = useState(false);

  useEffect(() => {
    if (props.approval) {
      props.getAllApprovals();
    } else {
      props.getAllUsers();
    }
  }, [props.messages]);

  function onEditHandler(user) {
    setEditUser(user);
    setEnableEditUser(!enableEditUser);
  }

  return (
    <div className="container">
      {enableEditUser && <EditUser info={editUser} route="/admin/dashboard" />}
      <h3 className="text-center">All Users</h3>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th scope="col">Username</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Address</th>
            <th scope="col">Phone</th>
            <th scope="col">Edit</th>
            <th scope="col">Block/Unblock</th>
          </tr>
        </thead>
        <tbody>
          {props.person.persons.map((user, key) => {
            return (
              <ListSingleUser
                onEditHandler={onEditHandler}
                enableEditUser={enableEditUser}
                user={user}
                key={key}
              />
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

const mapStateToProps = (state) => {
  const { person } = state;
  const { security } = state;
  const { messages } = state;
  return { person, messages, security };
};

export default connect(mapStateToProps, { getAllUsers })(UserTable);
