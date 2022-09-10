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
  return await axios
    .post(
      apiUrls.Auth + "login",
      JSON.stringify({
        username,
        password,
      }),
      {
        headers: { "Content-Type": "application/json" },
        withCredentials: true,
      }
    )
    .then(function (response) {
      if (response?.data) {
        console.log(response?.data);
        localStorage.setItem("user", JSON.stringify(response?.data));
        console.log(response.data);
        // window.location.reload(false);
      }
    })
    .catch(function (error) {
      if (!error?.response) {
        errorNotification("Bad credentials", "No response");
      } else if (error.response?.status === 400) {
        errorNotification(
          "Bad credentials",
          "Username or password is missing!"
        );
      } else if (error.response?.status === 401) {
        errorNotification(
          "Bad credentials",
          "Username or password that you have entered cannot be found!"
        );
      } else {
        errorNotification("Bad credentials", "Login failed!");
      }
    });
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
