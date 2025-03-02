import { Chip, Divider, IconButton } from "@mui/material";
import React, { useState } from "react";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";

const CartItem = () => {
  const [quantity, setQuantity] = useState(1);

  const addQuantity = () => {
    console.log("click");
    setQuantity(quantity + 1);
  };

  const subQuantity = () => {
    setQuantity(quantity - 1);
  };

  return (
    <div className="px-5">
      <div className="lg:flex items-center lg:space-x-5">
        <div>
          <img
            className=" h-[5rem] object-cover"
            src={
              "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_1280.jpg"
            }
            alt=""
          />
        </div>
        <div className="flex items-center justify-between lg:w-[70%]">
          <div className="space-y-1 lg:space-y-3 w-full">
            <p>Biryani</p>
            <div className="flex justify-between items-center">
              <div className="flex items-center space-x-1">
                <IconButton>
                  <RemoveCircleOutlineIcon />
                </IconButton>
                <div className="w-5 h-5 text-xs flex items-center justify-center">
                  {quantity}
                </div>
                <IconButton onChange={addQuantity}>
                  <AddCircleOutlineIcon />
                </IconButton>
              </div>
            </div>
          </div>
          <p>Rs1900</p>
        </div>
      </div>
      <div className="py-3 space-x-2">
        {[1, 1, 1, 1].map((item) => (
          <Chip label="bread" />
        ))}
      </div>
      <Divider />
    </div>
  );
};

export default CartItem;
