import axios from "axios";
import { apiUrls } from "../common/variables";
import authHeader from "./auth.header";

const saveSubject = async (subject, onClose) => {
  let token = JSON.parse(localStorage.getItem("user")).accessToken;
  let subjectJSON = JSON.stringify(subject);
  console.log(subjectJSON);
  let axiosConfig = {
    headers: {
      Authorization: "Bearer " + token,
      "Content-Type": "application/json;charset=UTF-8",
      "Access-Control-Allow-Origin": "*",
    },
  };
  // axios
  //   .post(apiUrls.SchoolStuff + "save/subject", subjectJSON, axiosConfig)
  //   .then();
};

const SchoolStuff = {
  saveSubject,
};

export default SchoolStuff;
