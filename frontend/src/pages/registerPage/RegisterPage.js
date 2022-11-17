import React from "react";
import RegisterBox from "../../components/box/RegisterBox";
import Logo from "../../images/SHOOTING.png"


const RegisterPage = () => {
  return <div style={{backgroundColor: 'white'}}>
    <div>
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
        <div>
          <RegisterBox />
        </div>
      </div>
    </div>
  </div>
};

export default RegisterPage;