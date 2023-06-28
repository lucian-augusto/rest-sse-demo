import {useState, useEffect} from "react";

import "./AuctionViewComponent.css";

const AuctionViewComponent = (props) => {
    const {code, name, seller, price, startDateTime, endDateTime, description} = props;

    return (
        <div className="card">
            <p>Product Code: {code}</p>
            <p>Product Name: {name}</p>
            <p>Seller: {seller}</p>
            <p>Price: ${price}</p>
            <p>Started: {startDateTime}</p>
            <p>Ends: {endDateTime}</p>
            <p>Description: {description}</p>
        </div>
        
    );
};

export default AuctionViewComponent;