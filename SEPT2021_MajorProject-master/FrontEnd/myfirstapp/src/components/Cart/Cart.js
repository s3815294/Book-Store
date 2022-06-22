import React, { useEffect, useState } from "react";
import "../../Cart.css";
import { getCart } from "../../actions/cartActions";
import CartItem from "./CartItem";
import { connect } from "react-redux";
import { getAllBooks } from "../../actions/bookActions";
import { createOrder } from "../../actions/orderActions";
import PaypalExpressBtn from "react-paypal-express-checkout";
import { useHistory } from "react-router-dom";

const Cart = (props) => {
  const [cart, updateCart] = useState(getCart());
  const history = useHistory();
  const env = "sandbox";
  const currency = "AUD";
  const client = {
    sandbox: "AfFprE27nX4HXTpV3Vd_lX-fpLooVK8qGD0bkKWLi4nMRvkroxmjVenpjNxtjwluvVEHbNT6CORiVcBE",
  };

  useEffect(() => {
    props.getAllBooks();
  }, []);

  function getQuantity() {
    let quantity = 0;
    Object.keys(cart).map((cartItem, key) => {
      return (quantity += parseInt(cart[cartItem]));
    });
    return quantity;
  }

  function getTotal() {
    let price = 0;
    Object.keys(cart).map((cartItem, key) => {
      return props.book.allBooks.map((book, key) => {
        if (book.id === parseInt(cartItem)) {
          price += parseInt(cart[cartItem]) * book.price;
        }
      });
    });
    return price;
  }

  const onSuccess = (payment) => {
    console.log("The payment was successful!", payment);
    let username = props.security.user.token.username;
    let userId = props.security.user.token.id;
    const booksOrdered = getOrderBooks();

    props.createOrder(
      {
        username,
        userId,
        booksOrdered,
      },
      history
    );
  };

  function getOrderBooks() {
    let orderBooks = [];
    Object.keys(cart).map((cartItem, key) => {
      return props.book.allBooks.map((book, key) => {
        if (book.id === parseInt(cartItem)) {
          let bookId = book.id;
          let title = book.title;
          let author = book.author;
          let isbn = book.isbn;
          let price = book.price;
          let sellerName = "admin@admin.com";
          let sellerId = 15;
          let quantity = parseInt(cart[cartItem]);

          orderBooks.push({ bookId, title, author, isbn, price, sellerName, sellerId, quantity });
        }
      });
    });
    return orderBooks;
  }

  const onCancel = (data) => {
    console.log("The payment was cancelled!", data);
    let username = props.security.user.token.username;
    let userId = props.security.user.token.id;
    let bookDetails = getOrderBooks();

    props.createOrder(
      {
        username,
        userId,
        booksOrdered: bookDetails,
      },
      history
    );
  };

  const onError = (err) => {
    console.log("Error!", err);
  };

  return (
    <div>
      <div className="row break-row">
        <div className="col-sm-2"></div>
        <div id="form" className="col-sm-8 heading-pad border border-secondary rounded">
          <form id="checkout" method="POST">
            <div className="cart">
              <h1 className="text-center">{props.messages.message}Shopping Cart</h1>
              <div className="cart-labels">
                <ul>
                  <li className="item item-heading">Item</li>
                  <li className="price">Price</li>
                  <li className="quantity">Quantity</li>
                  <li className="subtotal">Subtotal</li>
                </ul>
              </div>
              {Object.keys(cart).length > 0 ? (
                <div>
                  {Object.keys(cart).map((cartItem, key) => {
                    return props.book.allBooks.map((book, key) => {
                      return book.id === parseInt(cartItem) ? (
                        <CartItem
                          book={book}
                          key={key}
                          updateCart={updateCart}
                          quantity={cart[cartItem]}
                        />
                      ) : (
                        <div key={key}></div>
                      );
                    });
                  })}

                  <div className="cart-total">
                    <ul>
                      <li className="item item-heading"></li>
                      <li className="price"></li>
                      <li className="quantity">
                        <strong>Items: </strong>
                        {getQuantity()}
                      </li>
                      <li className="total">
                        <strong>Total: </strong>${getTotal()}
                      </li>
                    </ul>
                  </div>
                </div>
              ) : (
                <h3 className="text-center">Cart Empty</h3>
              )}
            </div>
          </form>
        </div>
        <div className="col-sm-2"></div>
      </div>

      {Object.keys(cart).length > 0 ? (
        <>
          <div>
            {props.security.user.token.username != null ? (
              <>
                <div className="row break-row">
                  <div className="col-sm-4"></div>
                  <div className="col-sm-4 text-center">
                    <h6 className="text-center">CHECKOUT</h6>
                    <PaypalExpressBtn
                      env={env}
                      client={client}
                      currency={currency}
                      total={getTotal()}
                      onError={onError}
                      onSuccess={onSuccess}
                      onCancel={onCancel}
                      style={{ shape: "rect", size: "large", margin: "1.5rem" }}
                    />
                  </div>
                  <div className="col-sm-4"></div>
                </div>
              </>
            ) : (
              <>
                <div className="row break-row">
                  <div className="col-sm-4"></div>
                  <div className="col-sm-4">
                    <h4 className="text-center">PLEASE LOGIN TO CHECKOUT</h4>
                  </div>
                  <div className="col-sm-4"></div>
                </div>
              </>
            )}
          </div>
        </>
      ) : (
        <div></div>
      )}
    </div>
  );
};

const mapStateToProps = (state) => {
  const { security } = state;
  const { book } = state;
  const { messages } = state;
  return { book, security, messages };
};

export default connect(mapStateToProps, { createOrder, getAllBooks })(Cart);
