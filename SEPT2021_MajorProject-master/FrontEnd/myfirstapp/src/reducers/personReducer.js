import { GET_SINGLE_USER, GET_ALL_USERS, GET_ALL_APPROVALS } from "../actions/types";

const initialState = {
  persons: [],
  approvals: [],
  person: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_SINGLE_USER:
      return {
        ...state,
        person: action.payload,
      };

    case GET_ALL_USERS:
      return {
        ...state,
        persons: action.payload,
      };

    case GET_ALL_APPROVALS:
      return {
        ...state,
        approvals: action.payload,
      };
    default:
      return state;
  }
}
