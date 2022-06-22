import React from "react";
import { connect } from "react-redux";
import { approveAccount } from "../../actions/personActions";

export const ListSingleUser = (props) => {
  const approveAccount = async () => {
    const blockPayload = {
      adminRole: props.security.user.token.role.toLowerCase(),
      userId: props.user.userId,
      businessApproval: !props.user.businessApproval,
    };
    props.approveAccount(blockPayload);
  };
  return (
    <>
      <tr>
        <td>{props.user.firstName}</td>
        <td>{props.user.lastName}</td>
        <td>{props.user.address}</td>
        <td>{props.user.phone}</td>

        <td>
          <button className="btn btn-primary" onClick={() => approveAccount()}>
            Approve
          </button>
        </td>
      </tr>
    </>
  );
};

const mapStateToProps = (state) => {
  const { security } = state;
  return { security };
};

export default connect(mapStateToProps, { approveAccount })(ListSingleUser);
