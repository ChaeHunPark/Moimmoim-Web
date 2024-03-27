export const ListComponent = (props) => {
    return (
        <>
            {props.list.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>작성일</th>
                            <th>카테고리</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.list.map((board) => (
                            <tr key={board.freeBoardKey}>
                                <td>{board.freeBoardKey}</td>
                                <td>{board.title}</td>
                                <td>{board.member.name}</td> 
                                <td>{board.createdAt}</td>
                                <td>{board.category}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p>게시글이 없습니다.</p>
            )}
        </>
    )
}

