import React, {useEffect, useState} from 'react';
import profileIcon from '../../images/user.png'
import '../../css/common.css'
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import Header from '../../components/header/Header'
import ProfileUploadCard from "../../components/cards/uploadCard/ProfileUploadCard";
import YellowButton from "../../components/button/YelloButton";
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

const ProfilePage = () => {
    const [username, setUsername] = useState(sessionStorage.getItem("name"));
    const [success, setSuccess] = useState([]);
    const [profile, setProfile] = useState({});
    const [imageSrc, setImageSrc] = useState();
    const [profileImage, setProfileImage] = useState({});
    const [follower, setFollower] = useState(0);
    const [following, setFollowing] = useState(0);
    const [edit, setEdit] = useState(false);

    const param = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (param.id === 'null'){
            navigate('/login');
            return;
        }

        axios({
            method: 'get',
            url: `/posts/types/u/users/${param.id}`
        }).catch(err => {
            alert("유저 정보가 옳바르지 않습니다. 다시 시도해 주세요");
        }).then(res => {
            sessionStorage.setItem('profileId', res.data.data[0].id);
            setProfile(res.data.data[0]);

            axios({
                method: 'get',
                url: `/images/post/${sessionStorage.getItem('profileId')}`,
            }).then(res => {
                res.data.data[0] && setProfileImage(res.data.data[0])
            });
        });

        axios({
            method: 'get',
            url: `/posts/types/s/users/${sessionStorage.getItem('id')}`
        }).then(res => {
            res.data.data && setSuccess(res.data.data);
        })
    }, [])

    const getProfileImage = () => {
        return profileImage.path ? profileImage.path : profileIcon;
    }

    const clickProfileEdit = () => {
        setEdit(!edit);
    }

    const updateProfileImage = () => {
        const form = new FormData();
        console.log(imageSrc);
        form.append('images', imageSrc);

        axios({
            header: {'content-type': 'multipart/form-data'},
            method: 'post',
            url: `/images/upload/${sessionStorage.getItem('profileId')}`,
            data: form
        }).then(res => {
            console.log(res.data.data)
            window.location.reload();
        })
    }

    const getProfileUploadStyle = () => {
        return edit ? {visibility: 'visible'} : {visibility: 'hidden'};
    }

    return (
        <>
            <div className={'profileUploadLocation'} style={getProfileUploadStyle()}>
                <ProfileUploadCard
                    content={profile.content}
                    imageSrc={imageSrc}
                    setImageSrc={setImageSrc}/>
                <div style={{marginTop: '15px'}} onClick={updateProfileImage}>
                    <YellowButton buttonName={'Save'}/>
                </div>
                <div style={{marginTop: '15px'}} onClick={clickProfileEdit}>
                    <span className={'profileUploadClose'}>close</span>
                </div>

            </div>
            <Header/>
            <div className={'profilePage'}>
                <div className={'profilePageHeader'}>
                    <div className={'profile'} onClick={clickProfileEdit}>
                        <img className={'profileImage'} src={getProfileImage()}/>
                        <span className={'description'}>
                            @{username} <br/>
                            {profile.content}
                        </span>
                    </div>
                    <div className={'profileSub'}>
                        <span>{success.length} Success</span>
                        <span>{following} Following</span>
                        <span>{follower} Follower</span>
                    </div>
                </div>

                <div className={'cardGrid'}>
                    {success.map(card =>
                        <div style={{padding: '5px'}}>
                            <SmallSuccessCard data={card}/>
                        </div>
                    )}
                </div>
            </div>
        </>
    )
};

export default ProfilePage;