import React from 'react';
import EmailInput from '../input/EmaiInput';
import PasswordInput from '../input/PasswordInput';
import YellowButton from '../button/YelloButton';
import { Link } from 'react-router-dom';

const LoginBox = (props) => {

    return <>
        <div className="loginformBox">
            <div className='loginBoxTitle'>
                LOGIN
            </div>
            <form>
                <div className='loginformLocation'>
                <div style={{ marginTop: '60px', marginLeft: '50px', marginRight: '50px' }}>
                        <EmailInput />
                    </div>
                    <div style={{ marginTop: '40px', marginLeft: '50px', marginRight: '50px' }}>
                        <PasswordInput />
                    </div>
                    <button style={{ marginTop: '95px', marginLeft: '50px', marginRight: '50px' }}>
                        <YellowButton buttonName={props.buttonName} />
                    </button>

                    <Link to={'/register'}>
                        <div className='register'>
                            Resigter
                        </div>
                    </Link>
                </div>
            </form>
        </div>
    </>
}

export default LoginBox;