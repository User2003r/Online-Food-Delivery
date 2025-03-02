import * as actionTypes from "./ActionTypes";

const intialState = {
  loading: false,
  orders: [],
  error: null,
};

export const orderReducer = (state = intialState, { type, payload }) => {
  switch (action.type) {
    case actionTypes.GET_USERS_ORDERS_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case actionTypes.GET_USERS_ORDERS_SUCCESS:
      return {
        ...state,
        error: null,
        loading: false,
        orders: payload,
      };
    case actionTypes.GET_USERS_ORDERS_FAILURE:
      return {
        ...state,
        error: payload,
        loading: false,
      };
    default:
      return state;
  }
};
