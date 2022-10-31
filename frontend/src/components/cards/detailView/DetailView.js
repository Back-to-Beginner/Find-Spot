import React from "react";
import Header from "../../header/Header";
import CommentCard from "../commentCard/CommentCard";
import YellowButton from "../../button/YelloButton";
import {Link} from 'react-router-dom';
import MissionCard from "../missionCard/MissionCard";

const DetailView = (props) => {

    return <>
        <Header/>
        <div className='background'>
            {/*<div className='detailViewTitle'>*/}
            {/*    Detail Page*/}
            {/*</div>*/}
            <div className='detailViewLocation'>
                <div style={{padding: 0}}>
                    <MissionCard/>
                </div>
                <div style={{padding: 0}}>
                    <CommentCard/>
                </div>
            </div>
            <Link to={'/upload/:missionId'}>
                <div style={{paddingTop: '50px'}}>
                    <YellowButton buttonName={'Challenge!'}/>
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

export default DetailView;