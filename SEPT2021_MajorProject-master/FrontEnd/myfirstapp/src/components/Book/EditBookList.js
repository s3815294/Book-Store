import React, { useState, useEffect } from "react";
import ListSingleBook from "./ListSingleBook";
import EditBook from "./EditBook";
import { CSVLink } from "react-csv";

const EditBookList = (props) => {
  const [editBook, setEditBook] = useState("");
  const [enableEditBook, setEnableEditBook] = useState(false);
  const [csvData, setCsvData] = useState([]);

  function onEditHandler(book) {
    setEditBook(book);
    setEnableEditBook(!enableEditBook);
  }

  const generateData = () => {
    let data = [["seller", "author", "title", "isbn", "price", "categories"]];
    let bookDetail = [];
    for (let i = 0; i < props.book.allBooks.length; i++) {
      bookDetail.push(props.book.allBooks[i].sellerName);
      bookDetail.push(props.book.allBooks[i].author);
      bookDetail.push(props.book.allBooks[i].title);
      bookDetail.push(props.book.allBooks[i].isbn);
      bookDetail.push(props.book.allBooks[i].price);
      for (let j = 0; j < props.book.allBooks[i].category.length; j++) {
        bookDetail.push(props.book.allBooks[i].category[j]);
      }
      data.push(bookDetail);
      bookDetail = [];
    }

    setCsvData(data);
  };

  useEffect(() => {
    generateData();
  }, [props.book]);

  return (
    <div className="container">
      {enableEditBook && <EditBook info={editBook} route="/admin/dashboard" />}
      <CSVLink className="btn btn-primary mb-2" data={csvData}>
        Download Book CSV
      </CSVLink>
      <h3 className="text-center">All Books</h3>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th scope="col">Seller</th>
            <th scope="col">Author</th>
            <th scope="col">Title</th>
            <th scope="col">ISBN</th>
            <th scope="col">Price</th>
            <th scope="col">Categories</th>
            <th scope="col">EDIT</th>
          </tr>
        </thead>
        <tbody>
          {props.book.allBooks.map((book, key) => {
            return (
              <ListSingleBook
                onEditHandler={onEditHandler}
                enableEditBook={enableEditBook}
                book={book}
                key={key}
              />
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default EditBookList;
