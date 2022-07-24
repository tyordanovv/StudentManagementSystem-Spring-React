import axios from "axios";
import { apiUrls } from "../common/variables";
import { errorNotification } from "../common/Notification";

const register = (firstName, lastName, birthday, email, password, roles) => {
  const response = axios.post(apiUrls.Auth + "register", {
    firstName,
    lastName,
    birthday,
    email,
    password,
    roles,
  });
  console.log(response.data);
  return response.data;
};

const login = async (username, password) => {
  console.log("start");
  try {
    const response = await axios.post(apiUrls.Auth + "login", {
      username,
      password,
    });
    if (response.data.username) {
      console.log("user stored");
      localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
  } catch (error) {
    errorNotification(
      "Bad credentials",
      "Username or password that you have entered cannot be found!"
    );
  }
  // console.log(response.data);
  // return response.data;
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
