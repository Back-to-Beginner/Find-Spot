import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import MissionCard from "./components/cards/missionCard/MissionCard";
import EmailInput from './components/input/EmaiInput';
import PasswordInput from './components/input/PasswordInput';
import YellowButton from './components/button/YelloButton';
import LoginBox from './components/box/LoginBox';
import LoginPage from './pages/loginPage/LoginPage';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/*<App />*/}
    
      <LoginPage/>
      
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
