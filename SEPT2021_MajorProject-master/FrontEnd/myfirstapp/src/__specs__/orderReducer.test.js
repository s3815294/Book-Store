import { DELETE_ORDER, GET_PENDING_USER_ORDERS } from "../actions/types";
import orderReducer from "../reducers/orderReducer";

const getDefaultStateValue = () => {
  return {
    pendingOrders: [],
    cancelOrder: {},
    orders: [],
  };
};

describe("order reducer", () => {
  it("Should return default state", () => {
    const defaultState = getDefaultStateValue();
    const newState = orderReducer(undefined, {});
    expect(newState).toEqual(defaultState);
  });

  it("should return orders equal to pendingOrders", () => {
    const returnValue = getDefaultStateValue();
    returnValue.pendingOrders = [
      {
        id: 1,
        orderAccount: {
          id: 1,
          username: "testuser",
          created_at: "2021-09-13T11:08:46.000+00:00",
          updated_at: null,
        },
        orderTotal: 89.6,
        singleOrderStatus: "PENDING",
        created_at: "2021-09-13T11:08:46.000+00:00",
        updated_at: null,
        bookDetails: [
          {
            id: 7,
            bookId: 52,
            title: "Test Title",
            author: "Test Author",
            isbn: "1231231R",
            price: 89.6,
            sellerName: "test@test.com",
            sellerId: 15,
            create_At: "2021-09-13T11:08:47.000+00:00",
            update_At: null,
          },
        ],
      },
    ];
    const newState = orderReducer(undefined, {
      type: GET_PENDING_USER_ORDERS,
      payload: returnValue.pendingOrders,
    });

    expect(newState).toEqual(returnValue);
  });

  it("should return order equal to cancelOrder", () => {
    const returnValue = getDefaultStateValue();

    returnValue.cancelOrder = {
      id: 1,
      orderAccount: {
        id: 1,
        username: "testuser",
        created_at: "2021-09-13T11:08:46.000+00:00",
        updated_at: null,
      },
      orderTotal: 89.6,
      singleOrderStatus: "PENDING",
      created_at: "2021-09-13T11:08:46.000+00:00",
      updated_at: null,
      bookDetails: [
        {
          id: 7,
          bookId: 52,
          title: "Test Title",
          author: "Test Author",
          isbn: "1231231R",
          price: 89.6,
          sellerName: "test@test.com",
          sellerId: 15,
          create_At: "2021-09-13T11:08:47.000+00:00",
          update_At: null,
        },
      ],
    };
    const newState = orderReducer(undefined, {
      type: DELETE_ORDER,
      payload: returnValue.cancelOrder,
    });

    expect(newState).toEqual(returnValue);
  });
});
