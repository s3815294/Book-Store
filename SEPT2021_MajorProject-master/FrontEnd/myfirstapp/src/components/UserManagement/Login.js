import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { connect } from "react-redux";
import { loginUser } from "../../actions/securityActions";

export const Login = (props) => {
  const history = useHistory();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const onSubmitHandler = (e) => {
    e.preventDefault();
    props.loginUser({ username, password }, history);
  };

  return (
    <div className="login__container">
      <h6 className="display-7 text-center">Log In</h6>
      <form>
        <div className="form-group">
          <input
            type="email"
            className="form-control form-control-lg"
            placeholder="Email Address"
            name="email"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <input
            type="password"
            className="form-control form-control-lg"
            placeholder="Password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {props.errors.username && (
          <p className="text-danger">
            {props.errors.username} or {props.errors.password}
          </p>
        )}
        {props.errors.blocked && <p className="text-danger"> {props.errors.blocked} </p>}
        <button className="btn btn-info btn-block mt-4" onClick={onSubmitHandler}>
          Submit
        </button>
      </form>
    </div>
  );
};

const mapStateToProps = (state) => ({
  security: state.security,
  errors: state.errors,
});

export default connect(mapStateToProps, { loginUser })(Login);
