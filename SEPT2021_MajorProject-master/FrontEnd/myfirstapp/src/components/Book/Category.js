import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { connect } from "react-redux";
import { getBooksByCategory } from "../../actions/bookActions";

import GroupedBookDisplay from "./GroupedBookDisplay";

const Category = (props) => {
  const { category } = useParams();

  useEffect(() => {
    props.getBooksByCategory(category);
  }, []);

  return <GroupedBookDisplay title={category} book={props.book} />;
};

const mapStateToProps = (state) => {
  const { book } = state;
  return { book };
};

export default connect(mapStateToProps, { getBooksByCategory })(Category);
