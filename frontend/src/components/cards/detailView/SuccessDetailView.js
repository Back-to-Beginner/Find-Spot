import React from "react";
import Header from "../../header/Header";
import SuccessCard from "../successCard/SuccessCard";
import CommentCard from "../commentCard/CommentCard";
import YellowButton from "../../button/YelloButton";
import {Link} from 'react-router-dom';

const SuccessDetailView = (props) => {

    return <>
        <Header/>
        <div className='background'>
            {/*<div className='detailViewTitle'>*/}
            {/*    Detail Page*/}
            {/*</div>*/}
            <div className='detailViewLocation'>
                <div style={{padding: 0}}>
                    <SuccessCard/>
                </div>
                <div style={{padding: 0}}>
                    <CommentCard/>
                </div>
            </div>
            <Link to={`/result/*`}>
                <div style={{paddingTop: '50px'}}>
                    <YellowButton buttonName={'Show other Mission !'}/>
                </div>
            </Link>
            <Link to={'/'}>
                <div style={{padding: '10px'}}>
                    <div className='findSuccess'>
                        Close
                    </div>
                </div>
            </Link>
        </div>
    </>
}

export default SuccessDetailView;