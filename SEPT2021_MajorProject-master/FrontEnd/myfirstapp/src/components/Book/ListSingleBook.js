import React from "react";

const ListSingleBook = (props) => {
  return (
    <>
      <tr>
        <td>{props.book.sellerName}</td>
        <td>{props.book.author}</td>
        <td>{props.book.title}</td>
        <td>{props.book.isbn}</td>
        <td>${props.book.price}</td>
        <td>
          {props.book.category.map((category, catKey) => {
            return (
              <span key={catKey} className="edit-book-categories border border-dark">
                {category}
              </span>
            );
          })}
        </td>
        <td>
          <button
            type="button"
            className="btn btn-primary btn-xs"
            onClick={() => props.onEditHandler(props.book)}
          >
            {props.enableEditBook ? "Cancel" : "Edit"}
          </button>
        </td>
      </tr>
    </>
  );
};

export default ListSingleBook;
