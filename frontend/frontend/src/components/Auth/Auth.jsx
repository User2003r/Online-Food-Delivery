import { Box, Modal } from "@mui/material";
import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import RegisterForm from "./RegisterForm";
import Login from "./Login";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  outline: "none",
  p: 4,
};

const Auth = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleOnClose = () => {
    navigate("/");
  };

  return (
    <>
      <Modal
        open={
          location.pathname == "/account/register" ||
          location.pathname == "/account/login"
        }
        onClose={handleOnClose}
      >
        <Box sx={style}>
          {location.pathname == "/account/register" ? (
            <RegisterForm />
          ) : (
            <Login />
          )}
        </Box>
      </Modal>
    </>
  );
};

export default Auth;
