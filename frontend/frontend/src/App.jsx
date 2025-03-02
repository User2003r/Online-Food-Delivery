import "./App.css";
import { ThemeProvider } from "@mui/material";
import Navbar from "./components/Navbar/Navbar";
import { darkTheme } from "../Theme/DarkTheme";
import { CssBaseline } from "@mui/material";

import { BrowserRouter } from "react-router-dom";
import CustomerRouter from "./components/Routers/CustomerRouter";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./components/State/Authentication/Action";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const jwtFromStore = useSelector((store) => store.auth.jwt);
  useEffect(() => {
    dispatch(getUser(jwtFromStore || jwt));
  }, [jwtFromStore]);
  return (
    <div>
      <BrowserRouter>
        <ThemeProvider theme={darkTheme}>
          <CssBaseline />
          {/* <Navbar />
          <Home /> */}
          <CustomerRouter />
        </ThemeProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;
