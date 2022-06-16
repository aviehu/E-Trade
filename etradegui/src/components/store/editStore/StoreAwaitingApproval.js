import {useEffect, useState} from "react";
import get from "../../util/get";
import Button from "@mui/material/Button";
import post from "../../util/post";

export default function StoreAwaitingApproval({storeName}) {
    const [awaitingApproval, setAwaitingApproval] = useState(null)

    const getAwaiting = async () => {
        const res = await get(`stores/approveowner/getwaiting/${storeName}`)
        const ans = await res.json()
        if(ans.val) {
            setAwaitingApproval(ans.val)
        }
    }
    useEffect(() => {
        getAwaiting()
    }, [])

    const calcStatus = (newOwner) => {
        const awaitings = awaitingApproval[newOwner];
        const count = Object.keys(awaitings).length
        const hasApproved = Object.keys(awaitings).reduce((prev, curr) => {
            if(awaitings[curr]) {
                return prev + 1
            }
            return prev
        } , 0)
        if(hasApproved === count) {
            return 'Approved'
        }
        return `${hasApproved} / ${count}`
    }

    async function handleSubmit(approved, ownersName) {
        const body = {
            appointee: ownersName,
            approve: approved
        }
        const res = await post(body, `stores/approveowner/approve/${storeName}`)
        const ans = await res.json()
        if(ans.val) {
            await getAwaiting()
        }
    }

    const renderAwaiting = () => {
        const userName = localStorage.getItem("userName");
        return <ul className='stores'>
            {Object.keys(awaitingApproval).map((newOwner) => (<li className='store'>
                <div className='topStore' >
                    <div>
                        <h4 className='title' >Awaiting for approve: {newOwner}</h4>
                    </div>
                    <div>
                        <h5 className='title' >Status: {calcStatus(newOwner)}</h5>
                    </div>
                </div>
                <div className="store-footer">
                    <Button disabled={awaitingApproval[newOwner][userName]} onClick={() => handleSubmit(true, newOwner)}>Approve</Button>
                    <Button disabled={awaitingApproval[newOwner][userName]} onClick={() => handleSubmit(false, newOwner)}>Reject</Button>
                </div>
            </li>))}
        </ul>
    }

    return <div>
        {awaitingApproval ? renderAwaiting() : null}
    </div>
}
