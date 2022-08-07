import axios from "axios";
import { apiUrls } from "../common/variables";

const getTeachers = async () => {
  axios.get(apiUrls.Users + "teachers/");
};

const SchoolStuff = {
  getTeachers,
};

export default SchoolStuff;
