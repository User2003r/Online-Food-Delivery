import React from "react";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { Button } from "@mui/material";

const UserProfile = () => {
  const handleLogout = () => {};
  return (
    <div>
      <div className="min-h-[80vh] flex flex-col justify-center items-center text-center">
        <div className="flex flex-col items-center justify-center">
          <AccountCircleIcon sx={{ fontSize: "9rem" }} />
          <h1 className="py-5 text-2xl font-semibold">Code With Zosh</h1>
          <p>Email: codewith@gmail.com</p>
          <Button
            sx={{ margin: "2rem 0rem" }}
            onClick={handleLogout}
            variant="contained"
          >
            Logout
          </Button>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;
