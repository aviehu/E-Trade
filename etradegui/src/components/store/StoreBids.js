import {useEffect, useState} from "react";
import Button from "@mui/material/Button";
import * as React from "react";
import get from "../get";
import post from "../post";

export default function StoreBids({storeName}) {

    const [bids, setBids] = useState(null)
    const userName = localStorage.getItem("userName");


    const getBids = async () => {
        const res = await get(`stores/bids/${storeName}`)
        const ans = await res.json();
        if(ans.val) {
            setBids(ans.val)
        }
    }

    const handleSubmit = async (approve, bidId) => {
        const body = {
            bidId: bidId,
            approve: approve
        }
        await post(body, `stores/reviewbid/${storeName}`)
        await getBids()
    }

    useEffect(() => {
        getBids()
    }, [])

    function renderBids() {
        return <ul className='stores'>
            {bids.map((bid) => (<li className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >Product Name: {bid.productName}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Bid Amount: {bid.amount}$</h5>
                    </div>
                    <div>
                        <h5 className='title' >Bidder's Name: {bid.biddersName}</h5>
                    </div>
                </div>
                <div className="store-footer">
                    <Button disabled={(bid.awaitingApprove[userName] || bid.rejected)} onClick={() => handleSubmit(true, bid.bidId)}>Approve</Button>
                    <Button disabled={(bid.awaitingApprove[userName] || bid.rejected)} onClick={() => handleSubmit(false, bid.bidId)}>Reject</Button>
                </div>
            </li>))}
        </ul>
    }
    return (<div>
        {bids ? renderBids() : <h5>No Bids For Now...</h5>}
    </div>)
}
