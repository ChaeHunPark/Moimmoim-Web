import { useEffect, useState } from 'react';
import './RegisterPage.css'
import { Link } from 'react-router-dom';
import { useFormik } from 'formik';

import { validationSchemaFirst, validationSchemaSecond } from '../../utils/Register/ValidationStorage';
import { Register } from './Api/Register';
import { checkIdAvailable, checkNicknameAvailable } from './Api/CheckAvailable';
import { formatPhoneNumber } from '../../utils/Register/phoneUtils';
import { formatBirthNumber } from '../../utils/Register/birthUtils';






const RegisterPage = () => {

    const [section, setSection] = useState(1);
    const [resdata, setResData] = useState('');

    const formikFirst = useFormik({
        initialValues: {
            id: '',
            password: '',
            confirmPassword: '',
            email: '',
            phone: '',
        },
        validationSchema: validationSchemaFirst,
        onSubmit: async values => {
            console.log(values);

            // 아이디 중복 검사 수행
            checkIdAvailable(formikFirst, values, setSection)
  
        },
    });

    const formikSecond = useFormik({
        initialValues: {
            name: '',
            birth: '',
            nickname: '',
            gender: '',
            question: '',
            answer: '',
            // 다른 필드에 대한 초기값 추가
        },
        validationSchema: validationSchemaSecond,
        onSubmit: async values => {
            const data = {...formikFirst.values, ...values}
            const isNicknameAvailable = await checkNicknameAvailable(formikSecond, values);

            if(isNicknameAvailable){
                console.log(data);
                Register(data, setResData);
            }
            
        },
    });


    // 전화번호 핸들링
    const handlePhoneChange = (e) => {
        const inputPhoneNumber = e.target.value;
        const formattedPhoneNumber = formatPhoneNumber(inputPhoneNumber);
        formikFirst.setFieldValue('phone', formattedPhoneNumber);
      
      };
    
    // 생년월일 핸들링
    const handleBirthChange = (e) => {
        const inputBirth = e.target.value;
        const formattedBirth = formatBirthNumber(inputBirth);
        formikSecond.setFieldValue('birth', formattedBirth)
    }



    return (
        <>
            <div className="register-body">
                <div className='register-container'>
                    <div className='register-logo'>회원가입</div>

                    {section === 1 && (

                        <form onSubmit={formikFirst.handleSubmit}>
                            <div className='register-form'>
                                <div className='input-id'>
                                    <label htmlFor="id">아이디:</label>
                                    <input
                                        type="text"
                                        id="id"
                                        name="id"
                                        onChange={formikFirst.handleChange}
                                        onBlur={formikFirst.handleBlur}
                                        value={formikFirst.values.id}
                                    />
                                    {formikFirst.touched.id && formikFirst.errors.id ? <div>{formikFirst.errors.id}</div> : null}
                                </div>
                                <div className='input-pw'>
                                    <label htmlFor="password">비밀번호:</label>
                                    <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        onChange={formikFirst.handleChange}
                                        onBlur={formikFirst.handleBlur}
                                        value={formikFirst.values.password}
                                    />
                                    {formikFirst.touched.password && formikFirst.errors.password ? (
                                        <div>{formikFirst.errors.password}</div>
                                    ) : null}

                                </div>
                                <div className='input-confirm-pw'>
                                    <label htmlFor="confirmPassword">비밀번호 확인:</label>
                                    <input
                                        type="password"
                                        id="confirmPassword"
                                        name="confirmPassword"
                                        onChange={formikFirst.handleChange}
                                        onBlur={formikFirst.handleBlur}
                                        value={formikFirst.values.confirmPassword}
                                    />
                                    {formikFirst.touched.confirmPassword && formikFirst.errors.confirmPassword ? (
                                        <div>{formikFirst.errors.confirmPassword}</div>
                                    ) : null}
                                </div>
                                <div className='input-email'>
                                    <label htmlFor="email">이메일:</label>
                                    <input
                                        type="text"
                                        id="email"
                                        name="email"
                                        onChange={formikFirst.handleChange}
                                        onBlur={formikFirst.handleBlur}
                                        value={formikFirst.values.email}
                                    />
                                    {formikFirst.touched.email && formikFirst.errors.email ? <div>{formikFirst.errors.email}</div> : null}
                                </div>
                                <div className='input-phone'>
                                    <label htmlFor="phone">휴대폰 번호:</label>
                                    <input
                                        type="text"
                                        id="phone"
                                        name="phone"
                                        onChange={handlePhoneChange}
                                        onBlur={formikFirst.handleBlur}
                                        value={formikFirst.values.phone}
                                    />
                                    {formikFirst.touched.phone && formikFirst.errors.phone ? <div>{formikFirst.errors.phone}</div> : null}
                                </div>
                            </div>
                            <div className='btn-next'>
                                <button type="submit">다음</button>
                            </div>
                        </form>
                    )}
                    {section === 2 && (
                        <>
                            <form onSubmit={formikSecond.handleSubmit}>
                                <div className='register-form'>
                                    <div className='input-id'>
                                        <label htmlFor="name">이름:</label>
                                        <input
                                            type="text"
                                            id="name"
                                            name="name"
                                            onChange={formikSecond.handleChange}
                                            onBlur={formikSecond.handleBlur}
                                            value={formikSecond.values.name}
                                        /> 
                                        {formikSecond.touched.name && formikSecond.errors.name ? <div>{formikSecond.errors.name}</div> : null}
                                    </div>

                                    <div className='input-birth'>
                                        <label htmlFor="birth">생년월일:</label>
                                        <input
                                            type="text"
                                            id="birth"
                                            name="birth"
                                            onChange={handleBirthChange}
                                            onBlur={formikSecond.handleBlur}
                                            value={formikSecond.values.birth}
                                        />
                                        {formikSecond.touched.birth && formikSecond.errors.birth ? <div>{formikSecond.errors.birth}</div> : null}
                                    </div>

                                    <div className='input-gender'>
                                        <label>성별:</label>
                                        <div>
                                            <label>
                                                <input
                                                    type="radio"
                                                    id="male"
                                                    name="gender"
                                                    value="male"
                                                    onChange={formikSecond.handleChange}
                                                    onBlur={formikSecond.handleBlur}
                                                    checked={formikSecond.values.gender === "male"}
                                                />
                                                남성
                                            </label>
                                        </div>
                                        <div>
                                            <label>
                                                <input
                                                    type="radio"
                                                    id="female"
                                                    name="gender"
                                                    value="female"
                                                    onChange={formikSecond.handleChange}
                                                    onBlur={formikSecond.handleBlur}
                                                    checked={formikSecond.values.gender === "female"}
                                                />
                                                여성
                                            </label>
                                        </div>
                                        {formikSecond.touched.gender && formikSecond.errors.gender ? <div>{formikSecond.errors.gender}</div> : null}
                                    </div>

                                    <div className='input-nikename'>
                                        <label htmlFor="nickname">닉네임:</label>
                                        <input
                                            type="text"
                                            id="nickname"
                                            name="nickname"
                                            onChange={formikSecond.handleChange}
                                            onBlur={formikSecond.handleBlur}
                                            value={formikSecond.values.nickname}
                                        />
                                        {formikSecond.touched.nickname && formikSecond.errors.nickname ? <div>{formikSecond.errors.nickname}</div> : null}
                                    </div>
                                </div>
                            
                            
                                <div className='find-pw-question'>
                                    <label htmlFor="question">비밀번호 찾기 질문 / 답변</label>
                                    <select
                                        id="question"
                                        name="question"
                                        onChange={formikSecond.handleChange}
                                        onBlur={formikSecond.handleBlur}
                                        value={formikSecond.values.question}
                                    >
                                        <option value="" label="질문을 선택해주세요." />
                                        <option value="again" label="다시 태어나면 되고 싶은 것은?" />
                                        <option value="motto" label="자신의 인생 좌우명은?" />
                                        <option value="treasure" label="자신의 보물 제1호는?" />
                                        <option value="book" label="인상 깊게 읽은 책 이름은?" />
                                        <option value="character" label="내가 좋아하는 캐릭터는?" />
                                    </select>
                                    {formikSecond.touched.question && formikSecond.errors.question ? <div>{formikSecond.errors.question}</div> : null}
                                </div>
                                <div className='find-pw-answers'>
                                    <label htmlFor="answer">답변:</label>
                                    <input
                                        type="text"
                                        id="answer"
                                        name="answer"
                                        onChange={formikSecond.handleChange}
                                        onBlur={formikSecond.handleBlur}
                                        value={formikSecond.values.answer}
                                    />
                                    {formikSecond.touched.answer && formikSecond.errors.answer ? <div>{formikSecond.errors.answer}</div> : null}
                                </div>
                                <button type='submit'>제출하기</button>
                                </form>
                        </>
                    )}


                </div>
            </div>
        </>
    );
};

export default RegisterPage;