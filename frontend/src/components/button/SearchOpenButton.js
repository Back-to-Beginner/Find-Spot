import React from 'react';
import searchlogo from "../../images/magnifying_glass_icon.png";

const SearchOpenButton = () => {

    return <>
        <div className={'searchOpen'}>
            <input className={'searchForm'} placeholder='apple'></input>
            <img className="searchIcon" src="searchlogo">
        </img>
        </div>
    </>
}

export default SearchOpenButton;