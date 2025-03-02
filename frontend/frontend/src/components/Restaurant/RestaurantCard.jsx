import { Card, Chip, Icon, IconButton } from "@mui/material";
import React from "react";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { addToFavourite } from "../State/Authentication/Action";
import { isPresentInFavourites } from "../config/logic";

const RestaurantCard = ({ item }) => {
  const { description, open } = item;
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const favouritesFromStore = useSelector((store) => store.auth.favourites);
  // console.log(auth);
  const jwt = localStorage.getItem("jwt");

  const handleAddToFavourites = () => {
    dispatch(addToFavourite({ restaurantId: item.id, jwt }));
  };

  const handleRestaurantNavigate = () => {
    if (open) {
      navigate(`/restaurant/${item.address.city}/${item.name}/${item.id}`);
    }
  };

  return (
    <Card className="m-5 w-[18rem]">
      <div
        className={`${open ? "cursor-pointer" : "cursor-not-allowed"} relative`}
      >
        <img
          className="rounded-t-md w-full h-[10rem] object-cover"
          src={item.image[0]}
          alt="image"
        />
        <Chip
          size="small"
          className="absolute top-2 left-2"
          color={`${open ? "success" : "error"}`}
          label={`${open ? "OPEN" : "CLOSED"}`}
        />
        <div className="">
          <div className="p-4 textpart lg:flex w-full justify-center">
            <div className="space-y-1">
              <p
                className="font-semibold text-lg"
                onClick={handleRestaurantNavigate}
              >
                {item.name}
              </p>
              <p className="text-gray-500 text-sm">{description}</p>
            </div>
            <div>
              <IconButton
                onClick={handleAddToFavourites}
                className="text-sm"
                color="error"
              >
                {isPresentInFavourites(favouritesFromStore, item) ? (
                  <FavoriteIcon />
                ) : (
                  <FavoriteBorderIcon />
                )}
              </IconButton>
            </div>
          </div>
        </div>
      </div>
    </Card>
  );
};

export default RestaurantCard;
