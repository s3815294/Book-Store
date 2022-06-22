import React from "react";

import SingleBook from "./SingleBook";

const GroupedBookDisplay = (props) => {
  return (
    <div className="container landing-seperator">
      <h2 className={`mt-5 mb-5 ${props.type !== "search" ? "text-capitalize" : ""}`}>
        {props.title}
      </h2>
      <div className="container groupedbook-container">
        <div className="groupedbook-items">
          {props.type === "search"
            ? props.book.searchBooks !== [] &&
              props.book.searchBooks.map((book, key) => {
                return <SingleBook book={book} key={key} link={`/book/${book.id}`} />;
              })
            : props.book.allBooks.map((book, key) => {
                return <SingleBook book={book} key={key} link={`/book/${book.id}`} />;
              })}
        </div>
      </div>
    </div>
  );
};

export default GroupedBookDisplay;
