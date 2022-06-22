import React, {useEffect, useState} from "react";
import Swal from 'sweetalert2'

import {getCart, insertOrUpdateCart} from "../../actions/cartActions";

const AddBookToCart = (props) => {
    const [quantity, setQuantity] = useState("0");
    const item = {};

    const onSubmitHandler = (event) => {
        event.preventDefault();
        if (quantity !== "0") {
            addToCart();
        }
    };

    function addToCart() {
        item.id = props.book.id;

        const cart = getCart();
        if(cart[item.id] !== undefined) {
            updateQuantity(cart);
        } else {
            item.quantity = quantity;
        }
        insertOrUpdateCart(item);
        Swal.fire(
            'Item Added To Cart',
            '',
            'success'
        );
        setQuantity("0");
    }

    function updateQuantity(cart) {
        let newQuantity = parseInt(cart[props.book.id]);
        newQuantity += parseInt(quantity);
        item.quantity = newQuantity;
    }

    return (
        <div className="add__to_cart align-items-center">
            <form className="input-group-prepend">
                <div className="col-auto">
                    <select className="custom-select" id="selectNumber" required value={quantity}
                            onChange={(e) => setQuantity(e.target.value)}>
                        <option defaultValue valuse="0">select quantity</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                    </select>
                </div>
                <div className="col-auto">
                    <button onClick={onSubmitHandler} id="inlineFormInput" className='add-button btn btn-dark'>
                        Add to Cart
                    </button>
                </div>
            </form>
        </div>
    )
};

export default AddBookToCart;