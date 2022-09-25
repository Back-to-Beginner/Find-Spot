import React, {useState} from 'react';
import '../../../css/common.css'
import sendIcon from '../../../images/paper-plane.png';
import Comment from "./Comment";

const CommentCard = (props) => {

    return (
        <div className={'commentView'}>
            <div className={'commentList'}>
                <Comment username={'bob'} content={'sooooo wired'}></Comment>
                <Comment username={'bob'} content={'sooooo wired'}></Comment>
            </div>
            <div className={'sendBox'}>
                <div className={'commentInputLocation'}>
                    <input className={'commentInput'}/>
                </div>
                    <button className={'sendIconButton'}>
                        <img className={'sendIcon'} src={sendIcon} alt=""/>
                    </button>
            </div>
        </div>
    )
}

export default CommentCard;