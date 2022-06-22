import React from "react";
import { connect } from "react-redux";
import { useHistory } from "react-router";
import { deleteOrder } from "../../actions/orderActions";

const SingleOrder = (props) => {
  const date = new Date(props.order.create_At);
  const history = useHistory();
  // compare the date of this order with 2 hours behind time.
  function cancelOrderAvailable() {
    let dateNow = new Date();
    dateNow.setHours(dateNow.getHours() - 2);
    if (date < dateNow) {
      return false;
    }
    return true;
  }
  // cancels the order and reloads the component with success useState.
  const cancelOrder = (id) => {
    props.deleteOrder(id);
  };

  const reviewSeller = (id) => {
    history.push(`/user/userReview/${id}`);
  };

  return (
    <div className="card">
      <h5 className="card-header">
        <div className="row align-items-start">
          <div className="col">Order Id: {props.order.id}</div>
          <div className="col">
            Order Date: {date.getDate()}/{date.getMonth() + 1}/{date.getFullYear()}
          </div>
          <div className="col">Order Total: ${props.order.orderTotal}</div>
          <div className="col">Total Items: {props.order.bookDetails.length}</div>
        </div>
      </h5>
      {props.order.bookDetails.map((book, bkey) => {
        return (
          <div className="card-body" key={bkey}>
            <div className="p-2 text-dark order__background">
              <h5 className="card-title">Book Title: {book.title} </h5>
              <p className="card-text">Price: ${book.price}</p>
              <p>
                <button
                  type="button"
                  className="btn btn-secondary btn-xs"
                  onClick={() => reviewSeller(book.sellerId)}
                >
                  Review Seller: {book.sellerName}{" "}
                </button>{" "}
              </p>
            </div>
            <a className="mt-1 btn btn-primary" href={`/user/review/${book.bookId}`}>
              Review
            </a>
          </div>
        );
      })}
      {cancelOrderAvailable() && (
        <>
          <button
            type="button"
            className="btn btn-primary btn-xs"
            onClick={() => cancelOrder(props.order.id)}
          >
            Cancel Order
          </button>
        </>
      )}
    </div>
  );
};

const mapStateToProps = (state) => {
  const { security } = state;
  const { errors } = state;
  const { orders } = state;
  return { errors, security, orders };
};

export default connect(mapStateToProps, { deleteOrder })(SingleOrder);
