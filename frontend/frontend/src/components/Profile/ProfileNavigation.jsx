import React from "react";
import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import FavoriteIcon from "@mui/icons-material/Favorite";
import HomeIcon from "@mui/icons-material/Home";
import AccountBalanceWalletIcon from "@mui/icons-material/AccountBalanceWallet";
import NotificationsActiveIcon from "@mui/icons-material/NotificationsActive";
import EventIcon from "@mui/icons-material/Event";
import LogoutIcon from "@mui/icons-material/Logout";
import { Divider, Drawer, useMediaQuery } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { logout } from "../State/Authentication/Action";
const menu = [
  {
    id: 1,
    title: "Orders",
    icon: <ShoppingBagIcon />,
  },
  {
    id: 2,
    title: "Favourites",
    icon: <FavoriteIcon />,
  },
  {
    id: 3,
    title: "Address",
    icon: <HomeIcon />,
  },
  {
    id: 4,
    title: "Payment",
    icon: <AccountBalanceWalletIcon />,
  },
  {
    id: 5,
    title: "Notification",
    icon: <NotificationsActiveIcon />,
  },
  {
    id: 6,
    title: "Events",
    icon: <EventIcon />,
  },
  {
    id: 7,
    title: "Logout",
    icon: <LogoutIcon />,
  },
];

const ProfileNavigation = ({ open, handleClose }) => {
  const isSmallScreen = useMediaQuery("(max-width:900px)");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleNavigation = (item) => {
    if (item.title === "Logout") {
      dispatch(logout());
      navigate("/");
    } else {
      navigate(`/my-profile/${item.title.toLowerCase()}`);
    }
  };
  return (
    <div>
      <Drawer
        sx={{ zIndex: 1 }}
        open={open}
        anchor="left"
        onClose={handleClose}
        variant={isSmallScreen ? "temporary" : "permanent"}
      >
        <div className="w-[50vw] lg:w-[20vw] h-[100vh] flex flex-col justify-center gap-8 pt-16">
          {menu.map((item, i) => {
            return (
              <>
                <div
                  key={i}
                  onClick={() => handleNavigation(item)}
                  className="flex px-5 items-center space-x-5 cursor-pointer"
                >
                  <div>{item.icon}</div>
                  <span>{item.title}</span>
                </div>
                {i != menu.length - 1 && <Divider />}
              </>
            );
          })}
        </div>
      </Drawer>
    </div>
  );
};

export default ProfileNavigation;
