import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import MissionCard from "./components/cards/missionCard/MissionCard";
import RegisterPage from './pages/registerPage/RegisterPage';
import Header from './components/header/Header';
import MainPage from './pages/mainPage/MainPage';
import DetailView from './pages/detailView/DetailVieiw';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/*<App />*/}
      {/*<RegisterPage/>*/}
      {/*<SearchClose/>*/}
      {/* <Header/> */}
      {/* <MainPage/> */}
      <DetailView/>
      
  </React.StrictMode>
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
