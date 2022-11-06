import React, {useEffect, useState} from "react";
import Header from "../../header/Header";
import SuccessCard from "../successCard/SuccessCard";
import CommentCard from "../commentCard/CommentCard";
import YellowButton from "../../button/YelloButton";
import {Link} from 'react-router-dom';
import SmallSuccessCard from "../successCard/SmallSuccessCard";
import axios from "axios";
import SmallMissionCard from "../missionCard/SmallMissionCard";

const SuccessDetailView = (props) => {
    const [post, setPost] = useState({});

    useEffect(() => {
        axios({
            url: `/posts/card/${sessionStorage.getItem('parentPostId')}`,
            method: 'get',
        }).then(res => {
            // console.log(res.data.data)
            res.data.data && setPost(res.data.data);
        })
    }, [])

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
            <div style={{paddingTop: '30px'}}>
                Success of this Mission
            </div>
            <div className={'cardGrid'}>
                <div style={{padding: '5px'}}>
                    <SmallMissionCard data={post}/>
                </div>
            </div>
        </div>
    </>
}

export default SuccessDetailView;