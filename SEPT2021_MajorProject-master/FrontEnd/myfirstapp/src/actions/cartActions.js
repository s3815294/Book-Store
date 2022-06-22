import {
    deleteLocalStorage,
    getLocalStorage,
    setLocalStorage
} from "./utilActions"

const CART_KEY = "SHOPPING-CART";

function initCart() {
    if(getLocalStorage(CART_KEY) !== null) {
        return;
    }
    setCart({});
}

function setCart(cart) {
    setLocalStorage(CART_KEY, JSON.stringify(cart));
}

function deleteCart() {
    deleteLocalStorage(CART_KEY);
}

function getCart() {
    const data = getLocalStorage(CART_KEY);
    return JSON.parse(data);
}

function insertOrUpdateCart(item) {
    const cart = getCart();
    cart[item.id] = item.quantity;
    setCart(cart);
}

export {
    insertOrUpdateCart,
    getCart,
    deleteCart,
    setCart,
    initCart
}