import React, {useState} from "react";
import SearchButton from "../../components/button/SearchButton";
import ProfileButton from "../../components/button/ProfileButton";

const SearchClose = () => {
    const [searchButtonValue, setSearchButtonValue] = useState(0);
    
    const searchbuttonClick = () => {
        if (searchButtonValue === 0) {
            setSearchButtonValue(1);
        } 
        // else {
        //     setSearchButtonValue(0);
        // }
    }

    const selectSearchButton = {
        1: <input/>,
        0: <SearchButton/>,
    };

    return <>
    
    <div className="searchcloseForm">
        
        <div className="headerLogo">
            <span className='headeryellowText'>
                F
            </span>
            IND SP
            <span className='headeryellowText'>
                O
            </span>
            T
        </div>
        <div className='headerbuttonGroup'>
            <div className="searchButtonLocation" onClick={searchbuttonClick}>
                {selectSearchButton[searchButtonValue]}
                {/* <SearchButton/> */}
            </div>
        <ProfileButton/>
        </div>
    </div>
    
    </>
}

export default SearchClose;