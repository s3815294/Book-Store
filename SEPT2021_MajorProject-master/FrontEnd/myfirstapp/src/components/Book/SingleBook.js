import React from "react";

const SingleBook = (props) => {
  return (
    <a className="book__container" key={props.book.id} href={props.link}>
      <img
        className="book__image"
        src={`https://sept-bookeroo.s3.ap-southeast-2.amazonaws.com/image/${props.book.id}.${props.book.imageType}`}
      />

      <div className="book__content">
        <span className="book__content-title">{props.book.title}</span>
        <span className="book__content-text">
          <i>Author</i>
          {props.book.author}
        </span>
        <span className="book__content-text">
          <i>ISBN</i> {props.book.ISBN}
        </span>
        <span className="book__content-text mb-4 text-center"></span>
        <div className="book__content-category-container">
          {props.book.category.map((category, catKey) => {
            return (
              <span key={catKey} className="book__content-text-category">
                {category}
              </span>
            );
          })}
        </div>
      </div>
    </a>
  );
};
export default SingleBook;
