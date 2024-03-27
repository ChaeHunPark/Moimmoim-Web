
import { useState } from "react";
import Header from "../../../static/Header/Header";
import { GetAllFreeBoardsByCategoty } from "./Component/GetAllFreeBoardsByCategoty";






const FreeBoardListPage = () => {
    const [selectedCategory, setSelectedCategory] = useState('전체보기');

    const categories = [
        '전체보기',
        '스포츠',
        '음악',
        '예술',
        '요리',
        '여행',
        '책',
        'IT/기술',
        '비즈니스',
        '사회활동',
        '공예',
        '기타'
    ];

    const handleCategoryChange = (category) => {
        setSelectedCategory(category);
    };

    return (
        <>
            <Header />
            <div>
                <h2>카테고리 선택</h2>
                <ul>
                    {categories.map(category => (
                        <li key={category}>
                            <button onClick={() => handleCategoryChange(category)}>{category}</button>
                        </li>
                    ))}
                </ul>
                
            </div>
            <p>선택한 카테고리: {selectedCategory}</p>
            {selectedCategory === "전체보기" && ((
                <GetAllFreeBoardsByCategoty/>
            ))}


        </>
    )
}

export default FreeBoardListPage;