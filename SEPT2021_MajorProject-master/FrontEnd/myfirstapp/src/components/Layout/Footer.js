import React, { Component } from "react";

class Footer extends Component {
  render() {
    return (
      <footer className="page-footer font-small blue pt-4">
        <hr />
        <div className="row">
          <div className="col-sm-1 col-md-1 col-lg-1"></div>
          <div className="col text-center">
            <a href="/about">About Us</a>
            <span className="footer-spacing"></span>
            <a href="/contact">Contact</a>
            <span className="footer-spacing"></span>
            <a href="/privacy">Privacy Policy</a>
          </div>
          <div className="col-sm-1 col-md-1 col-lg-1"></div>
        </div>
        <div className="footer-copyright text-center py-3">
          © 2020 Copyright:
          <a href="/">Bookeroo.com.au</a>
        </div>
      </footer>
    );
  }
}
export default Footer;
