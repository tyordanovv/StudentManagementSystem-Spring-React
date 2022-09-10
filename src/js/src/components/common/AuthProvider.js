import { createContext, useContext, useEffect, useState } from "react";
import AuthService from "../services/auth.service";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(null);
  const user = AuthService.getCurrentUser();

  if (user && !auth) {
    setAuth(user);
  }

  const login = (user) => {
    setAuth(user);
  };

  const logout = () => {
    setAuth(null);
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};

export default AuthContext;
