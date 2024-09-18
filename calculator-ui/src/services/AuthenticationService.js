import axios from "axios";

const AUTHENTICATION_URL = "http://localhost:8080/api/auth/login";

export const authenticate = async (username, password) => axios.post(AUTHENTICATION_URL, { username, password });