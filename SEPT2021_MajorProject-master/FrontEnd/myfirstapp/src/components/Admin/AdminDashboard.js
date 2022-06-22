import React, { useReducer } from "react";
import { connect } from "react-redux";
import AdminBook from "./AdminBook";
import AdminUser from "./AdminUser";
import AdminTransaction from "./AdminTransaction";

export const AdminDashboard = (props) => {
  const initialState = { component: <AdminUser />, name: "users" };
  const [state, dispatch] = useReducer(reducer, initialState);

  function reducer(state = initialState, action) {
    switch (action.type) {
      case "users":
        return {
          ...state,
          component: <AdminUser />,
          name: action.name,
        };
      case "books":
        return {
          ...state,
          component: <AdminBook />,
          name: action.name,
        };
      case "transactions":
        return {
          ...state,
          component: <AdminTransaction />,
          name: action.name,
        };
      default:
        return {
          ...state,
          component: <AdminUser />,
          name: action.name,
        };
    }
  }

  return (
    <div className="container">
      <ul className="nav nav-tabs">
        <li className="nav-item">
          <button
            className={`${state.name === "users" && "active"} nav-link`}
            aria-current="page"
            onClick={() => dispatch({ type: "users" })}
          >
            Users
          </button>
        </li>
        <li className="nav-item">
          <button
            className={`${state.name === "books" && "active"} nav-link`}
            onClick={() => dispatch({ type: "books" })}
          >
            Books
          </button>
        </li>
        <li className="nav-item">
          <button
            className={`${state.name === "transactions" && "active"} nav-link`}
            onClick={() => dispatch({ type: "transactions" })}
          >
            Transactions
          </button>
        </li>
      </ul>
      <div>{state.component}</div>
    </div>
  );
};

const mapStateToProps = (state) => ({
  security: state.security,
});
export default connect(mapStateToProps, {})(AdminDashboard);
