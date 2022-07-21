import axios from "axios";
import { apiUrls } from "../common/variables";

const register = (username, email, password) => {
  return axios.post(apiUrls.Auth + "register", {
    username,
    email,
    password,
  });
};

const login = async (username, password) => {
  const response = await axios.post(apiUrls.Auth + "login", {
    username,
    password,
  });
  if (response.data.username) {
    console.log("user stored");
    localStorage.setItem("user", JSON.stringify(response.data));
  }
  console.log(response.data);
  return response.data;
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;
