import React, { useEffect, useState } from "react";
import "./App.css";
import AuthService from "./components/services/auth.service";
import Login from "./components/auth/Login";
import Home from "./components/pages/Home";
import { userTypes } from "./components/common/variables";

function App() {
  function handleLogin() {
    setCurrentUser(AuthService.getCurrentUser());
    console.log("1");
    if (currentUser) {
      console.log("2");
      if (currentUser.roles.includes(userTypes.Admin)) {
        console.log("3");
        setAdminPage(true);
      }
    }
    setLoggedIn(true);
  }

  function handleLogout() {
    AuthService.logout();
    setCurrentUser(undefined);
    setAdminPage(false);
    setLoggedIn(false);
  }
  const [currentUser, setCurrentUser] = useState(AuthService.getCurrentUser());
  const [adminPage, setAdminPage] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    if (currentUser) {
      if (currentUser.roles.includes(userTypes.Admin)) {
        console.log("3");
        setCurrentUser(currentUser);
        setAdminPage(true);
        setLoggedIn(true);
      }
    }
  }, [currentUser, loggedIn]);

  if (adminPage && loggedIn) {
    console.log("da");
    return <Home user={currentUser} handleLogout={handleLogout} />;
  } else return <Login handleLogin={handleLogin} />;
}
export default App;
