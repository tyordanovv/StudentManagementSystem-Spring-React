import { useAuth } from "../common/AuthProvider";
import { Navigate } from "react-router-dom";

export const RequireAuth = ({ children }) => {
  const auth = useAuth();

  console.log(7);

  if (!auth.auth) {
    console.log(auth.auth);

    return <Navigate to="/login" />;
  }

  return children;
};
