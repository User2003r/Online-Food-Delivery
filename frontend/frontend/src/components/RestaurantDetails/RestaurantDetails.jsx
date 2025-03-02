import {
  Divider,
  FormControl,
  FormControlLabel,
  Grid2,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import MenuCard from "./MenuCard";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import {
  getRestaurantByid,
  getRestaurantCategory,
} from "../State/Restaurant/Action";

const categories = ["Pizza", "Biryani", "Burger", "Chicken", "Rice"];

const food = [
  {
    label: "All",
    value: "all",
  },
  {
    label: "Vegeterian only",
    value: "vegetarian",
  },
  {
    label: "Non-Vegetarian",
    value: "non-veg",
  },
  {
    label: "Seasonal",
    value: "seasonal",
  },
];

const RestaurantDetails = () => {
  const [foodTypes, setFoodTypes] = useState("all");
  const [category, setCategory] = useState("Pizza");

  const handleFilter = (e) => {
    setFoodTypes(e.target.value);
    setCategory(e.target.value);
  };

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { auth, restaurant } = useSelector((store) => store);
  const jwt = localStorage.getItem("jwt");
  const { id, city } = useParams();
  console.log(restaurant);
  useEffect(() => {
    dispatch(getRestaurantByid({ jwt, restaurantId: parseInt(id) }));
    dispatch(getRestaurantCategory({ jwt }));
  }, []);

  console.log(restaurant);

  return (
    <div className="px-5 lg:px-20">
      <section>
        <h3 className="text-gray-500 py-2 mt-10"></h3>
        <div>
          <Grid2 container spacing={1}>
            <Grid2 size={{ xs: 12 }}>
              <img
                className="w-full h-[40vh] object-cover"
                src="https://t4.ftcdn.net/jpg/08/17/31/35/240_F_817313551_ge3syDT8tzef3cHhDT6HPh3V5ok3RWeF.jpg"
                alt=""
              />
            </Grid2>
            <Grid2 size={{ xs: 12, lg: 6 }}>
              <img
                className="w-full h-[40vh] object-cover"
                src="https://t4.ftcdn.net/jpg/09/77/85/07/240_F_977850735_BTGfyZdQDdqfnqoY1KbOSD7dP9Tw75eG.jpg"
                alt=""
              />
            </Grid2>
            <Grid2 size={{ xs: 12, lg: 6 }}>
              <img
                className="w-full h-[40vh] object-cover"
                src="https://t3.ftcdn.net/jpg/08/97/71/72/240_F_897717212_rJIjkaS8sDEwBEbDp38078x9ESZXjPfx.jpg"
                alt=""
              />
            </Grid2>
          </Grid2>
        </div>
        <div className="pt-3 pb-5">
          <h1 className="text-4xl font-semibold">
            {restaurant.restaurant?.name}
          </h1>
          <p className="text-gray-500 my-2">
            <span>{restaurant.restaurant?.description}</span>
          </p>
          <p className="text-gray-500 my-2">
            <LocationOnIcon />
            <span className="ml-2">{`${city} ${restaurant.restaurant?.address.state} ${restaurant.restaurant?.address.country}`}</span>
          </p>
          <p className="text-gray-500">
            <CalendarTodayIcon />
            <span className="ml-2">{restaurant.restaurant?.openingHours}</span>
          </p>
        </div>
      </section>
      <Divider />
      <section className="pt-[2rem] lg:flex relative">
        <div className="space-y-10 lg:w-[20%] filter">
          <div className="box space-y-5 lg:sticky top-28">
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Type
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup
                  onChange={handleFilter}
                  value={foodTypes}
                  name="food_types"
                >
                  {food.map((item, i) => {
                    return (
                      <FormControlLabel
                        key={i}
                        label={item.label}
                        control={<Radio />}
                        value={item.value}
                      />
                    );
                  })}
                </RadioGroup>
              </FormControl>
            </div>
            <Divider />
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Categories
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup
                  onChange={handleFilter}
                  value={category}
                  name="food_types"
                >
                  {categories.map((item, i) => {
                    return (
                      <FormControlLabel
                        key={i}
                        label={item}
                        control={<Radio />}
                        value={item}
                      />
                    );
                  })}
                </RadioGroup>
              </FormControl>
            </div>
          </div>
        </div>
        <div className="space-y-5 lg:w-[80%] lg:pl-10">
          <MenuCard />
        </div>
      </section>
    </div>
  );
};

export default RestaurantDetails;
