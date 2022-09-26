import React from 'react';
import EmailInput from '../input/EmaiInput';
import PasswordInput from '../input/PasswordInput';
import YellowButton from '../button/YelloButton';
import PasswordConfirmInput from '../input/PasswordConfirmInput';

const RegisterBox = () => {

    return <>
        <div className="registerformBox">
            <div className='registerBoxTitle'>
                Register
            </div>
            <form>
                <div style={{ marginTop: '67px', marginLeft: '50px', marginRight: '50px' }}>
                    <EmailInput />
                </div>
                <div style={{ marginTop: '20px', marginLeft: '50px', marginRight: '50px' }}>
                    <PasswordInput />
                </div>
                <div style={{ marginTop: '20px', marginLeft: '50px', marginRight: '50px' }}>
                    <PasswordConfirmInput />
                </div>
                <button style={{ marginTop: '105px', marginLeft: '50px', marginRight: '50px' }}>
                    <YellowButton buttonName={'Confirm'} />
                </button>
            </form>
            <div className='login'>
                Login
            </div>
        </div>
    </>
}

export default RegisterBox;