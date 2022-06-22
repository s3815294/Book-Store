import { SET_CURRENT_USER } from "../actions/types";

// this is temporary until login tokens are implemented in back-end
const temporaryTokenValidation = () => {
  const retrieveData = localStorage.getItem("token");
  let data = false;
  if (retrieveData !== null) {
    data = true;
  }
  return data;
};

const initialState = {
  validToken: temporaryTokenValidation(),
  user: {
    token: "",
  },
};

const booleanActionPayload = (payload) => {
  if (payload) {
    return true;
  } else {
    return false;
  }
};

export default function (state = initialState, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        validToken: booleanActionPayload(action.payload),
        user: { ...state.user, token: action.payload },
      };
    default:
      return state;
  }
}
