import React from 'react';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import LoginPage from './loginPage/LoginPage';
import MainPage from './mainPage/MainPage';
import ProfilePage from './profilePage/ProfilePage';
import RegisterPage from './registerPage/RegisterPage';
import ResultPage from './resultPage/ResultPage';
import UploadPage from "./uploadPage/UploadPage";
import CollectionPage from './collectionPage/CollectionPage';
import DetailView from '../components/cards/detailView/DetailView';
import SuccessDetailView from '../components/cards/detailView/SuccessDetailView';
import GroupPage from "./groupPage/GroupPage";
import GroupJoinPage from "./groupPage/GroupJoinPage";

const RouterPage = () => {

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    {
                        sessionStorage.getItem('id') ? (<>
                            <Route path={'/upload/:id'} element={<UploadPage/>}/>
                            <Route path={'/user/:id'} element={<ProfilePage/>}/>
                            <Route path={'/result/:searchWord'} element={<ResultPage/>}/>
                            <Route path={'/collection'} element={<CollectionPage/>}/>
                            <Route path={'/detail/m'} element={<DetailView/>}/>
                            <Route path={'/detail/s'} element={<SuccessDetailView/>}/>
                            <Route path={'/group/:id'} element={<GroupPage/>}/>
                            <Route path={'/group'} element={<GroupJoinPage/>}/>
                            <Route path={'/'} element={<MainPage/>}/>
                            <Route path={'*'} element={<Navigate replace to={'/'}/>}/>
                        </>) : (<>
                            <Route path={'/login'} element={<LoginPage/>}/>
                            <Route path={'/register'} element={<RegisterPage/>}/>
                            <Route path={'/result/:searchWord'} element={<ResultPage/>}/>
                            <Route path={'/collection'} element={<CollectionPage/>}/>
                            <Route path={'/detail/m'} element={<DetailView/>}/>
                            <Route path={'/detail/s'} element={<SuccessDetailView/>}/>
                            <Route path={'/'} element={<MainPage/>}/>
                            <Route path={'*'} element={<Navigate replace to={'/login'}/>}/>
                        </>)
                    }
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default RouterPage;