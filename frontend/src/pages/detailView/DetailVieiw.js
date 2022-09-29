import React from "react";
import MissionCard from "../../components/cards/missionCard/MissionCard";
import CommentCard from "../../components/cards/commentCard/CommentCard";
import YellowButton from "../../components/button/YelloButton";

const DetailView = () => {

    return <>
        <div className='background'>
            <div className='missionDetailViewEdge'>
                <MissionCard />
            </div>
            <div className='CommentViewEdge'>
                <CommentCard />
            </div>
            <div className="detailBoxPosition">
                <YellowButton buttonName={'Challenge!'} />
            </div>
            <div className='findSuccess'>
                Find Success
            </div>
        </div>
    </>
}

export default DetailView;