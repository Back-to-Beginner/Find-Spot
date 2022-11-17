import React from 'react';
import profilelogo from "../../images/user.png";

const ProfileButton = () => {

    return <>
       <button className={'profilebuttonBox'}>
        <img className={'profileIcon'} src={profilelogo}/>
        
       </button>
    </>
} 

export default ProfileButton;