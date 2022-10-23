import React, { useState } from "react";
import Header from "../../header/Header";
import SuccessCard from "../successCard/SuccessCard";
import CommentCard from "../commentCard/CommentCard";
import YellowButton from "../../button/YelloButton";
import { Link } from 'react-router-dom';

const SuccessDetailView = (props) => {
    const [detailView, setDetailView] = useState(false);



    return <>
        <Header />
        <div className='background' >
            <div className='detailViewTitle'>
                SuccessDetailView Page
            </div>
            <div className='detailViewLocation'>

                <div style={{ padding: 0 }}>
                    <SuccessCard />
                </div>
                <div style={{ padding: 0 }}>
                    <CommentCard />
                </div>
            </div>
            <Link to={'/upload/:missionId'}>
            <div style={{ paddingTop: '50px' }} onClick={() => setDetailView(false)}>
                <YellowButton buttonName={'Challenge!'} />
            </div>
            </Link>
            <Link to={'/'}>
            <div style={{ padding: '10px' }} onClick={() => setDetailView(false)}>
                <div className='findSuccess'>
                    Close
                </div>
            </div>
            </Link>
        </div>
    </>
}

export default SuccessDetailView;