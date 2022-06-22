import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
// reference https://reactjsexample.com/a-simple-star-rating-component-with-react/
import { Rating } from "react-simple-star-rating";
import { useHistory, useParams } from "react-router-dom";
import { getSingleBook, addBookReview } from "../../actions/bookActions";

export const AddReview = (props) => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [rating, setRating] = useState(0);
  const { bookId } = useParams();
  const history = useHistory();

  const onSubmitHandler = (event) => {
    if (event) event.preventDefault();
    let username = props.security.user.token.username;

    props.addBookReview(
      {
        title,
        content,
        rating,
        bookId,
        username,
      },
      history
    );
  };

  const handleRating = (rate) => {
    setRating(rate);
  };

  useEffect(() => {
    props.getSingleBook(bookId);
  }, []);

  return (
    <div className="container">
      <form>
        <h1>Add Review for {props.book.activeBook.title}</h1>
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
        <div className="form-floating">
          <textarea
            className="form-control"
            id="floatingTextarea2"
            style={{ height: "100px" }}
            value={content}
            onChange={(e) => setContent(e.target.value)}
          ></textarea>
          <label htmlFor="floatingTextarea2">Comments</label>
        </div>
        <div className="text-danger">{props.errors.content}</div>
        <label htmlFor="floatingTextarea2" className="mt-3">
          <strong>Rating this book from 1 to 5 stars.</strong>
        </label>
        <div className="mt-2">
          <Rating
            onClick={handleRating}
            ratingValue={rating}
            size={20}
            label
            transition
            fillColor="orange"
            emptyColor="gray"
            className=""
          />
        </div>
        <div className="text-danger">{props.errors.rating}</div>
        <div className="mb-3">
          <button type="button" className="btn btn-primary mt-5" onClick={onSubmitHandler}>
            Add Review
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

export default connect(mapStateToProps, { getSingleBook, addBookReview })(AddReview);
