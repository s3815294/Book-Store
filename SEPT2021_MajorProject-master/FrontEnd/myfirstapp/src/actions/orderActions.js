import axios from "axios";
import { deleteCart } from "./cartActions";
import {
  GET_ERRORS,
  GET_PENDING_USER_ORDERS,
  GET_ALL_ORDERS,
  DELETE_ORDER,
  SUCCESS,
} from "./types";

export const getPendingOrdersByUser = (user) => (dispatch) => {
  axios
    .get(`http://localhost:8082/api/orders/pendingOrders?username=${user}`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: GET_PENDING_USER_ORDERS,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};

export const getAllOrders = (role) => (dispatch) => {
  axios
    .get(`http://localhost:8082/api/orders/getAllOrders?role=${role}`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: GET_ALL_ORDERS,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};
export const deleteOrder = (orderId) => (dispatch) => {
  axios
    .delete(`http://localhost:8082/api/orders/orders?id=${orderId}`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: DELETE_ORDER,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};

export const createOrder = (order, history) => (dispatch) => {
  axios
    .post(`http://localhost:8082/api/orders/add`, order)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Order Created: ${res.data.id}`,
      });
      deleteCart();
      history.push("/user/dashboard");
    })
    .catch((err) => {
      if (err.response !== undefined) {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        });
      }
    });
};
