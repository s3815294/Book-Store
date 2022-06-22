import React, { useReducer } from "react";
import { connect } from "react-redux";
import UserOrders from "./UserOrders";

import { getPendingOrdersByUser } from "../../actions/orderActions";
import AddBook from "../Book/AddBook";
import UserTransaction from "./UserTransaction";

const UserDashboard = (props) => {
  const initialState = { component: <UserOrders />, name: "orders" };
  const [state, dispatch] = useReducer(reducer, initialState);

  function reducer(state = initialState, action) {
    switch (action.type) {
      case "orders":
        return {
          ...state,
          component: <UserOrders />,
          name: action.name,
        };
      case "books":
        return {
          ...state,
          component: <AddBook />,
          name: action.name,
        };
      case "transactions":
        return {
          ...state,
          component: <UserTransaction />,
          name: action.name,
        };
      default:
        return {
          ...state,
          component: <UserOrders />,
          name: action.name,
        };
    }
  }

  // update the order value for UserOrders when it returns.
  return (
    <div className="container">
      <ul className="nav nav-tabs">
        <li className="nav-item">
          <button
            className={`${state.name === "orders" && "active"} nav-link`}
            onClick={() => dispatch({ type: "orders" })}
          >
            Orders
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

export default connect(mapStateToProps, {})(UserDashboard);
