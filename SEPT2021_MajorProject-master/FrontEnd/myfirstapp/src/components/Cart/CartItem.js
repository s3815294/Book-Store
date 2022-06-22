import React, { useEffect, useState } from "react";
import { getCart, insertOrUpdateCart, setCart } from "../../actions/cartActions";
import CartItemAmount from "./CartItemDetails";

const CartItem = (props) => {
  const [quantity, setQuantity] = useState(parseInt(props.quantity));
  const [subtotal, setSubtotal] = useState(props.book.price * parseInt(props.quantity));

  useEffect(() => {
    updateTotal();
    updateCart();
  }, [decrement(), increment(), remove()]);

  function updateCart() {
    if (quantity === -1) {
      removeItemFromCart(props.book.id);
    } else {
      updateCartQuantity(quantity);
    }
  }

  function removeItemFromCart(id) {
    const oldCart = getCart();
    const newCart = {};
    Object.keys(oldCart).map((cartItem) => {
      if (cartItem !== id.toString()) {
        newCart[cartItem] = oldCart[cartItem];
      }
    });
    setCart(newCart);
    props.updateCart(newCart);
  }

  function updateCartQuantity(quantity) {
    let item = {};
    item.id = props.book.id;
    item.quantity = quantity;
    insertOrUpdateCart(item);
    props.updateCart(getCart());
  }

  function increment() {
    let qty = quantity + 1;
    return qty;
  }

  function decrement() {
    let qty;
    if (quantity > 1) {
      qty = quantity - 1;
    } else {
      qty = quantity;
    }
    return qty;
  }

  function remove() {
    return -1;
  }

  function updateTotal() {
    setSubtotal(props.book.price * quantity);
  }

  return (
    <div className="cart-product" id={props.book.id}>
      <a href={`./book/${props.book.id}`} className="text-colour">
        <div className="item">
          <div className="cart-image">
            <img
              src={`https://sept-bookeroo.s3.ap-southeast-2.amazonaws.com/image/${props.book.id}.${props.book.imageType}`}
              alt={props.book.title}
              className="cart-frame"
            />
          </div>
          <div className="item-details">
            <h3>
              <strong>Title: </strong>
              {props.book.title}
            </h3>
            <p>
              <strong>Author: </strong>
              {props.book.author}
            </p>
            <p>ISBN: {props.book.isbn}</p>
          </div>
        </div>
      </a>
      <div className="price">${props.book.price}</div>
      <div className="quantity">
        {quantity}
        <button className="border" type="button" onClick={(e) => setQuantity(increment())}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            className="bi bi-plus"
            viewBox="0 0 16 16"
          >
            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
          </svg>
        </button>
        <div className="upDownSpacing"></div>
        <button className="border" type="button" onClick={(e) => setQuantity(decrement())}>
          <span className="minus">-</span>
        </button>
      </div>
      <div className="subtotal" id="subtotal">
        ${subtotal}
      </div>
      <div className="remove">
        <button type="button" className="cart_btn" onClick={(e) => setQuantity(remove())}>
          Remove
        </button>
      </div>
    </div>
  );
};

export default CartItem;
