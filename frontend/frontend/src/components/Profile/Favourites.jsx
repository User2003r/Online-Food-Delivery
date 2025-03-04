import React from "react";
import RestaurantCard from "../Restaurant/RestaurantCard";

const Favourites = () => {
  return (
    <div>
      <h1 className="py-5 text-xl font-semibold text-center">My Favourites</h1>
      <div className="flex flex-wrap  gap-3 justify-center">
        {[1, 1, 1].map((favourite, i) => {
          return <RestaurantCard key={i} />;
        })}
      </div>
    </div>
  );
};

export default Favourites;
