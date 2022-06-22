import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER, NETWORK_ERROR, SUCCESS } from "./types";
import { setLocalStorage, deleteLocalStorage, getLocalStorage } from "./utilActions";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

const localhost = "http://localhost:8080";

export const registerNewUser = (newUser, history) => (dispatch) => {
  axios
    .post(`${localhost}/api/users/register`, newUser)
    .then((res) => {
      history.push("/");
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

export const createNewUser = (newUser) => (dispatch) => {
  axios
    .post(`${localhost}/api/users/admin/add`, newUser)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully added: ${res.data.username}`,
      });
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

export const loginUser = (loginRequest, history) => (dispatch) => {
  axios
    .post(`${localhost}/api/users/login`, loginRequest)
    .then((res) => {
      if (res.status === 200) {
        setLocalStorage("token", res.data.token);
        setJWTToken(res.data.token);
        const decoded = jwt_decode(res.data.token);
        dispatch({
          type: SET_CURRENT_USER,
          payload: decoded,
        });
        history.push("/");
      }
    })
    .catch((err) => {
      if (err.response !== undefined) {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        });
      } else {
        dispatch({
          type: NETWORK_ERROR,
          payload: err.message,
        });
      }
    });
};

export const logoutUser = (history) => (dispatch) => {
  deleteLocalStorage("token");
  setJWTToken(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: "",
  });
  history.push("/");
};
