import axios from "axios";
import { apiUrls } from "../common/variables";

const getPublicContent = () => {
  return axios.get(apiUrls.Test + "all");
};

const getUserBoard = () => {
  return axios.get(apiUrls.Test + "user");
};

const getAdminBoard = () => {
  return axios.get(apiUrls.Test + "admin");
};

const UserService = {
  getAdminBoard,
  getPublicContent,
  getUserBoard,
};

export default UserService;
