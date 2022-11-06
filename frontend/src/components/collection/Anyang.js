import React, {useState} from 'react';
import profileIcon from '../../images/user.png'
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";

const Anynag = () => {

    const anynagMission = [
        {image: profileIcon, content: '안양8경 1'},
        {image: profileIcon, content: '안양8경 2'},
        {image: profileIcon, content: '안양8경 3'},
        {image: profileIcon, content: '안양8경 4'},
        {image: profileIcon, content: '안양8경 5'},
        {image: profileIcon, content: '안양8경 6'},
        {image: profileIcon, content: '안양8경 7'},
        {image: profileIcon, content: '안양8경 8'},
    ]

    return <>
    <div className={'AnyangCardGrid'}>
                    {anynagMission.map(card =>
                        <div style={{padding: '10px'}}>
                            <SmallSuccessCard image={card.image} content={card.content}/>
                        </div>
                    )}
                </div>
    </>

}

export default Anynag;