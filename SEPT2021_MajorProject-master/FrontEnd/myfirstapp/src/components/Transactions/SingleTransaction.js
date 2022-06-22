import React from "react";

export const SingleTransaction = (props) => {
  return (
    <>
      {props.order.bookDetails.map((book, bkey) => {
        return (
          <tr>
            <td>{props.username}</td>
            <td>{props.order.id}</td>
            <td>{book.title}</td>
            <td>${book.price}</td>
            <td>{book.sellerName}</td>
          </tr>
        );
      })}
    </>
  );
};

export default SingleTransaction;
