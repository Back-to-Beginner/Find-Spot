import React from 'react';
import EmailInput from '../input/EmaiInput';
import PasswordInput from '../input/PasswordInput';
import YellowButton from '../button/YelloButton';
import PasswordConfirmInput from '../input/PasswordConfirmInput';
import { Link } from 'react-router-dom';

const RegisterBox = () => {

    return <>
        <div className="registerformBox">
            <div className='registerBoxTitle'>
                Register
            </div>
            <form>
                <div className='registerformLocation'>
                <div style={{ marginTop: '60px', marginLeft: '50px', marginRight: '50px' }}>
                    <EmailInput />
                </div>
                <div style={{ marginTop: '20px', marginLeft: '50px', marginRight: '50px' }}>
                    <PasswordInput />
                </div>
                <div style={{ marginTop: '20px', marginLeft: '50px', marginRight: '50px' }}>
                    <PasswordConfirmInput />
                </div>
                <button style={{ marginTop: '40px', marginLeft: '50px', marginRight: '50px' }}>
                    <YellowButton buttonName={'Confirm'} />
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