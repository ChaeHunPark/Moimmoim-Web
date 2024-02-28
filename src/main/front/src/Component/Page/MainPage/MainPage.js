import { useEffect } from "react";
import Header from "../../static/Header/Header"
import PopularPosts from "./PopularPosts";
import { PageTracker } from "../../../api/PageTracker";

const MainPage = () => {

    //페이지 트랙커
    useEffect(() => {
    }, [])

    return (<>
        <Header />
        <PopularPosts />
    </>)
}

export default MainPage;