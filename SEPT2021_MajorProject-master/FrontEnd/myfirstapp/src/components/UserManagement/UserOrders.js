import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPendingOrdersByUser } from "../../actions/orderActions";
import SingleOrder from "../Order/SingleOrder";

const UserOrders = (props) => {
  useEffect(() => {
    props.getPendingOrdersByUser(props.security.user.token.username);
  }, []);

  return (
    <div className="container mt-5">
      {props.order.pendingOrders &&
        props.order.pendingOrders.map((order, key) => {
          return (
            <div key={key}>
              <SingleOrder order={order} />
            </div>
          );
        })}
    </div>
  );
};

const mapStateToProps = (state) => ({
  security: state.security,
  order: state.order,
});

export default connect(mapStateToProps, { getPendingOrdersByUser })(UserOrders);
