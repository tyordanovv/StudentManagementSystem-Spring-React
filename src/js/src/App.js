/* eslint-disable no-lone-blocks */
import React from "react";
import "./App.css";
import AuthService from "./components/services/auth.service";
import Login from "./components/auth/Login";
import Home from "./components/pages/Home";
import { useAuth } from "./components/common/AuthProvider";
import { useNavigate, Route, Routes } from "react-router-dom";
import { RequireAuth } from "./components/auth/RequireAuth";

function App() {
  const auth = useAuth();
  const navigate = useNavigate();

  function handleLogout() {
    AuthService.logout();
    auth.logout();
    navigate("/login");
  }
  return (
    <Routes>
      <Route
        path="/home"
        element={
          <RequireAuth>
            <Home handleLogout={handleLogout} />
          </RequireAuth>
        }
      />
      <Route path="/login" element={<Login />} />
    </Routes>
  );
}

export default App;
