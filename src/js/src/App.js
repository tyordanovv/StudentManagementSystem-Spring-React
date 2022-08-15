/* eslint-disable no-lone-blocks */
import React, { useEffect, useState, useContext } from "react";
import "./App.css";
import AuthService from "./components/services/auth.service";
import Login from "./components/auth/Login";
import Home from "./components/pages/Home";
import { userTypes } from "./components/common/variables";
import TeacherHomepage from "./components/pages/TeacherHomepage";
import StudentHomepage from "./components/pages/StudentHomepage";
import AuthContext from "./components/common/AuthProvider";

function App() {
  const [currentUser, setCurrentUser] = useState(AuthService.getCurrentUser());
  const [adminPage, setAdminPage] = useState(false);
  const [assistantPage, setAssistantPage] = useState(false);
  const [studentPage, setStudentPage] = useState(false);
  const [teacherPage, setTeacherPage] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const { auth } = useContext(AuthContext);

  // useEffect(() => {
  //   const loggedInUser = localStorage.getItem("user");
  //   if (loggedInUser) {
  //     const foundUser = JSON.parse(loggedInUser);
  //     setCurrentUser(foundUser);
  //   }
  // }, []);
  // useEffect(() => {
  //   if (!loggedIn) {
  //     return (
  //       <Login handleLogin={handleLogin} setCurrentUser={setCurrentUser} />
  //     );
  //   } else {
  //     if (adminPage || assistantPage) {
  //       return <Home user={auth} handleLogout={handleLogout} />;
  //     } else if (studentPage) {
  //       return <StudentHomepage user={auth} handleLogout={handleLogout} />;
  //     } else if (teacherPage) {
  //       return <TeacherHomepage user={auth} handleLogout={handleLogout} />;
  //     }
  //   }
  // }, loggedIn);

  useEffect(() => {
    async function handleLogin() {
      console.log(12);
      console.log(auth);
      if (auth.roles) {
        setCurrentUser(auth);
        if (auth.roles.includes(userTypes.Admin)) {
          setAdminPage(true);
          console.log(auth.roles);
        } else if (auth.roles.includes(userTypes.Student)) {
          setStudentPage(true);
          console.log(auth.roles);
        } else if (auth.roles.includes(userTypes.Teacher)) {
          setTeacherPage(true);
          console.log(auth.roles);
        } else if (auth.roles.includes(userTypes.Assistant)) {
          setAssistantPage(true);
          console.log(auth.roles);
        }
        setLoggedIn(true);
        console.log("logged in");
      }
    }

    handleLogin();
  }, [auth]);

  if (!loggedIn) {
    console.log(1);
    return <Login setCurrentUser={setCurrentUser} />;
  } else {
    console.log(2);
    if (adminPage || assistantPage) {
      return <Home user={auth} handleLogout={handleLogout} />;
    } else if (studentPage) {
      return <StudentHomepage user={auth} handleLogout={handleLogout} />;
    } else if (teacherPage) {
      return <TeacherHomepage user={auth} handleLogout={handleLogout} />;
    }
  }

  function handleLogout() {
    AuthService.logout();
    setCurrentUser(null);
    setAdminPage(false);
    setLoggedIn(false);
  }
}

export default App;
