

import React, { useEffect, useState } from 'react';
import './FindId.css'
import { Link } from 'react-router-dom';
import { PageTracker } from '../../../api/PageTracker';

const FindId = () => {

    const FindForm = (props) => {



        return (
            <>
                <div className='findid-birth-form-box'>
                    <input
                        className='btn-radio'
                        type="radio"
                        id={props.title}
                        name="findMethod"
                        value={props.title}
                        onChange={handleRadioChange}
                        checked={selectedRadio === props.title}
                    />
                    <label className='findid-birth-comment' htmlFor="birth">{props.comment}</label>

                    {selectedRadio === props.title && (
                        <div>
                            <div className='findid-birth-comment2'>{props.comment2}</div>

                            <form className='findid-birth-input'>
                                <div className='findid-birth-name'>
                                    <span>이 름</span>
                                    <input type="text" id="username" name="username" required />
                                </div>

                                <div className='findid-birth-birth'>
                                    <span>{props.inputName}</span>
                                    <input type="text" id="password" name="password" required placeholder={props.pHolder} />
                                    <button>인증하기</button>
                                </div>
                            </form>
                        </div>
                    )}
                </div>
            </>
        )
    }



    //라디오 옵션
    const [selectedRadio, setSelectedRadio] = useState('birth');


    //라디오 핸들러
    const handleRadioChange = (event) => {
        setSelectedRadio(event.target.value);
        console.log(event.target.value)

    };

    //페이지 트랙커
    useEffect(() => {
        PageTracker(window.location.pathname);
    }, [])


    return (
        <>
            <div className='findid-body'>
                <div className='findid-container'>
                    <div className='findid-logo'>아이디 찾기</div>
                    <div className='findid-comment'>아이디를 찾는 방법을 선택해 주세요.</div>
                    <div className='findid-form-box'>
                        <div className='findid-form-in'>
                            <FindForm
                                comment={'회원정보에 등록한 생년월일 인증'}
                                comment2={'회원정보에 등록된 생년월일과 입력한 생년월일 정보가 같아야 합니다.'}
                                inputName={'생년월일'}
                                title={'birth'}
                                pHolder={'주민번호 앞자리 6자리 숫자 입력'}
                            />

                            <FindForm
                                comment={'회원정보에 등록한 휴대전화 번호 인증'}
                                comment2={'회원정보에 등록된 휴대전화 번호와 입력한 휴대전화 정보가 같아야 합니다.'}
                                inputName={'휴대전화'}
                                title={'phone'}
                                pHolder={null}
                            />
                        </div>
                    </div>
                    <div className='go-to-page'>
                        <div><Link to={'/findpw'}>비밀번호 찾기</Link></div>
                        &nbsp; &nbsp;<span>|</span>&nbsp; &nbsp;
                        <div><Link to={'/login'}>로그인</Link></div>
                    </div>
                </div>
            </div>

        </>
    )

}

export default FindId;