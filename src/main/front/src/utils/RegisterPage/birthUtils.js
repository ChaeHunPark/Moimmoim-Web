export const formatBirthNumber = (input) => {
    const birthNumber = input.replace(/\D/g, '');
    let formattedBirthNumber = '';
  
    if (birthNumber.length <= 4) {
      formattedBirthNumber = birthNumber;
    } else if (birthNumber.length <= 6) {
      formattedBirthNumber = `${birthNumber.slice(0, 4)}-${birthNumber.slice(4)}`;
    } else{
      formattedBirthNumber = `${birthNumber.slice(0, 4)}-${birthNumber.slice(4, 6)}-${birthNumber.slice(6, 8)}`;
    }
  
    return formattedBirthNumber;
  };