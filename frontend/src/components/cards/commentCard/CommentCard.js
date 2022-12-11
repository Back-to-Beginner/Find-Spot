import React, {useEffect, useState} from 'react';
import '../../../css/common.css'
import sendIcon from '../../../images/paper-plane.png';
import Comment from "./Comment";
import axios from "axios";

const CommentCard = () => {
    const [chatInput, setChatInput] = useState('');
    const [chatList, setChatList] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        axios({
            method: 'get',
            url: `/api/v1/posts/parent/${sessionStorage.getItem("postId")}/child/c`
        }).then(r => {
            console.log(r.data.data);
            setChatList(r.data.data);
        });
        setIsLoading(false);
    }, [])

    const send = () => {
        sessionStorage.getItem('id') ?
        axios({
            method: 'post',
            url: '/api/v1/posts',
            data: {
                'type': 'c',
                'userId': sessionStorage.getItem('id'),
                'parentPostId': sessionStorage.getItem('postId'),
                'content': chatInput
            },
        }).then(r => {
            window.location.reload();
        }) : alert('로그인을 먼저 해주세요. [우측 상단 원형 아이콘]');
    }

    return !isLoading && (
        <div className={'commentView'}>
            <div className={'commentList'}>
                {
                    chatList.map(chat =>
                        <Comment username={chat.userName} content={chat.content}/>
                    )
                }
            </div>
            <div className={'sendBox'}>
                <input
                    type={"text"}
                    className={'commentInput'}
                    onChange={(event) => setChatInput(event.target.value)}
                    value={chatInput}/>
                <button className={'sendIconButton'} onClick={send}>
                    <img className={'sendIcon'} src={sendIcon} alt=""/>
                </button>
            </div>
        </div>
    )
}

export default CommentCard;