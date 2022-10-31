import React, {useCallback, useState} from 'react';
import axios from 'axios';
import EmailInput from '../input/EmaiInput';
import PasswordInput from '../input/PasswordInput';
import YellowButton from '../button/YelloButton';
import {Link, useNavigate} from 'react-router-dom';

const LoginBox = (props) => {
    const [formValue, setFormValue] = useState({
        email: "",
        pw: "",
    });

    const navigate = useNavigate();

    const login = useCallback((e) => {
        e.preventDefault();
        axios({
            method: "post",
            url: '/users/login',
            data: {
                "email": formValue.email,
                "pw": formValue.pw
            }
        }).catch((err) => {
            alert("로그인 정보가 일치하지 않습니다.");
        }).then((res) => {
            sessionStorage.setItem('id', res.data.data.id);
            sessionStorage.setItem('name', res.data.data.name);
            sessionStorage.setItem('email', res.data.data.email);
            if (sessionStorage.getItem('id')){
                // navigate('/');
                window.location.replace('/');
            }
        });
    });

    return <>
        <div className="loginformBox">
            <div className='loginBoxTitle'>
                Login
            </div>
            <form onSubmit={login}>
                <div className='loginformLocation'>
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
                    <div style={{marginTop: '40px', marginLeft: '50px', marginRight: '50px'}}>
                        <PasswordInput
                            value={formValue.pw}
                            setValue={(value) => {
                                setFormValue((state) => ({
                                    ...state,
                                    pw: value
                                }));
                            }}
                        />
                    </div>
                    <button style={{marginTop: '95px', marginLeft: '50px', marginRight: '50px'}}>
                        <YellowButton
                            buttonName={props.buttonName}
                        />
                    </button>

                    <Link to={'/register'}>
                        <div className='register'>
                            Resister
                        </div>
                    </Link>
                </div>
            </form>
        </div>
    </>
}

export default LoginBox;