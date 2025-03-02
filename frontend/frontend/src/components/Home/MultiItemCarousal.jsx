import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { topMeals } from "./Data";
import React from "react";
import Slider from "react-slick";
import CarouselItem from "./CarouselItem";

const MultiItemCarousal = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 3,
    autoplay: true,
    arrows: false,
  };

  return (
    <div>
      <Slider {...settings}>
        {topMeals.map((item, i) => {
          return <CarouselItem image={item.image} title={item.title} key={i} />;
        })}
      </Slider>
    </div>
  );
};

export default MultiItemCarousal;
