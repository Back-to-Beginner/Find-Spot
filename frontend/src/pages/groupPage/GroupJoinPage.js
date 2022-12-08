import React, {useEffect, useState} from "react";
import Header from '../../components/header/Header'
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import SmallProfileCard from "../../components/cards/profileCard/SmallProfileCard";
import YellowButton from "../../components/button/YelloButton";
import CommentCard from "../../components/cards/commentCard/CommentCard";

const GroupJoinPage = () => {

    const [groupList, setGroupList] = useState([]);
    const [name, setName] = useState("");
    const [info, setInfo] = useState("");

    const createGroup = () => {
        axios({
            method: "post",
            url: '/groups',
            data: {'name': name, 'info': info}
        }).then(r => {
            if (r.data.data) {
                window.location.href = `/group/${r.data.data.id}`;
            }
        })
    }

    const joinGroup = (index) => {
        axios({
            method: "patch",
            url: `/users/${sessionStorage.getItem('id')}/group-join/${index}`,
        }).then(r => {
            if (r.data.data) {
                sessionStorage.setItem("groupId", index);
                window.location.href = `/group/${index}`;
            }
        });
    }

    useEffect(() => {
        alert(sessionStorage.getItem("groupId"));
        if (sessionStorage.getItem("groupId") !== "null") {
            window.location.href = `/group/${sessionStorage.getItem("groupId")}`
        } else {
            axios({
                method: "get",
                url: `/groups`,
            }).then(r => {
                r.data.data[0] && setGroupList(r.data.data);
            });
        }
    }, []);

    return (<>
        <Header/>
        <div className={'profilePage'}>
            <div className={'registerformLocation'}>
                <span className={'mainPageTitle'} style={{paddingTop: '50px'}}>Group</span>

                <form>
                    Group Name<br/>
                    <input style={{width: '300px', margin: '20px', fontSize: '18px'}}
                           value={name}
                           onChange={event => setName(event.target.value)}
                    />
                    <br/>
                    Info<br/>
                    <input style={{width: '300px', margin: '20px', fontSize: '18px'}}
                           value={info}
                           onChange={event => setInfo(event.target.value)}
                    />
                    <br/>
                    <div onClick={createGroup}>
                        <YellowButton buttonName={'Create Group'}/>
                    </div>
                </form>
                <h1 style={{paddingTop: '100px'}}>Recommend</h1>
                <div className={'cardGrid'}>
                    <ul>
                        {groupList.map((group, index) =>
                            <li>
                                <input type={"button"} value={'join'}
                                       onClick={() => {
                                           joinGroup(index + 1)
                                       }}/>
                                [{group.name}] : {group.info}
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </div>
    </>)
}

export default GroupJoinPage;