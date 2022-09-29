import React from "react";
import Header from "../../components/header/Header";
import MissionCard from "../../components/cards/missionCard/MissionCard";
import SuccessCard from "../../components/cards/successCard/SuccessCard";
import YellowButton from "../../components/button/YelloButton";

const MainPage = () => {

    return <>
        <div className="mainPageWave">
            <Header />
            <div className='mainPageTitle'>
                New Success
            </div>
            <div className="missionViewEdge">
                <MissionCard />
            </div>
            <div className="successViewEdge">
                <SuccessCard />
            </div>
            <div className="postArrow">
            
            </div>
            <div className="mainBoxPosition">
                <YellowButton buttonName={'Challenge this Mission'} />
            </div>
            <div className='findOtherSpot'>
                Find other Spot
            </div>
        </div>
    </>
};

export default MainPage;