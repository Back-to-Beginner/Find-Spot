import React from 'react';
import profilelogo from "../../images/user.png";

const ProfileButton = () => {

    return <>
       <button className={'profilebuttonBox'}>
        <img className={'user-2'} src={profilelogo}/>
        
       </button>
    </>
} 

export default ProfileButton;