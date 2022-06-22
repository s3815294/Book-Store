import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { connect } from "react-redux";
import { getBooksBySearch } from "../../actions/bookActions";

import GroupedBookDisplay from "./GroupedBookDisplay";

const Search = (props) => {
  const { slug } = useParams();
  const { category } = useParams();

  useEffect(() => {
    props.getBooksBySearch(category, slug);
  }, []);

  return (
    <div className="container mt-5">
      {props.errors.data && <h3>{props.errors.data}</h3>}
      <GroupedBookDisplay
        title={`Showing search results for... ${slug}`}
        book={props.book}
        type={"search"}
      />
    </div>
  );
};

const mapStateToProps = (state) => {
  const { book } = state;
  const { errors } = state;
  return { book, errors };
};

export default connect(mapStateToProps, { getBooksBySearch })(Search);
