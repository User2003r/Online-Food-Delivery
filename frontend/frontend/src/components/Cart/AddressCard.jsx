import { Button, Card } from "@mui/material";
import React from "react";
import HomeIcon from "@mui/icons-material/Home";

const AddressCard = ({ item, showButton, handleSelectAddress }) => {
  return (
    <div>
      <Card className="flex gap-5 w-64 p-5">
        <HomeIcon />
        <div className="space-y-3 text-gray-500">
          <h2 className="text-lg text-white font-semibold">Home</h2>
          <p>V.P.O Panjoa Ladoli Teh. Amb Distt. Una Himachal Pradesh</p>
          {showButton && (
            <Button
              variant="outlined"
              fullWidth
              onClick={() => handleSelectAddress(item)}
            >
              Select
            </Button>
          )}
        </div>
      </Card>
    </div>
  );
};

export default AddressCard;
