import { useEffect } from "react";
import Header from "../../static/Header/Header"
import PopularPosts from "./PopularPosts";

const MainPage = () => {


    useEffect(() => {
    }, [])

    return (<>
        <Header />
        <PopularPosts />
    </>)
}

export default MainPage;