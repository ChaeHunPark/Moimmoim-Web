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
                        </tr>
                    </thead>
                    <tbody>
                        {props.list.map((board) => (
                            <tr key={board.freeBoardKey}>
                                <td>{board.freeBoardKey}</td>
                                <td>{board.title}</td>
                                <td>{board.member.name}</td> {/* 예시: 글쓴이 이름 필드에 맞게 수정 */}
                                <td>{board.createdAt}</td> {/* 예시: 작성일 필드에 맞게 수정 */}
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

