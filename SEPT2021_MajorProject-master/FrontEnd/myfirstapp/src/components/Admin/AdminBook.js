import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { connect } from "react-redux";
import AddBook from "../Book/AddBook";
import { getAllBooks } from "../../actions/bookActions";
import EditBookList from "../Book/EditBookList";

export const AdminBook = (props) => {
  const { slug } = useParams();

  useEffect(() => {
    props.getAllBooks(slug);
  }, []);

  const [addBook, setAddBook] = useState(false);
  useEffect(() => {}, []);
  return (
    <div className="container ">
      <h1>Book Dashboard</h1>
      <button className="btn btn-primary mt-3" onClick={() => setAddBook(!addBook)}>
        {addBook ? "Hide Book" : "Add Book"}
      </button>
      <div className="mt-5">{addBook && <AddBook route="/admin/dashboard" />}</div>
      <hr />
      <EditBookList book={props.book} />
    </div>
  );
};

const mapStateToProps = (state) => {
  const { book } = state;
  return { book };
};

export default connect(mapStateToProps, { getAllBooks })(AdminBook);
