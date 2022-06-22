import axios from "axios";
import { GET_ERRORS, GET_ALL_APPROVALS, GET_ALL_USERS, GET_SINGLE_USER, SUCCESS } from "./types";

const localhost = "http://localhost:8080";

export const createPerson = (person, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/person", person);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getAllUsers = () => async (dispatch) => {
  const res = await axios.get(`http://localhost:8080/api/users/all`);
  dispatch({
    type: GET_ALL_USERS,
    payload: res.data,
  });
};

export const getUser = (id) => async (dispatch) => {
  const res = await axios.get(`http://localhost:8080/api/users/single/?id=${id}`);
  dispatch({
    type: GET_SINGLE_USER,
    payload: res.data,
  });
};

export const getAllApprovals = (role) => async (dispatch) => {
  const res = await axios.get(`http://localhost:8080/api/users/approvals/?role=${role}`);
  dispatch({
    type: GET_ALL_APPROVALS,
    payload: res.data,
  });
};

export const blockAccount = (blockPayload) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/users/admin/block", blockPayload);
    dispatch({
      type: SUCCESS,
      payload: `Successfully blocked: ${res.data.username}`,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const approveAccount = (approvePayload) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/users/admin/approve", approvePayload);
    dispatch({
      type: SUCCESS,
      payload: `Successfully approved: ${res.data.username}`,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const updateUser = (updateUser) => (dispatch) => {
  axios
    .post(`${localhost}/api/users/update`, updateUser)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully edited: ${res.data.firstName}`,
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

export const addUserReview = (review) => (dispatch) => {
  axios
    .post(`http://localhost:8080/api/users/review`, review)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully Reviewed: ${res.data.username}`,
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
