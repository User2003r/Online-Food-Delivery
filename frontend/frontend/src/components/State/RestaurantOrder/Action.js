import { api, API_URL } from "../../config/api";
import {
  GET_RESTAURANTS_ORDER_FAILURE,
  GET_RESTAURANTS_ORDER_REQUEST,
  GET_RESTAURANTS_ORDER_SUCCESS,
  UPDATE_ORDER_STATUS_FAILURE,
  UPDATE_ORDER_STATUS_REQUEST,
  UPDATE_ORDER_STATUS_SUCCESS,
} from "./ActionTypes";

export const updateOrderStatus = ({ orderId, orderStatus, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: UPDATE_ORDER_STATUS_REQUEST });
    try {
      const res = await API_URL.put(
        `/api/admin/orders/${orderId}/${orderStatus}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      dispatch({ type: UPDATE_ORDER_STATUS_SUCCESS, payload: res.data });
      console.log("Update order", res.data);
    } catch (error) {
      dispatch({ type: UPDATE_ORDER_STATUS_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const fetchRestaurantOrder = ({ restaurantId, orderStatus, jwt }) => {
  return async (dipatch) => {
    dispatch({ type: GET_RESTAURANTS_ORDER_REQUEST });
    try {
      const { data } = await API_URL.get(
        `/api/admin/order/restaurant/${restaurantId}`,
        {
          param: {
            order_status: orderStatus,
          },
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      dispatch({ type: GET_RESTAURANTS_ORDER_SUCCESS, payload: data });
      console.log("order", data);
    } catch (error) {
      dispatch({ type: GET_RESTAURANTS_ORDER_FAILURE, payload: error });
      console.log(error);
    }
  };
};
