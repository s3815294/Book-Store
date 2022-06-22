import React, {useState} from "react";
import "./App.css";
import Header from "./components/Layout/Header";
import Footer from "./components/Layout/Footer";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Search from "./components/Book/Search";
import Book from "./components/Book/Book";
import EditBook from "./components/Book/EditBook";
import Category from "./components/Book/Category";
import SecuredRoute from "./securityUtils/SecureRoute";
import AdminDashboard from "./components/Admin/AdminDashboard";
import UserDashboard from "./components/UserManagement/UserDashboard";
import AboutUs from "./components/Layout/AboutUs";
import Contact from "./components/Layout/Contact";
import Privacy from "./components/Layout/Privacy";
import Cart from "./components/Cart/Cart";
import OrderSuccess from "./components/Order/OrderSuccess";
import { initCart } from "./actions/cartActions";

import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logoutUser } from "./actions/securityActions";
import AddReview from "./components/Book/AddReview";
import UserReview from "./components/UserManagement/UserReview";

const App = () => {
  const jwtToken = localStorage.getItem("token");
  const [cartInit, setCartInit] = useState(false);

  if(!cartInit) {
    initCart();
    setCartInit(true);
  }

  initCart();
  if (jwtToken) {
    setJWTToken(jwtToken);
    const decoded_jwtToken = jwt_decode(jwtToken);
    store.dispatch({
      type: SET_CURRENT_USER,
      payload: decoded_jwtToken,
    });

    const currentTime = Date.now() / 1000;
    if (decoded_jwtToken.exp < currentTime) {
      store.dispatch(logoutUser());
      window.location.href = "/";
    }
  }

  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          {
            //Public Routes
          }

          <Route exact path="/" component={Landing} />
          <Route exact path="/cart" component={Cart} />
          <Route exact path="/checkout-complete" component={OrderSuccess} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/search/:category/:slug" component={Search} />
          <Route exact path="/book/:slug" component={Book} />
          <Route exact path="/category/:category" component={Category} />
          <Route exact path="/about" component={AboutUs} />
          <Route exact path="/privacy" component={Privacy} />
          <Route exact path="/contact" component={Contact} />
          {
            //Private Routes
          }
          {/* <SecuredRoute exact path="/dashboard" component={Dashboard} />
          <SecuredRoute exact path="/addPerson" component={AddPerson} /> */}
          <SecuredRoute exact path="/user/review/:bookId" component={AddReview} />
          <SecuredRoute exact path="/user/userReview/:userId" component={UserReview} />
          <SecuredRoute exact path="/admin/dashboard" component={AdminDashboard} />
          <SecuredRoute exact path="/admin/dashboard/edit/:id" component={EditBook} />
          <SecuredRoute exact path="/user/dashboard" component={UserDashboard} />
        </div>
        <Footer />
      </Router>
    </Provider>
  );
};
export default App;
