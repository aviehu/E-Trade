import {useEffect, useState} from "react";
import Button from "@mui/material/Button";
import * as React from "react";
import get from "../../util/get";
import post from "../../util/post";
import CounterBid from "./CounterBid";

export default function StoreBids({storeName}) {

    const [bids, setBids] = useState(null)
    const [counterDialog, setCounterDialog] = useState(false);
    const [bidId, setBidId] = useState(0);
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

    const handleCounter = (bidId) => {
        setBidId(bidId)
        setCounterDialog(true)
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
                    {bid.rejected ?
                        <div>
                            <h5 className='title' >The bid was rejected</h5>
                        </div> : null
                    }
                </div>
                <div className="store-footer">
                    <Button disabled={(bid.awaitingApprove[userName] || bid.rejected)} onClick={() => handleSubmit(true, bid.bidId)}>Approve</Button>
                    <Button disabled={(bid.awaitingApprove[userName] || bid.rejected)} onClick={() => handleSubmit(false, bid.bidId)}>Reject</Button>
                    <Button disabled={(bid.awaitingApprove[userName] || bid.rejected)} onClick={() => handleCounter(bid.bidId)}>Counter Offer</Button>
                </div>
            </li>))}
        </ul>
    }
    return (<div>
        {bids ? renderBids() : <h5>No Bids For Now...</h5>}
        {counterDialog ? <CounterBid bidId={bidId} setOpen={setCounterDialog} open={counterDialog} storeName={storeName}></CounterBid> : null}
    </div>)
}
