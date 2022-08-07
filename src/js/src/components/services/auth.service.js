import axios from "axios";
import { apiUrls } from "../common/variables";
import { errorNotification, infoNotification } from "../common/Notification";

const register = async (user, onClose) => {
  axios
    .post(apiUrls.Auth + "register", user)
    .then(function (response) {
      if (response.status === 200) {
        console.log("ok");
        onClose();
        infoNotification("Success", "Registered user");
      }
    })
    .catch(function (error) {
      console.log(error);
      errorNotification(
        "Something went wrong",
        "Check user details! Current user may already exists!"
      );
    });
};

// axios.post((apiUrls.Auth + "register", props));
const login = async (username, password) => {
  console.log("start");
  const response = await axios
    .post(apiUrls.Auth + "login", {
      username,
      password,
    })
    .catch(function (error) {
      errorNotification(
        "Bad credentials",
        "Username or password that you have entered cannot be found!"
      );
    });
  if (response.data.username) {
    console.log("user stored");
    localStorage.setItem("user", JSON.stringify(response.data));
  }
  return response.data;

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
