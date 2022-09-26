import React from 'react';

const YellowButton = (props) => {

    return <>
      <button className={'yellowloginbuttonBox'}>
        <span class="yellowbuttonlogin">
            {props.buttonName}
            </span>
      </button>
    </>
}

export default YellowButton;