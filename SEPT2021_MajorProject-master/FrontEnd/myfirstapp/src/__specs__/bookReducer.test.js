import {
  GET_BOOKS_SEARCH,
  GET_ALL_BOOKS,
  GET_BOOK,
  GET_BOOK_CATEGORY,
  GET_BOOK_CATEGORIES,
} from "../actions/types";
import bookReducer from "../reducers/bookReducer";

const getDefaultStateValue = () => {
  return {
    searchBooks: [],
    activeBook: {},
    allBooks: [],
    categories: [],
  };
};

describe("Book reducer", () => {
  it("Should return default state", () => {
    const defaultState = getDefaultStateValue();
    const newState = bookReducer(undefined, {});
    expect(newState).toEqual(defaultState);
  });

  it("should return search books equal to searchBooks", () => {
    const returnValue = getDefaultStateValue();
    returnValue.searchBooks = [
      {
        id: 1,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
    ];
    const newState = bookReducer(undefined, {
      type: GET_BOOKS_SEARCH,
      payload: returnValue.searchBooks,
    });
    expect(newState).toEqual(returnValue);
  });

  it("should return get all books equal to all books", () => {
    const returnValue = getDefaultStateValue();
    returnValue.allBooks = [
      {
        id: 1,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
      {
        id: 2,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
      {
        id: 3,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
    ];
    const newState = bookReducer(undefined, {
      type: GET_ALL_BOOKS,
      payload: returnValue.allBooks,
    });
    expect(newState).toEqual(returnValue);
  });

  it("should return books by category equal to category", () => {
    const returnValue = getDefaultStateValue();
    returnValue.allBooks = [
      {
        id: 1,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
      {
        id: 2,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
      {
        id: 3,
        title: "book1",
        ISBN: 3248230,
        categories: [{ name: "fiction" }],
        author: "author1",
      },
    ];

    const newState = bookReducer(undefined, {
      type: GET_BOOK_CATEGORY,
      payload: returnValue.allBooks,
    });
    expect(newState).toEqual(returnValue);
  });

  it("should return single book equal to activeBook", () => {
    const returnValue = getDefaultStateValue();
    returnValue.activeBook = {
      id: 1,
      title: "book1",
      ISBN: 3248230,
      categories: [{ name: "fiction" }],
      author: "author1",
    };

    const newState = bookReducer(undefined, {
      type: GET_BOOK,
      payload: returnValue.activeBook,
    });
    expect(newState).toEqual(returnValue);
  });

  it("should return books by category equal to category", () => {
    const returnValue = getDefaultStateValue();
    returnValue.categories = ["Fiction", "New"];

    const newState = bookReducer(undefined, {
      type: GET_BOOK_CATEGORIES,
      payload: returnValue.categories,
    });
    expect(newState).toEqual(returnValue);
  });
});
