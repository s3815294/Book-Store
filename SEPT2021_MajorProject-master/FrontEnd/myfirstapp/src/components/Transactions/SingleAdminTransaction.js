import {React,useEffect} from "react";

const SingleAdminTransaction = (props) => {    
    
    return (              
        <>
            <tr>
                <td>{props.username}</td>
                <td>{props.id}</td>
                <td>{props.title}</td>
                <td>${props.price}</td>
                <td>{props.sellerName}</td>
            </tr>                   
        </>                
    );
};

export default SingleAdminTransaction;
