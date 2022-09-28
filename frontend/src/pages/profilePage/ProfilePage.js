import React, {useState} from 'react';
import profileIcon from '../../images/user.png'
import '../../css/common.css'
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";


const ProfilePage = () => {
    const [username, setUsername] = useState('username');
    const [successCount, setSuccessCount] = useState(0);
    const [imageSrc, setImageSrc] = useState('');
    const [description, setDescription] = useState('프로필 설명입니다.');
    const [follower, setFollower] = useState(0);
    const [following, setFollowing] = useState(0);

    const getProfileImage = () => {
        return imageSrc ? imageSrc : profileIcon;
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
            <div className={'profilePage'}>
                <div className={'profilePageHeader'}>
                    <div className={'profile'}>
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
                        <div style={{padding: '10px'}}>
                            <SmallSuccessCard image={card.image} content={card.content}/>
                        </div>
                    )}
                </div>
            </div>
        </>
    )
};

export default ProfilePage;