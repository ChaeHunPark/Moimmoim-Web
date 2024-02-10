import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import MainPage from './Component/Page/MainPage/MainPage';
import LoginPage from './Component/Page/LoginPage/LoginPage';
import FindId from './Component/Page/FindId/FindId';
import FindPw from './Component/Page/FindPw/FindPw';
import RegisterPage from './Component/Page/RegisterPage/RegisterPage';
import TestPage from './Component/Page/RegisterPage/TestPage';

function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage/>}></Route>
        <Route path="/login" element={<LoginPage/>}></Route>
        <Route path='/findid' element={<FindId/>}></Route>
        <Route path='/findpw' element={<FindPw/>}></Route>
        <Route path='/registeruser' element={<RegisterPage/>}></Route>
        <Route path='registeruser_next' element={<TestPage/>}></Route>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
