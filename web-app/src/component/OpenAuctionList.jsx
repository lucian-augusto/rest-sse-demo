import {useState, useEffect} from "react";

import AuctionViewComponent from "./AuctionViewComponent";

const OpenAuctionList = (props) => {
    const {} = props;
    const [auctions, setAuctions] = useState([]);

    const serverURL = process.env.REACT_APP_SERVER_URL;

    function updateAuctionList(newAuctions) {
        setAuctions(prevAuctions => {
            return [...prevAuctions, newAuctions];
        })
    }

    const getAuctionList = async () => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application/json" },
          };

        await fetch(
            `${serverURL}/auction/list`,
            requestOptions
            ).then(response => response.json())
            .then(data => {
                setAuctions(data);
            });
    }

    useEffect(() => {
        getAuctionList();
    }, []);

    return (
        <div>
        {auctions.map(auction => (
            <AuctionViewComponent
                code={auction.code}
                name={auction.name}
                seller={auction.seller}
                price={auction.price}
                startDateTime={auction.startDateTime}
                endDateTime={auction.endDateTime}
                description={auction.description}
            />
        ))
        }
        </div>
    );
};

export default OpenAuctionList;