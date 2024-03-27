

import Header from "../../../static/Header/Header"
import { useEffect} from "react";
import './CreateBoardPage.css'
import Editor from './Component/Editor';

const CreateBoardPage = () => {




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

export default CreateBoardPage;