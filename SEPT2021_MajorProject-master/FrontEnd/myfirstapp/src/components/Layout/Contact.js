import React, { useState } from "react";

import { connect } from "react-redux";

const Contact = (props) => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [phone, setPhone] = useState("");
  const [username, setUsername] = useState("");

  return (
    <div className="register">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center mb-4">Contact Us</h1>
            <form>
              <label className="form-label mb-4">Personal Details</label>
              <div className="form-group register__text">
                <input
                  type="text"
                  className={`${
                    props.errors.firstName && "is-invalid"
                  } form-control form-control-lg me-4`}
                  placeholder="First Name"
                  name="firstname"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                />
                <input
                  type="text"
                  className={`${
                    props.errors.lastName && "is-invalid"
                  } form-control form-control-lg`}
                  placeholder="Last Name"
                  name="lastname"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                />
              </div>
              {<div className="text-danger">{props.errors.firstName}</div>}
              {<div className="text-danger">{props.errors.lastName}</div>}

              {<div className="text-danger">{props.errors.country}</div>}
              {<div className="text-danger">{props.errors.postcode}</div>}
              <div className="form-group">
                <input
                  type="text"
                  className={`${props.errors.phone && "is-invalid"} form-control form-control-lg`}
                  placeholder="Phone"
                  name="phone"
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                  required
                />
                {<div className="text-danger">{props.errors.phone}</div>}
              </div>
              <div className="form-group">
                <input
                  className={`${
                    props.errors.username && "is-invalid"
                  } form-control form-control-lg`}
                  type="email"
                  className="form-control form-control-lg"
                  placeholder="Email Address"
                  name="email"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
                {<div className="text-danger">{props.errors.username}</div>}
              </div>
              <label className="form-label mb-4">Write your query</label>
              <div className="form-group">
                <input
                  className={`${
                    props.errors.username && "is-invalid"
                  } form-control form-control-lg`}
                  type="plain"
                  className="form-control form-control-lg"
                  placeholder="Write Something"
                  name="plain"
                  required
                />
              </div>

              <button className="btn btn-info btn-block mt-4">Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (state) => {
  const { errors } = state;
  return { errors };
};

export default connect(mapStateToProps, {})(Contact);
