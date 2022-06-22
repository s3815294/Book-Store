import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPendingOrdersByUser } from "../../actions/orderActions";
import SingleTransaction from "../Transactions/SingleTransaction";

const UserTransactions = (props) => {
  let headers = [
    { label: "User Name", key: "username" },
    { label: "Order Id", key: "orderid" },
    { label: "Book Title", key: "booktitle" },
    { label: "Book Price", key: "bookprice" },
    { label: "Seller Name", key: "sellername" },
  ];

  let data = [];
  useEffect(() => {
    props.getPendingOrdersByUser(props.security.user.token.username);
  }, []);

  return (
    <div className="container">
      <h3 className="text-center">All Transactions</h3>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th scope="col">User Name</th>
            <th scope="col">Order Id</th>
            <th scope="col">Book Title</th>
            <th scope="col">Book Price</th>
            <th scope="col">Seller Name</th>
          </tr>
        </thead>
        <tbody>
          {props.order.pendingOrders &&
            props.order.pendingOrders.map((order, key) => {
              data.push({
                username: props.security.user.token.username,
                orderid: order.id,
                booktitle: order.title,
                bookprice: order.price,
                sellername: order.sellerName,
              });
              return (
                <SingleTransaction
                  order={order}
                  username={props.security.user.token.username}
                  key={key}
                />
              );
            })}
        </tbody>
      </table>
    </div>
  );
};

const mapStateToProps = (state) => ({
  security: state.security,
  order: state.order,
});

export default connect(mapStateToProps, { getPendingOrdersByUser })(UserTransactions);
