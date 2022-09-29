import React from 'react';

const YellowButton = (props) => {

    return <>
      <button className={'yellowButtonBox'}>
        <span class="yellowButton">
            {props.buttonName}
            </span>
      </button>
    </>
}

export default YellowButton;