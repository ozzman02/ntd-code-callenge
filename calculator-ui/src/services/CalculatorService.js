import axios from "axios";

const CALCULATOR_SERVICE_URL = "http://localhost:8080/api/calculator";

export const generateMathematicalExpression = async (authHeader) => axios.get(CALCULATOR_SERVICE_URL + '/generate', { headers: authHeader });

export const calculateResult = async (authHeader, mathExpr) => axios.post(CALCULATOR_SERVICE_URL, mathExpr, { headers: authHeader });

export const getUserRecords = (authHeader, userId, pageNumber) => axios.get(CALCULATOR_SERVICE_URL + '/records/' + userId + '/page/' + pageNumber, { headers: authHeader }); 