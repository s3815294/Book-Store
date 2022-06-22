import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {Document, Page} from "react-pdf/dist/esm/entry.webpack";
import {connect} from "react-redux";
import {getSingleBook} from "../../actions/bookActions";

import Review from "./Review";
import AddBookToCart from "../Cart/AddBookToCart";

const Book = (props) => {
    const {slug} = useParams();
    const pdfPageNumber = 1;

    useEffect(() => {
        props.getSingleBook(slug);
    }, []);

    return (
        <div className="container">
            {props.book.activeBook.id && (
                <div className="purchase__container">
                    <h1 className="purchase__body-title">{props.book.activeBook.title}</h1>
                    <div className="purchase__body-content">
                        <p>Author: {props.book.activeBook.author}</p>
                        <p>Price: ${props.book.activeBook.price}</p>
                        <p>ISBN: {props.book.activeBook.isbn}</p>
                        <p>Categories</p>
                        <div className="mb-5">
                            {props.book.activeBook.category &&
                            props.book.activeBook.category.map((category, catKey) => {
                                return (
                                    <span key={catKey} className="book__content-text-category">{category}</span>
                                );
                            })}
                            <AddBookToCart book={props.book.activeBook} />

                        </div>
                    </div>
                    <div className="purchase__body-image">
                        <img
                            alt={props.book.activeBook.title}
                            src={`https://sept-bookeroo.s3.ap-southeast-2.amazonaws.com/image/${props.book.activeBook.id}.${props.book.activeBook.imageType}`}
                        />
                        <Document
                            className="purchase__body-pdf"
                            file={`https://sept-bookeroo.s3.ap-southeast-2.amazonaws.com/pdf/${props.book.activeBook.id}.pdf`}
                        >
                            <Page pageNumber={pdfPageNumber}/>
                        </Document>
                    </div>
                </div>
            )}
            <Review/>
        </div>
    )
};

const mapStateToProps = (state) => {
    const {book} = state;
    return {book};
};

export default connect(mapStateToProps, {getSingleBook})(Book);
