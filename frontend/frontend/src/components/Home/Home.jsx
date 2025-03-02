import React, { useEffect, useState } from "react";
import "./Home.css";
import MultiItemCarousal from "./MultiItemCarousal";
import RestaurantCard from "../Restaurant/RestaurantCard";
import { restaurantData } from "../Restaurant/RestaurantData";
import { useDispatch, useSelector } from "react-redux";
import { getRestaurantsAction } from "../State/Restaurant/Action";

const Home = () => {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  useEffect(() => {
    dispatch(getRestaurantsAction(jwt));
  }, []);

  const restaurant = useSelector((store) => store.restaurant.restaurant);
  console.log(restaurant);
  return (
    <div>
      <section className="banner  relative flex flex-col justify-center items-center">
        <div className="w-[50vw] z-10 text-center">
          <p className="text-2xl lg:text-6xl font-bold z-10 py-5">Zosh Food</p>
          <p className="z-10 text-gray-300 text-xl lg:text-4xl">
            Taste the convenience: Food, Fast and Delivered.{" "}
          </p>
        </div>
        <div className="cover absolute top-0 left-0 right-0"></div>
        <div className="fadeout"></div>
      </section>
      <section className="p-10 lg:py-10 lg:px-20">
        <p className="text-2xl font-semibold text-gray-400 py-3 pb-10 text-center">
          Top Meals
        </p>
        <MultiItemCarousal />
      </section>
      <section>
        <h1 className="text-center text-gray-400 py-3  text-2xl font-semibold">
          Order From Our Handpicked Favourites
        </h1>
        <div className="flex flex-wrap justify-evenly">
          {restaurant.map((item, i) => {
            return <RestaurantCard item={item} key={i} />;
          })}
        </div>
      </section>
    </div>
  );
};

export default Home;
