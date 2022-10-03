import React from "react";
import LoginBox from "../../components/box/LoginBox";
import tempLogo from "../../images/missionExample.jpeg"


const LoginPage = () => {
  return <div style={{backgroundColor: 'white'}}>
    <div className="topWave" />
    <div className="bottomWave" />
    <div className="loginLayout">
      <div className="loginLeft">
        <img className="logo" src="tempLogo"></img>
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