import {
  Accordion,
  AccordionSummary,
  AccordionDetails,
  FormControlLabel,
  Button,
} from "@mui/material";
import React from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { FormGroup, Checkbox } from "@mui/material";

const ingredients = [
  {
    category: "Nuts & Seeds",
    ingredient: ["Cashews"],
  },
  {
    category: "Protien",
    ingredient: ["Ground Beef", "Beacon strips"],
  },
];
const MenuCard = () => {
  return (
    <Accordion slotProps={{ heading: { component: "h4" } }}>
      <AccordionSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1-content"
        id="panel1-header"
      >
        <div className="lg:flex items-center justify-between"></div>
        <div className="lg:flex items-center lg:gap-5">
          <img
            className="w-[7rem] object-cover"
            src={`https://cdn.pixabay.com/photo/2021/01/06/10/11/burger-5893927_1280.jpg`}
            alt=""
          />
          <div className="spacey-y-1 lg:space-y-5 lg:max-w-2xl">
            <p className="font-bold text-xl">Burger</p>
            <p>&#8377;499</p>
            <p className="text-gray-400">
              Lorem ipsum dolor sit amet consectetur adipisicing elit.
              Exercitationem magni voluptatem repellat neque ad cum, nulla
              temporibus hic quas nostrum?
            </p>
          </div>
        </div>
      </AccordionSummary>
      <AccordionDetails>
        <form>
          <div className="flex gap-5 flex-wrap">
            {ingredients.map((item) => {
              return (
                <div>
                  <p>{item.category}</p>
                  {item.ingredient.map((ingredient) => {
                    return (
                      <FormGroup>
                        <FormControlLabel
                          control={<Checkbox />}
                          label={ingredient}
                        />
                      </FormGroup>
                    );
                  })}
                </div>
              );
            })}
          </div>
          <Button variant="contained" disabled={false} type="submit">
            {true ? "Add to cart" : "out of stock"}
          </Button>
        </form>
      </AccordionDetails>
    </Accordion>
  );
};

export default MenuCard;
