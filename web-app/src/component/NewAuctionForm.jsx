import {useState} from "react";

import AuctionViewComponent from "./AuctionViewComponent";

const NewAuctionForm = (props) => {
    
    const serverURL = process.env.REACT_APP_SERVER_URL;

    const {userName} = props;
    
    const [hasSent, setHasSent] = useState(false);
    const [auctionCode, setAuctionCode] = useState("");
    const [productName, setProductName] = useState("");
    const [initialPrice, setInitialPrice] = useState("");
    const [durarion, setDuration] = useState("");
    const [description, setDescription] = useState("");
    const [auctionCreated, setAuctionCreated] = useState();

    const handleAuctionCodeChange = (event) => {
        setAuctionCode(event.target.value);
    };

    const handleProductNameChange = (event) => {
        setProductName(event.target.value);
    };

    const handleInitialPriceChange = (event) => {
        setInitialPrice(event.target.value);
    };

    const handleDurationChange = (event) => {
        setDuration(event.target.value);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
    
        if (auctionCode && auctionCode.trim() !== "") {
          const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              "auctionCode": auctionCode,
              "productName": productName,
              "sellerName": userName,
              "initialPrice" : initialPrice,
              "duration" : durarion,
              "description" : description
            })
          };
    
          await fetch(
            `${serverURL}/auction/create`,
            requestOptions
          ).then(response => response.json())
          .then(data => {
            setHasSent(true);
            setAuctionCreated(data);
          });
        }
      };

    return (
        <div>
            {hasSent ? (
                <div>
                    <h1>Auciton Created!</h1>
                    <AuctionViewComponent
                        code={auctionCreated.code}
                        name={auctionCreated.name}
                        seller={auctionCreated.seller}
                        price={auctionCreated.price}
                        startDateTime={auctionCreated.startDateTime}
                        endDateTime={auctionCreated.endDateTime}
                        description={auctionCreated.description}
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
                        Product Name:   
                        <input type="text" placeholder="name" value={productName} onChange={handleProductNameChange} />
                    </label>
                    <label>
                        Initial Price:   
                        <input type="text" placeholder="price" value={initialPrice} onChange={handleInitialPriceChange} />
                    </label>
                    <label>
                        Duration (in seconds):   
                        <input type="text" placeholder="duration" value={durarion} onChange={handleDurationChange} />
                    </label>
                    <label>
                        Product Description:   
                        <input type="text" placeholder="description" value={description} onChange={handleDescriptionChange} />
                    </label>
                </div>    
                <button className="submit-name" type="submit">OK</button>
            </form>
            )}
        </div>
    );
};

export default NewAuctionForm;