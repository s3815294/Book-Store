import React from "react";

export const AboutUs = () => {
  return (
    <div>
      <div className="about-section">
        <h1>BOOKEROO</h1>
        <p>
          Here we help you get your favourite books directly to you. You can search for books by
          name, author, ISBN and category. add review for a specific book that has been bought or
          specific user that had interaction with. you can sell your own used book here and make
          some money too.
        </p>
        <p>
          If you are a publisher you can create an publisher account with us and add books in bulk.
          You can also buy books in bulk if you own a business(If you are verified as a business
          user).{" "}
        </p>

        <h3>Who are we?</h3>
        <p>
          This website is created by the team of 4 students of Software Engineering Process and
          tools. We have worked together in a team to bring out this website for our project.{" "}
        </p>
      </div>

      <center>
        <b>
          <h2 className="text-align:center">Our Team</h2>
        </b>
      </center>
      <div className="row">
        <div className="column">
          <div className="card">
            <img src="Alicia.jpg" alt="Alicia" />
            <div className="container">
              <h2>Alicia Hallal </h2>
              <p className="title">Member in SEPT Thurs 10:30 Group 1</p>

              <p>s3811836@student.rmit.edu.au</p>
              <p>
                <form action="/contact" className="inline">
                  <button className="button">Contact</button>
                </form>
              </p>
            </div>
          </div>
        </div>

        <div className="column">
          <div className="card">
            <img src="Maanav.jpg" alt="Manav" />
            <div className="container">
              <h2>Manav Makkar</h2>
              <p className="title">Member in SEPT Thurs 10:30 Group 1</p>
              <p>s3815294@student.rmit.edu.au</p>
              <p>
                <form action="/contact" className="inline">
                  <button className="button">Contact</button>
                </form>
              </p>
            </div>
          </div>
        </div>

        <div className="column">
          <div className="card">
            <img src="liam.jpg" alt="Liam" />
            <div className="container">
              <h2>Liam Hallinan </h2>
              <p className="title">Member in SEPT Thurs 10:30 Group 1</p>

              <p>s3377569@student.rmit.edu.au</p>
              <p>
                <form action="/contact" className="inline">
                  <button className="button">Contact</button>
                </form>
              </p>
            </div>
          </div>
        </div>
        <div className="column">
          <div className="card">
            <img src="Haarkirat.jpg" alt="Harkirat" />
            <div className="container">
              <h2>Harkirat Singh</h2>
              <p className="title">Member in SEPT Thurs 10:30 Group 1</p>

              <p>s3820700@student.rmit.edu.au</p>
              <p>
                <form action="/contact" className="inline">
                  <button className="button">Contact</button>
                </form>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default AboutUs;
