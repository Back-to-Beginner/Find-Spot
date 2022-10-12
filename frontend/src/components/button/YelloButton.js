import React from 'react';

const YellowButton = (props) => {

    return <>
        <button type={"submit"}
            className={'yellowButtonBox'}>
        <span className="yellowButton">
            {props.buttonName}
            </span>
        </button>
    </>
}

export default YellowButton;