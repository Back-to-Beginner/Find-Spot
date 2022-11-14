import React, {useEffect, useLayoutEffect, useState} from "react";
import Header from "../../components/header/Header";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import YellowButton from "../../components/button/YelloButton";
import axios from "axios";
import {Link} from 'react-router-dom';

const MainPage = () => {
    const [mission, setMission] = useState({});
    const [success, setSuccess] = useState([]);
    const [index, setIndex] = useState(0);
    const [missionList, setMissionList] = useState([]);

    useLayoutEffect(() => {
        axios({
            method: 'get',
            url: '/posts/types/m'
        }).then(misRes => {
            if (misRes.data.data[0] && misRes.data.data[0].type === 'm') {
                setMissionList(misRes.data.data);
                setMission(misRes.data.data[0]);
                getSuccess(misRes.data.data[0].id);
                sessionStorage.setItem("postId", misRes.data.data[0].id);
            }
        })
    }, []);

    const getSuccess = (index) => {
        console.log(11111);
        axios({
            method: 'get',
            url: `/posts/parent/${index}/child/s`
        }).then(sucRes => {
            sucRes.data.data && setSuccess(sucRes.data.data[0]);
        })
    }

    const setStorage = () => {
        sessionStorage.setItem("imageSrc", mission.imagePath);
        sessionStorage.setItem("content", mission.content);
        sessionStorage.setItem("postId", mission.id);
    }
    const getNextMission = () => {
        const i = missionList.length > index + 1? index + 1 : 0;
        setIndex(i);
        setMission(missionList.at(i));
        getSuccess(missionList.at(i).id);
    }

    const getPrevMission = () => {
        const i = 0 > index - 1 ? index - 1 : missionList.length - 1;
        setIndex(i);
        setMission(missionList.at(i));
        getSuccess(missionList.at(i).id);
    }

    return (<>
        <Header/>
        <div className="mainPageWave">
            <div className='mainPageTitle'>
                ✅ New Success
            </div>
            <div className="cardViewLocation">
                <div className="previousArrow" onClick={getPrevMission}>
                    &#5176;
                </div>
                <div style={{padding: '10px'}}>
                    <SmallMissionCard data={mission}/>
                </div>
                <div style={{padding: '10px'}}>
                    <SmallSuccessCard data={success}/>
                </div>
                <div className="postArrow" onClick={getNextMission}>
                    &#5171;
                </div>
            </div>

            <div className="mainBoxPosition">
                <div style={{paddingTop: '50px'}} onClick={setStorage}>
                    <Link to={`/upload/${sessionStorage.getItem('postId')}`}>
                        <YellowButton buttonName={'Challenge this Mission'}/>
                    </Link>
                </div>
                <div style={{padding: '10px'}}>
                    <Link to={'/collection'} onClick={setStorage}>
                        <div className='findOtherSpot'>
                            Find other Spot
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    </>)
};

export default MainPage;