import React, { useState } from "react";
import { createNewUser } from "../../actions/securityActions";
import { connect } from "react-redux";

export const AddUser = (props) => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [address, setAddress] = useState("");
  const [postcode, setPostcode] = useState("");
  const [country, setCountry] = useState("");
  const [phone, setPhone] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfPassword] = useState("");
  const [userRole, setUserRole] = useState("customer");
  const [abn, setAbn] = useState("");
  const [businessApproval, setbusinessApproval] = useState(false);

  const onSubmitHandler = (e) => {
    const adminRole = props.security.user.token.role.toLowerCase();
    e.preventDefault();
    props.createNewUser({
      firstName,
      lastName,
      address,
      country,
      phone,
      postcode,
      password,
      username,
      confirmPassword,
      userRole,
      abn,
      businessApproval,
      adminRole,
    });
  };

  const onChangeHandler = (event) => {
    setUserRole(event.target.value);
  };

  return (
    <div className="register">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center mb-4">Add New User</h1>
            <form>
              <label className="form-label mb-4">Registration Type</label>
              <select
                onChange={onChangeHandler}
                className="form-select"
                aria-label="Default select example"
              >
                <option value="customer">Customer</option>
                <option value="publisher">Publisher</option>
                <option value="admin">Admin</option>
              </select>
              {userRole !== "publisher" ? (
                <label className="form-label mb-4 mt-4">Personal Details</label>
              ) : (
                <label className="form-label mb-4 mt-4">Publisher Details</label>
              )}
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
              <div className="form-group">
                <input
                  type="text"
                  className={`${props.errors.address && "is-invalid"} form-control form-control-lg`}
                  placeholder="Address"
                  name="address"
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                  required
                />
                {<div className="text-danger">{props.errors.address}</div>}
              </div>
              <div className="form-group register__text">
                <input
                  type="text"
                  className={`${
                    props.errors.country && "is-invalid"
                  } form-control form-control-lg me-4`}
                  placeholder="Country"
                  name="country"
                  value={country}
                  onChange={(e) => setCountry(e.target.value)}
                  required
                />

                <input
                  type="text"
                  className={`${
                    props.errors.postcode && "is-invalid"
                  } form-control form-control-lg`}
                  placeholder="Postcode"
                  name="postcode"
                  value={postcode}
                  onChange={(e) => setPostcode(e.target.value)}
                  required
                />
              </div>
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
              </div>
              {userRole === "publisher" && (
                <>
                  <label className="form-label mb-4 mt-4">Business Details</label>
                  <div className="form-group">
                    <input
                      type="text"
                      className={`${props.errors.abn && "is-invalid"} form-control form-control-lg`}
                      placeholder="ABN"
                      name="abn"
                      value={abn}
                      onChange={(e) => setAbn(e.target.value)}
                    />
                  </div>
                  <div className="form-check mb-4">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      value={businessApproval}
                      id="flexCheckDefault"
                      onClick={() => setbusinessApproval(!businessApproval)}
                    />
                    <label className="form-check-label" htmlFor="flexCheckDefault">
                      Approve Business Check
                    </label>
                  </div>
                </>
              )}
              {<div className="text-danger">{props.errors.phone}</div>}
              <div className="form-group">
                <label className="form-label mb-4 mt-2">Account Information</label>
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
              <div className="form-group">
                <input
                  className={`${
                    props.errors.password && "is-invalid"
                  } form-control form-control-lg`}
                  type="password"
                  className="form-control form-control-lg"
                  placeholder="Password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
                {<div className="text-danger">{props.errors.password}</div>}
              </div>
              <div className="form-group">
                <input
                  className={`${
                    props.errors.confirmPassword && "is-invalid"
                  } form-control form-control-lg`}
                  type="password"
                  className="form-control form-control-lg"
                  placeholder="Confirm Password"
                  name="confirmPassword"
                  value={confirmPassword}
                  onChange={(e) => setConfPassword(e.target.value)}
                  required
                />
                {<div className="text-danger">{props.errors.confirmPassword}</div>}
              </div>
              <h4 className="text-success">{props.messages.message}</h4>
              <button className="btn btn-info btn-block mt-4" onClick={onSubmitHandler}>
                Create Account
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (state) => {
  const { errors } = state;
  const { messages } = state;
  const { security } = state;
  return { errors, messages, security };
};

export default connect(mapStateToProps, { createNewUser })(AddUser);
