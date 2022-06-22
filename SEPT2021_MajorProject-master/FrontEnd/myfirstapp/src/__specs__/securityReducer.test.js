import { SET_CURRENT_USER } from "../actions/types";
import securityReducer from "../reducers/securityReducer";

const getDefaultStateValue = () => {
  return {
    validToken: false,
    user: {
      token: "",
    },
  };
};

describe("security reducer", () => {
  it("Should return default state", () => {
    const returnState = getDefaultStateValue();
    const newState = securityReducer(undefined, {});
    expect(newState).toEqual(returnState);
  });

  it("should return new state if receiving type", () => {
    const returnState = getDefaultStateValue();
    returnState.user.token = "test user";
    returnState.validToken = true;
    const user = "test user";
    const newState = securityReducer(undefined, {
      type: SET_CURRENT_USER,
      payload: user,
    });
    expect(newState).toEqual(returnState);
  });
});
