import { SUCCESS } from "../actions/types";

const initialState = { message: "" };

export default function (state = initialState, action) {
  switch (action.type) {
    case SUCCESS:
      return {
        ...state,
        message: action.payload,
      };
    default:
      return state;
  }
}
