import { api, API_URL } from "../../config/api";
import {
  CREATE_CATEGORY_FAILURE,
  CREATE_CATEGORY_REQUEST,
  CREATE_CATEGORY_SUCCESS,
} from "../Restaurant/ActionType";
import {
  GET_INGREDIENTS,
  GET_INGREDIENTS_CATEGORY_FAILURE,
  GET_INGREDIENTS_CATEGORY_REQUEST,
  GET_INGREDIENTS_CATEGORY_SUCCESS,
  UPDATE_STOCK,
} from "./ActionTypes";

export const getIngredientsRestaurant = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      const res = await API_URL.get(`/api/admin/ingredients/restaurant/${id}`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({ type: GET_INGREDIENTS, payload: res.data });
      console.log("get all infredients", data);
    } catch (error) {
      console.log(error);
    }
  };
};

export const createIngredients = ({ data, jwt }) => {
  return async (dipatch) => {
    dispatch({ type: CREATE_CATEGORY_REQUEST });
    try {
      const res = await API_URL.post(`/api/admin/ingredients`, data, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({ type: CREATE_CATEGORY_SUCCESS, payload: res.data });
      console.log("created ingredient", res.data);
    } catch (error) {
      dispatch({ type: CREATE_CATEGORY_FAILURE, error });
      console.log(error);
    }
  };
};

export const getIngredientsCategory = ({ id, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: GET_INGREDIENTS_CATEGORY_REQUEST });
    try {
      const res = await API_URL.get(
        `/api/admin/ingredients/restaurant/${id}/category`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      dispatch({ type: GET_INGREDIENTS_CATEGORY_SUCCESS, payload: res.data });
      console.log("ingredients category", res.data);
    } catch (error) {
      dispatch({ type: GET_INGREDIENTS_CATEGORY_FAILURE, error });
      console.log(error);
    }
  };
};

export const updateStockOfIngredients = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      const { data } = await API_URL.put(
        `/api/admin/ingredients//${id}/stock`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      dispatch({ type: UPDATE_STOCK, payload: data });
      console.log("Update stock", data);
    } catch (error) {
      console.log(error);
    }
  };
};
