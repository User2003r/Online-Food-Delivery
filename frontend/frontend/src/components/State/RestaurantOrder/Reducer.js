import {
  GET_RESTAURANTS_ORDER_FAILURE,
  GET_RESTAURANTS_ORDER_REQUEST,
  GET_RESTAURANTS_ORDER_SUCCESS,
  UPDATE_ORDER_STATUS_FAILURE,
  UPDATE_ORDER_STATUS_REQUEST,
  UPDATE_ORDER_STATUS_SUCCESS,
} from "./ActionTypes";

const intialState = {
  loading: false,
  error: null,
  orders: [],
};

export const restaurantOrderReducer = (state = intialState, action) => {
  switch (action.type) {
    case GET_RESTAURANTS_ORDER_REQUEST:
    case UPDATE_ORDER_STATUS_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case GET_RESTAURANTS_ORDER_SUCCESS:
      return {
        ...state,
        loading: false,
        orders: action.payload,
      };
    case UPDATE_ORDER_STATUS_SUCCESS:
      const updatedOrder = state.orders.map((order) => {
        order.id === action.payload.id ? action.payload : order;
      });
      return {
        ...state,
        loading: false,
        orders: updatedOrder,
      };
    case GET_RESTAURANTS_ORDER_FAILURE:
    case UPDATE_ORDER_STATUS_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.error,
      };
    default:
      state;
  }
};
