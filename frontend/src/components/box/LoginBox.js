import React from 'react';
import EmailInput from '../input/EmaiInput';
import PasswordInput from '../input/PasswordInput';
import YellowButton from '../button/YelloButton';

const LoginBox = (props) => {

    return <>
    <div className="loginformBox">
        <div className='loginBoxTitle'>
            LOGIN
        </div>
        <form>
        
    
        <div style={{marginTop: '67px', marginLeft: '50px', marginRight: '50px'}}>
        <EmailInput/>
        </div>

        
    <div style={{marginTop: '20px', marginLeft: '50px', marginRight: '50px'}}>
    <PasswordInput/>
    </div>
    <button style={{marginTop: '105px', marginLeft: '50px', marginRight: '50px'}}>
        <YellowButton buttonName={props.buttonName}/>
    </button>
    </form>
    <div className='register'>
        Resigter
    </div>
    </div>
    </>
}

export default LoginBox;