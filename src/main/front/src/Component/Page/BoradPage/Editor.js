import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import './Editor.css'
import { useEffect, useState } from 'react';
import DaumAddress from './DaumAddress';
import axios from 'axios';

const Editor = () => {
    const [checkMoim, setCheckMoim] = useState("free");
    const [title, setTitle] = useState(""); // 게시글 제목
    const [content, setContent] = useState(""); // 게시글 내용
    const [category, setCategory] = useState(""); // 카테고리
    const [dateTime, setDateTime] = useState(""); // 날짜 및 시간
    const [peopleLimited, setPeopleLimited] = useState(""); // 참여자 수
    const [ageLimit, setAgeLimit] = useState(""); // 나이 제한
    const [latitude, setLatitude] = useState(""); // 위도
    const [longitude, setLongitude] = useState(""); // 경도
    const [address, setAddress] = useState(""); // 주소
    const [detailAddress, setDetailAddress] = useState(''); // 상세주소




    const handleMoimSelectChange = (e) => {
        setCheckMoim(e.target.value);
        console.log(checkMoim)
    };


    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleContentChange = (event, editor) => {
        const data = editor.getData();
        setContent(data);
    };

    const handleCategoryChange = (event) => {
        setCategory(event.target.value);
    };

    const handleDateTimeChange = (event) => {
        const selectedDateTime = event.target.value; // input 요소에서 선택된 날짜 및 시간을 가져옵니다.

        // JavaScript Date 객체로 변환합니다.
        const selectedDate = new Date(selectedDateTime);

        // MySQL DATETIME 형식으로 변환합니다.
        const formattedDateTime = selectedDate.toISOString().slice(0, 19).replace('T', ' ');

        // 변환된 값을 상태 변수에 설정합니다.
        setDateTime(formattedDateTime);
    };

    const handlePeopleLimitedChange = (event) => {
        setPeopleLimited(event.target.value);
    };

    const handleAgeLimitChange = (event) => {
        setAgeLimit(event.target.value);
    };


    const handleDataChange = (data) => {
        setLatitude(data.latitude);
        setLongitude(data.longitude);
        setAddress(data.address);
    };
    const handleDetailAddressChange = (event) => {
        setDetailAddress(event.target.value);
    };

    const handleSubmit = async () => {

        const data = await {
            userId : localStorage.getItem("userId"), // 유저 아이디
            title : title, // 제목
            category : category, // 카테고리
            dateTime : dateTime, // 모임 시간
            latitude : latitude, // 위도
            longitude : longitude, // 경도
            address : address, // 주소
            detailAddress : detailAddress, // 상세 주소
            peopleLimited : peopleLimited, // 인원제한
            ageLimit : ageLimit, // 나이 제한
        }
        console.log(data)

        // try {
        //     await axios.post('/api/create-board',data)

        //     console.log('Data sent successfully!');
        // } catch (error) {
        //     console.error(error);
        // }


    };


    return (
        <>
            <div>
                <select value={checkMoim} onChange={handleMoimSelectChange}>
                    <option value="free">자유게시글</option>
                    <option value="moim">모임모임</option>
                </select>
            </div>

            {checkMoim === "moim" && (
                <div>
                    <div>
                        <DaumAddress onDataChange={handleDataChange} />
                    </div>
                    <div>
                        주소: {address}
                    </div>
                    <div>
                        <label>상세주소:</label>
                        <input
                            type="text"
                            value={detailAddress}
                            onChange={handleDetailAddressChange}
                        />
                    </div>
                    <div>
                        <label>카테고리:</label>
                        <select value={category} onChange={handleCategoryChange}>
                            <option value="">카테고리 선택</option>
                            <option value="스포츠">스포츠</option>
                            <option value="음악">음악</option>
                            <option value="예술">예술</option>
                            <option value="요리">요리</option>
                            <option value="여행">여행</option>
                            <option value="책">책</option>
                            <option value="IT/기술">IT/기술</option>
                            <option value="비즈니스">비즈니스</option>
                            <option value="사회활동">사회활동</option>
                            <option value="공예">공예</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div>
                        <label>날짜 및 시간:</label>
                        <input
                            type="datetime-local"
                            value={dateTime}
                            onChange={handleDateTimeChange}
                        />
                    </div>
                    <div>
                        <label>참여자 수:</label>
                        <input
                            type="number"
                            value={peopleLimited}
                            onChange={handlePeopleLimitedChange}
                        />
                    </div>
                    <div>
                        <label>나이 제한:</label>
                        <input
                            type="number"
                            value={ageLimit}
                            onChange={handleAgeLimitChange}
                        />
                    </div>
                </div>
            )}
            <div>
                <div className='cheditor-logo'>
                    <input
                        type="text"
                        value={title}
                        onChange={handleTitleChange}
                        placeholder="게시글 제목을 입력하세요"
                    />
                </div>

                <div className='cheditor-container'>
                    <CKEditor
                        // 생성자
                        editor={ClassicEditor}
                        //초기 데이터
                        data={''}
                        config={{
                            toolbar: [
                                'heading', '|',
                                'fontFamily', 'fontSize', '|',
                                'bold', 'italic', 'underline', 'strikethrough', '|',
                                'link', '|',
                                'alignment', '|',
                                'bulletedList', 'numberedList', '|',
                                'indent', 'outdent', '|',
                                'blockQuote', '|',
                                'undo', 'redo'
                            ],
                            fontFamily: {
                                options: [
                                    'Arial, Helvetica, sans-serif',
                                    'Courier New, Courier, monospace',
                                    'Georgia, serif',
                                    'Lucida Sans Unicode, Lucida Grande, sans-serif',
                                    'Tahoma, Geneva, sans-serif',
                                    'Times New Roman, Times, serif',
                                    'Trebuchet MS, Helvetica, sans-serif',
                                    'Verdana, Geneva, sans-serif'
                                ]
                            },
                            fontSize: {
                                options: [
                                    9, 11, 12, 14, 16, 18, 20, 24, 30, 36
                                ]
                            }

                        }}
                        onReady={editor => {
                            // You can store the "editor" and use when it is needed.
                            console.log('Editor is ready to use!', editor);
                        }}

                        onChange={handleContentChange}

                    // onBlur={(event, editor) => {
                    //     console.log('Blur.', editor);
                    // }}

                    // onFocus={(event, editor) => {
                    //     console.log('Focus.', editor);
                    // }}
                    />

                </div>
            </div>
            <button onClick={handleSubmit}>제출</button>
        </>
    )
}

export default Editor;