import { useEffect, useRef, useState } from "react";
import "./styles.css";

import NewAuctionForm from "./component/NewAuctionForm";
import OpenAuctionList from "./component/OpenAuctionList";
import NewBidForm from "./component/NewBidForm";

export default function App() {
  
  const serverURL = process.env.REACT_APP_SERVER_URL;
  
  const [user, setUser] = useState("");
  const [nameInputValue, setNameInputValue] = useState("");
  const [page, setPage] = useState("main");
  
  const handleChange = (event) => {
    setNameInputValue(event.target.value);
  };

  const handleMainMenu = () => {
    setPage("main");
  }

  const handleCreateNewAuction = () => {
    setPage("new");
  }

  const handleListOpenAuctions = () => {
    setPage("list");
  }

  const handleNewBid = () => {
    setPage("bid");
  }
  
  const handleNameSubmit = async (event) => {
    event.preventDefault();

    if (nameInputValue && nameInputValue.trim() !== "") {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          "name": nameInputValue
        })
      };

      await fetch(
        `${serverURL}/user/new`,
        requestOptions
      ).then(response => response.json())
      .then(data => {
        setUser(data.name);
      });
    }
  };
  
  return (
    <div className="App">
      {user ? (
        <div>
          <div className="entered-name-div">
            Your name: <b>{user}</b>
          </div>
          <div>
            <button onClick={handleMainMenu}>Main Menu</button>
          </div>
          {page === "main" ? (
          <div>
            <table>
              <tr>
                <td>
                  <button onClick={handleCreateNewAuction}>Create new auction</button>
                </td>
                <td>
                  <button onClick={handleListOpenAuctions}>Create list active auctions</button>
                </td>
                <td>
                  <button onClick={handleNewBid}>New Bid</button>
                </td>
              </tr>
            </table>
          </div>
          ) : (page === "new" ? (
            <NewAuctionForm
              userName={user}
            />
          ) : (page === "list" ? (
            <OpenAuctionList />
          ) : 
            <NewBidForm
              userName={user}
            />
          )
          )
          }
        </div>
      ) : (
        <div className="name-form-div">
          <form className="form" onSubmit={handleNameSubmit}>
            <div className="segment">
              <h1>Welcome to the Auction House!</h1>
            </div>
            <label>
              <input
                type="text"
                placeholder="Name"
                value={nameInputValue}
                onChange={handleChange}
              />
            </label>
            <button className="submit-name" type="submit">OK</button>
          </form>
        </div>
      )}
    </div>
  );
}
