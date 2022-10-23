import React, {useEffect, useLayoutEffect, useState} from "react";
import Header from "../../components/header/Header";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import YellowButton from "../../components/button/YelloButton";
import DetailView from "../../components/cards/detailView/DetailView";
import axios from "axios";
import {Link} from 'react-router-dom';

const MainPage = () => {
    const [mission, setMission] = useState([]);
    const [success, setSuccess] = useState([]);
    const missionList = [];

    useLayoutEffect(() => {
        axios({
            method: 'get',
            url: '/posts/types/m'
        }).then(res => {
            if (res.data.data[0]) {
                // res.data.data.map(m => {
                //     missionList.push(m)
                // });
                // setMission(missionList.pop);
                sessionStorage.setItem("missionId", res.data.data[0].id);
                setMission(res.data.data);
                getSuccess();
            }
        })
    }, []);

    const getSuccess = () => {
        axios({
            method: 'get',
            url: `/posts/parent/${sessionStorage.getItem("missionId")}/child/s`
        }).then(res => {
            console.log(res.data.data[0])
            res.data.data && setSuccess(res.data.data[0]);
        })
    }

    const getNextMission = () => {
        const tempMission = missionList.pop;
        setMission(tempMission);
        missionList.push(tempMission);
        getSuccess()
    }

    return (<>
        <Header/>
        <div className="mainPageWave">
            <div className='mainPageTitle'>
                New Success
            </div>
            <div className="cardViewLocation">
                <div className="previousArrow">
                    &#5176;
                </div>
                <div style={{padding: '10px'}}>
                    <SmallMissionCard data={mission[0]}/>
                </div>
                <div style={{padding: '10px'}}>
                    <SmallSuccessCard data={success}/>
                </div>
                <div className="postArrow"
                    // onClick={() => getNextMission()}
                >
                    &#5171;
                </div>
            </div>

            <div className="mainBoxPosition">
                <div style={{paddingTop: '50px'}}>
                    <Link to={`/upload/${sessionStorage.getItem('missionId')}`}>
                        <YellowButton buttonName={'Challenge this Mission'}/>
                    </Link>
                </div>
                <div style={{padding: '10px'}}>
                    <Link to={'/result'}>
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