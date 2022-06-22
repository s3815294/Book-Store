import React, { useState } from "react";
import { registerNewUser } from "../../actions/securityActions";
import { useHistory } from "react-router-dom";
import { connect } from "react-redux";

export const Register = (props) => {
  const history = useHistory();
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

  const onSubmitHandler = (e) => {
    e.preventDefault();
    props.registerNewUser(
      {
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
      },
      history
    );
  };

  const onChangeHandler = (event) => {
    setUserRole(event.target.value);
  };

  return (
    <div className="register">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center mb-4">Register</h1>
            <form>
              <label className="form-label mb-4">Registration Type</label>
              <select
                onChange={onChangeHandler}
                className="form-select"
                aria-label="Default select example"
              >
                <option value="customer">Customer</option>
                <option value="publisher">Publisher</option>
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
              <div className="text-danger">{props.errors.firstName}</div>
              <div className="text-danger">{props.errors.lastName}</div>

              <div className="form-group ">
                <input
                  type="text"
                  className={`${props.errors.address && "is-invalid"} form-control form-control-lg`}
                  placeholder="Address"
                  name="address"
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                  required
                />
              </div>
              <div className="text-danger">{props.errors.address}</div>
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
              <div className="text-danger">{props.errors.country}</div>
              <div className="text-danger">{props.errors.postcode}</div>

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
              <div className="text-danger">{props.errors.phone}</div>

              {userRole === "publisher" && (
                <>
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
                  <div className="text-danger">{props.errors.abn}</div>
                </>
              )}

              <div className="form-group" style={{ marginTop: "1rem !important" }}>
                <label className="form-label mb-4">Account Information</label>
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
                <div className="text-danger">{props.errors.username}</div>
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
                <div className="text-danger">{props.errors.password}</div>
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
                <div className="text-danger">{props.errors.confirmPassword}</div>
              </div>
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
  return { errors };
};

export default connect(mapStateToProps, { registerNewUser })(Register);
