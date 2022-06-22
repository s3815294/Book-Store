import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import { getAllOrders } from "../../actions/orderActions";
import { CSVLink } from "react-csv";

const AdminTransactions = (props) => {
  const [csvData, setCsvData] = useState([]);

  const getOrderData = () => {
    props.getAllOrders(props.security.user.token.role.toLowerCase());
  };

  const generateData = () => {
    let data = [["order_id", "username", "user_id", "title", "price", "seller_name"]];

    for (let i = 0; i < props.order.orders.length; i++) {
      let bookDetail = [];
      for (let j = 0; j < props.order.orders[i].bookDetails.length; j++) {
        bookDetail.push(props.order.orders[i].id);
        bookDetail.push(props.order.orders[i].orderAccount.username);
        bookDetail.push(props.order.orders[i].orderAccount.id);
        bookDetail.push(props.order.orders[i].bookDetails[j].title);
        bookDetail.push(props.order.orders[i].bookDetails[j].price);
        bookDetail.push(props.order.orders[i].bookDetails[j].sellerName);
        data.push(bookDetail);
        bookDetail = [];
      }
    }
    setCsvData(data);
  };

  useEffect(() => {
    getOrderData();
  }, []);

  useEffect(() => {
    generateData();
  }, [props.order]);

  return (
    <div className="container">
      <h3 className="text-center">All Transactions</h3>
      <CSVLink className="btn btn-primary mb-2" data={csvData}>
        Download Transaction CSV
      </CSVLink>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th scope="col">Order Id</th>
            <th scope="col">Book Title</th>
            <th scope="col">Book Price</th>
            <th scope="col">Seller Name</th>
          </tr>
        </thead>
        <tbody>
          {props.order.orders &&
            props.order.orders.map((order, key) => {
              return order.bookDetails.map((book, bkey) => {
                return (
                  <tr key={bkey}>
                    <td>{order.id}</td>
                    <td>{book.title}</td>
                    <td>{book.price}</td>
                    <td>{book.sellerName}</td>
                  </tr>
                );
              });
            })}
        </tbody>
      </table>
    </div>
  );
};

const mapStateToProps = (state) => {
  const { order } = state;
  const { security } = state;
  const { messages } = state;
  return { order, messages, security };
};

export default connect(mapStateToProps, { getAllOrders })(AdminTransactions);
