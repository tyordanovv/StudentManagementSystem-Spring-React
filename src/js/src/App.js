/* eslint-disable no-lone-blocks */
import React, { useEffect, useState } from "react";
import "./App.css";
import AuthService from "./components/services/auth.service";
import Login from "./components/auth/Login";
import Home from "./components/pages/Home";
import { userTypes } from "./components/common/variables";
import TeacherHomepage from "./components/pages/TeacherHomepage";
import StudentHomepage from "./components/pages/StudentHomepage";

function App() {
  const [currentUser, setCurrentUser] = useState(AuthService.getCurrentUser());
  const [adminPage, setAdminPage] = useState(false);
  const [assistantPage, setAssistantPage] = useState(false);
  const [studentPage, setStudentPage] = useState(false);
  const [teacherPage, setTeacherPage] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);

  // useEffect(() => {
  //   setCurrentUser(localStorage.getItem("user"));
  //   if (currentUser) {
  //     console.log(currentUser);
  //     console.log(currentUser.roles);
  //     if (currentUser.roles.includes(userTypes.Admin)) {
  //       setAdminPage(true);
  //       console.log(currentUser.roles);
  //     } else if (currentUser.roles.includes(userTypes.Student)) {
  //       setStudentPage(true);
  //       console.log(currentUser.roles);
  //     } else if (currentUser.roles.includes(userTypes.Teacher)) {
  //       setTeacherPage(true);
  //       console.log(currentUser.roles);
  //     } else if (currentUser.roles.includes(userTypes.Assistant)) {
  //       setAssistantPage(true);
  //       console.log(currentUser.roles);
  //     }
  //     setLoggedIn(true);
  //     console.log("logged in");
  //   }
  // }, [currentUser]);
  useEffect(() => {
    const loggedInUser = localStorage.getItem("user");
    if (loggedInUser) {
      const foundUser = JSON.parse(loggedInUser);
      setCurrentUser(foundUser);
    }
  }, []);

  if (!loggedIn) {
    return <Login handleLogin={handleLogin} />;
  } else {
    if (adminPage || assistantPage) {
      return <Home user={currentUser} handleLogout={handleLogout} />;
    } else if (studentPage) {
      return <StudentHomepage user={currentUser} handleLogout={handleLogout} />;
    } else if (teacherPage) {
      return <TeacherHomepage user={currentUser} handleLogout={handleLogout} />;
    }
  }

  async function handleLogin() {
    setCurrentUser(AuthService.getCurrentUser());
    console.log(currentUser);
    if (currentUser) {
      console.log("da");
      if (currentUser.roles.includes(userTypes.Admin)) {
        setAdminPage(true);
        console.log(currentUser.roles);
      } else if (currentUser.roles.includes(userTypes.Student)) {
        setStudentPage(true);
        console.log(currentUser.roles);
      } else if (currentUser.roles.includes(userTypes.Teacher)) {
        setTeacherPage(true);
        console.log(currentUser.roles);
      } else if (currentUser.roles.includes(userTypes.Assistant)) {
        setAssistantPage(true);
        console.log(currentUser.roles);
      }
      setLoggedIn(true);
      console.log("logged in");
    }
  }

  function handleLogout() {
    AuthService.logout();
    setCurrentUser(undefined);
    setAdminPage(false);
    setLoggedIn(false);
  }

  // useEffect(() => {
  //   if (currentUser) {
  //     if (currentUser.roles.includes(userTypes.Admin)) {
  //       console.log("3");
  //       setCurrentUser(currentUser);
  //       setAdminPage(true);
  //       setLoggedIn(true);
  //       this.forceUpdate();
  //     }
  //   }
  // }, [currentUser]);
}

// if (adminPage && loggedIn) {
//   console.log("da");
//   return <Home user={currentUser} handleLogout={handleLogout} />;
// } else return <Login handleLogin={handleLogin} />;
export default App;
