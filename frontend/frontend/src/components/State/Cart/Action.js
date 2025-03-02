import {
  ADD_ITEM_TO_CART_FAILURE,
  ADD_ITEM_TO_CART_REQUEST,
  ADD_ITEM_TO_CART_SUCCESS,
  CLEAR_CART_FAILURE,
  CLEAR_CART_REQUEST,
  CLEAR_CART_SUCCESS,
  FIND_CART_FAILURE,
  FIND_CART_REQUEST,
  FIND_CART_SUCCESS,
  GET_ALL_CART_ITEM_FAILURE,
  GET_ALL_CART_ITEM_REQUEST,
  GET_ALL_CART_ITEM_SUCCESS,
  REMOVE_CARTITEM_FAILURE,
  REMOVE_CARTITEM_REQUEST,
  REMOVE_CARTITEM_SUCCESS,
  UPDATE_CARTITEM_FAILURE,
  UPDATE_CARTITEM_REQUEST,
  UPDATE_CARTITEM_SUCCESS,
} from "./ActionType";
import { api, API_URL } from "../../config/api";

export const findCart = (jwt) => {
  return async (dispatch) => {
    dispatch({ type: FIND_CART_REQUEST });
    try {
      const response = await API_URL.get(`/api/cart`, {
        heders: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({ type: FIND_CART_SUCCESS, payload: response.data });
      console.log("Find cart", response.data);
    } catch (error) {
      dispatch({ type: FIND_CART_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const getAllCartItems = (reqData) => {
  return async (dispatch) => {
    dispatch({ type: GET_ALL_CART_ITEM_REQUEST });
    try {
      const response = await API_URL.get(`/api/carts/${reqData.cartId}/items`, {
        headers: {
          Authorzation: `Bearer ${reqData.jwt}`,
        },
      });
      dispatch({ type: GET_ALL_CART_ITEM_SUCCESS, payload: response.data });
      console.log("Cart items", response.data);
    } catch (error) {
      dispatch({ type: GET_ALL_CART_ITEM_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const addItemToCart = (reqData) => {
  return async (dispatch) => {
    dispatch({ type: ADD_ITEM_TO_CART_REQUEST });
    try {
      const { data } = await API_URL.put(`/api/cart/add/`, reqData, {
        headers: {
          Authorzation: `Bearer ${reqData.jwt}`,
        },
      });
      dispatch({ type: ADD_ITEM_TO_CART_SUCCESS, payload: data });
      console.log("Added item", data);
    } catch (error) {
      dispatch({ type: ADD_ITEM_TO_CART_FAILURE, payload: error.message });
      console.log(error);
    }
  };
};

export const updateCartItem = (reqData) => {
  return async (dispatch) => {
    dispatch({ type: UPDATE_CARTITEM_REQUEST });
    try {
      const { data } = await API_URL.put(
        `/api/cart-item/update/${reqData.data}`,
        {
          headers: {
            Authorzation: `Bearer ${reqData.jwt}`,
          },
        }
      );
      dispatch({ type: UPDATE_CARTITEM_SUCCESS, payload: data });
      console.log("Update item data", data);
    } catch (error) {
      dispatch({ type: UPDATE_CARTITEM_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const removeCartItem = ({ cartItemId, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: REMOVE_CARTITEM_REQUEST });
    try {
      const { data } = await API_URL.delete(
        `/api/cart-item/${cartItemId}/remove`,
        {
          headers: {
            Authorzation: `Bearer ${jwt}`,
          },
        }
      );
      dispatch({ type: REMOVE_CARTITEM_SUCCESS, payload: cartItemId });
      console.log("remove item data", data);
    } catch (error) {
      dispatch({ type: REMOVE_CARTITEM_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const clearCartAction = () => {
  return async (dispatch) => {
    dispatch({ type: CLEAR_CART_REQUEST });
    try {
      const { data } = await API_URL.put(`/api/cart/clear`, {
        headers: {
          Authorzation: `Bearer ${localStorage.getItem("jwt")}`,
        },
      });
      dispatch({ type: CLEAR_CART_SUCCESS, payload: data });
      console.log("clear", data);
    } catch (error) {
      dispatch({ type: CLEAR_CART_FAILURE, payload: error });
      console.log(error);
    }
  };
};
