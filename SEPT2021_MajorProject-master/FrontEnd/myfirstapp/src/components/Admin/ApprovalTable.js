import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import { getAllApprovals } from "../../actions/personActions";
import ListSinglePublisher from "./ListSinglePublisher";

const ApprovalTable = (props) => {
  useEffect(() => {
    props.getAllApprovals(props.security.user.token.role.toLowerCase());
  }, [props.messages]);

  return (
    <div className="container">
      <h3 className="text-center">Publisher Approvals</h3>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            {/*<th scope="col">Seller</th>*/}
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Address</th>
            <th scope="col">Phone</th>
            <th scope="col">Approve</th>
          </tr>
        </thead>
        <tbody>
          {props.person.approvals.map((user, key) => {
            return <ListSinglePublisher user={user} key={key} />;
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

export default connect(mapStateToProps, { getAllApprovals })(ApprovalTable);
