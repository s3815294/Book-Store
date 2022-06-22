import {
  GET_BOOKS_SEARCH,
  GET_ALL_BOOKS,
  GET_BOOK,
  GET_BOOK_CATEGORY,
  GET_BOOK_CATEGORIES,
} from "../actions/types";

const initialState = {
  searchBooks: [],
  activeBook: {},
  allBooks: [],
  categories: [],
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_BOOKS_SEARCH:
      return {
        ...state,
        searchBooks: action.payload,
      };
    case GET_ALL_BOOKS:
      return {
        ...state,
        allBooks: action.payload,
      };
    case GET_BOOK:
      return {
        ...state,
        activeBook: action.payload,
      };
    case GET_BOOK_CATEGORY:
      return {
        ...state,
        allBooks: action.payload,
      };
    case GET_BOOK_CATEGORIES:
      return {
        ...state,
        categories: action.payload,
      };
    default:
      return state;
  }
}
