import React, {useEffect, useState} from 'react';
import '../../../css/common.css'

const SmallProfileCard = (props) => {
    const [content, setContent] = useState("");
    const [userName, setUserName] = useState("");
    const [imagePath, setImagePath] = useState("");

    useEffect(() => {
        if (props.data) {
            setContent(props.data.content);
            setUserName(props.data.userName);
            setImagePath(props.data.imagePath);
        }
    });

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <img className={'smallSuccessImage'} style={{borderRadius: '9999px'}} src={imagePath}/>
            </div>
            <div className={'smallSuccessCardContent'}>
                @{userName} <br/>
                {content}
            </div>
        </div>
    </>
}

export default SmallProfileCard;