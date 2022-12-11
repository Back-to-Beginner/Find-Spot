import React, {useEffect, useState} from "react";
import Header from "../../header/Header";
import CommentCard from "../commentCard/CommentCard";
import YellowButton from "../../button/YelloButton";
import {Link} from 'react-router-dom';
import MissionCard from "../missionCard/MissionCard";
import axios from "axios";
import SmallSuccessCard from "../successCard/SmallSuccessCard";

const DetailView = () => {
    const [postList, setPostList] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        axios({
            url: `/api/v1/posts/parent/${sessionStorage.getItem('postId')}/child/s`,
            method: 'get',
        }).then(res => res.data.data && setPostList(res.data.data))
            .then(() => setIsLoading(false));
    }, []);

    return !isLoading && <>
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
            <Link to={'/upload/:postId'}>
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
            <div style={{paddingTop: '30px'}}>
                Success of this Mission
            </div>
            <div className={'cardGrid'}>
                {postList.map(card => <div style={{padding: '5px'}}>
                    <SmallSuccessCard data={card}/>
                </div>)}
            </div>
        </div>
    </>
}

export default DetailView;