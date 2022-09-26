import React from "react";

const Comment = (props) => {

    const onClick = () => {
        console.log(props.username)
    };

    return (
        <div className={'commentBox'}>
            <span className={'commentUsername'} onClick={onClick}>
                @{props.username}
            </span>
            <span> </span>
            <span className={'commentContent'}>
                {props.content}
            </span>
        </div>
    )
}

export default Comment;