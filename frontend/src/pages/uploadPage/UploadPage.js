import React, {useEffect, useLayoutEffect, useState} from "react";
import Header from "../../components/header/Header";
import YellowButton from "../../components/button/YelloButton";
import axios from "axios";
import MissionCard from "../../components/cards/missionCard/MissionCard";
import UploadCard from "../../components/cards/uploadCard/UploadCard";
import {Link, Navigate, useNavigate, useParams} from "react-router-dom";

const UploadPage = (props) => {
    const [mission, setMission] = useState({});
    const [approve, setApprove] = useState(false);
    const [imageSrc, setImageSrc] = useState('');
    const [content, setContent] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        if (sessionStorage.getItem('id') === null) {
            navigate('/login');
            return;
        }
        setMission(props.mission);
    }, []);

    const uploadPost = () => {
        approve && axios({
            method: 'post',
            url: `/posts`,
            data: {
                'type': 's',
                'userId': sessionStorage.getItem('id'),
                'parentPostId': sessionStorage.getItem('missionId'),
                'content': content
            }
        }).then(res => res && axios({
                header: {'content-type': 'multipart/form-data'},
                method: 'post',
                url: `/images`,
                data: {
                    'postId': res.data.data.id,
                    'path': imageSrc
                }
            }).then(res => {
                alert('게시글 작성에 성공하였습니다!!');
                res && window.location.back();
            })
        );
    };

    return (<>
        <Header/>
        <div className="mainPageWave">
            <div className='mainPageTitle'>
                Upload You're New Success
            </div>

            <div className={'detailViewLocation'}>
                <MissionCard data={mission}/>
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