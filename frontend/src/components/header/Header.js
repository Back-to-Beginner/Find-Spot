import React, {useState} from "react";
import SearchButton from "../../components/button/SearchButton";
import SearchOpenButton from "../../components/button/SearchOpenButton";
import ProfileButton from "../../components/button/ProfileButton";
import {Link} from "react-router-dom";

const Header = () => {
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
        1: <SearchOpenButton/>,
        0: <SearchButton/>,
    };

    return <>

        <div className="searchcloseForm">

            <Link to={'/'} style={{textDecorationLine: 'none'}}>
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
            </Link>
            <div className='headerbuttonGroup'>
                <div className="searchButtonLocation" onClick={searchbuttonClick}>
                    {selectSearchButton[searchButtonValue]}
                    {/* <SearchButton/> */}
                </div>
                <Link to={`/user/${sessionStorage.getItem('id')}`}>
                    <ProfileButton/>
                </Link>
            </div>
        </div>

    </>
}

export default Header;