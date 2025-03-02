import { Badge, IconButton } from "@mui/material";
import "./Navbar.css";
import React from "react";
import SearchIcon from "@mui/icons-material/Search";
import { Avatar } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Person } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { store } from "../State/Authentication/Store";

const Navbar = () => {
  const navigate = useNavigate();
  const auth = useSelector((store) => store.auth);

  const handleAvatarClick = () => {
    if (auth.user.role === "ROLE_CUSTOMER") {
      navigate("/my-profile");
    } else {
      navigate("/admin/restaurant");
    }
  };

  const handlenavigate = () => {
    navigate("/");
  };

  return (
    <div className="px-5 z-50 py-[.8rem] bg-[#e91e63] lg:px-20 flex justify-between sticky top-0">
      <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
        <li
          className="logo font-semibold  text-gray-100 text-2xl"
          onClick={handlenavigate}
        >
          Zosh Food
        </li>
      </div>
      <div className="flex items-center space-x-2 lg:space-x-10">
        <div className="">
          <IconButton>
            <SearchIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        </div>
        <div>
          {auth.user ? (
            <Avatar
              onClick={handleAvatarClick}
              sx={{ bgcolor: "orange", width: "56", height: "56" }}
            >
              {auth.user?.name[0].toUpperCase()}
            </Avatar>
          ) : (
            <IconButton onClick={() => navigate("/account/login")}>
              <Person />
            </IconButton>
          )}
        </div>
        <div>
          <IconButton>
            <Badge color="secondary" badgeContent={3}>
              <ShoppingCartIcon sx={{ fontSize: "1.7rem" }} />
            </Badge>
          </IconButton>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
