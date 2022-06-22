import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {getAllBooks} from "../../actions/bookActions";

const OrderSuccess = (props) => {

    // function noOrder() {
    //     history.push("/path/to/push");
    // }

    return(
        <div>
            {props.security.user.token.username == null ?
                {}
                :
                <>
                <div>hey order success</div>
                </>}
        </div>
    );

}

const mapStateToProps = (state) => {
    const {security} = state;
    const {book} = state;
    return {book, security};
};

export default connect(mapStateToProps, {getAllBooks})(OrderSuccess);