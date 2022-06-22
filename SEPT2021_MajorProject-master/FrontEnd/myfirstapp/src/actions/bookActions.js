import axios from "axios";
import {
  GET_ERRORS,
  GET_BOOKS_SEARCH,
  GET_BOOK,
  GET_ALL_BOOKS,
  GET_BOOK_CATEGORY,
  GET_BOOK_CATEGORIES,
  SUCCESS,
} from "./types";

export const getBooksBySearch = (category, search) => (dispatch) => {
  axios
    .get(`http://localhost:8081/api/books/search/?${category}=${search}`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: GET_BOOKS_SEARCH,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};

export const getAllBooks = () => (dispatch) => {
  axios
    .get(`http://localhost:8081/api/books/all`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: GET_ALL_BOOKS,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};

export const getSingleBook = (id) => (dispatch) => {
  axios
    .get(`http://localhost:8081/api/books/id/?id=${id}`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: GET_BOOK,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};

export const getBooksByCategory = (category) => (dispatch) => {
  axios
    .get(`http://localhost:8081/api/books/category/?category=${category}`)
    .then((res) => {
      if (res.status === 200) {
        dispatch({
          type: GET_BOOK_CATEGORY,
          payload: res.data,
        });
      }
    })
    .catch((error) => {
      dispatch({
        type: GET_ERRORS,
        payload: error,
      });
    });
};

export const getBookCategories = () => (dispatch) => {
  axios.get("http://localhost:8081/api/books/categories").then((res) => {
    dispatch({
      type: GET_BOOK_CATEGORIES,
      payload: res.data,
    });
  });
};

export const addBook = (book, image, pdf, history, route) => (dispatch) => {
  const formData = new FormData();
  const imageFile = image.current.files[0];
  const pdfFile = pdf.current.files[0];

  formData.append("image", imageFile);
  formData.append("pdf", pdfFile);
  formData.append(
    "book",
    new Blob(
      [
        JSON.stringify({
          title: book.title,
          author: book.author,
          isbn: book.isbn,
          price: book.price,
          bookCondition: book.bookCondition,
          category: book.category,
          sellerName: book.sellerName,
          sellerId: book.sellerId,
        }),
      ],
      {
        type: "application/json",
      }
    )
  );
  axios
    .post(`http://localhost:8081/api/books/add`, formData)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully added: ${res.data.title}`,
      });
      history.push(route);
    })
    .catch((err) => {
      if (err.response !== undefined) {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        });
      }
    });
};

export const editBook = (book, image, pdf, history, route) => (dispatch) => {
  const formData = new FormData();
  if (image !== null) {
    formData.append("image", image.current.files[0]);
  }
  if (pdf !== null) {
    formData.append("pdf", pdf.current.files[0]);
  }
  formData.append(
    "book",
    new Blob(
      [
        JSON.stringify({
          id: book.id,
          title: book.title,
          author: book.author,
          isbn: book.isbn,
          price: book.price,
          bookCondition: book.bookCondition,
          category: book.category,
          imageType: book.imageType,
        }),
      ],
      {
        type: "application/json",
      }
    )
  );
  axios
    .post(`http://localhost:8081/api/books/id`, formData)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully Edited: ${res.data.title}`,
      });
      history.push(route);
    })
    .catch((err) => {
      if (err.response !== undefined) {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        });
      }
    });
};

export const addBookReview = (review, history) => (dispatch) => {
  axios
    .post(`http://localhost:8081/api/books/review`, review)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully Reviewed: ${res.data.title}`,
      });
    })
    .catch((err) => {
      if (err.response !== undefined) {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        });
      }
    });
};

export const editUser = (user, history, route) => (dispatch) => {
  const formData = new FormData();

  formData.append(
    "user",
    new Blob(
      [
        JSON.stringify({
          username: user.username,
          firstname: user.firstName,
          lastname: user.lastname,
          address: user.address,
          phone: user.phone,
        }),
      ],
      {
        type: "application/json",
      }
    )
  );
  axios
    .post(`http://localhost:8081/api/users/id/edit`, formData)
    .then((res) => {
      dispatch({
        type: SUCCESS,
        payload: `Successfully Edited: ${res.data.firstName}`,
      });
      history.push(route);
    })
    .catch((err) => {
      if (err.response !== undefined) {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        });
      }
    });
};
