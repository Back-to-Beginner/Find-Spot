import React, {useEffect, useState} from "react";
import Header from '../../components/header/Header'
import axios from "axios";
import {useParams} from "react-router-dom";
import SmallProfileCard from "../../components/cards/profileCard/SmallProfileCard";
import YellowButton from "../../components/button/YelloButton";
import CommentCard from "../../components/cards/commentCard/CommentCard";

const GroupPage = (props) => {
    const parm = useParams()

    let username = 'my group';
    let profile = 'group info';
    let success = [1, 2, 3, 4, 5];
    let user = 0;
    let groupCode = 10123;

    const [group, setGroup] = useState(false);

    return (<>
        <Header/>
        <div className={'profilePage'}>
            <div className={'registerformLocation'}>
                <span className={'mainPageTitle'} style={{paddingTop: '50px'}}>Group</span>
                {
                    group ? (<>
                        <input value={'Leave'} type={'button'}
                               style={{width: '50px', marginTop: '10px', fontSize: '24px'}}/>
                        <div className={'profilePageHeader'}>
                            <div className={'profile'}>
                                <span className={'description'}>
                                    @{username}<br/>
                                    {profile}
                                </span>
                            </div>
                            <div className={'profileSub'}>
                                <span>{success.length} Success</span>
                                <span>{user} User</span>
                                <span>{user} Group Code</span>
                            </div>
                        </div>
                        Group Success
                        <div className={'cardGrid'}>
                            {success.map(card =>
                                <div style={{padding: '5px'}}>
                                    <SmallProfileCard data={card}/>
                                </div>
                            )}
                        </div>
                        <div style={{padding: '5px'}}>
                            <CommentCard/>
                        </div>
                    </>) : (<>
                        <form>
                            Group Name<br/>
                            <input style={{width: '300px', margin: '20px', fontSize: '18px'}}/><br/>
                            Info<br/>
                            <input style={{width: '300px', margin: '20px', fontSize: '18px'}}/><br/>
                            <div onClick={() => setGroup(!group)}>
                                <YellowButton buttonName={'Create Group'}/>
                            </div>
                        </form>
                        <span style={{paddingTop: '100px'}}>Recommend</span>
                        <div className={'cardGrid'}>
                            {success.map(card =>
                                <div style={{padding: '5px'}}>
                                    <SmallProfileCard data={card}/>
                                </div>
                            )}
                        </div>
                    </>)
                }
            </div>
        </div>
    </>)
}

export default GroupPage;