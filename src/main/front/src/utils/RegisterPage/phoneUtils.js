export const formatPhoneNumber = (input) => {
    const numericPhoneNumber = input.replace(/\D/g, '');
    let formattedPhoneNumber = '';
  
    if (numericPhoneNumber.length <= 3) {
      formattedPhoneNumber = numericPhoneNumber;
    } else if (numericPhoneNumber.length <= 7) {
      formattedPhoneNumber = `${numericPhoneNumber.slice(0, 3)}-${numericPhoneNumber.slice(3)}`;
    } else if (numericPhoneNumber.length === 10) {
      formattedPhoneNumber = `${numericPhoneNumber.slice(0, 3)}-${numericPhoneNumber.slice(3, 6)}-${numericPhoneNumber.slice(6, 10)}`;
    } else {
      formattedPhoneNumber = `${numericPhoneNumber.slice(0, 3)}-${numericPhoneNumber.slice(3, 7)}-${numericPhoneNumber.slice(7, 11)}`;
    }
  
    return formattedPhoneNumber;
  };