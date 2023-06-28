import {useState} from "react";

import AuctionViewComponent from "./AuctionViewComponent";

const NewBidForm = (props) => {
    
    const serverURL = process.env.REACT_APP_SERVER_URL;

    const {userName} = props;
    
    const [hasSent, setHasSent] = useState(false);
    const [auctionUpdated, setAuctionUpdated] = useState();
    
    const [auctionCode, setAuctionCode] = useState("");
    const [bidOffer, setBidOffer] = useState("");

    const handleAuctionCodeChange = (event) => {
        setAuctionCode(event.target.value);
    };
    
    const handleBidOfferChange = (event) => {
        setBidOffer(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
    
        if (auctionCode && auctionCode.trim() !== "") {
          const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              "auctionCode": auctionCode,
              "userName": userName,
              "bidOffer" : bidOffer
            })
          };
    
          await fetch(
            `${serverURL}/auction/bid`,
            requestOptions
          ).then(response => response.json())
          .then(data => {
            setHasSent(true);
            setAuctionUpdated(data);
          });
        }
      };

    return (
        <div>
            {hasSent ? (
                <div>
                    <h1>Bid Accepted!</h1>
                    <AuctionViewComponent
                        code={auctionUpdated.code}
                        name={auctionUpdated.name}
                        seller={auctionUpdated.seller}
                        price={auctionUpdated.price}
                        startDateTime={auctionUpdated.startDateTime}
                        endDateTime={auctionUpdated.endDateTime}
                        description={auctionUpdated.description}
            /> 
                </div>
            ) : (
            <form className="form" onSubmit={handleSubmit}>
                <div className="segment">
                    <h1>Create New Auction</h1>
                </div>
                <div>
                    <label>
                        Product Code:   
                        <input type="text" placeholder="code" value={auctionCode} onChange={handleAuctionCodeChange} />
                    </label>
                    <label>
                        Bid Offer:   
                        <input type="text" placeholder="price" value={bidOffer} onChange={handleBidOfferChange} />
                    </label>
                </div>    
                <button className="submit-name" type="submit">OK</button>
            </form>
            )}
        </div>
    );
};

export default NewBidForm;