import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import Header from "../../components/header/Header";
import YellowButton from "../../components/button/YelloButton";
import MissionCard from "../../components/cards/missionCard/MissionCard";
import UploadCard from "../../components/cards/uploadCard/UploadCard";


const UploadPage = () => {
    const [approve, setApprove] = useState(false);
    const [imageSrc, setImageSrc] = useState('');
    const [content, setContent] = useState('');
    const [isLoading, setIsLoading] = useState(true);

    const navigate = useNavigate();

    useEffect(() => {
        if (sessionStorage.getItem('id') === null) {
            navigate('/login');
        }
        setIsLoading(false);
    }, []);

    const uploadPost = () => {
        setIsLoading(true);
        approve && axios({
            method: 'post',
            url: `/api/v1/posts`,
            data: {
                'type': 's',
                'userId': sessionStorage.getItem('id'),
                'parentPostId': sessionStorage.getItem('postId'),
                'content': content
            }
        }).then(res => res && axios({
                header: {'content-type': 'multipart/form-data'},
                method: 'post',
                url: `/api/v1/images`,
                data: {
                    'postId': res.data.data.id,
                    'path': imageSrc
                }
            }).then(res => {
                alert('게시글 작성에 성공하였습니다!!');
                res && navigate("/");
            })
        ).then(() => setIsLoading(false));
    };

    return !isLoading && (<>
        <Header/>
        <div className="mainPageWave">
            <div className='mainPageTitle'>
                📤 Upload You're New Success
            </div>

            <div className={'detailViewLocation'}>
                <MissionCard/>
                <UploadCard upload={setImageSrc}
                            approve={setApprove}
                            imageSrc={setImageSrc}
                            content={setContent}/>
            </div>

            <div className="mainBoxPosition">
                <div style={{paddingTop: '50px'}} onClick={uploadPost}>
                    <YellowButton buttonName={'Upload !!'}/>
                </div>
                <div style={{padding: '10px'}}>
                    <Link>
                        <div className='findOtherSpot' onClick={() => window.history.back()}>
                            cancel
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    </>)
};

export default UploadPage;