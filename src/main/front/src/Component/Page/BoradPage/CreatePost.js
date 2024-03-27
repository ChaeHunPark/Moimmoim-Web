

import Header from "../../static/Header/Header"
import { useEffect, useRef, useState } from "react";
import './CreatePost.css'
import Editor from './Editor';

export const CreatePost = () => {




    useEffect(() => {
    }, []);




    return (
        <>

            <Header />
            <div className="create-post-body">
                <div className="create-post-container">
                    <Editor />
                </div>
            </div>
        </>
    )
}