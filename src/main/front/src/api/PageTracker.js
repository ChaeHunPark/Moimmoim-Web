import axios from "axios";

export const PageTracker = async (pageName) => {
    try {
        const response = await axios.post('/api' + pageName);
        console.log(pageName,' ',response.data);
    } catch (error) {
        console.error(`Error while logging page visit for ${pageName}:`, error);
    }
};

