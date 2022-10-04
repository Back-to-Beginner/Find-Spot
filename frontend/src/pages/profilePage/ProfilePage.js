import React, {useState} from 'react';
import profileIcon from '../../images/user.png'
import '../../css/common.css'
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import Header from '../../components/header/Header'
import ProfileUploadCard from "../../components/cards/uploadCard/ProfileUploadCard";
import YellowButton from "../../components/button/YelloButton";

const ProfilePage = () => {
    const [username, setUsername] = useState('username');
    const [successCount, setSuccessCount] = useState(0);
    const [imageSrc, setImageSrc] = useState('');
    const [isDefaultImage, setIsDefaultImage] = useState(true);
    const [description, setDescription] = useState('프로필 설명입니다.');
    const [follower, setFollower] = useState(0);
    const [following, setFollowing] = useState(0);
    const [edit, setEdit] = useState(false);

    const getProfileImage = () => {
        return imageSrc ? imageSrc : profileIcon;
    }

    const clickProfileEdit = () => {
        setEdit(!edit);
    }

    const getProfileUploadStyle = () => {
        return edit ? {visibility: 'visible'} : {visibility: 'hidden'};
    }

    const getNotDefaultProfileImage = () => {
        return isDefaultImage ? null : imageSrc;
    }

    const temp = [
        {image: profileIcon, content: 'Success 1'},
        {image: profileIcon, content: 'Success 2'},
        {image: profileIcon, content: 'Success 3'},
        {image: profileIcon, content: 'Success 4'},
        {image: profileIcon, content: 'Success 5'},
        {image: profileIcon, content: 'Success 6'},
    ]

    return (
        <>
            <div className={'profileUploadLocation'} style={getProfileUploadStyle()}>
                <ProfileUploadCard image={getNotDefaultProfileImage()} content={description}/>
                <div style={{marginTop: '15px'}} onClick={clickProfileEdit}>
                    <YellowButton buttonName={'Save'}/>
                </div>
                <div style={{marginTop: '15px'}} onClick={clickProfileEdit}>
                    <span className={'profileUploadClose'}>cancel</span>
                </div>
            </div>
            <Header/>
            <div className={'profilePage'}>
                <div className={'profilePageHeader'}>
                    <div className={'profile'} onClick={clickProfileEdit}>
                        <img className={'profileImage'} src={getProfileImage()}/>
                        <span className={'description'}>
                            @{username} <br/>
                            {description}
                        </span>
                    </div>
                    <div className={'profileSub'}>
                        <span>{successCount} Success</span>
                        <span>{following} Following</span>
                        <span>{follower} Follower</span>
                    </div>
                </div>

                <div className={'cardGrid'}>
                    {temp.map(card =>
                        <div style={{padding: '5px'}}>
                            <SmallSuccessCard image={card.image} content={card.content}/>
                        </div>
                    )}
                </div>
            </div>
        </>
    )
};

export default ProfilePage;