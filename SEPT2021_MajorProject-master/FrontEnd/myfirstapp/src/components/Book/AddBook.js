import React, { useState, useEffect, useRef } from "react";
import { connect } from "react-redux";
import { useHistory } from "react-router-dom";
import { getBookCategories, addBook } from "../../actions/bookActions";

export const AddBook = (props) => {
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [isbn, setIsbn] = useState("");
  const [price, setPrice] = useState(0);
  const [category, setCategory] = useState([]);
  const [bookCondition, setBookCondition] = useState("used");
  const imageInput = useRef();
  const pdfInput = useRef();
  const history = useHistory();

  const handleChange = (event) => {
    if (category.includes(event.target.name)) {
      setCategory(category.filter((i) => i != event.target.name));
    } else {
      setCategory([...category, event.target.name]);
    }
  };

  const onSubmitHandler = (event) => {
    const sellerName = props.security.user.token.username;
    const sellerId = props.security.user.token.id;
    if (event) event.preventDefault();
    props.addBook(
      {
        title,
        author,
        isbn,
        price,
        category,
        bookCondition,
        sellerName,
        sellerId
      },
      imageInput,
      pdfInput,
      history,
      props.route
    );
  };

  const onChangeHandler = (event) => {
    setBookCondition(event.target.value);
  };

  useEffect(() => {
    props.getBookCategories();
  }, []);

  return (
    <div className="container search__container">
      <form>
        <h1>Add Book</h1>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            Title
          </label>
          <input
            type="text"
            className="form-control"
            id="title"
            aria-describedby="TitleHelp"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        {<div className="text-danger">{props.errors.title}</div>}
        <div className="mb-3">
          <label htmlFor="author" className="form-label">
            Author
          </label>
          <input
            type="text"
            className="form-control"
            id="author"
            aria-describedby="AuthorHelp"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
          />
        </div>
        {<div className="text-danger">{props.errors.author}</div>}
        <div className="mb-3">
          <label htmlFor="isbn" className="form-label">
            ISBN
          </label>
          <input
            type="text"
            className="form-control"
            id="isbn"
            aria-describedby="ISBNHelp"
            value={isbn}
            onChange={(e) => setIsbn(e.target.value)}
          />
        </div>
        {<div className="text-danger">{props.errors.isbn}</div>}
        <div className="mb-3">
          <label htmlFor="price" className="form-label">
            Price
          </label>
          <input
            type="text"
            className="form-control"
            id="price"
            aria-describedby="priceHelp"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
        </div>
        {<div className="text-danger">{props.errors.price}</div>}
        <div className="mb-3">
          <label htmlFor="categories" className="form-label">
            Categories
          </label>
        </div>
        <div className="btn-group" role="group" aria-label="Basic checkbox toggle button group">
          {props.book.categories.map((item, key) => {
            return (
              <React.Fragment key={key}>
                <input
                  type="checkbox"
                  className="btn-check"
                  id={item}
                  autoComplete="off"
                  onChange={handleChange}
                  name={item}
                  value={item}
                />
                <label className="btn btn-outline-primary" htmlFor={item}>
                  {item}
                </label>
              </React.Fragment>
            );
          })}
        </div>
        {(props.security.user.token.role === "ADMIN" ||
          props.security.user.token.role === "PUBLISHER") && (
          <div className="mb-3">
            <label className="form-label mb-4">Book Condition</label>
            <select
              onChange={onChangeHandler}
              className="form-select"
              aria-label="Default select example"
            >
              <option value="used">Used</option>
              <option value="new">New</option>
            </select>
          </div>
        )}
        {<div className="text-danger">{props.errors.categories}</div>}
        <div className="mb-3">
          <label htmlFor="image" className="form-label">
            Book Image
          </label>
          <input
            type="file"
            className="form-control"
            ref={imageInput}
            id="image"
            aria-describedby="priceHelp"
          />
        </div>
        <div className="mb-3">
          <label htmlFor="pdf" className="form-label">
            Book Pdf - Table of contents
          </label>
          <input
            type="file"
            className="form-control"
            ref={pdfInput}
            id="pdf"
            aria-describedby="priceHelp"
          />
        </div>
        {<div className="text-danger">{props.errors.file}</div>}
        <div className="mb-3">
          <button type="button" className="btn btn-primary" onClick={onSubmitHandler}>
            Add Book
          </button>
        </div>
        <h4 className="text-success">{props.messages.message}</h4>
      </form>
    </div>
  );
};

const mapStateToProps = (state) => {
  const { security } = state;
  const { book } = state;
  const { errors } = state;
  const { messages } = state;

  return { book, errors, messages, security };
};

export default connect(mapStateToProps, { getBookCategories, addBook })(AddBook);
