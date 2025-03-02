import { api, API_URL } from "../../config/api";
import {
  CREATE_MENU_ITEM_FAILURE,
  CREATE_MENU_ITEM_REQUEST,
  CREATE_MENU_ITEM_SUCCESS,
  DELETE_MENU_ITEM_FAILURE,
  DELETE_MENU_ITEM_REQUEST,
  DELETE_MENU_ITEM_SUCCESS,
  GET_MENU_ITEM_BY_RESTAURANT_ID_FAILURE,
  GET_MENU_ITEM_BY_RESTAURANT_ID_REQUEST,
  GET_MENU_ITEM_BY_RESTAURANT_ID_SUCCESS,
  SEARCH_MENU_ITEM_FAILURE,
  SEARCH_MENU_ITEM_REQUEST,
  SEARCH_MENU_ITEM_SUCCESS,
  UPDATE_MENU_ITEM_AVAILABILITY_FAILURE,
  UPDATE_MENU_ITEM_AVAILABILITY_REQUEST,
  UPDATE_MENU_ITEM_AVAILABILITY_SUCCESS,
} from "./ActionType";

export const createMenuItem = ({ menu, kwt }) => {
  return async (dispatch) => {
    dispatch({ type: CREATE_MENU_ITEM_REQUEST });
    try {
      const { data } = await API_URL.post(`api/admin/food`, menu, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });

      dispatch({ type: CREATE_MENU_ITEM_SUCCESS, payload: error });
      console.log("Data", data);
    } catch (error) {
      dispatch({ type: CREATE_MENU_ITEM_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const getMenuItemByRestaurantId = (reqData) => {
  return async (dispatch) => {
    dispatch({ type: GET_MENU_ITEM_BY_RESTAURANT_ID_REQUEST });
    try {
      const { data } = await API_URL.get(
        `/api/food/restaurant/${reqData.restaurantId}?vagetarian=${reqData.vegetarian}&nonveg=${reqData.nonveg}&seasonal=${reqData.seasonal}&food_category=${reqData.foodCategory}`,
        {
          headers: {
            Authorization: `Bearer ${reqData.jwt}`,
          },
        }
      );
      dispatch({
        type: GET_MENU_ITEM_BY_RESTAURANT_ID_SUCCESS,
        payload: reqData,
      });
      console.log("data", data);
    } catch (error) {
      dispatch({
        type: GET_MENU_ITEM_BY_RESTAURANT_ID_FAILURE,
        payload: error,
      });
      console.log(error);
    }
  };
};

export const searchMenuItem = ({ keyword, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: SEARCH_MENU_ITEM_REQUEST });
    try {
      const { data } = await API_URL.get(`/api/food/search?name=${keyword}`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({ type: SEARCH_MENU_ITEM_SUCCESS, payload: data });
      console.log("Search Result", data);
    } catch (error) {
      dispatch({ type: SEARCH_MENU_ITEM_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const getAllIngredientsOfMenuItem = (reqData) => {
  return async (dispatch) => {
    dispatch({ type: GET_MENU_ITEM_BY_RESTAURANT_ID_REQUEST });
    try {
      const { data } = await API_URL.get(
        `/api/admin/ingrdients/restaurant/${reqData.restaurantId}`,
        {
          headers: {
            Authorization: `Bearer ${reqData.jwt}`,
          },
        }
      );
      dispatch({ type: GET_MENU_ITEM_BY_RESTAURANT_ID_SUCCESS, payload: data });
      console.log("Ingredients", data);
    } catch (error) {
      dispatch({
        type: GET_MENU_ITEM_BY_RESTAURANT_ID_FAILURE,
        payload: error,
      });
      console.log(error);
    }
  };
};

export const updateMenuItemAvailability = ({ foodId, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: UPDATE_MENU_ITEM_AVAILABILITY_REQUEST });
    try {
      const { data } = await API_URL.put(
        `/api/admin/food/updateAvailability/${foodId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      dispatch({ type: UPDATE_MENU_ITEM_AVAILABILITY_SUCCESS, payload: data });
      console.log("Update Stock", data);
    } catch (error) {
      dispatch({ type: UPDATE_MENU_ITEM_AVAILABILITY_FAILURE, payload: error });
      console.log(error);
    }
  };
};

export const deleteFoodAction = ({ foodId, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: DELETE_MENU_ITEM_REQUEST });
    try {
      const { data } = await API_URL.delete(`/api/admin/food/${foodId}`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({ type: DELETE_MENU_ITEM_SUCCESS, payload: foodId });
      console.log("Delete Food", data);
    } catch (error) {
      dispatch({ type: DELETE_MENU_ITEM_FAILURE, payload: error });
      console.log(error);
    }
  };
};
