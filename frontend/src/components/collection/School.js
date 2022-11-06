import React, {useState} from 'react';
import profileIcon from '../../images/user.png'
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";

const School = () => {

    const schoolMission = [
        {image: profileIcon, content: '안양대학교 1'},
        {image: profileIcon, content: '안양대학교 2'},
        {image: profileIcon, content: '안양대학교 3'},
        {image: profileIcon, content: '안양대학교 4'},
        {image: profileIcon, content: '안양대학교 5'},
        {image: profileIcon, content: '안양대학교 6'},
    ]

    return <>
        <div className={'schoolCardGrid'}>
            {schoolMission.map(card =>
                <div style={{padding: '10px'}}>
                    <SmallSuccessCard image={card.image} content={card.content}/>
                </div>
            )}
        </div>
    </>

}

export default School;