import React, { useState, useEffect, useRef } from "react";
import { connect } from "react-redux";

import { updateUser } from "../../actions/personActions";

const EditUser = (props) => {
  const [firstName, setFirstName] = useState(props.info.firstName);
  const [lastName, setLastName] = useState(props.info.lastName);
  const [address, setAddress] = useState(props.info.address);
  const [phone, setPhone] = useState(props.info.phone);

  const onSubmitHandler = (event) => {
    event.preventDefault();
    const userId = props.info.userId;
    const role = props.security.user.token.role.toLowerCase();
    props.updateUser({
      role,
      userId,
      firstName,
      lastName,
      address,
      phone,
    });
  };

  return (
    <div className="container search__container">
      <form>
        <h2>Edit User</h2>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            First Name
          </label>
          <input
            type="text"
            className="form-control"
            id="firstName"
            aria-describedby="TitleHelp"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>

        {<div className="text-danger">{props.errors.firstName}</div>}

        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            Last Name
          </label>
          <input
            type="text"
            className="form-control"
            id="lastName"
            aria-describedby="TitleHelp"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>

        {<div className="text-danger">{props.errors.lastName}</div>}
        {<div className="text-danger">{props.errors.address}</div>}
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
            Address
          </label>
          <input
            type="text"
            className="form-control"
            id="address"
            aria-describedby="ISBNHelp"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </div>
        {<div className="text-danger">{props.errors.address}</div>}
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">
            Phone
          </label>
          <input
            type="text"
            className="form-control"
            id="phone"
            aria-describedby="priceHelp"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
          />
        </div>
        {<div className="text-danger">{props.errors.phone}</div>}

        <div className="mb-3">
          <button type="button" className="btn btn-primary" onClick={onSubmitHandler}>
            Edit User
          </button>
        </div>
        <h4 className="text-success">{props.messages.message}</h4>
      </form>
    </div>
  );
};

const mapStateToProps = (state) => {
  const { security } = state;
  const { user } = state;
  const { errors } = state;
  const { messages } = state;

  return { user, errors, messages, security };
};

export default connect(mapStateToProps, { updateUser })(EditUser);
