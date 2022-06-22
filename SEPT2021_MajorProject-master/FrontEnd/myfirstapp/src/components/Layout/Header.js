import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { connect } from "react-redux";
import Login from "../UserManagement/Login";
import { logoutUser } from "../../actions/securityActions";

const Header = (props) => {
  const history = useHistory();
  const [search, setSearch] = useState("");
  const [searchCategory, setSearchCategory] = useState("title");
  const handleClick = () => history.push(`/search/${searchCategory}/${search}`);

  const handleChange = (event) => {
    setSearchCategory(event.target.value);
  };

  return (
    <div>
      <nav className="navbar navbar-expand-sm navbar-dark mb-4">
        <div className="container">
          <a className="navbar-brand" href="/">
            BOOKEROO
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#mobile-nav"
          >
            <span className="navbar-toggler-icon" />
          </button>
          <div className="collapse navbar-collapse" id="mobile-nav">
            <ul className="navbar-nav mr-auto"></ul>
            <form className="form-inline my-2 my-lg-0" style={{ flexWrap: "nowrap" }}>
              <input
                className="form-control mr-sm-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
              <select
                onChange={handleChange}
                className="form-select"
                aria-label="Default select example"
              >
                <option value="title">Title</option>
                <option value="category">Category</option>
                <option value="author">Author</option>
                <option value="isbn">ISBN</option>
              </select>
              <button onClick={handleClick} className="btn btn-outline-light my-2 my-sm-0 mx-2">
                Search
              </button>
            </form>


            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <a className="nav-link " href="/cart">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                       className="bi bi-cart" viewBox="0 0 16 16">
                    <path
                        d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                  </svg>
                </a>
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  href="#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Categories
                </a>
                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li>
                    <a className="dropdown-item" href="/category/non-fiction">
                      Non-Fiction
                    </a>
                  </li>
                  <li>
                    <a className="dropdown-item" href="/category/fiction">
                      Fiction
                    </a>
                  </li>

                  <li>
                    <a className="dropdown-item" href="/category/educational">
                      Educational
                    </a>
                  </li>
                  <li>
                    <hr className="dropdown-divider" />
                  </li>
                  <li>
                    <a className="dropdown-item" href="/category/free">
                      Free
                    </a>
                  </li>
                </ul>
              </li>
              {props.security.user.token.role === "ADMIN" && (
                <a className="nav-link mx-2" href="/admin/dashboard">
                  Admin
                </a>
              )}
              {(props.security.user.token.role === "CUSTOMER" ||
                props.security.user.token.role === "PUBLISHER") && (
                <a className="nav-link mx-2" href="/user/dashboard">
                  Profile
                </a>
              )}
              {!props.security.validToken ? (
                <>
                  <li className="nav-item">
                    <a className="nav-link " href="/register">
                      Sign Up
                    </a>
                  </li>

                  <li className="nav-item dropdown">
                    <a
                      className="nav-link dropdown-toggle"
                      id="navbarDarkDropdownMenuLink"
                      role="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      Login
                    </a>
                    <ul
                      className="dropdown-menu dropdown-menu-dark"
                      aria-labelledby="navbarDarkDropdownMenuLink"
                    >
                      <li>
                        <Login setUserRole={props.setUserRole} />
                      </li>
                    </ul>
                  </li>
                </>
              ) : (
                <li className="nav-item">
                  <button className="btn btn-primary" onClick={() => props.logoutUser(history)}>
                    Logout
                  </button>
                </li>
              )}
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
};

const mapStateToProps = (state) => ({
  security: state.security,
  book: state.book,
});

export default connect(mapStateToProps, { logoutUser })(Header);
