import { useEffect, useState } from "react";
import axios from "axios";
import './GetAllFreeBoardsByCategoty.css'
import { ListComponent } from "./ListComponent";
export const GetAllFreeBoardsByCategoty = () => {

    const [allFreeBoards, setAllFreeBoards] = useState([]);

    const getAllFreeBoards = async () => {
        axios.get('/api/get-all-free-boards')
            .then((res) => {
                setAllFreeBoards(res.data);
                console.log(allFreeBoards)
            })
    }

    


    useEffect(() => {
        getAllFreeBoards();

    }, [])


    return (
        <>
            <ListComponent list={allFreeBoards} />
        </>
    );

}