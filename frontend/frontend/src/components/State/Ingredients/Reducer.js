import * as actionTypes from "./ActionTypes";

const initialState = {
  ingredients: [],
  update: null,
  category: [],
};

export const ingredientsReducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.GET_INGREDIENTS:
      return {
        ...state,
        ingredients: action.payload,
      };
    case actionTypes.GET_INGREDIENTS_CATEGORY_SUCCESS:
      return {
        ...state,
        category: action.payload,
      };
    case actionTypes.CREATE_INGREDIENTS_CATEGORY_SUCCESS:
      return {
        ...state,
        category: [...state, category, action.payload],
      };
    case actionTypes.CREATE_INGREDIENTS_SUCCESS:
      return {
        ...state,
        ingredients: [...state.ingredients, action.payload],
      };
    case actionTypes.UPDATE_STOCK:
      return {
        ...state,
        update: action.payload,
        ingredients: state.ingredients.map((item) => {
          item.id === action.payload.id ? action.payload : item;
        }),
      };
    default:
      state;
  }
};
