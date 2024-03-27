export const getAllBoardsByCategory = async (category) => {
    try {
        const response = await axios.get('/api/get-all-free-boards');
        const allBoards = response.data;
        return allBoards[category] || [];
    } catch (error) {
        console.error('Error fetching boards:', error);
        return [];
    }
};