import ClassicEditor from "@ckeditor/ckeditor5-build-classic"
import { CKEditor } from "@ckeditor/ckeditor5-react"
import UploadAdapter from '@ckeditor/ckeditor5-upload/src/';
import Header from "../../static/Header/Header"
import { useEffect, useState } from "react";
import axios from "axios";

export const CreatePost = () => {

    const [title, setTitle] = useState('');
    const [board, setBoard] = useState('');
    const [category, setCategory] = useState('');
    const [content, setContent] = useState('');

    const [editor, setEditor] = useState();

    useEffect(() => {
      if (editor) {
        // 업로드 어댑터를 설정
        editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
          return new UploadAdapter(editor);
        };
      }
    }, [editor]);

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        try {
            // 폼 데이터를 서버로 전송
            const response = await axios.post('/api/post-data', {
                title,
                board,
                category,
                content,
            });

            console.log('Server response:', response.data);
        } catch (error) {
            console.error('Error submitting form:', error);
        }
    };


    return (
        <>
            <Header />

            <div className="create-board-body">
                <div className="create-board-container">
                    <div className="create-board-logo">글쓰기</div>
                    <div className="create-board-form-box">
                        <form onSubmit={handleFormSubmit}>
                            <div className="create-board-title"><input type="text" id="title" value={title} placeholder="제목 입력" onChange={(e) => setTitle(e.target.value)} /></div>
                            
                            <span className="create-board-category">
                            <select id="board" value={board} onChange={(e) => setBoard(e.target.value)}>
                                <option value="on">온 모임</option>
                                <option value="off">오프 모임</option>
                                <option value="free">자유 게시판</option>
                            </select>
                            </span>

                            <CKEditor editor={ClassicEditor} data={content}
                                onChange={(event, editor) => {
                                    const data = editor.getData();
                                    setContent(data);
                                    console.log(data)
                                }}
                            />

                            <button type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            </div>

        </>
    )
}