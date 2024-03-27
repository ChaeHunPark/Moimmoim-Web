import { Link, useNavigate } from 'react-router-dom';
import './LoginPage.css'
import { useEffect, useState } from 'react';

import axios from 'axios';

const LoginPage = () => {

    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const navi = useNavigate();


    const handleChange = async (e,setState) =>{
        setState(e.target.value);
    }

    const handleLogin = async (e) =>{
        
        e.preventDefault();
        try {
            const data = {
                userId: userId,
                password: password
            };
            const response = await axios.post('/api/authenticate',data);
            const token = response.data.token;
            localStorage.setItem('token', token);
            localStorage.setItem('userId', userId);
            navi("/main")
            console.log(response.data.token)

        } catch (error) {
            alert("아이디 또는 비밀번호가 틀렸습니다.")
            console.log('로그인 실패: ', error);
        }

    }


    //페이지 트랙커
    useEffect(() => {
    }, [])

    return (<>
        <div className='login-body'>
            <div className="login-container">
                <form className="login-form" onSubmit={handleLogin} >
                    <div className='login-logo'>
                        <Link to={'/'}>MoimMoim</Link>
                    </div>
                    <input type="text" id="userId" name="userId" required placeholder='ID' value={userId} onChange={(e) =>{handleChange(e, setUserId)}}/>
                    <input type="password" id="password" name="password" required placeholder='Password' value={password} onChange={(e) =>{handleChange(e, setPassword)}} />
                    <div></div>
                    <button type="submit">Login</button>

                    <div className='find-container'>
                        <Link to={'/findid'}><nav>아이디 찾기</nav></Link>
                        &nbsp;&nbsp;
                        <span>|</span>
                        &nbsp;&nbsp;
                        <Link to={'/findpw'}><nav>비밀번호 찾기</nav></Link>
                        &nbsp;&nbsp;
                        <span>|</span>
                        &nbsp;&nbsp;
                        <Link to={'/registeruser'}><nav>회원가입</nav></Link>
                    </div>

                    <div className='copy-right'>
                        <span>MoimMoim </span>
                        Copyright © MoimMoim Corp. All Rights Reserved.
                    </div>
                </form>
            </div>
        </div>
    </>)
}

export default LoginPage;