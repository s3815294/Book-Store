import React from "react";
import { connect } from "react-redux";
import { blockAccount } from "../../actions/personActions";

export const ListSingleUser = (props) => {
  const blockAccount = async () => {
    const blockPayload = {
      adminRole: props.security.user.token.role.toLowerCase(),
      userId: props.user.userId,
      blockAccount: !props.user.blockAccount,
    };
    props.blockAccount(blockPayload);
  };
  return (
    <>
      <tr>
        <td>{props.user.username}</td>
        <td>{props.user.firstName}</td>
        <td>{props.user.lastName}</td>
        <td>{props.user.address}</td>
        <td>{props.user.phone}</td>

        <td>
          <button
            type="button"
            className="btn btn-primary btn-xs"
            onClick={() => props.onEditHandler(props.user)}
          >
            {props.enableEditUser ? "Cancel" : "Edit"}
          </button>
        </td>
        <td>
          <button className="btn btn-primary" onClick={() => blockAccount()}>
            {props.user.blockAccount ? "Unblock" : "Block"}
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

export default connect(mapStateToProps, { blockAccount })(ListSingleUser);
