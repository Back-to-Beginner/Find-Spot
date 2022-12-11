import React, {useCallback, useState} from 'react';
import EmailInput from '../input/EmaiInput';
import PasswordInput from '../input/PasswordInput';
import YellowButton from '../button/YelloButton';
import PasswordConfirmInput from '../input/PasswordConfirmInput';
import {Link, useNavigate} from 'react-router-dom';
import axios from "axios";
import NameInput from "../input/NameInput";

const RegisterBox = () => {
    const [formValue, setFormValue] = useState({
        email: "",
        name: "",
        pw: "",
        pwcf: ""
    });

    const navigate = useNavigate();

    const register = useCallback((e) => {
        e.preventDefault();
        if (formValue.pw !== formValue.pwcf) {
            alert('Password와 Password Confirm이 일치하지 않습니다.');
            return;
        }
        axios({
            method: "post",
            url: '/api/v1/users/',
            data: {
                "email": formValue.email,
                "name": formValue.name,
                "pw": formValue.pw
            }
        }).catch((err) => {
            alert("로그인 정보가 일치하지 않습니다." + err);
        }).then((res) => {
            res.data.data.id && axios({
                method: "post",
                url: '/api/v1/posts',
                data: {
                    "userId": Number(res.data.data.id),
                    "type": 'u',
                    "content": "신규 가입했습니다. 반갑습니다!",
                    "parentPostId": -1
                }
            }).catch(err => {
                alert(err);
            }).then(res => {
                res.data.data.id && axios({
                    method: "post",
                    url: '/api/v1/images',
                    data: {
                        "path": "",
                        "postId": `${res.data.data.id}`
                    }
                }).catch(err => {
                    alert(err);
                }).then(res => {
                    navigate('/login');
                });
            });
        });
    });

    return <>
        <div className="registerformBox">
            <div className='registerBoxTitle'>
                Register
            </div>
            <form onSubmit={register}>
                <div className='registerformLocation'>
                    <div style={{marginTop: '60px', marginLeft: '50px', marginRight: '50px'}}>
                        <EmailInput
                            value={formValue.email}
                            setValue={(value) => {
                                setFormValue((state) => ({
                                    ...state,
                                    email: value
                                }));
                            }}/>
                    </div>
                    <div style={{marginTop: '20px', marginLeft: '50px', marginRight: '50px'}}>
                        <NameInput
                            value={formValue.name}
                            setValue={(value) => {
                                setFormValue((state) => ({
                                    ...state,
                                    name: value
                                }));
                            }}/>
                    </div>
                    <div style={{marginTop: '20px', marginLeft: '50px', marginRight: '50px'}}>
                        <PasswordInput
                            value={formValue.pw}
                            setValue={(value) => {
                                setFormValue((state) => ({
                                    ...state,
                                    pw: value
                                }));
                            }}/>
                    </div>
                    <div style={{marginTop: '20px', marginLeft: '50px', marginRight: '50px'}}>
                        <PasswordConfirmInput
                            value={formValue.pwcf}
                            setValue={(value) => {
                                setFormValue((state) => ({
                                    ...state,
                                    pwcf: value
                                }));
                            }}/>
                    </div>
                    <button style={{marginTop: '31px', marginLeft: '50px', marginRight: '50px'}}>
                        <YellowButton buttonName={'Confirm'}/>
                    </button>
                </div>
                <Link to={'/login'}>
                    <div className='login'>
                        Login
                    </div>
                </Link>
            </form>
        </div>
    </>

}
export default RegisterBox;