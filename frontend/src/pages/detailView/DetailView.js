import React from "react";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SuccessCard from "../../components/cards/successCard/SuccessCard";
import CommentCard from "../../components/cards/commentCard/CommentCard";
import YellowButton from "../../components/button/YelloButton";

const DetailView = () => {

    return <>
        <div className='background' >
            <div className='detailViewLocation'>
                <div style={{ padding: 0 }}>
                    <SuccessCard /> {/*SmallSuccessCard */}
                </div>
                <div style={{ padding: 0 }}>
                    <CommentCard />  {/*SmallCommentCard */}
                </div>
            </div>
            <div className="mainBoxPosition">
                <div style={{ paddingTop: '50px' }}>
                    <YellowButton buttonName={'Challenge!'} />
                </div>
                <div style={{ padding: '10px' }}>
                    <div className='findSuccess'>
                        Find Success
                    </div>
                </div>
            </div>


        </div>
    </>
}

export default DetailView;