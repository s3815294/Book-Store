import React, {useEffect, useState} from "react";

const CartItemAmount = (props) => {
    const [quantity, setQuantity] = useState(parseInt(props.quantity));
    const [subtotal, setSubtotal] = useState(0);

    useEffect(() => {
        setSubtotal(props.book.price * quantity);
    }, [props.book.price]);

    useEffect(() => {
        updateTotal();
        props.updateCart(quantity, subtotal);
    }, [decrement(), increment(), remove()]);

    function increment() {
        let qty = quantity +1;
        return qty
    }

    function decrement() {
        let qty = null;
        if(quantity > 1) {
            qty =  quantity - 1;
        } else {
            qty =  quantity
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
        <div>
            <div className="price">${props.book.price}
            </div>
            <div className="quantity">
                {quantity}
                <button className="border" type="button" onClick={(e) => setQuantity(increment())}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         className="bi bi-plus" viewBox="0 0 16 16">
                        <path
                            d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                    </svg>
                </button>
                <div className="upDownSpacing"></div>
                <button className="border" type="button" onClick={(e) => setQuantity(decrement())}>
                    <span className="minus">-</span>
                </button>
            </div>
            <div className="subtotal" id="subtotal">${subtotal}</div>
            <div className="remove">
                <button type="button" onClick={(e) => setQuantity(remove())} >Remove</button>
            </div>
        </div>
    )
};


export default CartItemAmount;
