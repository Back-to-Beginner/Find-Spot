import React from "react";
import LoginBox from "../../components/box/LoginBox";
import Logo from "../../images/SHOOTING.png"


const LoginPage = () => {
  return <div style={{backgroundColor: 'white'}}>
    <div className="topWave" />
    <div className="bottomWave" />
    <div className="loginLayout">
      <div className="loginLeft">
        <img className="logo" src={Logo}></img>
        <div className="findText">
          <span className='yellowText'>
            F
          </span>
          IND
        </div>
        <div className="spotText">
          SP
          <span className='yellowText'>
            O
          </span>
          T
        </div>
      </div>
      <div className="loginBoxPosition"><LoginBox buttonName={'LOGIN'} /></div>
    </div>
  </div>
};

export default LoginPage;