import { GET_PENDING_USER_ORDERS, GET_ALL_ORDERS, DELETE_ORDER } from "../actions/types";

const initialState = {
  pendingOrders: [],
  orders: [],
  cancelOrder: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PENDING_USER_ORDERS:
      return {
        ...state,
        pendingOrders: action.payload,
      };
    case GET_ALL_ORDERS:
      return {
        ...state,
        orders: action.payload,
      };
    case DELETE_ORDER:
      return {
        ...state,
        cancelOrder: action.payload,
      };
    default:
      return state;
  }
}
