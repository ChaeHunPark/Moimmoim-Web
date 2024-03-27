import axios from "axios";

export const Register = async (formData, setResData) => {
    await axios.post('/api/register', formData)
        .then((response) => {
            console.log(response.data)
            setResData(response.data);
            alert(response.data);

        })
        .catch((err) => {
            alert(err)
        })
};