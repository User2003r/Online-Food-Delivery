import React, { useState } from "react";
import ProfileNavigation from "./ProfileNavigation";
import { Route, Routes } from "react-router-dom";
import UserProfile from "./UserProfile";
import Orders from "./Orders";
import Address from "./Address";
import Favourites from "./Favourites";
import Events from "./Events";

const Profile = () => {
  const [openSidebar, setOpenSidebar] = useState(true);
  return (
    <div className="lg:flex justify-between">
      <div className="sticky h-[80vh] lg:w-[20%]">
        <ProfileNavigation open={openSidebar} />
      </div>
      <div className="lg:w-[80%]">
        <Routes>
          <Route path="/orders" element={<Orders />} />
          <Route path="/address" element={<Address />} />
          <Route path="/favourites" element={<Favourites />} />
          <Route path="/events" element={<Events />} />
          <Route path="my-profile" element={<UserProfile />} />
        </Routes>
      </div>
    </div>
  );
};

export default Profile;
