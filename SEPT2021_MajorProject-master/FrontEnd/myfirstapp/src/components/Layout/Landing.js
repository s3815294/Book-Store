import React, { useEffect } from "react";
import { connect } from "react-redux";
import { useParams } from "react-router-dom";
import { getAllBooks } from "../../actions/bookActions";
import GroupedBookDisplay from "../Book/GroupedBookDisplay";

const Landing = (props) => {
  const { slug } = useParams();

  useEffect(() => {
    props.getAllBooks(slug);
  }, []);

  return (
    <div className="landing">
      <GroupedBookDisplay title="Featured Books" book={props.book} />
      <hr />
      <GroupedBookDisplay title="Used Books" book={props.book} />
    </div>
  );
};

const mapStateToProps = (state) => {
  const { book } = state;
  return { book };
};

export default connect(mapStateToProps, { getAllBooks })(Landing);
