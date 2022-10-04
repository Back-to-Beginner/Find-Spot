import React from "react";
import SuccessCard from "../successCard/SuccessCard";
import CommentCard from "../commentCard/CommentCard";
import YellowButton from "../../button/YelloButton";

const DetailView = (props) => {

    return <>
        <div className='background' >
            <div className='detailViewLocation'>
                <div style={{ padding: 0 }}>
                    <SuccessCard />
                </div>
                <div style={{ padding: 0 }}>
                    <CommentCard />
                </div>
            </div>
        </div>
    </>
}

export default DetailView;