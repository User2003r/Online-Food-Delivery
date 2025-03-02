import { API_URL } from "../../config/api";
import {
  CREATE_ORDER_FAILURE,
  CREATE_ORDER_REQUEST,
  CREATE_ORDER_SUCCESS,
  GET_USERS_NOTIFICATION_SUCCESS,
  GET_USERS_ORDERS_FAILURE,
  GET_USERS_ORDERS_REQUEST,
} from "./ActionTypes";

export const createOrder = ({ reqData }) => {
  return async (dispatch) => {
    dispatch({ type: CREATE_ORDER_REQUEST });
    try {
      const { data } = await API_URL.post(`/api/order`, reqData.order, {
        headers: {
          Authorization: `Bearer ${reqData.jwt}`,
        },
      });
      dispatch({ type: CREATE_ORDER_SUCCESS, payload: data });
      console.log("Created Order", data);
    } catch (error) {
      dispatch({ type: CREATE_ORDER_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const getUsersOrder = (jwt) => {
  return async (dispatch) => {
    dispatch({ type: GET_USERS_ORDERS_REQUEST });
    try {
      const { data } = await API_URL.get(`/api/order`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log("users order", data);
      dispatch({ type: GET_USERS_NOTIFICATION_SUCCESS, payload: data });
    } catch (error) {
      dispatch({ type: GET_USERS_ORDERS_FAILURE, payload: error });
      console.log(error);
    }
  };
};
