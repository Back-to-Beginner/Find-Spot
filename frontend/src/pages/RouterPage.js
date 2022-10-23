import React, {useEffect, useState} from 'react';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import LoginPage from './loginPage/LoginPage';
import MainPage from './mainPage/MainPage';
import ProfilePage from './profilePage/ProfilePage';
import RegisterPage from './registerPage/RegisterPage';
import ResultPage from './resultPage/ResultPage';
import UploadPage from "./uploadPage/UploadPage";
import CollectionPage from './collectionPage/CollectionPage';
import DetailView from '../components/cards/detailView/DetailView';
import SuccessDetailView from  '../components/cards/detailView/SuccessDetailView';

const RouterPage = () => {
    const [isLogin, setIsLogin] = useState(false);

    useEffect(() => {
        sessionStorage.getItem('id') && setIsLogin(true);
    })

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    {
                        isLogin ? (<>
                            <Route path={'/upload/:id'} element={<UploadPage/>}/>
                            <Route path={'/user/:id'} element={<ProfilePage/>}/>
                        </>) : (<>
                            <Route path={'/login'} element={<LoginPage/>}/>
                            <Route path={'/register'} element={<RegisterPage/>}/>
                        </>)
                    }
                    <Route path={'/result/:searchWord'} element={<ResultPage/>}/>
                    <Route path={'/collection'} element={<CollectionPage/>}/>
                    <Route path={'/'} element={<MainPage/>}/>
                    <Route path={'*'} element={<Navigate replace to={'/'}/>}/>
                    <Route path={'/result'} element={<ResultPage/>}/>
                    <Route path={'/collection'} element={<CollectionPage/>}/>
                    <Route path={'/detailview'} element={<DetailView/>}/>
                    <Route path={'/successdetailview'} element={<SuccessDetailView/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default RouterPage;